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
 * FireFox driver����
 */
public class FirefoxBrowserDriver implements Driver {

	private WebDriver ffdriver = null;
	
	//ʵ���¼�����,EventFiringWebDriver����ʵ�ֶ�driver��һЩ�����ļ��������ü���������ʵ�ֽ��ж������һЩ�Զ������
	private static EventFiringWebDriver  eventdriver = null;
	//���췽��������init������ʼ�������·��
	public FirefoxBrowserDriver(String path) {
		// TODO Auto-generated constructor stub
		init(path);
	}

	@Override
	public void init(String path) {
		// �������������·��
		System.setProperty("webdriver.firefox.bin", path);		
	}

	@Override
	public WebDriver getDriver() {
		// ���û�д���driver�ʹ���firefox��driver
		if(ffdriver == null || eventdriver == null) {
			ffdriver = new FirefoxDriver();
			eventdriver = new EventFiringWebDriver(ffdriver);	//��������firefox driverת��ΪEventFiringWebDriver
			
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
