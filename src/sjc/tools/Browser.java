package sjc.tools;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Browser {
	private WebDriver driver = null;
	public final static boolean DEBUG = true;//������
	//����ͼƬsaveToFile����ʹ��
	private static int BUFFER_SIZE = 8096;
	
	public Browser(WebDriver wd) {
		this.driver = wd;
	}
	
	//ɾ������cookie
	public void deleteCookies() {
		this.driver.manage().deleteAllCookies();
	}
	
	//���cookie
	public void addCookie(Cookie cookie) {
		this.driver.manage().addCookie(cookie);
	}
	
	//��ȡ����cookie
	public String getCookies(){
		Set<Cookie> cookiesSet = driver.manage().getCookies();
		String cookies = null;
		for (Cookie c : cookiesSet) {
			cookies += c.getName() + "=" + c.getValue() + ";";
		}
		return cookies;		
	}
	
	//��ȡָ��cookie
	public String getCookiesByName(String cookieName) {
		Set<Cookie> cookiesSet = driver.manage().getCookies();
		String Stringcookies = null;
		for (Cookie c : cookiesSet) {
	       if (c.getName().equals(cookieName)){
	              Stringcookies += c.getName() + "=" + c.getValue() + ";";
	       }
		}
		return Stringcookies;		
	}
	
	//����Ԫ�ض�λ
	
	//�ж�Ԫ���Ƿ���ʾ
	public boolean isDisplay(WebElement element) {		
		return element.isDisplayed();		
	}
	
	//��ȡԪ������
	public String getAttribute(WebElement element,String name) {		
		return element.getAttribute(name);		
	}
	
	//������ѡ��
	/*
	 * 
	 * WebElement element_province = driver.findElement(By.id(��province��));
	Ȼ�󽫶�λ����element����select
	
	Select province = new Select(element_province);
	
	select.selectByVisibleText(��A��);

	select.selectByValue(��1��); 
	
	select.deselectAll();
	
	select.deselectByValue(��1��);
	
	select.deselectByVisibleText(��A��);
	
	select.getAllSelectedOptions();
	
	select.getFirstSelectedOption();
	 */
	 
	//��ѡ��
	/*
	 * radio.click();��������   //ѡ��ĳ����ѡ��

	radio.clear();��������  //���ĳ����ѡ��
	
	radio.isSelected();����//�ж�ĳ����ѡ���Ƿ��Ѿ���ѡ��
	 */
	
	//��ѡ��
	/*
	 * checkbox.click();

	checkbox.clear();
	
	checkbox.isSelected();  //�ж϶�ѡ���Ƿ�ѡ��
	
	checkbox.isEnabled();  //�ж϶�ѡ���Ƿ����
	 */
	
	//��ť
	/*
	 * btn.click();���������� //�����ť

	btn.isEnabled ();����//�жϰ�ť�Ƿ����
	 */
	
	//�Ի���
	/*
	 * Alert  a= driver.switchTo().alert();
	 * alert.accept();����//���ȷ��

	alert.dismiss();�� //���ȡ��
	
	alert.getText();��//��ȡAlert�Ի�����ı�
	 */
	
	//��
	/*
	 * approve.click();   

	approve.submit();//ֻ�ʺ��ڱ����ύ
	 */
	
	//Windows �� Frames֮����л�
	/*
	 * driver.switchTo().defaultContent();����������//���ص�����frame/iframe

	driver.switchTo().frame("leftFrame");��������//�л���ĳ��frame��
	
	driver.switchTo().window("windowName");��//�л���ĳ��window 
	
	��Ҫע����ǣ�����һ��Frame1�л�����һ��Frame2��ʱ�򣬲���ֱ�ӵ����л�driver.switchTo().frame("Frame2");����������������Ҫ��Frame1���ص���㣬���л���Frame2���磺
	
	driver.switchTo().frame("Frame1");��
	
	driver.switchTo().defaultContent();
	
	driver.switchTo().frame("Frame2");
	 */
	
	//����Java Script
	/*
	 * JavascriptExecutor js =(JavascriptExecutor) driver;

	js.executeScript("JS�ű�");
	
	������ָ��Ԫ��
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", element); 
	������ָ���߶�
	((JavascriptExecutor)driver).executeScript("document.documentElement.scrollTop=" + height);  
	������ҳ�涥��
	((JavascriptExecutor)driver).executeScript("var q=document.documentElement.scrollTop=0");   
	�������Ԫ��
	((JavascriptExecutor)driver).executeScript("arguments[0].click();",element);   
	 */
	
	//iFrame����
	/*
	 * selenium webdriver���ṩ�˽���һ��iframe�ķ�����

	WebDriver org.openqa.selenium.WebDriver.TargetLocator.frame(String nameOrId)
	Ҳ�ṩ��һ������default content�ķ�����
	
	WebDriver org.openqa.selenium.WebDriver.TargetLocator.defaultContent()
	driver.switchTo().frame("id");//�������iframe��id
	������һ��iFrame��
	
	driver.switchTo().defualContent();
	 */
	
	/**
     * ��HTTP��Դ���Ϊ�ļ�,����Ŀ��url��ͼƬ�ĵ�ַ���Լ�����ļ�������Ҫͨ��Ԫ�ض�λ���ͼƬurl���ȶ�λ��ͼƬԪ�أ�Ȼ��ͨ��imgSrc.getAttribute("src")��ȡurl
     *
     * @param destUrl String
     * @param fileName String
     * @throws Exception
     */
    public static void saveToFile(String destUrl, String fileName) {
      FileOutputStream fos = null;
      BufferedInputStream bis = null;
      HttpURLConnection httpUrl = null;
      URL url = null;
      byte[] buf = new byte[BUFFER_SIZE];
      int size = 0;

      //��������
      try {
          url = new URL(destUrl);
          httpUrl = (HttpURLConnection) url.openConnection();
          //����ָ������Դ
          httpUrl.connect();
          //��ȡ����������
          bis = new BufferedInputStream(httpUrl.getInputStream());
          //�����ļ�
          fos = new FileOutputStream(fileName);

          if (DEBUG) 
              System.out.println("���ڻ�ȡ����[" + destUrl + "]������..\n���䱣��Ϊ�ļ�[" + fileName + "]");

          //�����ļ�
          while ( (size = bis.read(buf)) != -1) 
            fos.write(buf, 0, size);

      } catch (MalformedURLException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      } finally {
          try {
              if(fos!=null)fos.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
          try {
              if(bis!=null)bis.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
          httpUrl.disconnect();
      }
    }
}
