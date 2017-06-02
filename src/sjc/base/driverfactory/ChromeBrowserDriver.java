package sjc.base.driverfactory;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import sjc.base.listener.MyWebDriverListener;
/**
 * @author shengjc
 * chrome的driver创建类
 */
public class ChromeBrowserDriver implements Driver {

	private WebDriver chromedriver = null;
	
	//实现事件监听
	private static EventFiringWebDriver  eventdriver = null;
	
	public ChromeBrowserDriver(String path) {
		init(path);
		// 调用init方法设置chrome的启动路径
	}

	@Override
	public void init(String path) {
		// 设置chrome的启动路径
		System.setProperty("webdriver.chrome.driver", path);		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();	//用于设置浏览器的预期能力
		capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));		//启动浏览器时最大化	
		
	}

	@Override
	public WebDriver getDriver() {
		// 如果driver为空就创建driver
		if(eventdriver == null || chromedriver == null) {
			chromedriver = new ChromeDriver();
			eventdriver = new EventFiringWebDriver(chromedriver);	//将chromedriver转换为EventFiringWebDriver
			
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
