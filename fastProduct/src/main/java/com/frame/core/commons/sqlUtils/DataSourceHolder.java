/**   
*     
* 类名称：DataSourceHolder   
* 类描述：   数据源方式
* 创建人：jekoll  
* 创建时间：2016年3月24日 下午6:25:27   
* 修改人：jekoll   
* 修改时间：2016年3月24日 下午6:25:27   
* 修改备注：   
* @version  v1.0 
*    
*/
package com.frame.core.commons.sqlUtils;

import javax.sql.DataSource;

public class DataSourceHolder {

	private static final String MASTER = "master";

	private static final String SLAVE = "slave";

	/**
	 * dataSource master or slave
	 */
	private static final ThreadLocal<String> dataSources = new ThreadLocal<String>();

	/**
	 * master local
	 */
	private static final ThreadLocal<DataSource> masterLocal = new ThreadLocal<DataSource>();

	/**
	 * master local
	 */
	private static final ThreadLocal<DataSource> slaveLocal = new ThreadLocal<DataSource>();

	private static void setDataSource(String dataSourceKey) {
		dataSources.set(dataSourceKey);
	}

	private static String getDataSource() {
		return (String) dataSources.get();
	}

	/**
	 * 标志为master
	 */
	public static void setMaster() {
		setDataSource(MASTER);
	}

	/**
	 * 标志为slave
	 */
	public static void setSlave() {
		setDataSource(SLAVE);
	}

	/**
	 * 将master放入threadlocal
	 * @param master
	 */
	public static void setMaster(DataSource master) {
		masterLocal.set(master);
	}

	/**
	 * 将slave放入threadlocal
	 * @param master
	 */
	public static void setSlave(DataSource slave) {
		slaveLocal.set(slave);
	}

	public static boolean isMaster() {
		return getDataSource() == MASTER;
	}

	public static boolean isSlave() {
		return getDataSource() == SLAVE;
	}

	public static void clearDataSource() {
		dataSources.remove();
		masterLocal.remove();
		slaveLocal.remove();
	}
}
