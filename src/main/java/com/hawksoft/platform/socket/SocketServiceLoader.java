package com.hawksoft.platform.socket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SocketServiceLoader implements ServletContextListener {
    
    private static SocketThread socketThread; 

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        if (null != socketThread && !socketThread.isInterrupted()) {
            socketThread.closeSocketServer();
            socketThread.interrupt();
        }
    }

    public void contextInitialized(ServletContextEvent arg0) {
        if (null == socketThread) {
            try {
				socketThread = new SocketThread(null);
                socketThread.start();
                //在这里新建一个线程，在每天固定的时间节点生成数据并写入数据库中
			} catch (Exception e) {
	            System.out.println("SocketThread创建socket服务出错");
	            e.printStackTrace();
			}
        }
    }
    
    public static SocketThread getSocketThread() {
    	return socketThread;
    }
}
