package sjc.base.driverfactory;
/**
 * @author shengjc
 * chrome driver的工厂类
 */
public class ChromeFactory implements DriverFactory {

	@Override
	public Driver getDriver(String path) {
		// 通过构chrome driver的创建类的构造方法返回chromeBrowserDriver的实例
		return new ChromeBrowserDriver(path);
	}

}
