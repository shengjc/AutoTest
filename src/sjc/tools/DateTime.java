package sjc.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author shengjc
 * 时间格式工具
 */
public class DateTime {
	public String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	    String curenttime = format.format(new Date());
	    return curenttime;
	}
}
