/**    
* @Title: LawServiceImpl.java
* @Package com.frame.tobaCase.service.impl
* @Description: 法律法规service实现类
* @author: shizh
* @date 2017年2月13日 下午3:06:59
* @version V1.0
*/
package com.frame.tobaCase.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.tobaCase.dao.ILawDao;
import com.frame.tobaCase.entity.Law;
import com.frame.tobaCase.service.ILawService;

@Service("lawService")
public class LawServiceImpl extends BaseServiceImpl<Law, String> implements ILawService {
    @Resource
    private ILawDao lawDao;

    @Override
    protected IMybaitsBaseDao<Law, String> getBaseDao() {
	// TODO Auto-generated method stub
	return lawDao;
    }

    @Override
    public List<Law> findByLawNum(String lawNum) {
	return lawDao.findByLawNum(lawNum);
    }
}
