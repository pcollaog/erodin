import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginSantander {

	private static final String USER_SANTANDER = "14121303-2"; // format

	// 12345678-9
	private static final String PASSWORD_SANTANDER = "2912"; // four digits

	// 12345678-9
	private WebDriver driver;

	private String baseUrl;

	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {

		driver = new FirefoxDriver();
		baseUrl = "http://www.santander.cl/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testLoginSantander() throws Exception {

		driver.get(baseUrl + "/");
		driver.findElement(By.id("d_rut")).click();
		driver.findElement(By.id("d_rut")).clear();
		driver.findElement(By.id("d_rut")).sendKeys(USER_SANTANDER);
		driver.findElement(By.id("clave")).clear();
		driver.findElement(By.id("clave")).sendKeys(PASSWORD_SANTANDER);
		driver.findElement(By.id("botonenvio")).click();
		driver.switchTo().frame("frame2");
		driver.findElement(By.linkText("Cuentas")).click();
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("javascript:clickMenu(0,'CU1')");
		// <a href="javascript:clickMenu(0,'CU1')">Cuentas</a>

		driver.findElement(By.linkText("Cuentas Corrientes")).click();
		driver.findElement(By.linkText("Cartolas Hist�ricas")).click();
		driver.switchTo().frame("p4");
		driver.findElement(By.id("tipo1")).click();
		new Select(driver.findElement(By.name("mes0")))
				.selectByVisibleText("Marzo");
		new Select(driver.findElement(By.name("anno0")))
				.selectByVisibleText("2012");
		driver.findElement(By.name("BtnPER_Ver")).click();
		// driver.switchTo().frame("frame2");
		// driver.switchTo().frame("p4");
		String pageSource = driver.getPageSource();
		// System.out.println(pageSource);
		parseHTML(pageSource);
	}

	private void parseHTML(String pageSource) {
		Document doc = Jsoup.parse(pageSource);
		Elements td_f = doc.select("[class=td_f");
		for (int i = 0; i < td_f.size(); i++) {
			Element e = td_f.get(i);
			System.out.println(e.ownText());
		}
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
