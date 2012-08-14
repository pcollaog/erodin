package erodin2.bank;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import erodin2.Utils;

public abstract class AbstractBank {

	protected WebDriver driver;

	protected StringBuffer verificationErrors = new StringBuffer();

	public List<Cartola> getMovements(String user, String password,
			String[] months, String creditCard) {
		setup(user, password);
		login(user, password);
		List<Cartola> cartolas = createCartolas(months, creditCard);
		finalizing();
		return cartolas;

	}

	private void setup(String user, String password) {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	protected List<Cartola> createCartolas(String[] months, String creditCard) {
		List<Cartola> cartolas = new ArrayList<Cartola>();
		for (int i = 0; i < months.length; i++) {

			Cartola cartola = new Cartola();
			String month = months[i].split(" ")[0];
			String year = months[i].split(" ")[1];
			cartola.setMonth(Utils.monthToNumber(month));
			String sourceHtml = getHtmlWithMovements(month, year, creditCard);
			cartola.setMovements(parseMovements(cartola, year, sourceHtml));
			cartola.setReconciliationDate(getReconciliationDateFromHTML(year,
					sourceHtml, cartola.getMonth()));
			cartolas.add(cartola);
		}
		return cartolas;
	}

	public abstract String getReconciliationDateFromHTML(String year,
			String sourceHtml, String month);

	protected abstract List<Movement> parseMovements(Cartola cartola,
			String year, String sourceHtml);

	private void finalizing() {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	protected abstract void login(String user, String password);

	protected abstract String getHtmlWithMovements(String month, String year,
			String creditCard);

}
