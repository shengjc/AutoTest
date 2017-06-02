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
 * chrome��driver������
 */
public class ChromeBrowserDriver implements Driver {

	private WebDriver chromedriver = null;
	
	//ʵ���¼�����
	private static EventFiringWebDriver  eventdriver = null;
	
	public ChromeBrowserDriver(String path) {
		init(path);
		// ����init��������chrome������·��
	}

	@Override
	public void init(String path) {
		// ����chrome������·��
		System.setProperty("webdriver.chrome.driver", path);		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();	//���������������Ԥ������
		capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));		//���������ʱ���	
		
	}

	@Override
	public WebDriver getDriver() {
		// ���driverΪ�վʹ���driver
		if(eventdriver == null || chromedriver == null) {
			chromedriver = new ChromeDriver();
			eventdriver = new EventFiringWebDriver(chromedriver);	//��chromedriverת��ΪEventFiringWebDriver
			
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
