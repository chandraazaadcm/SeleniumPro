package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
	ExtentHtmlReporter EHR;
	ExtentReports ER;
	ExtentTest ET;
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test-Report-"+timeStamp+".html";
		
		EHR=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+repName);//specify location of the report
		EHR.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		
		ER=new ExtentReports();
		
		ER.attachReporter(EHR);
		ER.setSystemInfo("Host name","localhost");
		ER.setSystemInfo("Environemnt","QA");
		ER.setSystemInfo("user","Anush Kumar Reddy");
		
	    EHR.config().setDocumentTitle("Banking Test Project"); // Tile of report
		EHR.config().setReportName("Functional Test Automation Report"); // name of the report
		EHR.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
		EHR.config().setTheme(Theme.DARK);
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		ET=ER.createTest(tr.getName()); // create new entry in the report
		ET.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
	}
	
	public void onTestFailure(ITestResult tr)
	{
		ET=ER.createTest(tr.getName()); // create new entry in th report
		ET.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); // send the passed information to the report with GREEN color highlighted
		
		String screenshotPath=System.getProperty("user.dir")+"/screenshots/"+tr.getName()+".png";
		File f=new File(screenshotPath);
		if(f.exists())
		{
		try {
			ET.fail("ScreenShot for failed Test" +ET.addScreenCaptureFromPath(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		ET=ER.createTest(tr.getName()); // create new entry in th report
		ET.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext)
	{
		ER.flush();
	}


}
