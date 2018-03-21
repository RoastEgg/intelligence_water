package com.hawksoft.platform.util;


/**
 * Created by Administrator on 2017/9/18.
 */
public class DetectAndTrack {


    public native String detectObject_Img(String sourcePic);

    public static void main(String[] arg) throws Exception
    {
        System.loadLibrary("com_hawksoft_platform_util_DetectAndTrack");
        DetectAndTrack detectAndTrack = new DetectAndTrack();
        String pic =detectAndTrack.detectObject_Img("");

         System.out.println("do pic is :"+pic);
    }
}
