package sjc.tools.interfacetoeasymock;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Tester {

	public static void main(String[] args) {
		SaxService.readXML("src\\sjc\\tools\\interfacetoeasymock\\interfaceTemplate.xml");		
		InterfacceSax myhandler = new InterfacceSax();
		HashMap<String,InterfaceBean> data = (HashMap<String,InterfaceBean>)myhandler.getInfo();
		data.get("interfance");
		System.out.println(data.get("interfance").toString());
		
//		Iterator iiIterator = data.entrySet().iterator();
//		
//		while(iiIterator.hasNext()) {
//			Map.Entry entry = (Map.Entry) iiIterator.next();
//			System.out.println(entry.getValue());
//		}
	}

}
