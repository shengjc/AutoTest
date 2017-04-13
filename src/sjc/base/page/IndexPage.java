package sjc.base.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import annotation.TestCase;


public class IndexPage extends BasePage {	
	
	
	
	public IndexPage(WebDriver dr) {
		super(dr);		
		// TODO Auto-generated constructor stub
	}

	@FindBy(id="kw")
	@CacheLookup 
	private static WebElement keyword;
	
	@FindBy(id="su")
	private static WebElement searchbt;
	
	@FindBy(linkText="µ«¬º")
	private static WebElement loginbt;
	
	public IndexPage(WebDriver driver,String str) {
		super(driver,str);
	}
	
	
	@TestCase(caseId="22",SuitId="1",description="≤‚ ‘”√¿˝1")
	public void search(String content) {
		keyword.sendKeys(content);
		searchbt.click();
	}
	
}
