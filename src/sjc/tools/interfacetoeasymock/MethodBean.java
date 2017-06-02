package sjc.tools.interfacetoeasymock;

import java.util.Map;
import java.util.Set;
/**
 * @author shengjc
 * 存储方法信息的类
 */
public class MethodBean {
	private String methodName;	//方法名称，xml中的MethodName
	private String returnType;	//返回值类型，xml中的ReturnType
	private Map<String,ParamBean> params;	//方法需要的参数，xml中的Param
	private String methodDetails;	//方法描述，xml中的MethodDetails
	private boolean Mflag = true;	//方法是否启用，xml中的Mflag
	
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
