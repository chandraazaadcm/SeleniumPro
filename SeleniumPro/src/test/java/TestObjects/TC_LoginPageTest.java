package TestObjects;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.LoginPage;
import utilities.BaseClass;

public class TC_LoginPageTest extends BaseClass {
	@Test
	public void verifyLoginPage() throws Exception
	{
		driver.get(baseurl);
		log.info("page is opened");
		LoginPage l=new LoginPage(driver);
		l.getUsername(uid);
		log.info("user name entered");
		l.getPassword(pwd);
		log.info("User password entred");
		l.getLogin();
		String title = driver.getTitle();
		if(title.equals("GTPL Banking Manager HomePage1"))
		{
			Assert.assertTrue(true);
			log.info("Test is passed");
		}
		else
		{
		captureScreen(driver, "verifyLoginPage");
			Assert.assertTrue(false);
			log.info("Test is failed");
		}
	}

}
