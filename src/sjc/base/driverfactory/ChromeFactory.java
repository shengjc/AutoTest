package sjc.base.driverfactory;
/**
 * @author shengjc
 * chrome driver�Ĺ�����
 */
public class ChromeFactory implements DriverFactory {

	@Override
	public Driver getDriver(String path) {
		// ͨ����chrome driver�Ĵ�����Ĺ��췽������chromeBrowserDriver��ʵ��
		return new ChromeBrowserDriver(path);
	}

}
