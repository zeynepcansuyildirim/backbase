package pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComputerDatabasePage extends Setup {

	@FindBy(xpath = "//*[@id='add']")
	public WebElement addComputerBtn;
	@FindBy(xpath = "//*[@id='name']")
	public WebElement namebox;		
	@FindBy(xpath = "//*[@id='introduced']")
	public WebElement introducedbox;
	@FindBy(xpath = "//*[@id='discontinued']")
	public WebElement discontinuedbox;
	@FindBy(xpath = "//*[@id='company']")
	public WebElement companyCmbx;
	@FindBy(xpath = "//*[text()='Apple Inc.']")
	public WebElement chooseApple;
	@FindBy(xpath = "//*[text()='RCA']")
	public WebElement chooseRCA;
	@FindBy(xpath = "//*[@value='Create this computer']")
	public WebElement createBtn;

	@FindBy(xpath = "//*[@id='searchbox']")
	public WebElement searchbox;
	@FindBy(xpath = "//*[@id='searchsubmit']")
	public WebElement searchBtn;
	@FindBy(xpath = "//*[@class='alert-message warning']")
	public WebElement warning;
	@FindBy(xpath = "//following::table//tbody//tr")
	public WebElement table;
	
	@FindBy(xpath = "//*[@value='Save this computer']")
	public WebElement saveBtn;
	@FindBy(xpath = "//*[@value='Save this computer']")
	public WebElement deleteBtn;
	
	public WebDriver driver;
	
	public ComputerDatabasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
	}
	
	public String generateString(Random rng, String characters, int length) {
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		}

		return new String(text);
	}
	
	
	public ComputerDatabasePage CreateComputer() throws Exception {		
		Random r = new Random();
		String s = generateString(r, "0123456789", 5);	
		setConf("seed", s);	
		Thread.sleep(500);
		click(addComputerBtn);
		Thread.sleep(500);
		write(namebox, "Computer"+s);
		Thread.sleep(500);
		write(introducedbox, "2019-11-11");
		Thread.sleep(500);
		write(discontinuedbox, "2020-11-11");
		Thread.sleep(500);
		click(companyCmbx);
		Thread.sleep(500);
		click(chooseApple);
		Thread.sleep(500);
		click(createBtn);
		Thread.sleep(500);
		verifyText(warning,"Done!");
		return PageFactory.initElements(driver, ComputerDatabasePage.class);
	}

	public ComputerDatabasePage FindComputer() throws Exception {	
		String s = getConf("notification.properties", "seed");
		write(searchbox, "Computer"+s);
		Thread.sleep(500);
		click(searchBtn);
		Thread.sleep(500);
		WebElement target = driver.findElement(By.xpath("//*[text()="+"'"+"Computer"+s+"'"+"]"));
		click(target);
		Thread.sleep(1500);
		return PageFactory.initElements(driver, ComputerDatabasePage.class);
	}
	public ComputerDatabasePage EditComputer() throws Exception {	
		click(companyCmbx);
		Thread.sleep(500);
		click(chooseRCA);
		Thread.sleep(500);
		click(saveBtn);
		Thread.sleep(1500);
		return PageFactory.initElements(driver, ComputerDatabasePage.class);
	}
	public ComputerDatabasePage DeleteComputer() throws Exception {	
		String s = getConf("notification.properties", "seed");
		write(searchbox, "Computer"+s);
		Thread.sleep(500);
		click(searchBtn);
		Thread.sleep(500);
		WebElement target = driver.findElement(By.xpath("//*[text()="+"'"+"Computer"+s+"'"+"]"));
		click(target);
		Thread.sleep(500);
		click(deleteBtn);
		verifyText(warning,"Done!");
		Thread.sleep(1500);
		return PageFactory.initElements(driver, ComputerDatabasePage.class);
	}
}
