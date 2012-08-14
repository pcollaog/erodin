package erodin2.bank;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;

import erodin2.htmlparser.HtmlParserBancoChile;

public class BancoChile extends AbstractBank {

	protected String _baseUrl = "http://ww3.bancochile.cl/";

	@Override
	protected void login(String user, String password) {
		// surfing to get movements
		driver.get(_baseUrl + "/wps/wcm/connect/BancoDeChile/Personas/");
		driver.findElement(By.cssSelector("a.btn.AcceClie > span")).click();
		driver.findElement(By.id("XCustLoginID")).clear();
		driver.findElement(By.id("XCustLoginID")).sendKeys(user);
		driver.findElement(By.id("SignonPswd")).clear();
		driver.findElement(By.id("SignonPswd")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("llamarServer('Process?MID=20102&AID=LEGACY_SERVICE-0010&PAG=&RQE=f0600136fe026720',0,'Consultar','menuIzq3','Tarjetas de Crédito-Consultar-Mis Estados de Cuentas','Mis Estados de Cuentas');");
		driver.switchTo().frame("ifrm");
	}

	@Override
	protected String getHtmlWithMovements(String month, String year,
			String creditCard) {

		new Select(driver.findElement(By.id("codigoTarjeta")))
				.selectByVisibleText(creditCard);
		new Select(driver.findElement(By.name("menu2")))
				.selectByVisibleText(month + " " + year);
		driver.findElement(By.cssSelector("a > img")).click();
		String pageSource = driver.getPageSource();
		return pageSource;
	}

	@Override
	protected List<Movement> parseMovements(Cartola cartola, String year,
			String sourceHtml) {
		return new HtmlParserBancoChile().getMovements(sourceHtml, year,
				cartola.getMonth());
	}

	@Override
	public String getReconciliationDateFromHTML(String year, String sourceHtml,
			String month) {
		return new HtmlParserBancoChile().ReconciliationDateFromHTML(year,
				sourceHtml, month);
	}
}
