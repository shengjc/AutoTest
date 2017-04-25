package sjc.tools.interfacetoeasymock;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.cglib.transform.MethodFilterTransformer;

import java.util.Set;

public class Tester {
	
	
	public static void main(String[] args) throws Exception {
		GenerateMock myMock = new GenerateMock("src\\sjc\\tools\\interfacetoeasymock\\", "src\\sjc\\tools\\interfacetoeasymock\\interfaceTemplate.xml");
		myMock.generateInterface();
		myMock.generateEasyMock();
//		InterfaceBean data = SaxService.readXML("src\\sjc\\tools\\interfacetoeasymock\\interfaceTemplate.xml");
//		HashMap<String, MethodBean> mMap = (HashMap<String, MethodBean>) data.getMethods();
//		
//		Iterator<String> iterator_m = mMap.keySet().iterator();
//		while(iterator_m.hasNext()) {
//			String myKey = iterator_m.next();
//			MethodBean method = mMap.get(myKey);
////			System.out.print("返回值类型："+method.getReturnType());
//			System.out.println("返回值类型："+method.getReturnType()+" 方法名："+method.getMethodName());
////			System.out.println("方法说明："+method.getMethodDetails());
//			
//			HashMap<String, ParamBean> pMap = (HashMap<String, ParamBean>) method.getParams();
//			Iterator<String> iterator_p = pMap.keySet().iterator();			
//			while(iterator_p.hasNext()) {
//				String key = iterator_p.next();
//				ParamBean param = pMap.get(key);
//				System.out.println("参数类型："+param.getParamType()+" 参数名"+param.getParamName());
//				
//			}
//			System.out.println();
		}
//		
	}


