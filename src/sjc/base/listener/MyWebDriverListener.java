package sjc.base.listener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import sjc.common.Log;

public class MyWebDriverListener implements WebDriverEventListener {

	private Log log = new Log(MyWebDriverListener.class);
	
	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		log.debug("afterNavigateTo: "+url);
		log.debug("afterNavigateTo by driver: "+driver.getCurrentUrl());
		System.out.println("afterNavigateTo: "+url);
        System.out.println("afterNavigateTo by driver: "+driver.getCurrentUrl());

	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		log.debug("查找元素的条件是: "+ by.toString());
		System.out.println("查找元素的条件是: "+ by.toString());

	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		System.out.println("单机页面元素的属性: "+element.getAttribute("value"));

	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String dateString = format.format(new Date());

        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        try{
            File screenShot = new File("D:\\"+dateString+".png");
            FileUtils.copyFile(srcFile, screenShot);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        log.error("发生异常,原因是: "+throwable.getMessage());
        //后期可以替换为log4j
        System.out.println("发生异常,原因是: "+throwable.getMessage());
        
        log.error("截图保存在: "+"D:\\"+dateString+".png");
        //后期保存路径读取properties配置文件
        System.out.println("截图保存在: "+"D:\\"+dateString+".png");
	}

}
