package sjc.base.driverfactory;

import org.openqa.selenium.WebDriver;

public interface Driver {	
	public void init(String path);
	public WebDriver getDriver();
	public void closeDriver();
}
