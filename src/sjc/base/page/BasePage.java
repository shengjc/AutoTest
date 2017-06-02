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
 * ����ҳ�棬����һЩ������Ԫ�أ�������ҳ�涼��Ҫ�̳иû���ҳ��
 */
public class BasePage {
  public WebDriver dr;

    //��ʱʱ��
    private final int TIMEOUT = 10;    

    //ֻ��ʼ��driver�Ĺ��췽��
	public BasePage(WebDriver dr) {
        this.dr = dr;		//Ϊdr��ֵ
        PageFactory.initElements(new AjaxElementLocatorFactory(dr, TIMEOUT) , this);		//ʹ��selenium��PageFactory��initElements������Ϊdriver���ö�̬���Ԫ��ģʽ��ͬʱ������ajax
        AnnotationProperty ap = AnnotationProperty.getInstance();	//Ϊע�������������࣬���������ʵ��
        ap.setAnnotationClass(this.getClass());        //TestCaseע���ã�������BasePage��ҳ�����������õ�AnnotationProperty�����У��ڶ�Ӧ��ע�������AnnotationParse�л�ȡ��
    }
    
	//��ʼ��driver��������title
	public BasePage(WebDriver dr, final String title) {
        this.dr = dr;
        
        //����driver����Ԫ�صȴ�ʱ��
        WebDriverWait wait = new WebDriverWait(dr,TIMEOUT);
        try{
        	//��TIMEOUTʱ�䷶Χ�ڲ���title������ҵ��������Ϊtrue������Ϊfalse
            boolean flag = wait.until(new ExpectedCondition<Boolean>(){
                @Override
                public Boolean apply(WebDriver arg0) {
                    // TODO Auto-generated method stub
                    String acttitle = arg0.getTitle();
                    return acttitle.equals(title);                    
                }});
        }catch(TimeoutException te) {
            throw new IllegalStateException("��ǰ����Ԥ��ҳ�棬��ǰҳ��title�ǣ�" + dr.getTitle());		//���û���ҵ����׳��쳣
        }
        
        PageFactory.initElements(new AjaxElementLocatorFactory(dr, TIMEOUT) , this);		//ʹ��PageFactory.initElements����Ϊdriver���ö�̬��λԪ��ģʽ
        AnnotationProperty ap = AnnotationProperty.getInstance();		//Ϊע�������������࣬���������ʵ��//
        ap.setAnnotationClass(this.getClass());		//TestCaseע���ã�������BasePage��ҳ�����������õ�AnnotationProperty�����У��ڶ�Ӧ��ע�������AnnotationParse�л�ȡ��
    }
    
    //����ͨ����Ϸ�ʽ������ҳheader��footer�Ĺ�����ܲ���
    public CommonPage getCP() {
    	return new CommonPage();
    }
    
}
