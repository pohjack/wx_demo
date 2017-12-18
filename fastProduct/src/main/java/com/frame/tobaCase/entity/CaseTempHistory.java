/**    
* @Title: CaseTempHistory.java
* @Package com.frame.tobaCase.entity
* @Description: 案件与文书模板历史记录表实体对象
* @author: shizh
* @date 2017年3月2日 下午4:03:55
* @version V1.0
*/
package com.frame.tobaCase.entity;

import com.frame.core.entity.BizBaseEntity;

public class CaseTempHistory extends BizBaseEntity {

    /**
    * @ serialVersionUID : 序列化
    */
    private static final long serialVersionUID = 1L;

    private String caseId; // 案件ID
    private String docsId; // 文书模板ID
    private String content; // 模板内容

    public String getCaseId() {
	return caseId;
    }

    public void setCaseId(String caseId) {
	this.caseId = caseId;
    }

    public String getDocsId() {
	return docsId;
    }

    public void setDocsId(String docsId) {
	this.docsId = docsId;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

}
