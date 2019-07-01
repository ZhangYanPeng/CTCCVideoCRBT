package com.CTCC.CRBT.Common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
 
public class PropertiesUtil {
 
    //添加日志
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
    //添加proterties
    private static Properties properties;
    
    static{
        String fileName = "ftp.properties";
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName)));
        } catch (IOException e) {
            logger.error("配置文件读取异常",e);
        }
    }
 
    public static String getProperty(String key){
        String value = properties.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            return null;
        }
        return value;
    }
 
    public static String getProperty(String key,String defaultValue){
        String value = properties.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            return defaultValue;
        }
        return value;
    }
 
 
}