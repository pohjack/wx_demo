/**    
* @Title: ICaseCigarService.java
* @Package com.frame.tobaCase.service
* @Description: 涉案卷烟service接口
* @author: shizh
* @date 2017年2月13日 下午2:56:57
* @version V1.0
*/
package com.frame.tobaCase.service;

import java.util.List;
import java.util.Map;

import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.CaseCigar;

public interface ICaseCigarService extends IBaseService<CaseCigar, String> {

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
    * @Description: 根据案件id查询所有涉案卷烟属性
    * @param @param id
    * @param @return
    * @author shizh
    * @date 2017年2月22日 下午2:09:51
    * @throws
     */
    String findTypesById(String id);

    /**
     * 
    * @Description: 分类统计总值
    * @param @param id 案件ID
    * @param @param param 卷烟属性，如假或非或私
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
    public String saveAll(List<CaseCigar> caseCigar, String caseInfoId);

    /**
    * @Description: 根据卷烟品牌和时间段查询假非私数量以及总数
    * @param @param map
    * @param @return
    * @author: lpy
    * @date 2017年3月3日 下午2:25:44
    * @throws
     */
    public Map<String, Object> findCigarName(Map<String, Object> map);

    /**
    * @Description: 根据时间段统计假非私数量，总值以及罚款总和以及立案件数
    * @param @param map
    * @param @return
    * @author: lpy
    * @date 2017年3月3日 下午2:42:44
    * @throws
     */
    public Map<String, Object> findInspectResult(Map<String, Object> map);

    /**
    * @Description: 根据时间段统计假非私数量，总值以及罚款总和以及立案件数
    * @param @param map
    * @param @return
    * @author: lpy
    * @date 2017年3月3日 下午2:42:44
    * @throws
    */
    public Map<String, Object> findInspectResult2(Map<String, Object> map);

    /**
    * @Description: 修改
    * @param @param caseCigar
    * @param @return
    * @author: lpy
    * @date 2017年3月2日 下午7:22:49
    * @throws
     */
    public int updateAll(CaseCigar caseCigar, int type);

    /**
    * @Description: 查询时间段内卷烟类型数据
    * @param @return
    * @author: lpy
    * @date 2017年3月7日 下午10:21:42
    * @throws
     */
    public Map<String, Object> findCompareByResult(Map<String, Object> map);
}
