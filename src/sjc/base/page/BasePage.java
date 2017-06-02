package sjc.base.page;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import annotation.AnnotationParse;
import annotation.AnnotationProperty;
/**
 * @author shengjc
 * 基础页面，包括一些公共的元素，所有子页面都需要继承该基础页面
 */
public class BasePage {
  public WebDriver dr;

    //超时时间
    private final int TIMEOUT = 10;    

    //只初始化driver的构造方法
	public BasePage(WebDriver dr) {
        this.dr = dr;		//为dr赋值
        PageFactory.initElements(new AjaxElementLocatorFactory(dr, TIMEOUT) , this);		//使用selenium的PageFactory的initElements方法，为driver设置动态检测元素模式，同时适用于ajax
        AnnotationProperty ap = AnnotationProperty.getInstance();	//为注解解析器服务的类，创建该类的实例
        ap.setAnnotationClass(this.getClass());        //TestCase注解用，将调用BasePage的页面对象的类设置到AnnotationProperty对象中，在对应的注解解析类AnnotationParse中获取用
    }
    
	//初始化driver并检查标题title
	public BasePage(WebDriver dr, final String title) {
        this.dr = dr;
        
        //设置driver查找元素等待时间
        WebDriverWait wait = new WebDriverWait(dr,TIMEOUT);
        try{
        	//在TIMEOUT时间范围内查找title，如果找到并相等则为true，否则为false
            boolean flag = wait.until(new ExpectedCondition<Boolean>(){
                @Override
                public Boolean apply(WebDriver arg0) {
                    // TODO Auto-generated method stub
                    String acttitle = arg0.getTitle();
                    return acttitle.equals(title);                    
                }});
        }catch(TimeoutException te) {
            throw new IllegalStateException("当前不是预期页面，当前页面title是：" + dr.getTitle());		//如果没有找到则抛出异常
        }
        
        PageFactory.initElements(new AjaxElementLocatorFactory(dr, TIMEOUT) , this);		//使用PageFactory.initElements方法为driver设置动态定位元素模式
        AnnotationProperty ap = AnnotationProperty.getInstance();		//为注解解析器服务的类，创建该类的实例//
        ap.setAnnotationClass(this.getClass());		//TestCase注解用，将调用BasePage的页面对象的类设置到AnnotationProperty对象中，在对应的注解解析类AnnotationParse中获取用
    }
    
    //可以通过组合方式加入网页header和footer的公共框架部分
    public CommonPage getCP() {
    	return new CommonPage();
    }
    
}
