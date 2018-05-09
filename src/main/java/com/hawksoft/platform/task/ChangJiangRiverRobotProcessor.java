package com.hawksoft.platform.task;

import com.hawksoft.platform.dao.FlowDao;
import com.hawksoft.platform.dao.WaterDao;
import com.hawksoft.platform.dao.WaterStationDao;
import com.hawksoft.platform.entity.Flow;
import com.hawksoft.platform.entity.Water;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Component
public class ChangJiangRiverRobotProcessor implements PageProcessor {
    private ApplicationContext context;
    @Autowired
    private WaterStationDao  waterStationDao;
    @Autowired
    private WaterDao waterDao;
    @Autowired
    private FlowDao flowDao;
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");


    @Override
    public void process(Page page) {
        List<String> list = page.getHtml().xpath("table/tbody/tr/td/table/tbody/tr").all();

        for (int i = 0; i < list.size(); i++) {
            Html h = new Html(list.get(i).replace("td", "div"));
            String name = h.xpath("//div[1]/text()").get();
            if(name.equals(null)||name.equals("")){
                continue;
            }
            //处理时间
            String time = h.xpath("//div[2]/text()").get();

            //获取日与小时
            int dateIndex=time.indexOf("日");
            int date=Integer.parseInt(time.substring(0,dateIndex).trim());
            int hourIndex=time.indexOf("时");
            int hour=Integer.parseInt(time.substring(dateIndex+2,hourIndex).trim());

            Calendar calendar=Calendar.getInstance();
            //获取当前年份
            int year=calendar.get(Calendar.YEAR);
            //获取当前月份
            int month=calendar.get(Calendar.MONTH)+1;
            SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String waterTime=year+"-"+month+"-"+date+" "+hour+":00:00";

            String strWaterLevel = h.xpath("//div[3]/text()").get();
            if(strWaterLevel.trim().equals("-")){
                strWaterLevel="";
            }
            //将String类型转化成BigDecimal
            BigDecimal waterLevel=new BigDecimal(strWaterLevel);

            String strflow = h.xpath("//div[4]/text()").get();
            if(strflow.trim().equals("-")){
                strflow="";
            }
            Double avgflow=null;
            if(!strflow.equals("")&&!strflow.equals(null)){
                avgflow=Double.parseDouble(strflow);
            }

            //通过站点名称获取站点ID
            int stnID=waterStationDao.queryIdByName(name);
           //将数据插入水位表
            Water water=new Water();
            water.setStnId(stnID);
            water.setWaterLevel(waterLevel);
            water.setC_time(waterTime);
            waterDao.saveWater(water);

            //将数据插入流量表
            Flow flow=new Flow();
            flow.setStnId(stnID);
            flow.setAvgFlow(avgflow);
            flow.setCollectionTime(waterTime);
            flowDao.saveFlow(flow);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }



}

