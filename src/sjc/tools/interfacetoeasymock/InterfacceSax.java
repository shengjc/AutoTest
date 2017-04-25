package sjc.tools.interfacetoeasymock;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class InterfacceSax extends DefaultHandler {
	private InterfaceBean interfaceBean;
//	private Map<String,InterfaceBean> interfaces;
//	private String interfaceName;
	private MethodBean method;
	private Map<String,MethodBean> methods;
//	private String interfaceDetails;
	private Map<String, ParamBean> params;
	private ParamBean param;
	
//	private final String TAG_Interfaces = "Interfaces";
	private final String TAG_Interface = "Interface";
	private final String TAG_IFlag = "Iflag";
	private final String TAG_InterfaceName = "InterfaceName";
	private final String TAG_Methods = "Methods";
	private final String TAG_Method = "Method";
	private final String TAG_MFlag = "Mflag";
	private final String TAG_MethodName = "MethodName";
	private final String TAG_ReturnType = "ReturnType";
	private final String TAG_MethodDetails = "MethodDetails";
	private final String TAG_InterfaceDetails = "InterfaceDetails";
	private final String TAG_Params = "Params";
	private final String TAG_Param = "Param";
	private final String TAG_PFlag = "Pflag";
	private final String TAG_ParamName = "ParamName";
	private final String TAG_ParamType = "ParamType";
	
	private final boolean TAG_OUT = false;
	
	private String sb;
	private String currentTap = null;
	private String preTap = null;
	private String currentInterfaceName = null;
	private String currentMethodName = null;
	private String currentParamName = null;
	private boolean interfaceFlag = true;
	private boolean methodFlag = true;
	private boolean paramFlag = true;
	
	int count=0;
	
	

	public InterfacceSax() {
		super();
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
//		interfaces = new HashMap<String,InterfaceBean>();
		methods = new HashMap<String,MethodBean>();		
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		//(3) 开始收集新的标签的数据时，先清空历史数据
		sb = null;		
		if(qName.equals(TAG_Interface)) {
			interfaceBean = new InterfaceBean();
		}
		if(qName.equals(TAG_Method)) {
			method = new MethodBean();			
		}
		if(qName.equals(TAG_Param)) {
			param = new ParamBean();			
		}
		if(qName.equals(TAG_Params)) {
			params = new HashMap<String,ParamBean>();
		}
		currentTap = qName;
		
		if(attributes.getValue(TAG_IFlag) != null && attributes.getValue(TAG_IFlag).equals("false")) {
			interfaceFlag = false;
			interfaceBean.setIfalg(interfaceFlag);
		}
		if(attributes.getValue(TAG_MFlag) != null && attributes.getValue(TAG_MFlag).equals("false")) {
			methodFlag = false;
			method.setMflag(methodFlag);
		}
		if(attributes.getValue(TAG_PFlag) != null && attributes.getValue(TAG_PFlag).equals("false")) {
			paramFlag = false;
			param.setPflag(paramFlag);
		}
		
		
			switch (currentTap) {
			case TAG_Interface:
				if(interfaceFlag) {
					interfaceBean.setInterfaceName(attributes.getValue(TAG_InterfaceName));
					currentInterfaceName = attributes.getValue(TAG_InterfaceName);
					if(TAG_OUT)
						System.out.println(interfaceBean.getInterfaceName());
				}				
				break;
			case TAG_Method:
				if(interfaceFlag&&methodFlag) {
					method.setMethodName(attributes.getValue(TAG_MethodName));
					method.setReturnType(attributes.getValue(TAG_ReturnType));
					currentMethodName = attributes.getValue(TAG_MethodName);
					if(TAG_OUT)
						System.out.println(method.getMethodName());
				}				
				break;				
			case TAG_Param:
				if(interfaceFlag&&methodFlag&&paramFlag) {	
					
					if(attributes.getQName(0).equals(TAG_ParamType)) {
						param.setParamType(attributes.getValue(TAG_ParamType));
						if(TAG_OUT)
							System.out.println(param.getParamType());
					}
					if(attributes.getQName(1).equals(TAG_ParamName)) {
						param.setParamName(attributes.getValue(TAG_ParamName));
						currentParamName = attributes.getValue(TAG_ParamName);
						if(TAG_OUT)
							System.out.println(param.getParamName());
					}
				}				
				break;
			default:
				break;
			}
		
		preTap = qName;
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		
		//(2)不管在startElement到endElement的过程中，执行了多少次characters， 都会将内容添加到StringBuilder中，不会丢失内容
		sb = new String(ch, start, length);
		if(sb != null) {
			switch (currentTap) {
			case TAG_ReturnType:
				method.setReturnType(sb);
				if(TAG_OUT)
					System.out.println(method.getReturnType());
				break;
			case TAG_MethodDetails:
				method.setMethodDetails(sb);
				if(TAG_OUT)
					System.out.println(method.getMethodDetails());
				break;
			case TAG_InterfaceDetails:
				interfaceBean.setInterfaceDetails(sb);
				if(TAG_OUT)
					System.out.println(interfaceBean.getInterfaceDetails());
				break;
			default:
				break;
			}
		}
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		
		//(4)原来在characters中取值，现改在此取值
//		String value = sb;
		switch (qName) {
		case TAG_Param:
			params.put(currentInterfaceName+currentMethodName+currentParamName, param);
			currentParamName = null;			
			break;
		case TAG_Method:
			methods.put(currentInterfaceName+currentMethodName, method);
			currentMethodName = null;			
			break;
//		case TAG_Interface:
//			interfaces.put(currentInterfaceName+currentMethodName+currentParamName, interfaceBean);
//			currentInterfaceName = null;
		case TAG_Params:
			method.setParams(params);			
			break;
		case TAG_Methods:
			interfaceBean.setMethods(methods);			
			break;		
		default:
			break;
		}
		
	}
	
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		
		
	}

	public InterfaceBean getInterfaceBean() {
//		System.out.println(interfaceBean.getInterfaceName());
		return interfaceBean;
	}	
	
//	public Map<String,InterfaceBean> getInfo(){
//		return interfaces;
//	}
}
