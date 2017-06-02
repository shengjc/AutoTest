package sjc.tools.interfacetoeasymock;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
/**
 * @author shengjc
 * 自定义xml解析规则
 */
public class InterfacceSax extends DefaultHandler {
	private InterfaceBean interfaceBean;	//创建存储xml中接口信息的bean
//	private Map<String,InterfaceBean> interfaces;
//	private String interfaceName;
	private MethodBean method;		//储存xml中方法信息的bean
	private Map<String,MethodBean> methods;		//存储解析出的所有方法
//	private String interfaceDetails;
	private Map<String, ParamBean> params;		//存储解析出的所有参数
	private ParamBean param;		//储存xml中的参数信息的bean
	
//	private final String TAG_Interfaces = "Interfaces";
	//与XML中的标签对应
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
	
	private final boolean TAG_OUT = false;	//是否打印调试信息开关
	
	private String sb;
	private String currentTap = null;	//当前xml标签
	private String preTap = null;	//上一个标签
	private String currentInterfaceName = null;	//当前接口名称
	private String currentMethodName = null;	//当前方法名称
	private String currentParamName = null;		//当前参数名称
	private boolean interfaceFlag = true;		//接口是否启用的标记
	private boolean methodFlag = true;			//方法是否启用的标记
	private boolean paramFlag = true;			//参数是否启用的标记
	
	//int count=0;
	
	

	public InterfacceSax() {
		super();
	}

	//开始读取xml文档
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
//		interfaces = new HashMap<String,InterfaceBean>();
		methods = new HashMap<String,MethodBean>();			//将存储所有方法的methods初始化为hashmap，准备存储所有method信息
	}

	//开始读取元素
	/*
	 * uri:元素标识符
	 * localName：元素简称名称
	 * qName：元素完整名称
	 * attributes：元素属性
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		//(3) 开始收集新的标签的数据时，先清空历史数据
		sb = null;		
		//如果元素名称是Interface,则创建InterfaceBean实例，准备存储interface信息
		if(qName.equals(TAG_Interface)) {
			interfaceBean = new InterfaceBean();
		}
		//如果元素名称是Method,则创建MethodBean实例，准备存储method信息
		if(qName.equals(TAG_Method)) {
			method = new MethodBean();			
		}
		//如果元素名称是Param，则创建paramBean实例，准备存储param信息
		if(qName.equals(TAG_Param)) {
			param = new ParamBean();			
		}
		//如果元素名称是params，则创建hashmap，准备存储所有param信息
		if(qName.equals(TAG_Params)) {
			params = new HashMap<String,ParamBean>();
		}
		currentTap = qName;	//存储当前元素
		
		//如果当前酸雨属性Iflag不为null且为false，则设置interfaceFlag（接口是否启用标记）为false，并存储到interfaceBean中
		if(attributes.getValue(TAG_IFlag) != null && attributes.getValue(TAG_IFlag).equals("false")) {
			interfaceFlag = false;
			interfaceBean.setIfalg(interfaceFlag);
		}
		//如果当前酸雨属性Mflag不为null且为false，则设置methodFlag（方法是否启用标记）为false，并存储到methodBean中
		if(attributes.getValue(TAG_MFlag) != null && attributes.getValue(TAG_MFlag).equals("false")) {
			methodFlag = false;
			method.setMflag(methodFlag);
		}
		//如果当前酸雨属性Pflag不为null且为false，则设置paramFlag（参数是否启用标记）为false，并存储到paramBean中
		if(attributes.getValue(TAG_PFlag) != null && attributes.getValue(TAG_PFlag).equals("false")) {
			paramFlag = false;
			param.setPflag(paramFlag);
		}
		
		
		switch (currentTap) {
		//存储接口信息
		case TAG_Interface:
			if(interfaceFlag) {
				interfaceBean.setInterfaceName(attributes.getValue(TAG_InterfaceName));
				currentInterfaceName = attributes.getValue(TAG_InterfaceName);
				if(TAG_OUT)
					System.out.println(interfaceBean.getInterfaceName());
			}				
			break;
		//存储方法信息
		case TAG_Method:
			if(interfaceFlag&&methodFlag) {
				method.setMethodName(attributes.getValue(TAG_MethodName));
				method.setReturnType(attributes.getValue(TAG_ReturnType));
				currentMethodName = attributes.getValue(TAG_MethodName);
				if(TAG_OUT)
					System.out.println(method.getMethodName());
			}				
			break;
		//存储参数信息
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
	
	//读取属性值
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		
		//(2)不管在startElement到endElement的过程中，执行了多少次characters， 都会将内容添加到StringBuilder中，不会丢失内容
		sb = new String(ch, start, length);
		if(sb != null) {
			switch (currentTap) {
			//读取返回值信息并存储到方法bean中
			case TAG_ReturnType:
				method.setReturnType(sb);
				if(TAG_OUT)
					System.out.println(method.getReturnType());
				break;
			//读取methodDetails值并存储到methodBean中
			case TAG_MethodDetails:
				method.setMethodDetails(sb);
				if(TAG_OUT)
					System.out.println(method.getMethodDetails());
				break;
			//读取InterfaceDetails值并存储到interfaceBean中
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
	
	//元素读取结束
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		
		//(4)原来在characters中取值，现改在此取值
//		String value = sb;
		switch (qName) {
		//如果param元素结束，则将param实例放到param的hashmap中，并将当前参数名称设置为null
		case TAG_Param:
			params.put(currentInterfaceName+currentMethodName+currentParamName, param);
			currentParamName = null;			
			break;
		//如果method元素结束，则将method实例放到method的hashmap中，并将当前方法名称设置为null
		case TAG_Method:
			methods.put(currentInterfaceName+currentMethodName, method);
			currentMethodName = null;			
			break;
//		case TAG_Interface:
//			interfaces.put(currentInterfaceName+currentMethodName+currentParamName, interfaceBean);
//			currentInterfaceName = null;
		//如果params元素结束，则将param的hashmap存储到method实例中
		case TAG_Params:
			method.setParams(params);			
			break;
		//如果methods元素结束，则将method的hashmap存储到interface实例中
		case TAG_Methods:
			interfaceBean.setMethods(methods);			
			break;		
		default:
			break;
		}
		
	}
	
	//xml文档结束
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		
		
	}

	//获取接口文档的实例，在生成mock文件时使用（GenerateMock)
	public InterfaceBean getInterfaceBean() {
//		System.out.println(interfaceBean.getInterfaceName());
		return interfaceBean;
	}	
	
//	public Map<String,InterfaceBean> getInfo(){
//		return interfaces;
//	}
}
