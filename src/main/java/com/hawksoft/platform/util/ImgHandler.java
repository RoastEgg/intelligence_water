package com.hawksoft.platform.util;

import com.hawksoft.platform.constant.WaterConstant;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class ImgHandler {
    /**
     * TODO:将byte数组以Base64方式编码为字符串
     *
     * @param bytes 待编码的byte数组
     * @return 编码后的字符串
     */
    public static String encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * TODO:将以Base64方式编码的字符串解码为byte数组
     *
     * @param encodeStr 待解码的字符串
     * @return 解码后的byte数组
     * @throws IOException
     */
    public static byte[] decode(String encodeStr) throws IOException {
        byte[] bt = null;
        BASE64Decoder decoder = new BASE64Decoder();
        bt = decoder.decodeBuffer(encodeStr);
        return bt;
    }

    /**
     * TODO:将两个byte数组连接起来后，返回连接后的Byte数组
     *
     * @param front 拼接后在前面的数组
     * @param after 拼接后在后面的数组
     * @return 拼接后的数组
     */
    public static byte[] connectBytes(byte[] front, byte[] after) {
        byte[] result = new byte[front.length + after.length];
        System.arraycopy(front, 0, result, 0, after.length);
        System.arraycopy(after, 0, result, front.length, after.length);
        return result;
    }

    /**
     * TODO:将图片以Base64方式编码为字符串
     *
     * @param imgUrl 图片的绝对路径（例如：D:\\jsontest\\abc.jpg）
     * @return 编码后的字符串
     * @throws IOException
     */
    public static String encodeImage(String imgUrl) throws IOException {
        FileInputStream fis = new FileInputStream(imgUrl);
        byte[] rs = new byte[fis.available()];
        fis.read(rs);
        fis.close();
        return encode(rs);
    }

    /**
     * 保存图片
     *
     * @param picContent 文件内容
     * @return 返回图片保存的地址
     */
    public static void savePic(byte[] picContent, String filePath) {
        FileOutputStream outputStream = null;
        try {

            filePath = imgFilePathAndName("floating",1+"");
            System.out.println(new String(picContent));
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            outputStream = new FileOutputStream(file);
            outputStream.write(picContent);
            outputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 创建图片路径
     *
     * @param stnId 站点id
     * @return 图片的路径
     */
    public static String imgFilePathAndName(String type,String stnId) {
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        LocalTime time = LocalTime.now();
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        String fileName = WaterConstant.FILE_DUMMY_PATH + type +
                WaterConstant.FILE_SPLIT + stnId +
                WaterConstant.FILE_SPLIT + year +
                WaterConstant.FILE_SPLIT + month +
                WaterConstant.FILE_SPLIT + day +
                WaterConstant.FILE_SPLIT + year + WaterConstant.FILE_NAME_UNDERLINE +
                month + WaterConstant.FILE_NAME_UNDERLINE + day + WaterConstant.FILE_NAME_UNDERLINE
                + hour + WaterConstant.FILE_NAME_UNDERLINE + minute + WaterConstant.FILE_NAME_UNDERLINE +
                second + WaterConstant.FILE_NAME_SUFFIX;

        return fileName;
    }

}
