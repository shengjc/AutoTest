package sjc.base.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class IndexPage extends BasePage {	
	
	@FindBy(id="kw")
	@CacheLookup 
	private static WebElement keyword;
	
	@FindBy(id="su")
	private static WebElement searchbt;
	
	@FindBy(linkText="µÇÂ¼")
	private static WebElement loginbt;
	
	public IndexPage(WebDriver driver,String str) {
		super(driver,str);
	}
	
	public void search(String content) {
		keyword.sendKeys(content);
		searchbt.click();
	}
	
}
