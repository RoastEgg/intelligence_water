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
