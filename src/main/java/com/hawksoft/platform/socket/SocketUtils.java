package com.hawksoft.platform.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SocketUtils {
    public static final int LOCATION_NAME_MAX_LEN = 40;
    public static final int PIC_MSG_SIZE = 1024;

    public static final Map<Integer, String> siteENName;
    static Logger logger= LoggerFactory.getLogger(SocketUtils.class);
    static
    {
        siteENName = new HashMap<Integer, String>();
        siteENName.put(1, "QianHan");
        siteENName.put(2, "XZM");
        siteENName.put(3, "BZFQ");
        siteENName.put(4, "XJY");
        siteENName.put(5, "TEST");
    }

    public static final Map<Integer, String> checkTypeENName;
    static
    {
        checkTypeENName = new HashMap<Integer, String>();
        checkTypeENName.put(1, "WaterL"); // 水位
        checkTypeENName.put(2, "Flux");   // 流量
        checkTypeENName.put(3, "WaterQ"); // 水质
        checkTypeENName.put(4, "WaterOp");// 透明度
        checkTypeENName.put(5,"FOR");// 漂浮物
        checkTypeENName.put(10,"UB");// 无人船

    }

    public enum IWRtuMsgType {
        WL("水位", 1, 44),
        FLUX("流速", 2, 44),
        WQ("水质", 3, 32),
        OPACITY("透明度", 4, 44),
        FOR("漂浮物",5,44),
        UB("无人船",10,52),
        //UB(unmanned boat) header包括消息类型（4位），站点编号（4位），无人船编号（20位），时间（20位，如："2018-01-01 09:58:21"）
        //共48位
        NONE("none", 0, 0);

        private String name;
        private int index;
        private int header_size;

        private IWRtuMsgType(String name, int index, int header_size) {
            this.name = name;
            this.index = index;
            this.header_size = header_size;
        }

        public static IWRtuMsgType getName(int index) {
            for (IWRtuMsgType c : IWRtuMsgType.values()) {
                if (c.getIndex() == index) {
                    return c;
                }
            }

            return NONE;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getIndex() {
            return index;
        }
        public void setIndex(int index) {
            this.index = index;
        }
        public int getHeaderSize() {
            return header_size;
        }
    }
    
    public static int getInteger(byte[] bytes, int startIdx) {
        return bytes[startIdx + 3] & 0xFF | 
                (bytes[startIdx + 2] & 0xFF) << 8 | 
                (bytes[startIdx + 1] & 0xFF) << 16 | 
                (bytes[startIdx] & 0xFF) << 24;
        
    }
    
    public static byte[] getBytes(int v) {
        byte[] targets = new byte[4];
        
        targets[0] = (byte) (v & 0xff);
        targets[1] = (byte) ((v >> 8) & 0xff);
        targets[2] = (byte) ((v >> 16) & 0xff);
        targets[3] = (byte) (v >>> 24);
        return targets;
    }
    
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static String getWebBasePath() {
        if (isWindows()) {
            return "D:\\SW\\tomcat-904\\webapps";
        } else if (isMac()) {
            return "";
        } else if (isUnix()) {
            return "/opt/apache-tomcat-8.5.24/webapps";
        } else {
            return "/opt/apache-tomcat-8.5.24/webapps";
        }
    }

    public static String getPicturesPath() {
        if (isWindows()) {
            return "D:\\SW\\tomcat-904\\webapps\\pictures";
        } else if (isMac()) {
            return "";
        } else if (isUnix()) {
            return "/opt/apache-tomcat-8.5.24/webapps/pictures";
        } else {
            return "/opt/apache-tomcat-8.5.24/webapps/pictures";
        }
    }
    
    public static String getDbPicPath() {
    	if (isWindows()){
    		return "/pictures";
    	}
    	else if(isUnix()) {
       	    return "/pictures";
       	}else
       	{
       		return "/pictures";
       	}
    }
    
    private static boolean isWindows() {
        return (OS.indexOf("win") >= 0 || OS.indexOf("Win") >= 0);
    }

    private static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    private static boolean isUnix() {
        return (OS.indexOf("nux") >= 0);
    }
    
    
    public static Socket findSocket(String location) {
        SocketThread soThread = SocketServiceLoader.getSocketThread();
        return soThread.getSocket(location);
	}   
    
    public static void sendMessage(Socket so, String message) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                                     so.getOutputStream()), true);
        writer.println(message);
    }
//    public static void sendMessage(Socket so, byte[] message) throws IOException {
//        PrintWriter writer = new PrintWriter(new OutputStreamWriter(
//                so.getOutputStream()), true);
//        writer.println(message);
//    }
    
    public static String recvMessage(Socket so) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                                        so.getInputStream()));

        return reader.readLine();
    }
}
