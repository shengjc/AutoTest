package sjc.common;

import org.apache.log4j.Logger;
import org.testng.Reporter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author shengjc
 * log4jµÄ·â×°
 */
public class Log {
	
	private Logger logger;
    
	private DateFormat df;
    
    public Log(Class<?> clazz){
        logger=Logger.getLogger(clazz);
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    
    public Log(String s){
        logger=Logger.getLogger(s);
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    
    public Log(){
        logger = Logger.getLogger("");
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    
    public void info(Object message){
        logger.info(message);
        this.testngLogOutput(message);
    }
    
    public void error(Object message) {        
        logger.error(message);
        this.testngLogOutput(message);
    }

    public void warn(Object message) {        
        logger.warn(message);
        this.testngLogOutput(message);
    }
    
    public void debug(Object message) {        
        logger.debug(message);
        this.testngLogOutput(message);
    }
    
    private void testngLogOutput(Object message){
        Reporter.log(df.format(new Date())+":"+message);
    }
}