package sjc.tools.interfacetoeasymock;

import java.util.Map;
import java.util.Set;
/**
 * @author shengjc
 * �洢������Ϣ����
 */
public class MethodBean {
	private String methodName;	//�������ƣ�xml�е�MethodName
	private String returnType;	//����ֵ���ͣ�xml�е�ReturnType
	private Map<String,ParamBean> params;	//������Ҫ�Ĳ�����xml�е�Param
	private String methodDetails;	//����������xml�е�MethodDetails
	private boolean Mflag = true;	//�����Ƿ����ã�xml�е�Mflag
	
	public MethodBean() {		
	}

	public MethodBean(String methodName, String returnType, Map<String,ParamBean> params, String methodDetails,
			boolean mflag) {
		super();
		this.methodName = methodName;
		this.returnType = returnType;
		this.params = params;
		this.methodDetails = methodDetails;
		Mflag = mflag;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}


	public Map<String, ParamBean> getParams() {
		return params;
	}

	public void setParams(Map<String, ParamBean> params) {
		this.params = params;
	}

	public String getMethodDetails() {
		return methodDetails;
	}

	public void setMethodDetails(String methodDetails) {
		this.methodDetails = methodDetails;
	}

	public boolean isMflag() {
		return Mflag;
	}

	public void setMflag(boolean mflag) {
		Mflag = mflag;
	}
	
	
}
