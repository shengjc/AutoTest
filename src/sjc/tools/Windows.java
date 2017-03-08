package sjc.tools;

import org.openqa.selenium.WebDriver;

public class Windows {
	public void closeOtherWindows(WebDriver driver) {

        for(String windows : driver.getWindowHandles())

             if(!windows.equals("")) {

                   driver.switchTo().window(windows);

                   driver.close();

             }

        driver.switchTo().window("");

	}
}
