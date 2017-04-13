package sjc.main;

import org.openqa.selenium.WebDriver;

import sjc.base.driverfactory.ChromeFactory;
import sjc.base.driverfactory.DriverFactory;
import sjc.base.driverfactory.FirefoxFactory;
import sjc.base.page.IndexPage;

public class Main {
	static WebDriver wd = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		getDriver("chrome");
		wd.get("https://www.baidu.com/");
		IndexPage indexpage = new IndexPage(wd,"百度一下，你就知道");
		indexpage.search("123");
	}
	
	private static void getDriver(String type) {
			
		DriverFactory df = null;
		switch(type) {
		case "chrome":
			df = new ChromeFactory();
			wd = df.getDriver("\\src\\chromedriver.exe").getDriver();
			break;
		case "firefox":
			df = new FirefoxFactory();
			wd = df.getDriver("D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe").getDriver();
			break;
		case "ie":
			break;
		case "safari":
			break;
		default:
			break;
		}		
	}

}
