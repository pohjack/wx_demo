package com.frame.tobaCase.service;

import java.util.List;

import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.NonCigar;

public interface INonCigarService extends IBaseService<NonCigar, String> {

    /**
     * 
    * @Description: 根据案件id查询所有涉案非烟信息
    * @param @param id
    * @param @return
    * @author shizh
    * @date 2017年2月14日 上午10:32:56
    * @throws
     */
    List<NonCigar> findByCaseId(String id);

    /**
     * 
    * @Description:根据案件id和涉案卷烟表的主键id进行查找
    * @param @param caseId
    * @param @param cigarId
    * @param @return
    * @author yuyf
    * @date 2017年5月11日 下午5:35:24
    * @throws
     */
    List<NonCigar> findByCaseIdAndCigarId(String caseId, String cigarId);

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
    public String saveAll(List<NonCigar> nonCigar, String caseInfoId);

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
    * @date 2017年5月11日 下午5:36:14
    * @throws
     */

    public int removeByCaseIdCigarId(String caseId, String cigarId);

}
