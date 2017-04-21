package sjc.tools.interfacetoeasymock;

import java.util.Set;

public class MethodBean {
	private String methodName;
	private String returnType;
	private Set<ParamBean> params;
	private String methodDetails;
	private boolean Mflag = true;
	
	public MethodBean() {		
	}

	public MethodBean(String methodName, String returnType, Set<ParamBean> params, String methodDetails,
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

	public Set<ParamBean> getParams() {
		return params;
	}

	public void setParams(Set<ParamBean> params) {
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
