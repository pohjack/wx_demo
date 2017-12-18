/**   
*     
* 类名称：DynamicDataSource   
* 类描述：   动态数据源
* 创建人：jekoll  
* 创建时间：2016年3月24日 下午6:29:59   
* 修改人：jekoll   
* 修改时间：2016年3月24日 下午6:29:59   
* 修改备注：   
* @version  v1.0 
*    
*/
package com.frame.core.commons.sqlUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class DynamicDataSource extends AbstractRoutingDataSource {

	private static Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

	private AtomicInteger counter = new AtomicInteger();

	/**
	 * master库 dataSource
	 */
	private DataSource master;

	/**
	 * slaves
	 */
	private List<DataSource> slaves;

	@Override
	protected Object determineCurrentLookupKey() {
		return null;
	}

	@Override
	public void afterPropertiesSet() {
	}

	/**
	 * 根据标识
	 * 获取数据源
	 * 
	 */
	@Override
	protected DataSource determineTargetDataSource() {
		DataSource returnDataSource = null;
		if (DataSourceHolder.isMaster()) {
			returnDataSource = master;
		} else if (DataSourceHolder.isSlave()) {
			int count = counter.incrementAndGet();
			if (count > 1000000) {
				counter.set(0);
			}
			int n = slaves.size();
			int index = count % n;
			returnDataSource = slaves.get(index);
		} else {
			returnDataSource = master;
		}
		/**
		if (returnDataSource instanceof ComboPooledDataSource) {
			ComboPooledDataSource source = (ComboPooledDataSource) returnDataSource;
			String jdbcUrl = source.getJdbcUrl();
			logger.debug("jdbcUrl:" + jdbcUrl);
		}**/
		return returnDataSource;
	}

	public DataSource getMaster() {
		return master;
	}

	public void setMaster(DataSource master) {
		this.master = master;
	}

	public List<DataSource> getSlaves() {
		return slaves;
	}

	public void setSlaves(List<DataSource> slaves) {
		this.slaves = slaves;
	}
}
