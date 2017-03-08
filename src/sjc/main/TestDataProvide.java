package sjc.main;

import org.testng.annotations.DataProvider;

public class TestDataProvide {
	@DataProvider(name = "search")
	public static Object[][] loginDataProvide() {
		return new Object[][] {{ "123"}};
	}
}
