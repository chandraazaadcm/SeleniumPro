package utilities;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	ReadConfig rc=new ReadConfig();
	public String baseurl=rc.geturl();
	public String uid=rc.getUid();
	public String pwd=rc.getPassword();
    public static WebDriver driver;
    public Logger log;
    @BeforeClass
    @Parameters("browser")
    public void browser(String br)
    {
    	if(br.equals("chrome"))
{
	System.setProperty("webdriver.chrome.driver",rc.getChromePath());
	driver=new ChromeDriver();
}
    	else if(br.equals("firefox"))
    	{
    		System.setProperty("webdriver.gecko.driver", rc.getFireFoxPath());
    		driver=new FirefoxDriver();
    	}
    	else if(br.equals("ie"))
    	{
    		System.setProperty("webdriver.ie.driver", rc.getIEPath());
    		driver=new InternetExplorerDriver();
    	}
    	 log=Logger.getLogger("Test-log");
    	PropertyConfigurator.configure("log4j.properties");
    }
    @AfterClass
    public void tearDown() throws Exception
    {
    	Thread.sleep(3000);
    	driver.quit();
    }
    public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	
	}

}
