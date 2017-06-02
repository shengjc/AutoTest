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
 * 使用解析接口描述文档xml后的结果，生成EasyMock的java文件
 */
public class GenerateMock implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private String packagePath = null;	//包路径
	private InterfaceBean data;		//解析后的xml信息
	private String MyDAO = "myDAO";
	private ArrayList<String> methodArray = null;	//所有方法
	private ArrayList<String> mehtodReturnType = null;	//所有返回值类型
	//"src\\sjc\\tools\\interfacetoeasymock\\", "src\\sjc\\tools\\interfacetoeasymock\\interfaceTemplate.xml"
	//构造方法，设置包路径以及xml存放位置，并使用SaxService的readXML方法开始解析XML
	public GenerateMock(String packagePath,String XMLPath) {
		super();
		this.packagePath = packagePath;
		data = SaxService.readXML(XMLPath);	
	}

	//生成接口文件
	public void generateInterface() throws Exception
    {
		methodArray = new ArrayList<String>();	//初始化methodArray为ArrayList
		mehtodReturnType = new ArrayList<String>();		//初始化mehtodReturnType为ArrayList
		StringBuilder sb = new StringBuilder(1024);		//存储要生成的java文件内容
		
		HashMap<String, MethodBean> mMap = (HashMap<String, MethodBean>) data.getMethods();		//获得解析后的所有方法	
		Iterator<String> iterator_m = mMap.keySet().iterator();		//得到迭代器
		
		sb.append("package packagePath;\n\n");
        sb.append("public interface "+data.getInterfaceName() + "\n");
        sb.append("{\n");
        
        //开始遍历所有方法
		while(iterator_m.hasNext()) {
			
			String myKey = iterator_m.next();	//得到key
			MethodBean method = mMap.get(myKey);	//通过key得到method信息
			mehtodReturnType.add(method.getReturnType());	//将返回值添加到mehtodReturnType数组
			methodArray.add(method.getMethodName());			//将方法名添加到methodArray数组
			sb.append("\tpublic "+method.getReturnType()+" "+method.getMethodName()+"(");	//"\tpublic $Proxy1(InvocationHandler h)\n"
			
			HashMap<String, ParamBean> pMap = (HashMap<String, ParamBean>) method.getParams();	//获取所有参数信息
			Iterator<String> iterator_p = pMap.keySet().iterator();			//迭代器
			//开始遍历当前方法的所有参数
			while(iterator_p.hasNext()) {
				String key = iterator_p.next();
				ParamBean param = pMap.get(key);
//				System.out.println("参数类型："+param.getParamType()+" 参数名"+param.getParamName());
				sb.append(param.getParamType()+" "+param.getParamName());
				if(iterator_p.hasNext())
					sb.append(",");				
			}		
			sb.append(");"+"\n");
	        
//			System.out.println("返回值类型："+method.getReturnType()+" 方法名："+method.getMethodName());			
			
		}      
        
        sb.append("}");
        
        /** 生成一段Java代码 */
        String fileDir = System.getProperty("user.dir");
        String fileName = fileDir +"\\"+ packagePath+data.getInterfaceName()+".java";
        File javaFile = new File(fileName);
        Writer writer = new FileWriter(javaFile);
        writer.write(sb.toString());
        writer.close();
        
        /** 动态编译这段Java代码,生成.class文件 */
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager sjfm = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> iter = sjfm.getJavaFileObjects(fileName);
        CompilationTask ct = compiler.getTask(null, sjfm, null, null, null, iter);
        ct.call();
        sjfm.close();
        
        /** 将生成的.class文件载入内存，默认的ClassLoader只能载入CLASSPATH下的.class文件 */
//        URL[] urls = new URL[] {(new URL("file:\\" + System.getProperty("user.dir") + "\\src"))};
//        URLClassLoader ul = new URLClassLoader(urls);
//        Class<?> c = Class.forName("MyProxy.$Proxy1", false, ul);
        
        /** 利用反射将c实例化出来 */
//        Constructor<?> constructor = c.getConstructor(InvocationHandler.class);
//        Object obj = constructor.newInstance(h);
        
        /** 使用完毕删除生成的代理.java文件和.class文件，这样就看不到动态生成的内容了 */
//        File classFile = new File(fileDir + "\\src\\MyProxy\\$Proxy1.class");
//        javaFile.delete();
//        classFile.delete();
    }
	
	//生成EasyMock文件
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
		
		
		/** 生成Java代码 */
        String fileDir = System.getProperty("user.dir");
        String fileName = fileDir + "\\"+packagePath+data.getInterfaceName()+"Mock.java";
        File javaFile = new File(fileName);
        Writer writer = new FileWriter(javaFile);
        writer.write(sb.toString());
        writer.close();
	}
}
