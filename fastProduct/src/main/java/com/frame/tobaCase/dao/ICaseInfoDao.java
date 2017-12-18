/**    
* @Title: ICaseInfoDao.java
* @Package com.frame.tobaCase.dao
* @Description: 案件信息dao接口
* @author: shizh
* @date 2017年2月13日 下午3:10:28
* @version V1.0
*/
package com.frame.tobaCase.dao;

import java.util.List;
import java.util.Map;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.tobaCase.entity.CaseInfo;
import com.frame.tobaCase.entity.GatherModel;
import com.frame.tobaCase.entity.NonExcel;
import com.frame.tobaCase.entity.SearchDataVo;

public interface ICaseInfoDao extends IMybaitsBaseDao<CaseInfo, String> {

    /**
    * @Description:查询立案或结案件数
    * @param @param endCase
    * @param @return
    * @author: lpy
    * @date 2017年3月6日 下午8:04:52
    * @throws
     */
    public String findCaseCount(Map<String, Object> endCase);

    /**
     * 
    * @Description: 按ID查询案件信息并返回map
    * @param @param id
    * @param @return
    * @author shizh
    * @date 2017年2月15日 上午10:02:12
    * @throws
     */
    Map<String, Object> findByKey(String id);

    /**
     * 
    * @Description: 查找当前最大的案件编号
    * @param @return
    * @author shizh
    * @date 2017年3月6日 下午5:44:11
    * @throws
     */
    public int findMaxCaseNo();

    /**
    * @Description: 按时间分段查询案件数量
    * @param @param map
    * @param @return
    * @author: lpy
    * @date 2017年3月8日 下午5:16:11
    * @throws
     */
    public Map<String, Object> findCompareCase(Map<String, Object> map);

    /**
     * 
    * @Description: 更新案件案由
    * @param @param caseInfo
    * @param @return
    * @author shizh
    * @date 2017年3月9日 下午4:09:12
    * @throws
     */
    int updateCause(CaseInfo caseInfo);

    /**
     * 
     * @Description: 更新案件来源
     * @param @param caseInfo
     * @param @return
     * @author shizh
     * @date 2017年3月9日 下午4:09:12
     * @throws
     */
    int updateSource(CaseInfo caseInfo);

    /**
     * 
    * @Description: 结案方法
    * @param @param caseId
    * @param @return
    * @author shizh
    * @date 2017年3月17日 下午5:34:06
    * @throws
     */
    int endCase(String caseId);

    /**
     * 
    * @Description: 根据搜索条件查询 结果
    * @param @param sv
    * @param @return
    * @author: liy
    * @date 2017年4月14日 上午10:02:53
    * @throws
     */
    public List<GatherModel> findBySearchVo(SearchDataVo sv);

    /**
    * @Description: 判断案件编号是否已经存在
    * @param @param regiNo
    * @param @return
    * @author: lpy
    * @date 2017年4月14日 下午4:21:51
    * @throws
     */
    public int regiNoIsSame(String regiNo);

    public List<NonExcel> findBySearch(SearchDataVo sv);

    /**
     * 
    * @Description: 查找委托人
    * @param @param caseInfoId
    * @param @return
    * @author yuyf
    * @date 2017年5月12日 下午3:00:58
    * @throws
     */
    public String findCountByCaseId(String caseInfoId);
}
