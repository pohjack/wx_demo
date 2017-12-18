package com.frame.tobaCase.utils;

import java.io.File;

public class GetServerRealPath {
    /**
     * 获取项目在服务其中的真实路径的工具类
     * 
     * 这是在web项目中，获取项目实际路径的最佳方式，在windows和linux系统下均可正常使用
     * 
     */
    public static String getRootPath() {

	String classPath = GetServerRealPath.class.getClassLoader().getResource("/").getPath();
	String rootPath = "";
	// windows下
	if ("\\".equals(File.separator)) {
	    rootPath = classPath.substring(1, classPath.indexOf("/WEB-INF/classes"));
	    rootPath = rootPath.replace("/", "\\");
	}
	// linux下
	if ("/".equals(File.separator)) {
	    rootPath = classPath.substring(0, classPath.indexOf("/WEB-INF/classes"));
	    rootPath = rootPath.replace("\\", "/");
	}
	return rootPath;
    }
}
