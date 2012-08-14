package erodin2.bank;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;

import erodin2.htmlparser.HtmlParserBancoSantander;

public class BancoSantander extends AbstractBank {

	protected String _baseUrl = "http://www.santander.cl/";

	@Override
	protected void login(String user, String password) {
		// surfing to get movements
		driver.get(_baseUrl);
		driver.findElement(By.id("d_rut")).click();
		driver.findElement(By.id("d_rut")).clear();
		driver.findElement(By.id("d_rut")).sendKeys(user);
		driver.findElement(By.id("clave")).clear();
		driver.findElement(By.id("clave")).sendKeys(password);
		driver.findElement(By.id("botonenvio")).click();

	}

	@Override
	protected String getHtmlWithMovements(String month, String year,
			String creditCard) {
		driver.switchTo().frame(0);
		driver.switchTo().frame("frame2");
		driver.findElement(By.linkText("Cuentas")).click();
		driver.findElement(By.linkText("Cuentas Corrientes")).click();
		// driver.findElement(By.linkText("Cartolas Históricas")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("javascript:clickMenu(2,'CH3')");
		driver.switchTo().frame("p4");
		driver.findElement(By.id("tipo1")).click();
		new Select(driver.findElement(By.name("mes0")))
				.selectByVisibleText(month);
		new Select(driver.findElement(By.name("anno0")))
				.selectByVisibleText(year);
		driver.findElement(By.name("BtnPER_Ver")).click();
		String pageSource = driver.getPageSource();
		return pageSource;
	}

	@Override
	protected List<Movement> parseMovements(Cartola cartola, String year,
			String sourceHtml) {
		return new HtmlParserBancoSantander().getMovements(sourceHtml, year,
				cartola.getMonth());
	}

	@Override
	public String getReconciliationDateFromHTML(String year, String sourceHtml,
			String month) {
		return new HtmlParserBancoSantander().ReconciliationDateFromHTML(year,
				sourceHtml, month);
	}
}
