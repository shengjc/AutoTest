package sjc.base.driverfactory;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import sjc.base.listener.MyWebDriverListener;

public class ChromeBrowserDriver implements Driver {

	private WebDriver chromedriver = null;
	
	//ʵ���¼�����
	private static EventFiringWebDriver  eventdriver = null;
	
	public ChromeBrowserDriver(String path) {
		init(path);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(String path) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", path);		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));		
		
	}

	@Override
	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		if(eventdriver == null || chromedriver == null) {
			chromedriver = new ChromeDriver();
			eventdriver = new EventFiringWebDriver(chromedriver);
			
			//Ϊeventdriver��Ӽ�����MyWebDriverListener
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
