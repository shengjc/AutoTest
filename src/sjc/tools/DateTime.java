package sjc.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author shengjc
 * ʱ���ʽ����
 */
public class DateTime {
	public String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	    String curenttime = format.format(new Date());
	    return curenttime;
	}
}
