package sjc.base.driverfactory;
/**
 * @author shengjc
 * firefoxdriver�Ĺ�����
 */
public class FirefoxFactory implements DriverFactory {

	@Override
	public Driver getDriver(String path) {
		// TODO Auto-generated method stub
		return new FirefoxBrowserDriver(path);
	}

}
