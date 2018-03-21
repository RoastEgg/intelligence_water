package com.hawksoft.platform.socket;

import com.hawksoft.platform.constant.WaterConstant;
import com.hawksoft.platform.entity.*;
import com.hawksoft.platform.service.*;
import com.hawksoft.platform.util.ImgHandler;
import com.hawksoft.platform.util.JsonUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class SocketService {

    private StringBuffer filePath = new StringBuffer("E:\\test.jpg");
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private ApplicationContext context = null;
    private class HandlerThread implements Runnable{
        private Socket socket;


        /**
         * 对线程进行启动
         * @param client 接受的socket客户端
         */
        public HandlerThread(Socket client){
            this.socket = client;
            new Thread(this).start();
        }

        @Override
        public synchronized void run() {

            InputStream inputStream = null;
            DataInputStream din = null;
            try {

                inputStream = socket.getInputStream();
                din = new DataInputStream(inputStream);
                Thread.sleep(5000);
                int sumFileLength = din.available();
                System.out.println(sumFileLength);
                //byte[] header = new byte[WaterConstant.BYTE_NUM];
                byte [] header = new byte[sumFileLength];
                System.out.println(header.length+"开始接收数据..."+sumFileLength);
                int len = din.read(header, 0, header.length);
                System.out.println(new String(header));
                //String prefix = new String(header, "UTF-8");
                String prefix = "ok";

                //处理数据，如果是json数据运行下面
               if (WaterConstant.LINE_JSON_PREFIX.equals(prefix)){
                   byte[] json_byte = new byte[sumFileLength - header.length];
                   din.read(json_byte, 0, json_byte.length);
                   String json_ = new String(json_byte, "UTF-8");
                   Object o = JsonUtil.parseJson(json_);
                   save2DB(o);
               }else{

                   doReceive(din);
               }
               if(WaterConstant.LINE_PIC_PREFIX.equals(prefix)){
                   doReceive(din);
               }

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                   if (inputStream != null){
                       inputStream.close();
                   }
                   if (socket != null){
                       socket.close();
                   }
               }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            }
        }


    /**
     * 初始化方法
     */
    public void init(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(WaterConstant.SOCKET_SERVER_PORT);
            while (true){
                Socket client = serverSocket.accept();
                new HandlerThread(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 保存元数据到数据库
     * @param o 实体对象，或者是水位，或者是流量，或者是水质
     */
    public void save2DB(Object o){
        if(context == null){
            context = getContext();
        }
        if (o instanceof Water){

            Water water = (Water) o;
            filePath.append(water.getPicPath()) ;
            WaterService waterService = context.getBean(WaterService.class);
            waterService.saveWater(water);

        }else if(o instanceof SpeedFlow){

            SpeedFlow speedFlow = (SpeedFlow)o;
            String instockTime = sdf.format(new Date());
            speedFlow.setInstockTime(instockTime);
            SpeedFlowService speedFlowService = context.getBean(SpeedFlowService.class);
            speedFlowService.saveSpeedFlow(speedFlow);

        }else if(o instanceof WaterQuality){
            WaterQuality waterQuality = (WaterQuality) o;
            WaterQualityService waterQualityService = context.getBean(WaterQualityService.class);
            waterQualityService.saveWaterQuality(waterQuality);
        }else if(o instanceof FloatingMatter){
            FloatingMatter floatingMatter = (FloatingMatter)o;
            String filePathOrigin= ImgHandler.imgFilePathAndName("floatOrigin",floatingMatter.getStnId().toString());
            //ImgHandler.savePic(floatingMatter.getPicOrigin(),filePathOrigin);
            floatingMatter.setFilePathOrigin(filePathOrigin);

            String filePathResult= ImgHandler.imgFilePathAndName("floatResult",floatingMatter.getStnId().toString());
          //  ImgHandler.savePic(floatingMatter.getPicResult(),filePathResult);
            floatingMatter.setFilePathOrigin(filePathResult);
            FloatingMatterService floatingMatterService = context.getBean(FloatingMatterService.class);
            floatingMatterService.saveFloatingMatter(floatingMatter);
        }
    }

    /**
     * 对图片数据进行处理
     * @param din 数据的输入流
     */
    public void doReceive(DataInputStream din){
        try {
            int sumFileLength = din.available();
            byte[] pic_byte = new byte[sumFileLength - WaterConstant.BYTE_NUM];
            din.read(pic_byte,0, pic_byte.length);
            ImgHandler.savePic(pic_byte,filePath.toString());

            int length = filePath.length();
            filePath.delete(0, length);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取spring的容器
     * @return spring的容器
     */
    public ApplicationContext getContext(){
        if (context == null){
            return new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        }
        return context;
    }
}

