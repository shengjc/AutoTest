package sjc.tools;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScript {
	//��inputִ������,ֱ������value���ԣ� �˷�����ҪӦ��������Զ���ȫ�Լ�readonly���Ե�element��sendkeys���ȶ�
	private String INPUT = "arguments[0].value=";
	
	//ȥ��input��ֻ������
	private String REMOVEREADONLY ="arguments[0].removeAttribute(\\\"+\"readonly\"+\\\")" ;
	
	//�Ը��ı���Ĳ���,��ҪӦ�Ը��ı��򣬿��Է�װ��ȡ���ı������ݺ����ø��ı�·�����ݵķ���
	private String SETRICHEDIT = "arguments[0].innerHTML = ";
	
	//��ȡ���ı�������
	private String GETRICHEDIT = "var result=arguments[0].innerHTML;return result";
	
	//������ָ��λ��
	private String SCROLL = "arguments[0].scrollIntoViewIfNeeded(true);";
	
	//��ȡ��ƵURL
	private String GETVIDEOURL = "return arguments[0].currentSrc;";
	
	//������Ƶ
	private String PLAYVIDEO = "return arguments[0].play()";
	
	//��ͣ��Ƶ
	private String PAUSEVIDEO = "arguments[0].pause()";
	
	//��inputִ������
	public void setInputValue(WebElement e,String value,WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value=\""+value+"\"", e);
	}
	
	//ȥ��input��ֻ������
	public void removeReadOnly(WebElement e,WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		   js.executeScript("arguments[0].removeAttribute(\""+"readonly\""+")", e);
	}
	
	//���ø��ı�������
	public void setRichEdit(WebElement e,WebDriver driver,String text){
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].innerHTML = \"" + text + "\"", e);
	}
	
	//��ȡ���ı�������
	public String getRichEdit(WebElement e,WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        String result=(String) js.executeScript("var result=arguments[0].innerHTML;return result", e);
        return result;
	}
	
	//������ָ��λ��
	public void scrollToElement(WebElement e,WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        // roll down and keep the element to the center of browser
        js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", e);
	}	
	
}
