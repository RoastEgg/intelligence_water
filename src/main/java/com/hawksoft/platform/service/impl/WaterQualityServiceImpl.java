package com.hawksoft.platform.service.impl;

import com.hawksoft.platform.dao.WaterQualityDao;
import com.hawksoft.platform.entity.WaterQuality;
import com.hawksoft.platform.entity.WaterEarlyWarn;
import com.hawksoft.platform.entity.WaterStation;
import com.hawksoft.platform.service.WaterQualityService;
import com.hawksoft.platform.util.DataUtil;
import com.hawksoft.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WaterQualityServiceImpl implements WaterQualityService {

    @Autowired
    private WaterQualityDao WaterQualityDao;

    @Override
    public List<WaterQuality> findLastWaterQuality(int stnId) {
        return WaterQualityDao.findLastWaterQuality(stnId);
    }

    @Override
    public List<WaterQuality> hisWaterQuality(Map<String, Object> params) {
        return WaterQualityDao.hisWaterQuality(params);
    }

    @Override
    public List<WaterStation> queryAllWaterStation() {
        return WaterQualityDao.queryAllWaterStation();
    }

    @Override
    public List<String> queryCityData() {
        return WaterQualityDao.queryCityData();
    }

    @Override
    public WaterQuality queryLastWaterQualityByStnId(int stnId) {
        return WaterQualityDao.queryLastWaterQualityByStnId(stnId);
    }

    /**
     * 查询上周水质
     *
     * @param map
     * @return
     */
    @Override
    public List<WaterQuality> queryLastWeekWaterQuality(Map<String, Object> map) {
        return WaterQualityDao.queryLastWeekWaterQuality(map);
    }

    /**
     * 查询当前站点某个参数的水质数据
     *
     * @return 当前站点某个参数的水质信息
     */
    @Override
    public List<WaterQuality> queryLastWaterQualityByStnIdType(Map<String, Object> map) {
        return WaterQualityDao.queryLastWaterQualityByStnIdType(map);
    }

    /**
     * 查询上月水质
     *
     * @param map
     * @return 因为查询语句都一样，所以直接用queryLastWeekWaterQuality(map)来进行查询
     */
    @Override
    public List<WaterQuality> lastMonthWaterQuality(Map<String, Object> map) {
        return WaterQualityDao.queryLastWeekWaterQuality(map);
    }

    /**
     * 查询上年水质
     *
     * @param map
     * @return 因为查询语句都一样，所以直接用queryLastWeekWaterQuality(map)来进行查询
     */
    @Override
    public List<WaterQuality> lastYearWaterQuality(Map<String, Object> map) {
        return WaterQualityDao.queryLastWeekWaterQuality(map);
    }


    /**
     * 保存水质信息
     *
     * @param waterquality
     */
    @Override
    public int saveWaterQuality(WaterQuality waterquality) {
        return WaterQualityDao.saveWaterQuality(waterquality);
    }

    /**
     * 更新水质信息
     *
     * @param waterquality
     */
    @Override
    public int updateWaterQuality(WaterQuality waterquality) {
        return WaterQualityDao.updateWaterQuality(waterquality);
    }

    /**
     * 删除水质信息
     *
     * @param waterquality
     */
    @Override
    public int deleteWaterQuality(WaterQuality waterquality) {
        return WaterQualityDao.deleteWaterQuality(waterquality);
    }

    @Override
    public int updateWaterQualityWarn(WaterEarlyWarn waterEarlyWarn) {
        return WaterQualityDao.updateWaterQualityWarn(waterEarlyWarn);
    }

    @Override
    public WaterEarlyWarn getWaterQualityWarn(int stnId, int type) {
        return WaterQualityDao.getWaterQualityWarn(stnId, type);
    }

    /**
     * 取最新的DAYS条实时水质
     *
     * @return 当前站点某个参数的水质信息
     */
    @Override
    public List<WaterQuality> lastWaterQualityRecordsNum(Map<String, Object> map) {
        return WaterQualityDao.lastWaterQualityRecordsNum(map);
    }

    /**
     * 从无人船中获取水质信息
     *
     * @param map
     * @return 站点Id，GPS经纬度，水质参数（温度、PH、溶氧、氧化还原）
     */
    public Map<String, Object> queryWaterQualityFromUB(Map<String, Object> map) {
        return WaterQualityDao.queryWaterQualityFromUB(map);
    }

    @Override
    public int queryStationInfo(int stnId) {
        return WaterQualityDao.queryStationInfo(stnId);
    }

    @Override
    public int generateData(int stnId, Date date) {

        String temper = "";
        double minTemper = 3.00, maxTemper = 8.00;        //温度值
        double ph=0.0, minPh = 6.00, maxPh = 6.70;                 //PH值
        double dissolvedOxygen=0.0, minDissolvedOxygen = 2.00, maxDissolvedOxygen = 3.00;//溶解氧值
        double redox=0.0, minRedox = 100.0, maxRedox = 300.0;//氧化还原值
        double transparency=0.0, minTransparency = 10.0, maxTransparency = 100.0;
        double conductivity = 0.0, minConductivity = 0.381, maxConductivity = 0.900;
        double turbidity = 0.0, minTurbidity = 4.28, maxTurbidity = 243.035;
        double salinity = 0.0, minSalinity = 0.184, maxSalinity = 0.43;
        double tds = 0.0, minTds = 0.255, maxTds = 0.603;
        double density = 0.0, minDensity = 500.046, maxDensity = 1000.319;
        double doSaturation = 0.0, minDoSaturation = 35.129, maxDoSaturation = 138.515;
        double tss = 0.0, minTss = 0.024, maxTss = 0.972;
        double chlorophylA = 0.0, minChlorophylA = -0.744, maxChlorophylA = 124.168;
        double phycocyanobilin = 0.0, minPhycocyanobilin = 9.295, maxPhycocyanobilin = 113.817;
        double voltage = 0.0, minVoltage = 11.850, maxVoltage = 15.892;
        WaterQuality lastWQ = queryLastWaterQualityByStnId(stnId);

        String wqTime = DateUtil.parseDate(date);
        //System.out.println("date: "+date+" time:  "+wqTime);
        WaterQuality waterQuality = new WaterQuality();
        //数据库里如果已有数据，就在此基础上随机，否则就新随机
        if (lastWQ != null) {
            switch (stnId){
                case 1:
                    temper = Double.toString(DataUtil.adjustData(
                            Double.parseDouble(lastWQ.getTemperature()), maxTemper, minTemper));
                    ph = DataUtil.adjustData(lastWQ.getPh(), maxPh, minPh);
                    dissolvedOxygen = DataUtil.adjustData(lastWQ.getDissolvedOxygen(), maxDissolvedOxygen,
                            minDissolvedOxygen);
                    redox = DataUtil.adjustData(lastWQ.getRedox(), maxRedox, minRedox);
                    transparency = DataUtil.adjustData(lastWQ.getTransparency(), maxTransparency, minTransparency);
                    break;
                case 6:
                    temper = Double.toString(DataUtil.adjustData(
                            Double.parseDouble(lastWQ.getTemperature()), maxTemper, minTemper));
                    ph = DataUtil.adjustData(lastWQ.getPh(), maxPh, minPh);
                    dissolvedOxygen = DataUtil.adjustData(lastWQ.getDissolvedOxygen(), maxDissolvedOxygen,
                            minDissolvedOxygen);
                    redox = DataUtil.adjustData(lastWQ.getRedox(), maxRedox, minRedox);
                    turbidity = DataUtil.getRandom(maxTurbidity, minTurbidity);
                    doSaturation = DataUtil.adjustData(lastWQ.getDoSaturation(), maxDoSaturation,
                            minDoSaturation);
                    break;
                case 7:
                    temper = Double.toString(DataUtil.getRandom(maxTemper, minTemper));
                    ph = DataUtil.getRandom(maxPh, minPh);
                    dissolvedOxygen = DataUtil.getRandom(maxDissolvedOxygen, minDissolvedOxygen);
                    redox = DataUtil.getRandom(maxRedox, minRedox);
                    transparency = DataUtil.getRandom(maxTransparency, minTransparency);
                    conductivity = DataUtil.getRandom(maxConductivity, minConductivity);
                    turbidity = DataUtil.getRandom(maxTurbidity, minTurbidity);
                    salinity = DataUtil.getRandom(maxSalinity, minSalinity);
                    tds = DataUtil.getRandom(maxTds, minTds);
                    density = DataUtil.getRandom(maxDensity, minDensity);
                    doSaturation = DataUtil.getRandom(maxDoSaturation, minDoSaturation);
                    tss = DataUtil.getRandom(maxTss, minTss);
                    chlorophylA = DataUtil.getRandom(maxChlorophylA, minChlorophylA);
                    phycocyanobilin = DataUtil.getRandom(maxPhycocyanobilin, minPhycocyanobilin);
                    voltage = DataUtil.getRandom(maxVoltage, minVoltage);
                    break;
            }
        } else {
            temper = Double.toString(DataUtil.getRandom(maxTemper, minTemper));
            ph = DataUtil.getRandom(maxPh, minPh);
            dissolvedOxygen = DataUtil.getRandom(maxDissolvedOxygen, minDissolvedOxygen);
            redox = DataUtil.getRandom(maxRedox, minRedox);
            transparency = DataUtil.getRandom(maxTransparency, minTransparency);

            //System.out.println("first:  "+transparency);

            conductivity = DataUtil.getRandom(maxConductivity, minConductivity);
            turbidity = DataUtil.getRandom(maxTurbidity, minTurbidity);
            salinity = DataUtil.getRandom(maxSalinity, minSalinity);
            tds = DataUtil.getRandom(maxTds, minTds);
            density = DataUtil.getRandom(maxDensity, minDensity);
            doSaturation = DataUtil.getRandom(maxDoSaturation, minDoSaturation);
            tss = DataUtil.getRandom(maxTss, minTss);
            chlorophylA = DataUtil.getRandom(maxChlorophylA, minChlorophylA);
            phycocyanobilin = DataUtil.getRandom(maxPhycocyanobilin, minPhycocyanobilin);
            voltage = DataUtil.getRandom(maxVoltage, minVoltage);
        }


        //Calendar cal =Calendar.getInstance();
        //cal.setTime(date);
        //int temp = cal.get(Calendar.MONTH) - 2;//上面设置的min和max是2月份的数据，水温变化幅度比较大，
        // 所以每隔1一个月就加10摄氏度
        //temper =Double.toString( Double.parseDouble(temper)+temp*10);

        switch (stnId){
            case 1://1号站点只填充温度、ph、溶氧、氧化还原、时间这几个参数
                waterQuality =
                        new WaterQuality(stnId, temper, ph, dissolvedOxygen, redox, transparency, null,
                                null, null, wqTime, wqTime, null, null, null, null, null,
                                null, null, null, null, null, null, null, null);
                break;

            case 6://6号站点只填充温度、ph、溶氧、氧化还原、浊度，cod、时间这几个参数
                waterQuality =
                        new WaterQuality(stnId, temper, ph, dissolvedOxygen, redox, null, null,
                                turbidity, null, wqTime, wqTime, null, null, null, null, null,
                                null, doSaturation, null, null, null, null, null, null);
                break;

            case 7:
                waterQuality =
                        new WaterQuality(stnId, temper, ph, dissolvedOxygen, redox, transparency, conductivity,
                                turbidity, 0.0, wqTime, wqTime, "1", "v", "", salinity, tds,
                                density, doSaturation, tss, chlorophylA, phycocyanobilin, 0.0, 0.0, voltage);
                break;

        }

        int ans = saveWaterQuality(waterQuality);
        return ans;
    }

}
