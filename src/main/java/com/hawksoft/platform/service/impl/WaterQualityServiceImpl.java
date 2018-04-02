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
     * @param map
     * @return
     */
    @Override
    public List<WaterQuality>  queryLastWeekWaterQuality(Map<String, Object> map){
        return WaterQualityDao.queryLastWeekWaterQuality(map);
    }

    /**
     * 查询当前站点某个参数的水质数据
     * @return 当前站点某个参数的水质信息
     */
    @Override
    public List<WaterQuality> queryLastWaterQualityByStnIdType(Map<String, Object> map){
        return WaterQualityDao.queryLastWaterQualityByStnIdType(map);
    }

    /**
     * 查询上月水质
     * @param map
     * @return
     * 因为查询语句都一样，所以直接用queryLastWeekWaterQuality(map)来进行查询
     */
    @Override
    public List<WaterQuality>  lastMonthWaterQuality(Map<String, Object> map){
        return WaterQualityDao.queryLastWeekWaterQuality(map);
    }

    /**
     * 查询上年水质
     * @param map
     * @return
     * 因为查询语句都一样，所以直接用queryLastWeekWaterQuality(map)来进行查询
     */
    @Override
    public List<WaterQuality>  lastYearWaterQuality(Map<String, Object> map){
        return WaterQualityDao.queryLastWeekWaterQuality(map);
    }


    /**
     * 保存水质信息
     * @param waterquality
     */
    @Override
    public int saveWaterQuality(WaterQuality waterquality) {
        return WaterQualityDao.saveWaterQuality(waterquality);
    }

    /**
     * 更新水质信息
     * @param waterquality
     */
    @Override
    public int updateWaterQuality(WaterQuality waterquality){
        return WaterQualityDao.updateWaterQuality(waterquality);
    }

    /**
     * 删除水质信息
     * @param waterquality
     */
    @Override
    public int deleteWaterQuality(WaterQuality waterquality){
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
     * @return 当前站点某个参数的水质信息
     */
    @Override
    public List<WaterQuality> lastWaterQualityRecordsNum(Map<String, Object> map)
    {
        return WaterQualityDao.lastWaterQualityRecordsNum(map);
    }

    /**
     * 从无人船中获取水质信息
     * @param map
     * @return 站点Id，GPS经纬度，水质参数（温度、PH、溶氧、氧化还原）
     */
    public List<Map<String ,Object>> queryWaterQualityFromUB(Map<String,Object> map){
        return WaterQualityDao.queryWaterQualityFromUB(map);
    }

    @Override
    public int queryStationInfo(int stnId) {
        return WaterQualityDao.queryStationInfo(stnId);
    }

    @Override
    public boolean generateData() {
        String temper ="";
        double minTemper = 2.401,maxTemper = 11.675 ,rangeTemper = 1.0;        //温度值
        double ph,minPh = 3.879,maxPh = 8.551 ,rangePh = 0.5;                 //PH值
        double dissolvedOxygen,minDissolvedOxygen = 4.105,maxDissolvedOxygen = 15.489,
                rangeDissolvedOxygen = 1.0;    //溶解氧值

        double redox,minRedox = 103.923,maxRedox = 412.66 ,rangeRedox = 30.0;               //氧化还原值  ***

        double conductivity,minConductivity = 0.381,maxConductivity = 0.9,rangeConductivity = 0.06;
        double turbidity,minTurbidity = 4.28,maxTurbidity = 243.035 ,rangeTurbidity = 20.0;
        double salinity,minSalinity = 0.184,maxSalinity = 0.43 , rangeSalinity = 0.025;
        double tds,minTds = 0.255,maxTds = 0.603 , rangeTds = 0.04;
        double density,minDensity = 500.046,maxDensity = 1000.319 ,rangeDesity = 50.0;
        double doSaturation,minDoSaturation = 35.129,maxDoSaturation = 138.515 , rangeDoSaturation = 10.0;
        double tss,minTss = 0.024,maxTss = 0.972 , rangeTss = 0.09;
        double chlorophylA,minChlorophylA = -0.744,maxChlorophylA = 124.168 ,rangeChlorophylA = 12.0;
        double phycocyanobilin,minPhycocyanobilin = 9.295,maxPhycocyanobilin = 113.817 ,rangePhycocyanobilin = 10.0;
        double voltage,minVoltage = 11.85,maxVoltage = 15.892 ,rangeVoltage = 0.4;

        for (int i=1;i<7;i=7){//1号站点和7号站点有水质数据

            WaterQuality lastWQ = queryLastWaterQualityByStnId(i);
            //利用已有的实现，判断数据库里有没有今天的数据
            if (DateUtil.judgeDate(DateUtil.parseString(lastWQ.getCollectionTime()))){
                temper = Double.toString(DataUtil.adjustData(
                        Double.parseDouble(lastWQ.getTemperature()),maxTemper,minTemper,rangeTemper));
                ph = DataUtil.adjustData(lastWQ.getPh(),maxPh,minPh,rangePh);
                dissolvedOxygen = DataUtil.adjustData(lastWQ.getDissolvedOxygen(),maxDissolvedOxygen,
                        minDissolvedOxygen,rangeDissolvedOxygen);
                redox = DataUtil.adjustData(lastWQ.getRedox(),maxRedox,minRedox,rangeRedox);
                conductivity = DataUtil.adjustData(lastWQ.getConductivity(),maxConductivity,minConductivity,
                        rangeConductivity);
                turbidity = DataUtil.adjustData(lastWQ.getTurbidity(),maxTurbidity,minTurbidity,rangeTurbidity);
                salinity = DataUtil.adjustData(lastWQ.getSalinity(),maxSalinity,minSalinity,rangeSalinity);
                tds = DataUtil.adjustData(lastWQ.getTds(),maxTds,minTds,rangeTds);
                density =DataUtil.adjustData(lastWQ.getDensity(),maxDensity,minDensity,rangeDesity);
                doSaturation = DataUtil.adjustData(lastWQ.getDoSaturation(),maxDoSaturation,
                        minDoSaturation,rangeDoSaturation);
                tss = DataUtil.adjustData(lastWQ.getTss(),maxTss,minTss,rangeTss);
                chlorophylA = DataUtil.adjustData(lastWQ.getChlorophylA(),maxChlorophylA,
                        minChlorophylA, rangeChlorophylA);
                phycocyanobilin = DataUtil.adjustData(lastWQ.getPhycocyanobilin(),maxPhycocyanobilin,
                        minPhycocyanobilin,rangePhycocyanobilin);
                voltage = DataUtil.adjustData(lastWQ.getVoltage(),maxVoltage,minVoltage,rangeVoltage);
            }
            else {
                temper = Double.toString(DataUtil.getRandom(maxTemper, minTemper));
                ph = DataUtil.getRandom(maxPh, minPh);
                dissolvedOxygen = DataUtil.getRandom(maxDissolvedOxygen, minDissolvedOxygen);
                redox = DataUtil.getRandom(maxRedox, minRedox);
                conductivity = DataUtil.getRandom(maxConductivity,minConductivity);
                turbidity = DataUtil.getRandom(maxTurbidity,minTurbidity);
                salinity = DataUtil.getRandom(maxSalinity,minSalinity);
                tds = DataUtil.getRandom(maxTds,minTds);
                density =DataUtil.getRandom(maxDensity,minDensity);
                doSaturation = DataUtil.getRandom(maxDoSaturation,minDoSaturation);
                tss = DataUtil.getRandom(maxTss,minTss);
                chlorophylA = DataUtil.getRandom(maxChlorophylA,minChlorophylA);
                phycocyanobilin = DataUtil.getRandom(maxPhycocyanobilin,minPhycocyanobilin);
                voltage = DataUtil.getRandom(maxVoltage,minVoltage);
            }

            String wqTime = DateUtil.parseDate(new Date());//获取当前时间
                WaterQuality waterQuality =
                        new WaterQuality(i,temper+"℃",ph,dissolvedOxygen,redox,0.0,conductivity,
                                turbidity,0.0,wqTime,wqTime,"1","v","",salinity,tds,
                                density,doSaturation,tss,chlorophylA,phycocyanobilin,0.0,0.0,voltage);
                saveWaterQuality(waterQuality);

        }

        return true;
    }

}
