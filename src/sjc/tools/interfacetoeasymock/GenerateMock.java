package sjc.tools.interfacetoeasymock;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import org.easymock.EasyMock;
import org.junit.Assert;

/**
 * @author shengjc
 * ʹ�ý����ӿ������ĵ�xml��Ľ��������EasyMock��java�ļ�
 */
public class GenerateMock implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private String packagePath = null;	//��·��
	private InterfaceBean data;		//�������xml��Ϣ
	private String MyDAO = "myDAO";
	private ArrayList<String> methodArray = null;	//���з���
	private ArrayList<String> mehtodReturnType = null;	//���з���ֵ����
	//"src\\sjc\\tools\\interfacetoeasymock\\", "src\\sjc\\tools\\interfacetoeasymock\\interfaceTemplate.xml"
	//���췽�������ð�·���Լ�xml���λ�ã���ʹ��SaxService��readXML������ʼ����XML
	public GenerateMock(String packagePath,String XMLPath) {
		super();
		this.packagePath = packagePath;
		data = SaxService.readXML(XMLPath);	
	}

	//���ɽӿ��ļ�
	public void generateInterface() throws Exception
    {
		methodArray = new ArrayList<String>();	//��ʼ��methodArrayΪArrayList
		mehtodReturnType = new ArrayList<String>();		//��ʼ��mehtodReturnTypeΪArrayList
		StringBuilder sb = new StringBuilder(1024);		//�洢Ҫ���ɵ�java�ļ�����
		
		HashMap<String, MethodBean> mMap = (HashMap<String, MethodBean>) data.getMethods();		//��ý���������з���	
		Iterator<String> iterator_m = mMap.keySet().iterator();		//�õ�������
		
		sb.append("package packagePath;\n\n");
        sb.append("public interface "+data.getInterfaceName() + "\n");
        sb.append("{\n");
        
        //��ʼ�������з���
		while(iterator_m.hasNext()) {
			
			String myKey = iterator_m.next();	//�õ�key
			MethodBean method = mMap.get(myKey);	//ͨ��key�õ�method��Ϣ
			mehtodReturnType.add(method.getReturnType());	//������ֵ��ӵ�mehtodReturnType����
			methodArray.add(method.getMethodName());			//����������ӵ�methodArray����
			sb.append("\tpublic "+method.getReturnType()+" "+method.getMethodName()+"(");	//"\tpublic $Proxy1(InvocationHandler h)\n"
			
			HashMap<String, ParamBean> pMap = (HashMap<String, ParamBean>) method.getParams();	//��ȡ���в�����Ϣ
			Iterator<String> iterator_p = pMap.keySet().iterator();			//������
			//��ʼ������ǰ���������в���
			while(iterator_p.hasNext()) {
				String key = iterator_p.next();
				ParamBean param = pMap.get(key);
//				System.out.println("�������ͣ�"+param.getParamType()+" ������"+param.getParamName());
				sb.append(param.getParamType()+" "+param.getParamName());
				if(iterator_p.hasNext())
					sb.append(",");				
			}		
			sb.append(");"+"\n");
	        
//			System.out.println("����ֵ���ͣ�"+method.getReturnType()+" ��������"+method.getMethodName());			
			
		}      
        
        sb.append("}");
        
        /** ����һ��Java���� */
        String fileDir = System.getProperty("user.dir");
        String fileName = fileDir +"\\"+ packagePath+data.getInterfaceName()+".java";
        File javaFile = new File(fileName);
        Writer writer = new FileWriter(javaFile);
        writer.write(sb.toString());
        writer.close();
        
        /** ��̬�������Java����,����.class�ļ� */
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager sjfm = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> iter = sjfm.getJavaFileObjects(fileName);
        CompilationTask ct = compiler.getTask(null, sjfm, null, null, null, iter);
        ct.call();
        sjfm.close();
        
        /** �����ɵ�.class�ļ������ڴ棬Ĭ�ϵ�ClassLoaderֻ������CLASSPATH�µ�.class�ļ� */
//        URL[] urls = new URL[] {(new URL("file:\\" + System.getProperty("user.dir") + "\\src"))};
//        URLClassLoader ul = new URLClassLoader(urls);
//        Class<?> c = Class.forName("MyProxy.$Proxy1", false, ul);
        
        /** ���÷��佫cʵ�������� */
//        Constructor<?> constructor = c.getConstructor(InvocationHandler.class);
//        Object obj = constructor.newInstance(h);
        
        /** ʹ�����ɾ�����ɵĴ���.java�ļ���.class�ļ��������Ϳ�������̬���ɵ������� */
//        File classFile = new File(fileDir + "\\src\\MyProxy\\$Proxy1.class");
//        javaFile.delete();
//        classFile.delete();
    }
	
	//����EasyMock�ļ�
	public void generateEasyMock() throws IOException {
		StringBuilder sb = new StringBuilder(1024);
		sb.append("package packagePath;\n\n");
		sb.append("import org.easymock.EasyMock;\n");
		sb.append("import org.junit.Assert;\n\n");
		sb.append("public class "+data.getInterfaceName()+"MockTest throws Exception" + "\n");
		sb.append("{\n\t");
		sb.append(MyDAO+" mock = EasyMock.createMock(" +MyDAO+".class);"+"\n\t");		
		for(String str : methodArray) {
			sb.append("EasyMock.expect(mock.");
			sb.append(" "+str+"()");
			sb.append(").andReturn();"+"\n\t");
		}		
		sb.append("EasyMock.replay(mock);"+"\n\t");
		sb.append("Assert.assertEquals();"+"\n\t");
		sb.append("EasyMock.verify(mock);"+"\n\t");
		sb.append("}"+"\n\t");		
		
		
		/** ����Java���� */
        String fileDir = System.getProperty("user.dir");
        String fileName = fileDir + "\\"+packagePath+data.getInterfaceName()+"Mock.java";
        File javaFile = new File(fileName);
        Writer writer = new FileWriter(javaFile);
        writer.write(sb.toString());
        writer.close();
	}
}
