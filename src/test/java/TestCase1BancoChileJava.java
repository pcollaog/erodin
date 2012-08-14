import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCase1BancoChileJava {

	private static Logger logger = LoggerFactory
			.getLogger(TestCase1BancoChileJava.class);

	private WebDriver driver;

	private String baseUrl;

	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		// driver = new HtmlUnitDriver();
		baseUrl = "http://ww3.bancochile.cl/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testCase1BancoChileJava() throws Exception {
		driver.get(baseUrl + "/wps/wcm/connect/BancoDeChile/Personas/");
		driver.findElement(By.cssSelector("a.btn.AcceClie > span")).click();
		driver.findElement(By.id("XCustLoginID")).clear();
		driver.findElement(By.id("XCustLoginID")).sendKeys("");
		driver.findElement(By.id("SignonPswd")).clear();
		driver.findElement(By.id("SignonPswd")).sendKeys("");
		driver.findElement(By.id("btnLogin")).click();
		driver.findElement(By.id("XXXX-XXXX-XXXX-9493")).click();
		String sourceHTML = driver.getPageSource();
		// driver.wait(5000);
		// driver.findElement(By.id("20102")).click();
		logger.debug(sourceHTML);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}