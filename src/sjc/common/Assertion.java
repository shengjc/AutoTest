package sjc.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import sjc.tools.DateTime;

public class Assertion {
	public static boolean flag = true;
	public static List<Error> errors = new ArrayList<Error>();	
	private static DateTime dt = new DateTime();
	private static Log log = new Log();
	
	public static void verifyEquals(Object actual,Object expected) {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Error e) {
			log.error("断言失败，实际值：" + actual.toString() + "期望值：" + expected.toString());
			errors.add(e);
			flag = false;
			
		}
	}
	
	public static void verifyEquals(Object actual,Object expected,String message ) {
		try {
			Assert.assertEquals(actual, expected,message);
		} catch (Error e) {
			log.error("断言失败，实际值：" + actual.toString() + "期望值：" + expected.toString());
			errors.add(e);
			flag = false;
		}
	}
	
	public static void verifyEquals(Object actual,Object expected,boolean result ) {
		try {
			if(result) {
				verifyEquals(actual,expected);
			}
			else {
				Assert.assertNotSame(actual, expected);
			}			
		} catch (Error e) {
			log.error("断言失败，实际值：" + actual.toString() + "期望值：" + expected.toString());
			errors.add(e);			
			flag = false;						
		}
	}
	
	//带截图功能的构造方法
	public static void verifyEquals(Object actual,Object expected,WebDriver driver) {
		try {
			Assert.assertEquals(actual, expected);
			snapshot((TakesScreenshot)driver,dt.getCurrentTime());
		} catch (Error e) {
			log.error("断言失败，实际值：" + actual.toString() + "期望值：" + expected.toString());
			errors.add(e);
			flag = false;
			snapshot((TakesScreenshot)driver,dt.getCurrentTime());
		}
	}
	
	//带截图功能的构造方法
	public static void verifyEquals(Object actual,Object expected,String message,WebDriver driver ) {
		try {
			Assert.assertEquals(actual, expected,message);
			snapshot((TakesScreenshot)driver,dt.getCurrentTime());
		} catch (Error e) {
			log.error("断言失败，实际值：" + actual.toString() + "期望值：" + expected.toString());
			errors.add(e);
			flag = false;
			snapshot((TakesScreenshot)driver,dt.getCurrentTime());
		}
	}
	
	//带截图功能的构造方法
	public static void verifyEquals(Object actual,Object expected,boolean result,WebDriver driver ) {
		try {
			if(result) {
				verifyEquals(actual,expected,driver);
				snapshot((TakesScreenshot)driver,dt.getCurrentTime());
			}
			else {
				Assert.assertNotSame(actual, expected);
				snapshot((TakesScreenshot)driver,dt.getCurrentTime());
			}			
		} catch (Error e) {
			log.error("断言失败，实际值：" + actual.toString() + "期望值：" + expected.toString());
			errors.add(e);			
			flag = false;
			snapshot((TakesScreenshot)driver,dt.getCurrentTime());
		}
	}
	
	//截图功能
	public static void snapshot(TakesScreenshot drivername, String filename)
	  {		
	      // this method will take screen shot ,require two parameters ,one is driver name, another is file name
	      
	    String currentPath = System.getProperty("user.dir"); //get current work folder
	    System.out.println(currentPath);
	    File scrFile = drivername.getScreenshotAs(OutputType.FILE);
	        // Now you can do whatever you need to do with it, for example copy somewhere
	        try {
	            System.out.println("save snapshot path is:"+currentPath+"/"+filename);
	            FileUtils.copyFile(scrFile, new File(currentPath+"\\"+filename));
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            System.out.println("Can't save screenshot");
	            e.printStackTrace();
	        } 
	        finally
	        {
	           
	            System.out.println("screen shot finished");
	        }
	  }
}
