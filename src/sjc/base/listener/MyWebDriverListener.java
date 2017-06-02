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
/**
 * @author shengjc
 * sjc.base.driverfactory.chromeBrowserDriver/FirefoxBrowserDriver 中WebDriverEventDriver的监听类，通过实现WebDriverEventListener接口
 */
public class MyWebDriverListener implements WebDriverEventListener {

	//为该监听类使用log4j功能
	private Log log = new Log(MyWebDriverListener.class);
	
	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		// 跳转页面前
		
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		// 跳转页面后
		log.debug("afterNavigateTo: "+url);
		log.debug("afterNavigateTo by driver: "+driver.getCurrentUrl());
		System.out.println("afterNavigateTo: "+url);
        System.out.println("afterNavigateTo by driver: "+driver.getCurrentUrl());

	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		// 回退页面前

	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		// 回退页面后

	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		// 使用浏览器前进按钮前

	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		// 使用浏览器前进按钮后

	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		// 使用浏览器刷新按钮前

	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		// 使用浏览器刷新按钮后

	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		// 定位元素前
		log.debug("查找元素的条件是: "+ by.toString());
		System.out.println("查找元素的条件是: "+ by.toString());

	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// 定位元素后
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		// 点击元素前
		System.out.println("单机页面元素的属性: "+element.getAttribute("value"));

	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		// 点击元素后

	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		// 改变元素值前

	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		// 改变元素值之后

	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		// 执行脚本前

	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		// 执行脚本后

	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		// 出现异常时
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
