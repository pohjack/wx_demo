/**   
*     
* 类名称：SQLAdapter   
* 类描述：   
* 创建人：jekoll  
* 创建时间：2016年3月24日 下午9:43:01   
* 修改人：jekoll   
* 修改时间：2016年3月24日 下午9:43:01   
* 修改备注：   
* @version  v1.0 
*    
*/
package com.frame.core.commons.sqlUtils;

public class SQLAdapter {
	String sql;

	/**
	 * @param sql2
	 */
	public SQLAdapter(String sql) {
		this.sql = sql;
	}

	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @param sql the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}
}
