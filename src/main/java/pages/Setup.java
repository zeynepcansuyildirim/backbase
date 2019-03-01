package pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Setup {
	
	public WebDriver driver;
	public WebDriverWait wait;
	public WebElement element;
	public Actions action;
	public Properties prop;
	
	public static final String url = "http://computer-database.herokuapp.com/";

	public WebDriver getDriver() {
		return driver;
	}

	public void init() throws FileNotFoundException, IOException {
		System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		options.addArguments("--start-maximized");
		options.addArguments("--disable-notifications");
		options.addArguments("disable-infobars");
		this.driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.manage().window().maximize();
		driver.get(url);
		wait = new WebDriverWait(driver, 120);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public String getConf(String configFileName, String value) throws FileNotFoundException, IOException {
		prop = new Properties();
		prop.load(new FileInputStream(configFileName));
		String val = prop.getProperty(value);
		return val;
	}
	
	public void setConf(String key, String value) throws FileNotFoundException, IOException {
		prop = new Properties();
		prop.put(key, value);
		FileWriter writer = new FileWriter("notification.properties");
		prop.store(writer, "host settings");
	}

	public void click(WebElement element) throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			Thread.sleep(500);
			element.click();
		}
		catch (StaleElementReferenceException e) {
			System.out.println("s");
			click(element); }
	}
	
	public void write(WebElement element, String metin) throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			element.clear();
			element.sendKeys(metin);
			element.sendKeys(Keys.TAB);
		}

		catch (StaleElementReferenceException e) {
			write(element, metin); }
	}
	
	public void verifyText(WebElement element, String mesaj) throws InterruptedException {
		wait.until(ExpectedConditions.textToBePresentInElement(element, mesaj));
	}


}
