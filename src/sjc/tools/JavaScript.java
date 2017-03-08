package sjc.tools;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScript {
	//对input执行输入,直接设置value属性， 此方法主要应对输入框自动补全以及readonly属性的element，sendkeys不稳定
	private String INPUT = "arguments[0].value=";
	
	//去掉input的只读属性
	private String REMOVEREADONLY ="arguments[0].removeAttribute(\\\"+\"readonly\"+\\\")" ;
	
	//对富文本框的操作,主要应对富文本框，可以封装获取富文本框内容和设置富文本路况内容的方法
	private String SETRICHEDIT = "arguments[0].innerHTML = ";
	
	//获取富文本框内容
	private String GETRICHEDIT = "var result=arguments[0].innerHTML;return result";
	
	//滚动到指定位置
	private String SCROLL = "arguments[0].scrollIntoViewIfNeeded(true);";
	
	//获取视频URL
	private String GETVIDEOURL = "return arguments[0].currentSrc;";
	
	//播放视频
	private String PLAYVIDEO = "return arguments[0].play()";
	
	//暂停视频
	private String PAUSEVIDEO = "arguments[0].pause()";
	
	//对input执行输入
	public void setInputValue(WebElement e,String value,WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value=\""+value+"\"", e);
	}
	
	//去掉input的只读属性
	public void removeReadOnly(WebElement e,WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		   js.executeScript("arguments[0].removeAttribute(\""+"readonly\""+")", e);
	}
	
	//设置富文本框内容
	public void setRichEdit(WebElement e,WebDriver driver,String text){
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].innerHTML = \"" + text + "\"", e);
	}
	
	//获取富文本框内容
	public String getRichEdit(WebElement e,WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        String result=(String) js.executeScript("var result=arguments[0].innerHTML;return result", e);
        return result;
	}
	
	//滚动到指定位置
	public void scrollToElement(WebElement e,WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        // roll down and keep the element to the center of browser
        js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", e);
	}	
	
}
