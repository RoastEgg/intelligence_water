package com.hawksoft.platform.socket;

import com.alibaba.fastjson.JSON;
import com.hawksoft.platform.constant.WaterConstant;
import com.hawksoft.platform.entity.Water;
import com.hawksoft.platform.util.ImgHandler;

import javax.json.Json;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.Socket;

public class SocketClient {

    public void send(String json){
        Socket socket = null;
        DataOutputStream outputStream = null;
        try {
            socket = new Socket(WaterConstant.SOCKET_SERVER_IP, WaterConstant.SOCKET_SERVER_PORT);
            outputStream = new DataOutputStream(socket.getOutputStream());

            byte[] b = json.getBytes();
            outputStream.write(b);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
