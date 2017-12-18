/**   
*     
* 类名称：PropertiesUtil   
* 类描述：   读取properties资源文件
* 创建人：jekoll  
* 创建时间：2016年3月23日 下午9:30:42   
* 修改人：jekoll   
* 修改时间：2016年3月23日 下午9:30:42   
* 修改备注：   
* @version  v1.0 
*    
*/
package com.frame.core.commons.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

public class PropertiesUtil {

	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	/**  
	* @Fields SYSCONFIG_FILE : 全局系统配置文件  
	*/
	private static final String SYSCONFIG_FILE = "sysconfig.properties";

	/**  
	* @Fields DEFAULT_ENCODING : 文件编码使用UTF-8 
	*/
	private static final String DEFAULT_ENCODING = "UTF-8";

	private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();

	private static ResourceLoader resourceLoader = new DefaultResourceLoader();
	private static PropertiesUtil single = null;

	private PropertiesUtil() {
	}

	public static PropertiesUtil getInstance() {
		if (single == null) {
			synchronized (PropertiesUtil.class) {
				if (single == null) {
					single = new PropertiesUtil();
				}
			}
		}
		return single;
	}

	/**  
	* @Title: loadSysConfig  
	* @Description: 读取全局设置文件
	* @param @throws IOException     
	* @return Properties      
	* @throws  
	*/
	private Properties loadProperties() {
		String filePath = getRealFile(SYSCONFIG_FILE);
		Properties pro = new Properties();
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(filePath));
			pro.load(in);

		} catch (IOException e) {
			logger.info("Could not load properties from classpath:" + filePath + ": " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.info("SysConfig InputStream is close fail:" + e.getMessage());

				}
			}

		}
		return pro;
	}

	/**  
	* @Title: loadProperties  
	* @Description: 获取配置文件 
	* @param @param filePath
	* @return Properties    返回类型  
	* @throws  
	*/
	private Properties loadProperties(String filePath) {
		filePath = getRealFile(filePath);
		Properties props = new Properties();
		InputStream in = null;
		try {
			Resource resource = resourceLoader.getResource(filePath);
			in = resource.getInputStream();
			propertiesPersister.load(props, new InputStreamReader(in, DEFAULT_ENCODING));

		} catch (IOException e) {
			logger.info("Could not load properties from classpath:" + filePath + ": " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.info("loadProperties InputStream is close fail:" + e.getMessage());

				}
			}

		}
		return props;
	}

	/**  
	* @Title: loadProperties  
	* @Description: 读取多个配置文件  
	* @param @param resourcesPaths
	* @param @throws IOException     
	* @return Properties 
	* @throws  
	*/
	private Properties loadProperties(String... resourcesPaths) {
		Properties props = new Properties();
		for (String location : resourcesPaths) {
			logger.debug("Loading properties file from:" + location);
			InputStream is = null;

			try {

				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				propertiesPersister.load(props, new InputStreamReader(is, DEFAULT_ENCODING));
			} catch (IOException ex) {
				logger.info("Could not load properties from classpath:" + location + ": " + ex.getMessage());
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						logger.info("InputStream is close fail:" + e.getMessage());
					}
				}

			}

		}
		return props;
	}

	/**  
	* @Title: getRealFile  
	* @Description: 获得文件路径 
	* @param @param filePath  文件路径
	* @return String    文件路径  
	* @throws  
	*/
	public String getRealFile(String filePath) {
		return PropertiesUtil.class.getResource("/" + filePath).toString();
	}

	/**  
	* @Title: getSysConfigProperty  
	* @Description: 获取配置文件属性值
	* @param @param key 配置文件key值
	* @return String      
	* @throws  
	*/
	private String getProperty(String key, Properties props) {
		return props.getProperty(key);
	}

	/**  
	* @Title: getProperty  
	* @Description: 根据文件路径获取文件属性值 
	* @param @param key
	* @param @param filePath
	* @return String
	* @throws  
	*/
	public String getProperty(String key, String filePath) {
		Properties props = loadProperties(filePath);
		return getProperty(key, props);
	}

	/**  
	* @Title: getProperty  
	* @Description: 根据多个文件路径获取文件属性值 )  
	* @param @param key
	* @param @param resourcesPaths
	* @return String    返回类型  
	* @throws  
	*/
	public String getProperty(String key) {
		String mFile = getSysConfigProperty("proFileList");
		String[] resourcesPaths = new String[] {};
		if (mFile.indexOf(",") == -1) {
			resourcesPaths[0] = mFile;
		} else {
			resourcesPaths = mFile.split(",");
		}
		Properties props = loadProperties(resourcesPaths);
		return getProperty(key, props);
	}

	/**  
	* @Title: getSysConfigProperty  
	* @Description:获取全局系统文件配置值
	* @param @param key
	* @return String  
	* @throws  
	*/
	public String getSysConfigProperty(String key) {
		Properties props = loadProperties();
		return getProperty(key, props);
	}
	
	   static final String LOG = "log.properties";
	   
    public static String getLogProperty(String key){
        InputStream is;
        Properties properties = new Properties ();
        try{
            is = PropertiesUtil.class.getClassLoader ().getResourceAsStream (LOG);
            properties.load (is);
        }
        catch (FileNotFoundException e1){
            logger.error ("找不到" + LOG);
            e1.printStackTrace ();
        }
        catch (IOException e){
            logger.error ("读取文件" + LOG + " 异常");
            e.printStackTrace ();
        }
        return properties.getProperty (key);
    }
}
