package com.frame.tobaCase.service;

import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.CaseSend;

public interface ICaseSendService extends IBaseService<CaseSend, String> {

    /**
     * 
    * @Description: 查询案件的某一送达
    * @param @param caseId
    * @param @param cigarId
    * @param @return
    * @author yuyf
    * @date 2017年5月9日 上午10:21:43
    * @throws
     */
    CaseSend findByCaseIdAndCode(String caseId, String code);

}
