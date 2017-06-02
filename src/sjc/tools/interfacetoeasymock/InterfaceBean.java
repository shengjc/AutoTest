package sjc.tools.interfacetoeasymock;

import java.util.Map;
import java.util.Set;
/**
 * @author shengjc
 * 存储接口信息的类
 */
public class InterfaceBean {
	private String interfaceName;	//接口名称，xml中的InterfaceName
	private Map<String,MethodBean> methods;		//所有方法，xml中的Method
	private String interfaceDetails;	//接口描述，xml中的InterfaceDetails
	private boolean Ifalg = true;	//接口是否启用，xml中的Iflag
	
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
