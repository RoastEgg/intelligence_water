package com.hawksoft.platform.constant;

/**
 * 项目常量
 */
public interface WaterConstant {

    //json的类型
    String COMMON_JSON_TYPE = "type";
    //json的数据
    String COMMON_JSON_DATA = "data";

    //socket的ip，仅仅用于测试
    String SOCKET_SERVER_IP = "localhost";
    //sockerservice的服务端口
    int SOCKET_SERVER_PORT = 4445;

    //虚拟路径
    String FILE_DUMMY_PATH = "D:/";
    //文件名后缀
    String FILE_NAME_SUFFIX = ".bmp";
    //文件路径的分隔符
    String FILE_SPLIT = "/";
    //文件名的下划线
    String FILE_NAME_UNDERLINE = "_";

    //读入的header头的大小
    int BYTE_NUM = 4;

    //读入头的类型
    String LINE_JSON_PREFIX = "json";
    String LINE_PIC_PREFIX = "pict";

    enum WQ_PARAM {
        PH, TP, DO, CT, TU, ORP, BOD, COD, OPA
        // PH, 温度，溶解氧，电导率，浊度, 氧化还原电位，生化耗氧量，化学需氧量, 透明度
    }

    public static void main(String[] args) {
        for (WQ_PARAM em : WQ_PARAM.values()) {
            System.out.println(em + "  ordinal  " + em.ordinal());
        }

        String test = "2018-02-09 20_20_30";
        System.out.println("test = " + test.substring(11));
        System.out.println("test = " + test.replace("_", ""));
    }
}
