/**    
* @Title: ILicenceInfoService.java
* @Package com.frame.tobaCase.service
* @Description: 许可证信息service接口
* @author: shizh
* @date 2017年2月13日 下午2:56:57
* @version V1.0
*/
package com.frame.tobaCase.service;

import java.util.List;

import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.LicenceInfo;

public interface ILicenceInfoService extends IBaseService<LicenceInfo, String> {
    /**
     * 根据许可证查询数量
     * @param licNo 许可证号
     * @return
     */
    public Integer findBylicNoOutCount(String licNo);

    /**
     * 根据许可证查询一条记录
     * @param licNo 许可证号
     * @return
     */
    public LicenceInfo findBylicNoOutObject(String licNo);

    /**
     * 根据id数组获取记录集合
     * @param licNo 许可证号
     * @return
     */
    public List<LicenceInfo> findByIds(String[] ids);

    /**
    * @Description: 模糊搜索所有人名
    * @param @return
    * @author: lpy
    * @date 2017年2月27日 下午7:57:36
    * @throws
     */
    public List<String> findPerRespon(String perRespon);

    /**
     * 
    * @Description: 持久化导入的数据
    * @param @param licenceInfos
    * @param @return
    * @author shizh
    * @date 2017年3月4日 下午5:42:14
    * @throws
     */
    public int importLic(List<LicenceInfo> licenceInfos);

}
