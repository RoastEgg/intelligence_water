package com.hawksoft.platform.task;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ChinaRiverRobotProcessor implements PageProcessor {


    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");


    public void process(Page page) {
        WebDriver driver = new ChromeDriver();
        driver.get("http://xxfb.hydroinfo.gov.cn/ssIndex.html?type=2");
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement webElement = driver.findElement(By.id("hdtable"));
        String str = webElement.getAttribute("outerHTML");
//        System.out.println(str);

        //HTML JAR包分析
        Html html = new Html(str);
        List<String> list=html.xpath("//table/tbody/tr").all();
        for (int i = 0;i<list.size();i++) {
            System.out.println("list.size()"+list.size());
            Html html1 = new Html(list.get(i));
            System.out.println(html1.xpath("//td[2]/text()"));
        }
//        System.out.println("size():"+html.xpath("//table//tr").all());
//
//        String companyCode = html.xpath("//tbody/tr[1]/td/text()").get();
//
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        String dateString = html.xpath("//tbody/tr[3]/td/text()").get().split("/")[0];
//
//        String stockCode = html.xpath("//tbody/tr[2]/td/text()").get().split("/")[0];
//        String name = html.xpath("//tbody/tr[5]/td/text()").get().split("/")[0];
//        String department = html.xpath("//tbody/tr[14]/td/text()").get().split("/")[0];
//        System.out.println(companyCode);
//        System.out.println(stockCode);
//        System.out.println(name);
//        System.out.println(department);
        driver.close();

    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\MyDrivers\\chromedriver_win32\\chromedriver.exe");
        Spider.create(new ChinaRiverRobotProcessor())
                .addUrl("http://xxfb.hydroinfo.gov.cn/ssIndex.html?type=2")
                .thread(5)
                .run();
    }
}

