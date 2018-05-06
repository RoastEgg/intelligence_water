package com.hawksoft.platform.service.impl;

import com.hawksoft.platform.VO.Water_PictureVO;
import com.hawksoft.platform.VO.Water_queryLastAndHisVO;
import com.hawksoft.platform.dao.WaterDao;
import com.hawksoft.platform.dao.WaterStationDao;
import com.hawksoft.platform.entity.CameraArgs;
import com.hawksoft.platform.entity.Water;
import com.hawksoft.platform.entity.WaterEarlyWarn;
import com.hawksoft.platform.entity.WaterStation;
import com.hawksoft.platform.service.WaterService;
import com.hawksoft.platform.service.WaterStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WaterServiceImpl implements WaterService {

    @Autowired
    private WaterDao waterDao;
    @Autowired
    private WaterStationDao waterStationDao;

    @Override
    public List<Water> findLastWater() {
        return waterDao.findLastWater();
    }

    @Override
    public List<Water> hisWater(Map<String, Object> params) {
        return waterDao.hisWater(params);
    }

    @Override
    public Water hisWaterLevelPic(Map<String, Object> params) {
        return waterDao.hisWaterLevelPic(params);
    }

    @Override
    public List<WaterStation> queryAllWaterStation(Map<String, Object> params) {
        return waterDao.queryAllWaterStation(params);
    }

    @Override
    public List<String> queryCityData() {
        return waterDao.queryCityData();
    }

    @Override
    public Water queryLastWaterByStnId(int stnId) {
        return waterDao.queryLastWaterByStnId(stnId);
    }

    @Override
    public void saveWater(Water water) {
        waterDao.saveWater(water);
    }

    @Override
    public List<String> queryPicPathByStnId(int stnId) {
        return waterDao.queryPicPathByStnId(stnId);
    }

    @Override
    public int saveCameraArgs(CameraArgs args) {
        return waterDao.saveCameraArgs(args);
    }

    @Override
    public CameraArgs findCameraArgsById(int id) {
        return waterDao.findCameraArgsById(id);
    }

    @Override
    public List<CameraArgs> findCameraArgsByStnId(int stnId) {
        return waterDao.findCameraArgsByStnId(stnId);
    }

    @Override
    public CameraArgs findCameraArgsNoDefaultById(int id) {
        return waterDao.findCameraArgsNoDefaultById(id);
    }

    @Override
    public CameraArgs findCameraArgsNoDefault(int stnId) {
        return waterDao.findCameraArgsNoDefault(stnId);
    }

    @Override
    public int deleteCameraArgsNoDefaultById(int id) {
        return waterDao.deleteCameraArgsNoDefaultById(id);
    }

    @Override
    public int updateWaterWarn(WaterEarlyWarn waterEarlyWarn) {
        return waterDao.updateWaterWarn(waterEarlyWarn);
    }

    @Override
    public WaterEarlyWarn getWaterWarn(int stnId, int type) {
        return waterDao.getWaterWarn(stnId, type);
    }

    /**
     * 取最新的DAYS条实时水位
     * @param map 查询参数
     * @return 返回历史水位的数据
     */
    @Override
    public List<Water> lastWaterRecordsNum(Map<String, Object> map)

    {
        return waterDao.lastWaterRecordsNum(map);
    }

    @Override
    public int queryStationInfo(int stnId) {
        return waterDao.queryStationInfo(stnId);
    }

    @Override
    public Water_queryLastAndHisVO queryLastAndHis(Map<String, Object> map) {
        Water_queryLastAndHisVO w_qVO = new Water_queryLastAndHisVO();
        Water_PictureVO lastPic = new Water_PictureVO();
        List<Water_PictureVO> picList = new ArrayList<>();

        int stnId = (Integer) map.get("stnId");
        double maxLevel = -1 ,minLevel = 9999;

        Water water = queryLastWaterByStnId(stnId);//当前水位信息
        lastPic.setUrl(water.getPicPath());
        lastPic.setDate(water.getC_time());
        picList.add(lastPic);//当前图片URL和日期

        //System.out.println("last info:"+lastPic.getUrl()+"  "+lastPic.getDate());


        List<Water> waterList = hisWater(map);//历史水位信息
        if (waterList.size()>0){
            maxLevel = waterList.get(0).getWaterLevel().doubleValue();
            minLevel = waterList.get(0).getWaterLevel().doubleValue();
        }
        int count =0;
        for (Water w:waterList){
            double temp  = w.getWaterLevel().doubleValue();
            if (temp>maxLevel) {maxLevel = temp;}
            if (temp<minLevel) {minLevel = temp;}
            Water_PictureVO hisPic =  new Water_PictureVO();
            if (count<3){
                count++;
                hisPic.setUrl(w.getPicPath());
                hisPic.setDate(w.getC_time());
                picList.add(hisPic);//历史图片，包括url和date，最多放3条历史图片
            }
        }
        if (count<3){
            for (int i=1;i<=3-count;i++){
                picList.add(lastPic);//如果历史记录不够3条，则用最新记录补全到3条
            }
        }
        w_qVO.setPicList(picList);

        List<WaterStation> waterStationList = waterStationDao.queryStationBaseInfo();
        for (WaterStation ws:waterStationList){
            if (ws.getId() == stnId){
                w_qVO.setStnName(ws.getStnName());//站点名称
            }
        }
        w_qVO.setLastWaterLevel(water.getWaterLevel().doubleValue());//当前水位
        w_qVO.setMaxLevel(maxLevel);//历史水位最大值
        w_qVO.setMaxLevel(minLevel);//历史水位最小值

        return w_qVO;
    }

    /**
     * 修改水位信息
     * @param water
     * @return
     */
    @Override
    public int updateWater(Water water) {
        return waterDao.updateWater(water);
    }

    /**
     * 删除水位信息
     * @param water
     * @return
     */
    @Override
    public int deleteWater(Water water) {
        return waterDao.deleteWater(water);
    }

}
