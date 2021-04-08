package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ReadConfig {
	Properties pro;
	public ReadConfig()
	{
		File source=new File("./configuration/config.properties");
		try {
			FileInputStream fis=new FileInputStream(source);
			 pro=new Properties();
			 pro.load(fis);
			
		} catch (Exception e) 
		{
			
			System.out.println(e.getMessage());
		}
	}
public String geturl()
{
String url = pro.getProperty("url");
return url;
}
public String getUid()
{
String uid=pro.getProperty("username");
return uid;
}
public String getPassword()
{
String pwd=pro.getProperty("password");
return pwd;
}
public String getChromePath()
{
String cpath=pro.getProperty("chromepath");
return cpath;
}
public String getFireFoxPath()
{
String fpath=pro.getProperty("firefoxpath");
return fpath;
}
public String getIEPath()
{
String ipath=pro.getProperty("iepath");
return ipath;
}
public String getOpera()
{
	String opath = pro.getProperty("oppath");
	return opath;
}
}

