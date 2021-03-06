package sjc.base.driverfactory;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import sjc.base.listener.MyWebDriverListener;
/**
 * @author shengjc
 * FireFox driver创建
 */
public class FirefoxBrowserDriver implements Driver {

	private WebDriver ffdriver = null;
	
	//实现事件监听,EventFiringWebDriver可以实现对driver的一些操作的监听，设置监听类后可以实现进行动作后的一些自定义操作
	private static EventFiringWebDriver  eventdriver = null;
	//构造方法，调用init方法初始化浏览器路径
	public FirefoxBrowserDriver(String path) {
		// TODO Auto-generated constructor stub
		init(path);
	}

	@Override
	public void init(String path) {
		// 设置启动浏览器路径
		System.setProperty("webdriver.firefox.bin", path);		
	}

	@Override
	public WebDriver getDriver() {
		// 如果没有创建driver就创建firefox的driver
		if(ffdriver == null || eventdriver == null) {
			ffdriver = new FirefoxDriver();
			eventdriver = new EventFiringWebDriver(ffdriver);	//将创建的firefox driver转换为EventFiringWebDriver
			
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
