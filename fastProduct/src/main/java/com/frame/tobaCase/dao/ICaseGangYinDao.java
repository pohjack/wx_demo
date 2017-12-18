/**    
* @Title: ICaseCigarDao.java
* @Package com.frame.tobaCase.dao
* @Description: 涉案卷烟dao接口
* @author: shizh
* @date 2017年2月13日 下午3:10:28
* @version V1.0
*/
package com.frame.tobaCase.dao;

import java.util.List;
import java.util.Map;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.tobaCase.entity.CaseGangYin;

public interface ICaseGangYinDao extends IMybaitsBaseDao<CaseGangYin, String> {

    /**
     * 
    * @Description: 根据案件id查询所有涉案卷烟信息
    * @param @param id
    * @param @return
    * @author shizh
    * @date 2017年2月14日 上午10:32:56
    * @throws
     */
    List<CaseGangYin> findByCaseId(String id);

    /**
     * 
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @param @param id
    * @param @param param
    * @param @return
    * @author shizh
    * @date 2017年2月22日 下午2:46:30
    * @throws
     */
    Double countTotalVal(String id, String param);

    /**
    * @Description: 一次性保存多条记录
    * @param @param caseCigar
    * @param @return
    * @author: lpy
    * @date 2017年2月24日 下午2:07:23
    * @throws
     */
    public int saveAll(List<CaseGangYin> caseGangYin);



    /**
     * 
    * @Description: 查询某案件的某一卷烟的记录数
    * @param @param caseId
    * @param @param cigarId
    * @param @return
    * @author yuyf
    * @date 2017年5月9日 上午10:20:55
    * @throws
     */
    public int findEachCigarTotal(String caseId, String cigarId);

    /**
     * 
    * @Description: 查找某案件涉及的所有卷烟品种
    * @param @param caseId
    * @param @return
    * @author yuyf
    * @date 2017年5月9日 上午11:19:26
    * @throws
     */
    public List<String> findCigarIds(String caseId);

    /**
     * 
    * @Description: 根据案件id和涉案卷烟的id删除
    * @param @param caseId
    * @param @param cigarId
    * @param @return
    * @author yuyf
    * @date 2017年5月11日 下午5:26:39
    * @throws
     */
    public int removeByCaseIdCigarId(String caseId, String cigarId);
    /**
     * 
    * @Description: 根据案件id和涉案卷烟的id查找
    * @param @param caseId
    * @param @param cigarId
    * @param @return
    * @author yuyf
    * @date 2017年5月11日 下午5:26:51
    * @throws
     */
    List<CaseGangYin> findByCaseIdAndCigarId(String caseId, String cigarId);

}
