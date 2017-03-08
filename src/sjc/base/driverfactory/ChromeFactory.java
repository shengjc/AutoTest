package sjc.base.driverfactory;

public class ChromeFactory implements DriverFactory {

	@Override
	public Driver getDriver(String path) {
		// TODO Auto-generated method stub
		return new ChromeBrowserDriver(path);
	}

}
