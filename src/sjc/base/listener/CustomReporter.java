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
 * testNG��������࣬�����Զ��屨�棬ͨ��ʵ��IReporter�ӿ�
 */
public class CustomReporter implements IReporter {

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		// testNG��Suite�ı���
		for (ISuite suite : suites) {
            //�õ�suit������
            String suiteName = suite.getName();
		    //�õ�suite�����н��
		    Map<String, ISuiteResult> suiteResults = suite.getResults();
		    //����suite�Ľ��
		    for (ISuiteResult sr : suiteResults.values()) {
		    	//����������ݸ���ITestContextʵ��
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
