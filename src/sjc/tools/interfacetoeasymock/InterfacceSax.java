package sjc.tools.interfacetoeasymock;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
/**
 * @author shengjc
 * �Զ���xml��������
 */
public class InterfacceSax extends DefaultHandler {
	private InterfaceBean interfaceBean;	//�����洢xml�нӿ���Ϣ��bean
//	private Map<String,InterfaceBean> interfaces;
//	private String interfaceName;
	private MethodBean method;		//����xml�з�����Ϣ��bean
	private Map<String,MethodBean> methods;		//�洢�����������з���
//	private String interfaceDetails;
	private Map<String, ParamBean> params;		//�洢�����������в���
	private ParamBean param;		//����xml�еĲ�����Ϣ��bean
	
//	private final String TAG_Interfaces = "Interfaces";
	//��XML�еı�ǩ��Ӧ
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
	
	private final boolean TAG_OUT = false;	//�Ƿ��ӡ������Ϣ����
	
	private String sb;
	private String currentTap = null;	//��ǰxml��ǩ
	private String preTap = null;	//��һ����ǩ
	private String currentInterfaceName = null;	//��ǰ�ӿ�����
	private String currentMethodName = null;	//��ǰ��������
	private String currentParamName = null;		//��ǰ��������
	private boolean interfaceFlag = true;		//�ӿ��Ƿ����õı��
	private boolean methodFlag = true;			//�����Ƿ����õı��
	private boolean paramFlag = true;			//�����Ƿ����õı��
	
	//int count=0;
	
	

	public InterfacceSax() {
		super();
	}

	//��ʼ��ȡxml�ĵ�
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
//		interfaces = new HashMap<String,InterfaceBean>();
		methods = new HashMap<String,MethodBean>();			//���洢���з�����methods��ʼ��Ϊhashmap��׼���洢����method��Ϣ
	}

	//��ʼ��ȡԪ��
	/*
	 * uri:Ԫ�ر�ʶ��
	 * localName��Ԫ�ؼ������
	 * qName��Ԫ����������
	 * attributes��Ԫ������
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		//(3) ��ʼ�ռ��µı�ǩ������ʱ���������ʷ����
		sb = null;		
		//���Ԫ��������Interface,�򴴽�InterfaceBeanʵ����׼���洢interface��Ϣ
		if(qName.equals(TAG_Interface)) {
			interfaceBean = new InterfaceBean();
		}
		//���Ԫ��������Method,�򴴽�MethodBeanʵ����׼���洢method��Ϣ
		if(qName.equals(TAG_Method)) {
			method = new MethodBean();			
		}
		//���Ԫ��������Param���򴴽�paramBeanʵ����׼���洢param��Ϣ
		if(qName.equals(TAG_Param)) {
			param = new ParamBean();			
		}
		//���Ԫ��������params���򴴽�hashmap��׼���洢����param��Ϣ
		if(qName.equals(TAG_Params)) {
			params = new HashMap<String,ParamBean>();
		}
		currentTap = qName;	//�洢��ǰԪ��
		
		//�����ǰ��������Iflag��Ϊnull��Ϊfalse��������interfaceFlag���ӿ��Ƿ����ñ�ǣ�Ϊfalse�����洢��interfaceBean��
		if(attributes.getValue(TAG_IFlag) != null && attributes.getValue(TAG_IFlag).equals("false")) {
			interfaceFlag = false;
			interfaceBean.setIfalg(interfaceFlag);
		}
		//�����ǰ��������Mflag��Ϊnull��Ϊfalse��������methodFlag�������Ƿ����ñ�ǣ�Ϊfalse�����洢��methodBean��
		if(attributes.getValue(TAG_MFlag) != null && attributes.getValue(TAG_MFlag).equals("false")) {
			methodFlag = false;
			method.setMflag(methodFlag);
		}
		//�����ǰ��������Pflag��Ϊnull��Ϊfalse��������paramFlag�������Ƿ����ñ�ǣ�Ϊfalse�����洢��paramBean��
		if(attributes.getValue(TAG_PFlag) != null && attributes.getValue(TAG_PFlag).equals("false")) {
			paramFlag = false;
			param.setPflag(paramFlag);
		}
		
		
		switch (currentTap) {
		//�洢�ӿ���Ϣ
		case TAG_Interface:
			if(interfaceFlag) {
				interfaceBean.setInterfaceName(attributes.getValue(TAG_InterfaceName));
				currentInterfaceName = attributes.getValue(TAG_InterfaceName);
				if(TAG_OUT)
					System.out.println(interfaceBean.getInterfaceName());
			}				
			break;
		//�洢������Ϣ
		case TAG_Method:
			if(interfaceFlag&&methodFlag) {
				method.setMethodName(attributes.getValue(TAG_MethodName));
				method.setReturnType(attributes.getValue(TAG_ReturnType));
				currentMethodName = attributes.getValue(TAG_MethodName);
				if(TAG_OUT)
					System.out.println(method.getMethodName());
			}				
			break;
		//�洢������Ϣ
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
	
	//��ȡ����ֵ
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		
		//(2)������startElement��endElement�Ĺ����У�ִ���˶��ٴ�characters�� ���Ὣ������ӵ�StringBuilder�У����ᶪʧ����
		sb = new String(ch, start, length);
		if(sb != null) {
			switch (currentTap) {
			//��ȡ����ֵ��Ϣ���洢������bean��
			case TAG_ReturnType:
				method.setReturnType(sb);
				if(TAG_OUT)
					System.out.println(method.getReturnType());
				break;
			//��ȡmethodDetailsֵ���洢��methodBean��
			case TAG_MethodDetails:
				method.setMethodDetails(sb);
				if(TAG_OUT)
					System.out.println(method.getMethodDetails());
				break;
			//��ȡInterfaceDetailsֵ���洢��interfaceBean��
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
	
	//Ԫ�ض�ȡ����
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		
		//(4)ԭ����characters��ȡֵ���ָ��ڴ�ȡֵ
//		String value = sb;
		switch (qName) {
		//���paramԪ�ؽ�������paramʵ���ŵ�param��hashmap�У�������ǰ������������Ϊnull
		case TAG_Param:
			params.put(currentInterfaceName+currentMethodName+currentParamName, param);
			currentParamName = null;			
			break;
		//���methodԪ�ؽ�������methodʵ���ŵ�method��hashmap�У�������ǰ������������Ϊnull
		case TAG_Method:
			methods.put(currentInterfaceName+currentMethodName, method);
			currentMethodName = null;			
			break;
//		case TAG_Interface:
//			interfaces.put(currentInterfaceName+currentMethodName+currentParamName, interfaceBean);
//			currentInterfaceName = null;
		//���paramsԪ�ؽ�������param��hashmap�洢��methodʵ����
		case TAG_Params:
			method.setParams(params);			
			break;
		//���methodsԪ�ؽ�������method��hashmap�洢��interfaceʵ����
		case TAG_Methods:
			interfaceBean.setMethods(methods);			
			break;		
		default:
			break;
		}
		
	}
	
	//xml�ĵ�����
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		
		
	}

	//��ȡ�ӿ��ĵ���ʵ����������mock�ļ�ʱʹ�ã�GenerateMock)
	public InterfaceBean getInterfaceBean() {
//		System.out.println(interfaceBean.getInterfaceName());
		return interfaceBean;
	}	
	
//	public Map<String,InterfaceBean> getInfo(){
//		return interfaces;
//	}
}
