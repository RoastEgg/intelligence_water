package com.hawksoft.platform.socket;

import com.hawksoft.platform.constant.WaterConstant;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;


public class SocketThread extends Thread {
    
    private ServerSocket serverSocket = null;
    private Map<String, Socket> locSocketMap = new HashMap<String, Socket>();
    private Map<String, SocketOperate> clientThreadMap = new HashMap<String, SocketOperate>();  
    
    public SocketThread(ServerSocket serverScoket) throws Exception {
        if (null == serverSocket) {
            this.serverSocket = new ServerSocket(WaterConstant.SOCKET_SERVER_PORT);
            System.out.println("Server start on port " + WaterConstant.SOCKET_SERVER_PORT);
        }
    }
    
    public void run() {
        while (!this.isInterrupted()) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                if (null != socket && !socket.isClosed()) {
                    
                    SocketOperate operate = new SocketOperate(socket, locSocketMap);
                    operate.start();

//                    socket.setSoTimeout(30000);
                    socket.setKeepAlive(true);

                    clientThreadMap.put(socket.toString(), operate);
                }
            } catch (Exception e) {
                if (e instanceof SocketException) {
                    System.out.printf("服务端端口断开或者 TOMCAT shutdown");
                    break;
                }

                if (socket != null) {
                    try {
                        socket.close();
                        socket = null;
                    } catch (Exception ioe) {
                        System.out.println("关闭与客户端的链接出错！");
                    }
                }
                e.printStackTrace();
            }
        }

        for (SocketOperate op : clientThreadMap.values()) {
            op.setStop(true);
        }

        for (Socket sock : locSocketMap.values()) {
            try {
                sock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        clientThreadMap.clear();
        locSocketMap.clear();
    }
    
    public void closeSocketServer() {
        try {
            if (null != serverSocket && !serverSocket.isClosed()) {
                System.out.println("Server Socket closed!");

                for (SocketOperate op : clientThreadMap.values()) {
                    op.setStop(true);
                }
                for (Socket sock : locSocketMap.values()) {
                    sock.close();
                }

                locSocketMap.clear();
                clientThreadMap.clear();
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Socket getSocket(String location) {
        Socket so = locSocketMap.get(location);
        if (so != null && so.isConnected()) {
            return so;
        } else {
            return null;
        }
    }
}
