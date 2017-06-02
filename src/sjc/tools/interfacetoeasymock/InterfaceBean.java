package sjc.tools.interfacetoeasymock;

import java.util.Map;
import java.util.Set;
/**
 * @author shengjc
 * �洢�ӿ���Ϣ����
 */
public class InterfaceBean {
	private String interfaceName;	//�ӿ����ƣ�xml�е�InterfaceName
	private Map<String,MethodBean> methods;		//���з�����xml�е�Method
	private String interfaceDetails;	//�ӿ�������xml�е�InterfaceDetails
	private boolean Ifalg = true;	//�ӿ��Ƿ����ã�xml�е�Iflag
	
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
