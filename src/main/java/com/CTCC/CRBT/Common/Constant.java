package com.CTCC.CRBT.Common;

public class Constant {
	public static int PAGESIZE = 8;//list每页最大项数
	public static final String STANDARD_FORMAT = "yyyy-MM-dd";//文件上传以当天日期为目录

	//获取配置文件中的配置参数
    public static String ftpIp = PropertiesUtil.getProperty("ftp.server.ip");
    public static String ftpUser = PropertiesUtil.getProperty("ftp.user");
    public static String ftpPwd = PropertiesUtil.getProperty("ftp.pass");
    public static int ftpPort = Integer.valueOf(PropertiesUtil.getProperty("ftp.prot")).intValue();
}
