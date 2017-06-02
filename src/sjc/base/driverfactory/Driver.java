package sjc.base.driverfactory;

import org.openqa.selenium.WebDriver;
/**
 * @author shengjc
 * 接口，获取selenium driver
 */
public interface Driver {	
	public void init(String path);		//初始化driver
	public WebDriver getDriver();		//得到driver实例
	public void closeDriver();		//关闭driver
}
