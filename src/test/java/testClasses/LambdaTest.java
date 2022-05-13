package testClasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import pomClasses.LoginPOM;
import utility.Utility;


public class LambdaTest {
	
	public String baseUrl = "https://accounts.lambdatest.com";
    String driverPath = "D:\\chrome\\chromedriver.exe";
    public WebDriver driver ; 
	LoginPOM loginPOM = new LoginPOM();
	ExtentReports extent;
	ExtentTest test;
	Utility utility;
	ExtentReports reports;
    ExtentHtmlReporter htmlReporter;
     
    
	

    @BeforeTest
    public void startTest() {
                reports = new ExtentReports();
                htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//test-output//ExtentReport.html");
                reports.attachReporter(htmlReporter);
                reports.setSystemInfo("Machine", "Agnel");
                reports.setSystemInfo("Env", "QA");
                reports.setSystemInfo("Build", "Integration");
                reports.setSystemInfo("Browser", "Chrome");
    }
    
    @BeforeMethod
    public void openApplication() {
                System.setProperty("webdriver.chrome.driver",driverPath);
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.get(baseUrl);
    }
    
   @Test(priority=0)
    public void verifyTittleTest() {
                test = reports.createTest("verifyTittleTest");
                String expetedTitle = "Log in";
                String pageTitle = driver.getTitle();
                Assert.assertEquals(pageTitle, expetedTitle);
                test.log(Status.PASS,"Navigated to the Home Page");
    }
	
	@Test(priority=1)
    public void loginLambdaTest() {
    	       test=reports.createTest("loginLambdaTest");
    	       driver.findElement(loginPOM.email).sendKeys("agnelrayan@outlook.com");
               driver.findElement(loginPOM.pwd_labmda).sendKeys("caleb123@");
               driver.findElement(loginPOM.login_lambda).click();
               test.log(Status.PASS, "Navigated to Login Page");
 	     
 	      

   }
  
  
  @AfterMethod
  public void setTestResult(ITestResult result) throws IOException {
  	
              String screenShot = Utility.getScreenshot(driver);
              if (result.getStatus() == ITestResult.FAILURE) {
                          test.log(Status.FAIL, result.getName());
                          test.log(Status.FAIL,result.getThrowable());
                         test.fail("Screen Shot : " + test.addScreenCaptureFromPath(screenShot));
              } else if (result.getStatus() == ITestResult.SUCCESS) {
                          test.log(Status.PASS, result.getName());
                          test.pass("Screen Shot : " + test.addScreenCaptureFromPath(screenShot));
              } else if (result.getStatus() == ITestResult.SKIP) {
                          test.skip("Test Case : " + result.getName() + " has been skipped");
              }
              reports.flush();
              driver.close();
  }
  @AfterTest
  public void endTest() {
             
              reports.flush();
  }
    
  
}
