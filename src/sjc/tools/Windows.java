package sjc.tools;

import org.openqa.selenium.WebDriver;
/**
 * @author shengjc
 * 选择windows句柄的一些方法
 */
public class Windows {
	public void closeOtherWindows(WebDriver driver) {

        for(String windows : driver.getWindowHandles())

             if(!windows.equals("主窗口句柄")) {

                   driver.switchTo().window(windows);

                   driver.close();

             }

        driver.switchTo().window("主窗口句柄");

	}
}
