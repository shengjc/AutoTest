package sjc.base.driverfactory;

import org.openqa.selenium.WebDriver;
/**
 * @author shengjc
 * �ӿڣ���ȡselenium driver
 */
public interface Driver {	
	public void init(String path);		//��ʼ��driver
	public WebDriver getDriver();		//�õ�driverʵ��
	public void closeDriver();		//�ر�driver
}
