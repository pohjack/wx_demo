/**    
* @Title: ITemplateService.java
* @Package com.frame.tobaCase.service
* @Description: TODO(用一句话描述该文件做什么)
* @author: shizh
* @date 2017年2月23日 下午8:57:33
* @version V1.0
*/
package com.frame.tobaCase.service;

public interface ITemplateService {

    /**
     * 
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @param @param modelNo
    * @param @param caseId
    * @param @return
    * @author shizh
    * @date 2017年2月24日 上午11:27:55
    * @throws
     */
    public String AutoGenerate(String modelNo, String caseId, int tag);

    /**
     * 
    * @Description: 子模板编辑预览方法
    * @param @param tid 子模板ID
    * @param @param caseId	用于展示的案件ID
    * @param @param modelNo 文书模板ID
    * @param @return
    * @author shizh
    * @date 2017年2月25日 下午2:48:41
    * @throws
     */
    String BaseTempEdit(String tid, String caseId, String modelNo);

    /**
     * 
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @param @param template
    * @param @param caseId
    * @param @param modelNo
    * @param @return
    * @author shizh
    * @date 2017年3月2日 下午8:49:43
    * @throws
     */
    public StringBuffer BaseTempGenerate(String template, String caseId, String modelNo, String bId);

    /**
     * 
    * @Description: 生成原始未替换的内容
    * @param @param modelNo
    * @param @param caseId
    * @param @return
    * @author shizh
    * @date 2017年3月2日 下午8:49:13
    * @throws
     */
    public String GenerateOriginCont(String modelNo, String caseId, int tag);
}
