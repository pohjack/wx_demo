/**    
* @Title: ICaseInfoService.java
* @Package com.frame.tobaCase.service
* @Description: 案件信息service接口
* @author: shizh
* @date 2017年2月13日 下午2:56:57
* @version V1.0
*/
package com.frame.tobaCase.service;

import java.util.List;
import java.util.Map;

import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.CaseInfo;
import com.frame.tobaCase.entity.CaseInfoSlave;
import com.frame.tobaCase.entity.GatherModel;
import com.frame.tobaCase.entity.NonExcel;
import com.frame.tobaCase.entity.SearchDataVo;

public interface ICaseInfoService extends IBaseService<CaseInfo, String> {

    /**
     * 
    * @Description: 按id查询案件信息并返回map
    * @param @param id
    * @param @return
    * @author shizh
    * @date 2017年2月15日 上午10:00:50
    * @throws
     */
    Map<String, Object> findByKey(String id);

    /**
    * @Description: 保存基本数据
    * @param @param info
    * @param @param salve
    * @param @return
    * @author: lpy
    * @date 2017年2月22日 下午4:38:23
    * @throws
     */
    public int saveCaseInfo(CaseInfo info, CaseInfoSlave slave);

    /**
    * @Description: 修改基本数据
    * @param @param info
    * @param @param salve
    * @param @return
    * @author: lpy
    * @date 2017年2月23日 下午5:03:24
    * @throws
     */
    public int updateCaseInfo(CaseInfo info, CaseInfoSlave slave);

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
    * @Description: 查找当前最大的案件编号
    * @param @return
    * @author shizh
    * @date 2017年3月6日 下午5:44:11
    * @throws
     */
    public String findMaxCaseNo();

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
    * @Description: 修改案件案由和案件来源
    * @param @param caseId
    * @param @param caseNo
    * @param @param caseCause
    * @param @param source
    * @param @return
    * @author shizh
    * @date 2017年3月9日 下午4:06:31
    * @throws
     */
    int updateCaseCauseInfo(String caseId, String caseNo, String caseCause, String source);

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

    List<NonExcel> findBySearch(SearchDataVo sv);

    public String findCountByCaseId(String caseInfoId);
}
