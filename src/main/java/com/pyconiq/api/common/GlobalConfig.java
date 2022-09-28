package com.pyconiq.api.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GlobalConfig {

	private Properties properties;
	private static GlobalConfig instance;
    private static String path=System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties";
	
    public GlobalConfig(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + path);
		}		
	}

	public String getURL() {
		return properties.getProperty("baseurl");

	}
	public String getAuthUserName() {
		return properties.getProperty("auth_user");

	}
	public String getAuthPswd() {
		return properties.getProperty("auth_pswd");

	}
	
	public String getBasicAuth() {
		return properties.getProperty("basicAuth");

	}
	
	public String getTokenForUpdate() {
		return properties.getProperty("tokenForupdate");

	}

	public String getParameter(String par) {
		return properties.getProperty(par);

	}

}
