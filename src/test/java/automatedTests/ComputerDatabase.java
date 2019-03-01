package automatedTests;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pages.ComputerDatabasePage;
import pages.Setup;

public class ComputerDatabase extends Setup {

	@Test
	public void test_all() throws Exception {
		init();
		ComputerDatabasePage cdb = PageFactory.initElements(driver, ComputerDatabasePage.class);
		cdb.CreateComputer();
		cdb.FindComputer();
		cdb.EditComputer();
		cdb.DeleteComputer();
	}

	@AfterMethod
	public void endTest() throws InterruptedException, IOException {
		if (driver != null) {
			driver.quit();
		}
	}
	
	public static void main(String[] args) {
		ITestNGListener tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] {ComputerDatabase.class});
		testng.addListener(tla);
		testng.run();
	}

}