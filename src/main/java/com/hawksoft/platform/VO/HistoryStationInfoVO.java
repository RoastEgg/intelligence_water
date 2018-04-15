package com.hawksoft.platform.VO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryStationInfoVO {
    public static Map<Integer,List<PdfVO>> pdfMap  = new HashMap<Integer,List<PdfVO>>(){
        {
            //11号站点没有PDF
//            PdfVO pdf11 = new PdfVO("","","2018-04-13");
//            List<PdfVO> list11 = new ArrayList<PdfVO>();
//            list11.add(pdf11);
//            put(11,list11);

            PdfVO pdf12 = new PdfVO("乌东德原观实验报告","/historyRecord/12/pdf/乌东德原观实验报告.pdf","2018-04-13");
            List<PdfVO> list12 = new ArrayList<PdfVO>();
            list12.add(pdf12);
            put(12,list12);

            PdfVO pdf13 = new PdfVO("云南龙开口水电站水流表面流场测量","/historyRecord/13/pdf/云南龙开口水电站水流表面流场测量.pdf","2018-04-13");
            List<PdfVO> list13 = new ArrayList<PdfVO>();
            list13.add(pdf13);
            put(13,list13);

            PdfVO pdf14 = new PdfVO("201401四川成都都江堰龙池镇南岳测试报告","/historyRecord/14/pdf/201401四川成都都江堰龙池镇南岳测试报告.pdf","2018-04-13");
            List<PdfVO> list14 = new ArrayList<>();
            list14.add(pdf14);
            put(14,list14);

            //15号站点没有PDF
//            PdfVO pdf15 = new PdfVO("","historyRecord/12/pdf/乌东德原观实验报告.pdf","2018-04-13");
//            List<PdfVO> list15 = new ArrayList<PdfVO>();
//            list15.add(pdf15);
//            put(15,list15);

            PdfVO pdf16 = new PdfVO("太湖梅梁湾原型观测项目报告-V1.1","/historyRecord/16/pdf/太湖梅梁湾原型观测项目报告-V1.1.pdf","2018-04-13");
            List<PdfVO> list16 = new ArrayList<PdfVO>();
            list16.add(pdf16);
            put(16,list16);

            //17号站点没有PDF
//            PdfVO pdf17 = new PdfVO("","","2018-04-13");
//            List<PdfVO> list17 = new ArrayList<PdfVO>();
//            list17.add(pdf17);
//            put(17,list17);

        }

    };
    public static Map<Integer,List<String>> picMap = new HashMap<Integer,List<String>>(){
        {
            List<String> list11 = new ArrayList<String>(){
                {
                    add("/historyRecord/11/picture/DJI00361.JPG");
                    add("/historyRecord/11/picture/avgSpeed.png");
                    add("/historyRecord/11/picture/avgFlowSpeed.png");
                }
            };
            put(11,list11);

            List<String> list12 = new ArrayList<String>(){
                {
                    add("/historyRecord/12/picture/DSC_0055.JPG");
                    add("/historyRecord/12/picture/upstream.jpg");
                    add("/historyRecord/12/picture/downstream.jpg");
                }
            };
            put(12,list12);

            List<String> list13 = new ArrayList<String>(){
                {
                    add("/historyRecord/13/picture/DSC_0012.JPG");
                    add("/historyRecord/13/picture/20141011171915.jpg");
                    add("/historyRecord/13/picture/avgFlowSpeed.png");
                    add("/historyRecord/13/picture/avgFlowSpeedDistr.png");
                    add("/historyRecord/13/picture/sideFlow.png");
                }
            };
            put(13,list13);

            List<String> list14 = new ArrayList<String>(){
                {
                    add("/historyRecord/14/picture/pic1.jpg");
                }
            };
            put(14,list14);

            List<String> list15 = new ArrayList<String>(){
                {
                    add("/historyRecord/15/picture/jingHong1.png");
                    add("/historyRecord/15/picture/jingHong2.png");
                }
            };
            put(15,list15);

            List<String> list16 = new ArrayList<String>(){
                {
                    add("/historyRecord/16/picture/MLW1.jpg");
                    add("/historyRecord/16/picture/MLW2.jpg");
                }
            };
            put(16,list16);

            List<String> list17 = new ArrayList<String>(){
                {
                    add("/historyRecord/17/picture/mark.png");
                    add("/historyRecord/17/picture/threeGorges.png");
                    add("/historyRecord/17/picture/threeGorges.png3.jpg");
                    add("/historyRecord/17/picture/left.png");
                }
            };
            put(17,list17);

        }
    };

    public HistoryStationInfoVO(){

    }

}
