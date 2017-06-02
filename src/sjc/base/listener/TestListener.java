package sjc.base.listener;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import sjc.common.Assertion;

/**
 * @author shengjc
 * testNG测试监听类，通过接口TestListenerAdapter实现
 */
public class TestListener extends TestListenerAdapter {
	//测试开始执行
	@Override
	public void onTestStart(ITestResult result) {
		Assertion.flag = true;
		Assertion.errors.clear();
	}
	
	//测试执行失败
	@Override
	public void onTestFailure(ITestResult tr) {
		this.handleAssertion(tr);
	}
	
	//测试被跳过
	@Override
	public void onTestSkipped(ITestResult tr) {
		this.handleAssertion(tr);
	}
	
	//测试执行成功
	@Override
	public void onTestSuccess(ITestResult tr) {
		this.handleAssertion(tr);
	}   
	
	private int index = 0;		//
	private void handleAssertion(ITestResult tr) {
		//如果断言失败则获取测试结果的异常
		if(!Assertion.flag){
            Throwable throwable = tr.getThrowable();           
            if(throwable==null){
                throwable = new Throwable();
            }
            StackTraceElement[] traces = throwable.getStackTrace();		//获得异常方法的调用栈
            StackTraceElement[] alltrace = new StackTraceElement[0];    //获取栈中的第一个数据       
            for (Error e : Assertion.errors) {
                StackTraceElement[] errorTraces = e.getStackTrace();
                StackTraceElement[] et = this.getKeyStackTrace(tr, errorTraces);
                StackTraceElement[] message = new StackTraceElement[]{new StackTraceElement("message : "+e.getMessage()+" in method : ", tr.getMethod().getMethodName(), tr.getTestClass().getRealClass().getSimpleName(), index)};
                index = 0;
                alltrace = this.merge(alltrace, message);
                alltrace = this.merge(alltrace, et);
            }
            if(traces!=null){
                traces = this.getKeyStackTrace(tr, traces);
                alltrace = this.merge(alltrace, traces);
            }           
            throwable.setStackTrace(alltrace);
            tr.setThrowable(throwable);
            Assertion.flag = true;   
            Assertion.errors.clear();
            tr.setStatus(ITestResult.FAILURE);
		}		
	}
	
    private StackTraceElement[] getKeyStackTrace(ITestResult tr, StackTraceElement[] stackTraceElements){
	        List<StackTraceElement> ets = new ArrayList<StackTraceElement>();
	        for (StackTraceElement stackTraceElement : stackTraceElements) {           
	            if(stackTraceElement.getClassName().equals(tr.getTestClass().getName())){               
	                ets.add(stackTraceElement);
	                index = stackTraceElement.getLineNumber();
	            }
	        }
	        StackTraceElement[] et = new StackTraceElement[ets.size()];
	        for (int i = 0; i < et.length; i++) {
	            et[i] = ets.get(i);
	        }
	        return et;
	}
	     
    private StackTraceElement[] merge(StackTraceElement[] traces1, StackTraceElement[] traces2){
        StackTraceElement[] ste = new StackTraceElement[traces1.length+traces2.length];
        for (int i = 0; i < traces1.length; i++) {
            ste[i] = traces1[i];
        }
        for (int i = 0; i < traces2.length; i++) {
            ste[traces1.length+i] = traces2[i];
        }
        return ste;
    }
}
