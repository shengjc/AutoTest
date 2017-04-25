package sjc.tools.interfacetoeasymock;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class SaxService {
	public static InterfaceBean readXML(String uri){
		SAXParserFactory parserFactory=SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = parserFactory.newSAXParser();
			InterfacceSax myhandler=new InterfacceSax();			
			parser.parse(uri, myhandler);
			return myhandler.getInterfaceBean();
			 
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
				
	}
}
