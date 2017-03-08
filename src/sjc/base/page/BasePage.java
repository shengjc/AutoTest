package sjc.base.page;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	public WebDriver dr;	
	

	//��ʱʱ��
    private final int TIMEOUT = 10;    

	public BasePage(WebDriver dr) {
        this.dr = dr;
        PageFactory.initElements(new AjaxElementLocatorFactory(dr, TIMEOUT) , this);
    }
    
	public BasePage(WebDriver dr, final String title) {
        this.dr = dr;
        
        //����������жϣ�
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
            throw new IllegalStateException("��ǰ����Ԥ��ҳ�棬��ǰҳ��title�ǣ�" + dr.getTitle());
        }
        
        PageFactory.initElements(new AjaxElementLocatorFactory(dr, TIMEOUT) , this);
        
    }
    
    //����ͨ����Ϸ�ʽ������ҳheader��footer�Ĺ�����ܲ���
    public CommonPage getCP() {
    	return new CommonPage();
    }
    
}
