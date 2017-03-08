package sjc.base.page;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	public WebDriver dr;	
	

	//超时时间
    private final int TIMEOUT = 10;    

	public BasePage(WebDriver dr) {
        this.dr = dr;
        PageFactory.initElements(new AjaxElementLocatorFactory(dr, TIMEOUT) , this);
    }
    
	public BasePage(WebDriver dr, final String title) {
        this.dr = dr;
        
        //如果不进行判断，
        WebDriverWait wait = new WebDriverWait(dr,TIMEOUT);
        try{
            boolean flag = wait.until(new ExpectedCondition<Boolean>(){
                @Override
                public Boolean apply(WebDriver arg0) {
                    // TODO Auto-generated method stub
                    String acttitle = arg0.getTitle();
                    return acttitle.equals(title);                    
                }});
        }catch(TimeoutException te) {
            throw new IllegalStateException("当前不是预期页面，当前页面title是：" + dr.getTitle());
        }
        
        PageFactory.initElements(new AjaxElementLocatorFactory(dr, TIMEOUT) , this);
        
    }
    
    //可以通过组合方式加入网页header和footer的公共框架部分
    public CommonPage getCP() {
    	return new CommonPage();
    }
    
}
