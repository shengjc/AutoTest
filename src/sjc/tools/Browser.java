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
	public final static boolean DEBUG = true;//调试用
	//下载图片saveToFile方法使用
	private static int BUFFER_SIZE = 8096;
	
	public Browser(WebDriver wd) {
		this.driver = wd;
	}
	
	//删除所有cookie
	public void deleteCookies() {
		this.driver.manage().deleteAllCookies();
	}
	
	//添加cookie
	public void addCookie(Cookie cookie) {
		this.driver.manage().addCookie(cookie);
	}
	
	//获取所有cookie
	public String getCookies(){
		Set<Cookie> cookiesSet = driver.manage().getCookies();
		String cookies = null;
		for (Cookie c : cookiesSet) {
			cookies += c.getName() + "=" + c.getValue() + ";";
		}
		return cookies;		
	}
	
	//获取指定cookie
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
	
	//八种元素定位
	
	//判断元素是否显示
	public boolean isDisplay(WebElement element) {		
		return element.isDisplayed();		
	}
	
	//获取元素属性
	public String getAttribute(WebElement element,String name) {		
		return element.getAttribute(name);		
	}
	
	//下拉框选项
	/*
	 * 
	 * WebElement element_province = driver.findElement(By.id(“province”));
	然后将定位到的element传入select
	
	Select province = new Select(element_province);
	
	select.selectByVisibleText(“A”);

	select.selectByValue(“1”); 
	
	select.deselectAll();
	
	select.deselectByValue(“1”);
	
	select.deselectByVisibleText(“A”);
	
	select.getAllSelectedOptions();
	
	select.getFirstSelectedOption();
	 */
	 
	//单选框
	/*
	 * radio.click();　　　　   //选择某个单选项

	radio.clear();　　　　  //清空某个单选项
	
	radio.isSelected();　　//判断某个单选项是否已经被选择
	 */
	
	//复选框
	/*
	 * checkbox.click();

	checkbox.clear();
	
	checkbox.isSelected();  //判断多选框是否被选择
	
	checkbox.isEnabled();  //判断多选框是否可用
	 */
	
	//按钮
	/*
	 * btn.click();　　　　　 //点击按钮

	btn.isEnabled ();　　//判断按钮是否可用
	 */
	
	//对话框
	/*
	 * Alert  a= driver.switchTo().alert();
	 * alert.accept();　　//点击确定

	alert.dismiss();　 //点击取消
	
	alert.getText();　//获取Alert对话框的文本
	 */
	
	//表单
	/*
	 * approve.click();   

	approve.submit();//只适合于表单的提交
	 */
	
	//Windows 和 Frames之间的切换
	/*
	 * driver.switchTo().defaultContent();　　　　　//返回到最顶层的frame/iframe

	driver.switchTo().frame("leftFrame");　　　　//切换到某个frame：
	
	driver.switchTo().window("windowName");　//切换到某个window 
	
	需要注意的是，当从一个Frame1切换到另一个Frame2的时候，不能直接调用切换driver.switchTo().frame("Frame2");这里会出问题我们需要从Frame1返回到最顶层，再切换到Frame2，如：
	
	driver.switchTo().frame("Frame1");　
	
	driver.switchTo().defaultContent();
	
	driver.switchTo().frame("Frame2");
	 */
	
	//调用Java Script
	/*
	 * JavascriptExecutor js =(JavascriptExecutor) driver;

	js.executeScript("JS脚本");
	
	滚动到指定元素
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", element); 
	滚动到指定高度
	((JavascriptExecutor)driver).executeScript("document.documentElement.scrollTop=" + height);  
	滚动到页面顶部
	((JavascriptExecutor)driver).executeScript("var q=document.documentElement.scrollTop=0");   
	点击隐藏元素
	((JavascriptExecutor)driver).executeScript("arguments[0].click();",element);   
	 */
	
	//iFrame处理
	/*
	 * selenium webdriver中提供了进入一个iframe的方法：

	WebDriver org.openqa.selenium.WebDriver.TargetLocator.frame(String nameOrId)
	也提供了一个返回default content的方法：
	
	WebDriver org.openqa.selenium.WebDriver.TargetLocator.defaultContent()
	driver.switchTo().frame("id");//传入的是iframe的id
	返回上一个iFrame：
	
	driver.switchTo().defualContent();
	 */
	
	/**
     * 将HTTP资源另存为文件,传入目标url即图片的地址，以及另存文件名，需要通过元素定位获得图片url，先定位到图片元素，然后通过imgSrc.getAttribute("src")获取url
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

      //建立链接
      try {
          url = new URL(destUrl);
          httpUrl = (HttpURLConnection) url.openConnection();
          //连接指定的资源
          httpUrl.connect();
          //获取网络输入流
          bis = new BufferedInputStream(httpUrl.getInputStream());
          //建立文件
          fos = new FileOutputStream(fileName);

          if (DEBUG) 
              System.out.println("正在获取链接[" + destUrl + "]的内容..\n将其保存为文件[" + fileName + "]");

          //保存文件
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
