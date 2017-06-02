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
 * sjc.base.driverfactory.chromeBrowserDriver/FirefoxBrowserDriver ��WebDriverEventDriver�ļ����࣬ͨ��ʵ��WebDriverEventListener�ӿ�
 */
public class MyWebDriverListener implements WebDriverEventListener {

	//Ϊ�ü�����ʹ��log4j����
	private Log log = new Log(MyWebDriverListener.class);
	
	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		// ��תҳ��ǰ
		
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		// ��תҳ���
		log.debug("afterNavigateTo: "+url);
		log.debug("afterNavigateTo by driver: "+driver.getCurrentUrl());
		System.out.println("afterNavigateTo: "+url);
        System.out.println("afterNavigateTo by driver: "+driver.getCurrentUrl());

	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		// ����ҳ��ǰ

	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		// ����ҳ���

	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		// ʹ�������ǰ����ťǰ

	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		// ʹ�������ǰ����ť��

	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		// ʹ�������ˢ�°�ťǰ

	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		// ʹ�������ˢ�°�ť��

	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		// ��λԪ��ǰ
		log.debug("����Ԫ�ص�������: "+ by.toString());
		System.out.println("����Ԫ�ص�������: "+ by.toString());

	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// ��λԪ�غ�
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		// ���Ԫ��ǰ
		System.out.println("����ҳ��Ԫ�ص�����: "+element.getAttribute("value"));

	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		// ���Ԫ�غ�

	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		// �ı�Ԫ��ֵǰ

	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		// �ı�Ԫ��ֵ֮��

	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		// ִ�нű�ǰ

	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		// ִ�нű���

	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		// �����쳣ʱ
		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String dateString = format.format(new Date());

        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        try{
            File screenShot = new File("D:\\"+dateString+".png");
            FileUtils.copyFile(srcFile, screenShot);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        log.error("�����쳣,ԭ����: "+throwable.getMessage());
        //���ڿ����滻Ϊlog4j
        System.out.println("�����쳣,ԭ����: "+throwable.getMessage());
        
        log.error("��ͼ������: "+"D:\\"+dateString+".png");
        //���ڱ���·����ȡproperties�����ļ�
        System.out.println("��ͼ������: "+"D:\\"+dateString+".png");
	}

}
