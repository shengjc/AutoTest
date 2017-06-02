package sjc.base.listener;

import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;
/**
 * @author shengjc
 * testNG报告监听类，可以自定义报告，通过实现IReporter接口
 */
public class CustomReporter implements IReporter {

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		// testNG中Suite的遍历
		for (ISuite suite : suites) {
            //得到suit的名字
            String suiteName = suite.getName();
		    //得到suite的运行结果
		    Map<String, ISuiteResult> suiteResults = suite.getResults();
		    //遍历suite的结果
		    for (ISuiteResult sr : suiteResults.values()) {
		    	//将结果的内容赋给ITestContext实例
		        ITestContext tc = sr.getTestContext();
		        System.out.println("Passed tests for suite '" + suiteName +
		             "' is:" + tc.getPassedTests().getAllResults().size());
		        System.out.println("Failed tests for suite '" + suiteName +
		             "' is:" + 
		             tc.getFailedTests().getAllResults().size());
		        System.out.println("Skipped tests for suite '" + suiteName +
		             "' is:" + 
		             tc.getSkippedTests().getAllResults().size());
		    }
        }
	}

}
