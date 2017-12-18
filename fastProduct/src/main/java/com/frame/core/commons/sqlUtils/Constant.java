/**    
* @Title: Constant.java
* @Package com.frame.core.commons
* @Description: 定义基础的mybaits命名空间
* @author: lzl
* @date 2016年7月4日 上午9:55:39
* @version V1.0
*/
package com.frame.core.commons.sqlUtils;
public interface Constant{
	String SQLNAME_SEPARATOR = ".";
	/**
	* @ MAPPING_ID_FINDTREE : 查询树结构数据
	*/
    String MAPPING_ID_FINDTREE = "findTree";
	/**
	* @ SQL_FINDALL : 查询所有
	*/
	String MAPPING_ID_FINDALL = "findAll";

	/**
	* @ SQL_FINDBYID : 根据业务id查询
	*/
	String MAPPING_ID_FINDBYID = "findById";

	/**
	* @ MAPPING_ID_FINDALLTOTAL : 统计总数
	*/
	String MAPPING_ID_FINDALLTOTAL = "findAllTotal";

	/**
	* @ MAPPING_ID_QUERYALLBYPAGE : 分页查询
	*/
	String MAPPING_ID_QUERYALLBYPAGE = "queryAllByPage";

	/**
	* @ MAPPING_ID_DELETE : 根据业务id删除数据
	*//*
	String MAPPING_ID_DELETE = "removeById";*/

	/**
	* @ MAPPING_IDS_DELETE : 根据业务id批量删除
	*/
	String MAPPING_IDS_DELETE = "removeByIds";
	/**
	 * @ MAPPING_DELETE_ALL : 删除所有
	 */
	String MAPPING_DELETE_ALL = "removeAll";

	/**
	* @ MAPPING_ID_SAVE : 保存
	*/
	String MAPPING_ID_SAVE = "save";

	/**
	* @ MAPPING_ID_UPDATE : 更新
	*/
	String MAPPING_ID_UPDATE = "update";
	/**
	 * @ MAPPING_ID_FINDBYPAGE : 分页查询
	 */
	String MAPPING_ID_FINDBYPAGE = "findByPage";
}
