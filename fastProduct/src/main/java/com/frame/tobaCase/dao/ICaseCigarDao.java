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
import com.frame.tobaCase.entity.CaseCigar;

public interface ICaseCigarDao extends IMybaitsBaseDao<CaseCigar, String> {

    /**
     * 
    * @Description: 根据案件id查询所有涉案卷烟信息
    * @param @param id
    * @param @return
    * @author shizh
    * @date 2017年2月14日 上午10:32:56
    * @throws
     */
    List<CaseCigar> findByCaseId(String id);

    /**
     * 
    * @Description: 根据案件id查询所有涉案卷烟的属性
    * @param @param id
    * @param @return
    * @author shizh
    * @date 2017年2月22日 下午2:10:58
    * @throws
     */
    String findTypesById(String id);

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
     * 
    * @Description: 查询某个属性的所有卷烟
    * @param @param id
    * @param @param param
    * @param @return
    * @author shizh
    * @date 2017年2月22日 下午3:38:24
    * @throws
     */
    List<CaseCigar> findByAttr(String id, String param);

    /**
     * 
    * @Description: 返回某个案件涉及的所有卷烟品种与数量合并之后的字符串
    * 如“11,285”，表示11个品种285条
    * @param @param id
    * @param @return
    * @author shizh
    * @date 2017年2月22日 下午4:50:48
    * @throws
     */
    String countType(String id);

    /**
    * @Description: 一次性保存多条记录
    * @param @param caseCigar
    * @param @return
    * @author: lpy
    * @date 2017年2月24日 下午2:07:23
    * @throws
     */
    public int saveAll(List<CaseCigar> caseCigar);

    /**
    * @Description: 根据案件id删除信息
    * @param @return
    * @author: lpy
    * @date 2017年2月24日 下午5:22:19
    * @throws
     */
    public int removeByCaseId(String caseId);

    /**
    * @Description: 卷烟品牌查询数量
    * @param @param map
    * @param @return
    * @author: lpy
    * @date 2017年3月2日 下午6:01:55
    * @throws
     */
    public List<Map<String, Object>> findCigarName(Map<String, Object> map);

    /**
    * @Description: 假非私数量以及总值统计
    * @param @param map
    * @param @return
    * @author: lpy
    * @date 2017年3月2日 下午7:24:06
    * @throws
     */
    public List<Map<String, Object>> findInspectResult(Map<String, Object> map);

    /**
    * @Description: 假非私数量以及总值统计
    * @param @param map
    * @param @return
    * @author: lpy
    * @date 2017年3月2日 下午7:24:06
    * @throws
     */
    public List<Map<String, Object>> findInspectResult2(Map<String, Object> map);

    /**
    * @Description: 查询时间段内卷烟类型数据
    * @param @return
    * @author: lpy
    * @date 2017年3月7日 下午10:21:42
    * @throws
     */
    public Map<String, Object> findCompareByResult(Map<String, Object> map);

}
