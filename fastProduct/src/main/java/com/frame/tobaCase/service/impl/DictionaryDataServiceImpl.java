/**    
* @Title: DictionaryDataServiceImpl.java
* @Package com.frame.tobaCase.service.impl
* @Description: 数据字典service实现类
* @author: shizh
* @date 2017年2月13日 下午3:06:59
* @version V1.0
*/
package com.frame.tobaCase.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.tobaCase.dao.IDictionaryDataDao;
import com.frame.tobaCase.entity.DictionaryData;
import com.frame.tobaCase.service.IDictionaryDataService;

@Service("dictionaryDataService")
public class DictionaryDataServiceImpl extends BaseServiceImpl<DictionaryData, String>
	implements IDictionaryDataService {

    @Resource
    private IDictionaryDataDao dictionaryDataDao;

    @Override
    protected IMybaitsBaseDao<DictionaryData, String> getBaseDao() {
	return dictionaryDataDao;
    }

    @Override
    public DictionaryData findByCode(String code) {
	return dictionaryDataDao.findByCode(code);
    }

}
