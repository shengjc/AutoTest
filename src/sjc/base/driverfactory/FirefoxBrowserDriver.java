package sjc.base.driverfactory;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import sjc.base.listener.MyWebDriverListener;

public class FirefoxBrowserDriver implements Driver {

	private WebDriver ffdriver = null;
	
	//实现事件监听
	private static EventFiringWebDriver  eventdriver = null;
	public FirefoxBrowserDriver(String path) {
		// TODO Auto-generated constructor stub
		init(path);
	}

	@Override
	public void init(String path) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.firefox.bin", path);		
	}

	@Override
	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		if(ffdriver == null || eventdriver == null) {
			ffdriver = new FirefoxDriver();
			eventdriver = new EventFiringWebDriver(ffdriver);
			
			//为eventdriver添加监听类MyWebDriverListener
			eventdriver.register(new MyWebDriverListener());
		}
		return eventdriver;
	}

	@Override
	public void closeDriver() {
		// TODO Auto-generated method stub
		eventdriver.close();
	}
	
}
