package com.hawksoft.platform.socket;

import com.hawksoft.platform.constant.WaterConstant;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketClient1 {

    public void send(String filePath) throws IOException {
        Socket socket = null;
        DataOutputStream outputStream = null;
        try {
            socket = new Socket(WaterConstant.SOCKET_SERVER_IP, WaterConstant.SOCKET_SERVER_PORT);
            outputStream = new DataOutputStream(socket.getOutputStream());

            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            int len = fileInputStream.available();
            byte[] b= new byte[len];

            fileInputStream.read(b, 0 , b.length);

            byte b1[] = new byte[len + 4];

            byte b2[] = WaterConstant.LINE_PIC_PREFIX.getBytes();
            for (int i = 0; i < b2.length; i++){
                b1[i] = b2[i];
            }

            for (int i = 0; i < b.length; i++){
                b1[i + 4] = b[i];
            }
            outputStream.write(b1);
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
                socket.close();
            }

        }
    }
}
