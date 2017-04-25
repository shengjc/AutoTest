package sjc.tools.interfacetoeasymock;

import java.util.Map;
import java.util.Set;

public class InterfaceBean {
	private String interfaceName;
	private Map<String,MethodBean> methods;	
	private String interfaceDetails;
	private boolean Ifalg = true;
	
	public InterfaceBean() {				
	}

	public InterfaceBean(String interfaceName, Map<String,MethodBean> methods, String interfaceDetails, boolean ifalg) {
		super();
		this.interfaceName = interfaceName;
		this.methods = methods;
		this.interfaceDetails = interfaceDetails;
		Ifalg = ifalg;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public Map<String,MethodBean> getMethods() {
		return methods;
	}

	public void setMethods(Map<String,MethodBean> methods) {
		this.methods = methods;
	}

	public String getInterfaceDetails() {
		return interfaceDetails;
	}

	public void setInterfaceDetails(String interfaceDetails) {
		this.interfaceDetails = interfaceDetails;
	}

	public boolean isIfalg() {
		return Ifalg;
	}

	public void setIfalg(boolean ifalg) {
		Ifalg = ifalg;
	}
	
}
