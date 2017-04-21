package sjc.tools.interfacetoeasymock;

import java.util.Set;

public class InterfaceBean {
	private String interfaceName;
	private Set<MethodBean> methods;	
	private String interfaceDetails;
	private boolean Ifalg = true;
	
	public InterfaceBean() {				
	}

	public InterfaceBean(String interfaceName, Set<MethodBean> methods, String interfaceDetails, boolean ifalg) {
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

	public Set<MethodBean> getMethods() {
		return methods;
	}

	public void setMethods(Set<MethodBean> methods) {
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
