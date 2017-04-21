package sjc.tools.interfacetoeasymock;

public class ParamBean {
	private String paramType;
	private String paramName;
	private boolean Pflag = true;
	
	public ParamBean() {
		super();
	}

	public ParamBean(String paramType, String paramName, boolean pflag) {
		super();
		this.paramType = paramType;
		this.paramName = paramName;
		Pflag = pflag;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public boolean isPflag() {
		return Pflag;
	}

	public void setPflag(boolean pflag) {
		Pflag = pflag;
	}

	
}
