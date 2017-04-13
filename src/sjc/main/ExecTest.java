package sjc.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import annotation.AnnotationParse;
import sjc.base.driverfactory.ChromeFactory;
import sjc.base.driverfactory.DriverFactory;
import sjc.base.driverfactory.FirefoxFactory;
import sjc.base.listener.*;
import sjc.base.page.IndexPage;


@Listeners({TestListener.class,sjc.base.listener.CustomReporter.class})
public class ExecTest {	
	
//	@DataProvider(name = "search")
//    public static Object[][] primeNumbers() {
//        return new Object[][] {{ "123"}};
//    }
	
	WebDriver wd = null;
	@Test(testName = "baidu",dataProviderClass = TestDataProvide.class,dataProvider = "search")
//	@Test(testName = "baidu",dataProvider = "search")
	public void login(String content) {
		
		getDriver("chrome");
		wd.get("https://www.baidu.com/");
		IndexPage indexpage = new IndexPage(wd,"百度一下，你就知道");		
		indexpage.search(content);		
		
	}
	
	private void getDriver(String type) {
		
		DriverFactory df = null;
		switch(type) {
		case "chrome":
			df = new ChromeFactory();
			this.wd = df.getDriver("./other/chromedriver.exe").getDriver();
			break;
		case "firefox":
			df = new FirefoxFactory();
			this.wd = df.getDriver("D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe").getDriver();
			break;
		case "ie":
			break;
		case "safari":
			break;
		default:
			break;
		}		
	}
	
	@AfterTest
	public void end() {		
		wd.close();
		AnnotationParse.TestCaseParse();
	}
	
}
