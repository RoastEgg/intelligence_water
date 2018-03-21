package com.hawksoft.platform.dao;

import com.hawksoft.platform.entity.CameraArgs;
import com.hawksoft.platform.entity.Water;
import com.hawksoft.platform.entity.WaterEarlyWarn;
import com.hawksoft.platform.entity.WaterStation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WaterDao {

    /**
     * 查询实时水位数据
     * @return 所有站点的水位信息
     */
    public List<Water> findLastWater();

    /**
     * 取最新的DAYS条实时水位
     * @param map 查询参数
     * @return 返回历史水位的数据
     */
    public List<Water> lastWaterRecordsNum(Map<String, Object> map);


    /**
     * 获取历史水位
     * @param map 查询参数
     * @return 返回历史水位的数据
     */
    public List<Water> hisWater(Map<String, Object> map);


    /**
     * 获取当前时刻的某个水位的图片路径
     * @param map 查询参数
     * @return 返回指定时间的水位的数据
     */
    public Water hisWaterLevelPic(Map<String, Object> map);

    /**
     * 查询所有站点的信息
     * @return 返回站点的信息
     */
    public List<WaterStation> queryAllWaterStation(Map<String, Object> params);

    /**
     * 查询所有城市的数据，比如南京，北京
     * @return
     */
    public List<String> queryCityData();

    /**
     * 根据站点id查询实时水位
     * @param stnId
     * @return
     */
    public Water queryLastWaterByStnId(int stnId);

    /**
     * 保存水位信息
     * @param water
     */
    public void saveWater(Water water);

    /**
     * 根据站点id查询最近10张图片，默认10张
     * @param stnId
     * @return
     */
    public List<String> queryPicPathByStnId( @Param("stnId") int stnId);

    /**
     * 保存相机参数
     * @param args
     * @return
     */
    public int saveCameraArgs(CameraArgs args);

    /**
     * 通过相机id查询相机参数
     * @param id
     * @return
     */
    public CameraArgs findCameraArgsById(int id);

    /**
     * 通过站点id查询相机参数
     * @param stnId
     * @return
     */
    public List<CameraArgs> findCameraArgsByStnId(int stnId);

    /**
     * 通过相机id查询非默认的相机参数
     * @param id    相机id
     * @return
     */
    public CameraArgs findCameraArgsNoDefaultById(int id);

    /**
     * 通过站点id查询非默认的相机参数
     * @param stnId
     * @return
     */
    public CameraArgs findCameraArgsNoDefault(int stnId);

    /**
     * 删除不是默认的相机参数
     * @param id
     * @return
     */
    public int deleteCameraArgsNoDefaultById(int id);

    /**
     * 更新预警值
     * @param waterEarlyWarn
     * @return
     */
    public int updateWaterWarn(WaterEarlyWarn waterEarlyWarn);

    /**
     * 获取预警值
     * @param stnId
     * @param type 1代表是默认的，2代表是自定义的
     * @return 水位预警信息
     */
    public WaterEarlyWarn getWaterWarn(@Param("stnId") int stnId, @Param("type") int type);
}
