import com.hawksoft.platform.entity.CameraArgs;
import com.hawksoft.platform.entity.Water;
import com.hawksoft.platform.entity.WaterStation;
import com.hawksoft.platform.service.WaterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath:spring-mybatis.xml"),
        @ContextConfiguration(name = "child", locations = "classpath:springmvc.xml")
})
public class InterfaceTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    /**
     * 获取所有水位信息
     * @throws Exception
     */
    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/water/getAll/1/2015-10-12/2015-11-12"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    /**
     * 获取历史水位
     */
    @Test
    public void getHisWater() throws Exception {
        mockMvc.perform(get("/water/hisWater/2/2014-10-11/2016-1-1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * 获取最近水位
     */
    @Test
    public void getLastWater() throws Exception {
        mockMvc.perform(get("/water/lastWater/2"))
                .andExpect(status().isOk())
                .andDo(print());
    }


    /**
     * 获取相机参数
     * @throws Exception
     */
    @Test
    public void getCameraArgs() throws Exception {
        mockMvc.perform(get("/water/getCameraArgs/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * 设置相机参数
     * @throws Exception
     */
    @Test
    public void saveCameraArgs() throws Exception {
        mockMvc.perform(put("/water/setCameraArgs?RR=480&exposure=10&intensity=10&tone_up=10&stnId=2"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * 设置水位预警值
     * @throws Exception
     */
    @Test
    public void updateWaterWarn() throws Exception {
        mockMvc.perform(get("/water/updateWaterWarn?stnId=1&wl_status=picture"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * 获取水位预警值
     * @throws Exception
     */
    @Test
    public void getWaterWarn() throws Exception {
        mockMvc.perform(get("/water/getWaterWarn/1/2"))
                .andExpect(status().isOk())
                .andDo(print());
    }


    /**
     * 保存水质信息
     * @throws Exception
     */
    @Test
    public void saveWaterQuality() throws Exception {
        mockMvc.perform(get("/waterquality/saveWaterQuality?stnId=1&temperature=5.2&ph=5.2&dissolvedOxygen=5.2&collectionTime=2017-12-21 08:30:00&id=55"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    /**
     * 更新水质信息
     * @throws Exception
     */
    @Test
    public void updateWaterQuality() throws Exception {
        mockMvc.perform(get("/waterquality/updateWaterQuality?stnId=3&temperature=8.8&ph=8.8&dissolvedOxygen=8.8&id=33"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    /**
     * 删除水质信息
     * @throws Exception
     */
    @Test
    public void deleteWaterQuality() throws Exception {
        mockMvc.perform(get("/waterquality/deleteWaterQuality?stnId=1&temperature=10.2&ph=8.2&dissolvedOxygen=3.2&id=33"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * 保存流量流速信息
     * @throws Exception
     */
    @Test
    public void saveSpeedFlow() throws Exception {
        mockMvc.perform(get("/speedflow/saveSpeedFlow?stnId=1&waterSpeed1=5.1&waterFlow1=55.4&waterSpeed2=5.2&waterFlow2=5.6&collectionTime=2017-12-25 08:30:00&FilePath1=c:/ll.txt"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    /**
     * 修改流量流速信息
     * @throws Exception
     */
    @Test
    public void updateSpeedFlow() throws Exception {
        mockMvc.perform(get("/speedflow/updateSpeedFlow?speedflowid=2&stnId=2&waterSpeed1=3.1&waterFlow1=3.4&waterSpeed2=3.2&waterFlow2=3.6&waterFlow3=33.3&collectionTime=2017-12-23 08:30:00&FilePath1=c:/cc.txt"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    /**
     * 删除流量流速信息
     * @throws Exception
     */
    @Test
    public void deleteSpeedFlow() throws Exception {
        mockMvc.perform(get("/speedflow/deleteSpeedFlow?speedflowid=2"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    /**
     * 查询这段时间内流速流量数据
     * @param *如果有时间参数:startTime 开始时间， endTime 结束时间
     * @return
     */
    @Test
    public void queryAllSpeedFlowByTime() throws Exception {
        mockMvc.perform(get("/speedflow/queryAllSpeedFlowByTime/1/2017-12-20/2017-12-30"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * 通过站点取得实时流量汇总数据（最新时间的流量汇总数据）
     * @return
     */
    @Test
    public void queryLastFlowByStdId() throws Exception {
        mockMvc.perform(get("/flow/queryLastFlowByStdId/2"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * 通过站点ID和时间查询该站点这段时间内流量汇总数据
     * @param
     * @return 返回该站点这段时间内流量汇总数据
     */
    @Test
    public void queryFlowByStdIdTime() throws Exception {
        mockMvc.perform(get("/flow/queryFlowByStdIdTime/2/2017-12-20/2017-12-26"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * 查询这段时间内流量汇总数据
     * @param *map ，如果map有时间参数:startTime 开始时间， endTime 结束时间
     * @return
     */
    @Test
    public void queryAllFlowByTime() throws Exception {
        mockMvc.perform(get("/flow/queryAllFlowByTime/2017-12-20/2017-12-26"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * 保存流量汇总信息
     * @param
     */
    @Test
    public void saveFlow() throws Exception {
        mockMvc.perform(get("/flow/saveFlow?stnId=1&avgSpeed=2.1&riverArea=2.4&avgFlow=2&collectionTime=2017-12-22%2008:30:00&dayFlow=22.34"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    /**
     * 更新流量汇总信息
     * @param
     */
    @Test
    public void updateFlow() throws Exception {
        mockMvc.perform(get("/flow/updateFlow?stnId=2&avgSpeed=0.1&riverArea=0.4&avgFlow=50&collectionTime=2017-12-30%2008:30:00&dayFlow=20.34&flowId=2"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * 删除流量汇总信息
     * @param
     */
    @Test
    public void deleteFlow() throws Exception {
        mockMvc.perform(get("/flow/deleteFlow?flowId=2"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void importImg() throws Exception {
        mockMvc.perform(post("/floating/importImgDetail"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
