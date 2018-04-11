package com.hawksoft.platform.socket;

import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.*;

public class SocketOperate extends Thread {

    private Socket socket;
    private Map<String, Socket> locSockMap;
    private String location;
    private RtuHeaderInfo headerInfo;
    private boolean stop;
    private byte[] inputByte;

    public SocketOperate(Socket socket, Map<String, Socket> clientMap) {
        this.stop       = false;
        this.socket     = socket;
        this.locSockMap = clientMap;
        this.headerInfo = new RtuHeaderInfo();
        this.inputByte  = new byte[SocketUtils.PIC_MSG_SIZE];
    }

    public void run() {
        DataInputStream din = null;

        try {
            din = new DataInputStream(socket.getInputStream());

            byte[] name = new byte[SocketUtils.LOCATION_NAME_MAX_LEN]; // save location name
            readData(din, name, name.length);

            int len = SocketUtils.getInteger(name, 0);
            location = new String(Arrays.copyOfRange(name, 4, len + 4), "UTF-8");
            Socket s = locSockMap.get(location);
            if (s != null) {
                s.close();
                locSockMap.remove(location);
            }

            locSockMap.put(location, socket);

            doReceive(din); // 循环命令和数据

            din.close();
        } catch (Exception ex) {
            try {
                if (null != din) {
                    din.close();
                }
                if (null != socket) {
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void makePath(String p) {
        File f = new File(p);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    private void doReceive(DataInputStream din) {

        System.out.println("#### 开始接收数据!");
        byte[] byMsgType = new byte[4]; //标识
        while (!stop) {
            try {
                readData(din, byMsgType, 4);
                if (decodeHeader(din, SocketUtils.getInteger(byMsgType, 0)) == 0) {
                    int size = headerInfo.getSize();
                    if (size > 0) {
                        switch (headerInfo.getMsgType()) {
                            case WQ:
                                byte[] content = new byte[size];
                                readData(din, content, size);
                                SocketSaveToDB.saveWQ(headerInfo,
                                        new String(Arrays.copyOfRange(content, 0, size), "UTF-8"));
                                break;

                            case FLUX:
                            case WL:
                            case FOR:
                            case OPACITY:
                                LocalDateTime now = LocalDateTime.now();
                                String filename = FilenameUtils.getBaseName(headerInfo.getFilename());

                                // dbPath 保存数据库用到此路径,是网站能访问的相对路径，不是绝对路径
                                String path = String.format("%s/%s/%s/%04d-%02d-%02d/Camera%02d",
                                        SocketUtils.getDbPicPath(), location,
                                        SocketUtils.checkTypeENName.get(headerInfo.getType()),
                                        now.getYear(), now.getMonthValue(), now.getDayOfMonth(),
                                        headerInfo.getCameraId());

                                String dbFile = String.format("%s/%s_%02d%02d%02d.%s", path,
                                        filename.substring(11).replace("_", ""),
                                        now.getHour(), now.getMinute(), now.getSecond(),
                                        FilenameUtils.getExtension(headerInfo.getFilename()));

                                System.out.println("#### path: " + path + ", dbfile = " + dbFile + " size = " + size);

                                makePath(SocketUtils.getWebBasePath() + path);
                                saveFile(din, dbFile, size);
                                SocketSaveToDB.save(headerInfo, dbFile, filename.replace("_", ":"));
                                break; // case break;
                            case UB:
                                byte[] contentUB = new byte[size];
                                readData(din, contentUB, size);

                                SocketSaveToDB.saveUB(headerInfo,
                                        new String(Arrays.copyOfRange(contentUB, 0, size)));
                                break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (e instanceof SocketException) {
                    System.out.println("#### client connect exit or socket exception.");
                    try { socket.close(); } catch (Exception e1) { e1.printStackTrace(); }

                    locSockMap.remove(location);
                    stop = true;
                }
            }
        } // End of while (true)
    }

    private int decodeHeader(DataInputStream din, int type) throws Exception {
        headerInfo.setType(type);
        System.out.println("type: "+ type);
        int hd_size = 0;
        switch (SocketUtils.IWRtuMsgType.getName(type)) {
            case WQ:
                hd_size = SocketUtils.IWRtuMsgType.WQ.getHeaderSize() - 4;
                headerInfo.setMsgType(SocketUtils.IWRtuMsgType.WQ);
                break;
            case WL:
                hd_size = SocketUtils.IWRtuMsgType.WL.getHeaderSize() - 4;
                headerInfo.setMsgType(SocketUtils.IWRtuMsgType.WL);
                break;
            case FLUX:
                hd_size = SocketUtils.IWRtuMsgType.FLUX.getHeaderSize() - 4;
                headerInfo.setMsgType(SocketUtils.IWRtuMsgType.FLUX);
                break;
            case FOR:
                hd_size = SocketUtils.IWRtuMsgType.FOR.getHeaderSize() - 4;
                headerInfo.setMsgType(SocketUtils.IWRtuMsgType.FOR);
                break;
            case OPACITY:
                hd_size = SocketUtils.IWRtuMsgType.OPACITY.getHeaderSize() - 4;
                headerInfo.setMsgType(SocketUtils.IWRtuMsgType.OPACITY);
                break;
            case UB:
                hd_size = SocketUtils.IWRtuMsgType.UB.getHeaderSize()-4;
                System.out.println("ub  head size:  "+hd_size);
                headerInfo.setMsgType(SocketUtils.IWRtuMsgType.UB);
                break;

            case NONE:
                return -1;
        }

        byte[] header = new byte[hd_size];
        readData(din, header, hd_size);

        headerInfo.setSiteNo(SocketUtils.getInteger(header, 0));

        switch (headerInfo.getMsgType()) {
            case WQ:
                headerInfo.setSiteNo(SocketUtils.getInteger(header, 0));
                headerInfo.setStime(new String(Arrays.copyOfRange(header, 4, 23), "UTF-8"));
                headerInfo.setSize(SocketUtils.getInteger(header, 24));
                break;

            case WL:
            case FLUX:
            case OPACITY:
            case FOR://此处存疑
                headerInfo.setCameraId(SocketUtils.getInteger(header, 4));
                headerInfo.setValue(SocketUtils.getInteger(header, 8));
                headerInfo.setFilename(new String(Arrays.copyOfRange(header, 12, 36), "UTF-8"));
                headerInfo.setSize(SocketUtils.getInteger(header, 36));
                break;
            case UB:
                headerInfo.setUbNo(new String(Arrays.copyOfRange(header,4,24)).trim());//最长20位的无人船编号，去除头尾空格
                headerInfo.setStime(new String(Arrays.copyOfRange(header,24,43)));//20位的日期，去掉末尾空格
                headerInfo.setSize(SocketUtils.getInteger(header,44));
                break;
        }

        return 0;
    }

    private void readData(DataInputStream din, byte[] buf, int size)  throws Exception {
        int idx = 0;
        while (size > 0) {
            int len = din.read(buf, idx, size);
            if (len > 0) {
                if (len < size) {
                    size -= len;
                    idx += len;
                } else {
                    break;
                }
            } else if (len < 0) {
                throw new SocketException();
            } else {
                System.out.println("No data read!");
                throw new SocketException();
            }
        }
    }

    private void saveFile(DataInputStream din, String dbFile, int size) throws Exception {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(SocketUtils.getWebBasePath() + dbFile));
            int len = 0;
            while (size > 0) {
                if (size < inputByte.length) {
                    len = din.read(inputByte, 0, size);
                } else {
                    len = din.read(inputByte, 0, inputByte.length);
                }

                if (len == -1) {
                    fos.close();
                    throw new SocketException();
                } else if (len > size) {
                    System.out.println("len = " + len + ", size = " + size);
                    len = size;
                }

                fos.write(inputByte, 0, len);
                fos.flush();

                size -= len;
            }

            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            if (fos != null) fos.close();
        }
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}