package sjc.tools;

import org.openqa.selenium.WebDriver;
/**
 * @author shengjc
 * ѡ��windows�����һЩ����
 */
public class Windows {
	public void closeOtherWindows(WebDriver driver) {

        for(String windows : driver.getWindowHandles())

             if(!windows.equals("�����ھ��")) {

                   driver.switchTo().window(windows);

                   driver.close();

             }

        driver.switchTo().window("�����ھ��");

	}
}
