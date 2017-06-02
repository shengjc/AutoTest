package sjc.base.driverfactory;
/**
 * @author shengjc
 * 使用简单工厂模式创建driver
 */
public interface DriverFactory {
	public Driver getDriver(String path);
}
