import static org.junit.Assert.assertEquals;

import java.util.List;

import org.jboss.netty.util.internal.StringUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import erodin2.Utils;
import erodin2.bank.BancoChile;
import erodin2.bank.AbstractBank;
import erodin2.bank.Movement;
import erodin2.bank.operation.AbstractBankOperations;
import erodin2.bank.operation.BancoSantanderOperations;
import erodin2.file.FileOperations;
import erodin2.htmlparser.HtmlParserBancoSantander;

public class ErodinTests {

	private static Logger logger = LoggerFactory.getLogger(ErodinTests.class);

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testWriteSourceHTML() throws Exception {
		FileOperations writeSourceHTML = new FileOperations();
		writeSourceHTML.write("<Head>HTML test á</Head>",
				"CartolaSantander.html");
	}

	// @Test
	// public void testLocateSantanderMovements() throws Exception {
	// Bank bank = new BancoSantander();
	// String months = "Diciembre 2011,Enero 2012,Febrero 2012,Marzo 2012";
	// List<Cartola> cartolas = bank.getMovements(
	// Messages.getString("Main.user.bsantander"),
	// Messages.getString("Main.password.bsantander"),
	// Utils.splitMonth(months));
	// long total = 0;
	// for (Cartola cartola : cartolas) {
	//
	// for (Movement movement : cartola.getMovements()) {
	//
	// System.out.println("Date: " + movement.getDate());
	// System.out.println("Description: " + movement.getDescription());
	// System.out.println("Debit: " + movement.getDebit());
	// System.out.println("Credit: " + movement.getCredit());
	// System.out.println("Amount: " + movement.getAmount());
	//
	// total = total + Long.parseLong(movement.getAmount());
	//
	// System.out.println("---");
	// }
	// }
	// System.out.println("total: " + total);
	// }

	// Arreglar una vez que se repare el acceso al Banco Santander
	// @Test
	// public void testLocateBancoSantanderMovements() throws Exception {
	// String months =
	// "Julio 2011,Agosto 2011,Septiembre 2011,Octubre 2011,Noviembre 2011,Diciembre 2011,Enero 2012,Febrero 2012,Marzo 2012,Abril 2012";
	// new BankOperations().locateBancodeSantanderMovements(months);
	// }

	@Test
	public void testLocateBancoChileMovements() throws Exception {
		// String months =
		// "Agosto 2011,Septiembre 2011,Octubre 2011,Noviembre 2011,Diciembre 2011,Enero 2012,Febrero 2012,Marzo 2012,Abril 2012,Mayo 2012,Junio 2012";
		String months = "Mayo 2012,Junio 2012";
		AbstractBankOperations bankOperations = new BancoSantanderOperations();
		bankOperations.locateMovements(months);
	}

	// @Test
	// public void testWriteBancoChileMovements() throws Exception {
	// Bank bank = new BancoChile();
	// String htmlMovements = bank.getMovements(
	// Messages.getString("Main.user"),
	// Messages.getString("Main.password"), null, null);
	// new FileOperations()
	// .write(htmlMovements, "BcoChile_TCredito_Marzo2012");
	// System.out.println(htmlMovements);
	// }

	@Test
	public void testReadFile() throws Exception {
		String fileContent = new FileOperations().read("BancoSantander.html");
	}

	@Test
	public void testHtmlParserWithFileSantander() throws Exception {
		List<Movement> movements = new HtmlParserBancoSantander().getMovements(
				new FileOperations().read("BancoSantander.html"), "2012",
				"Enero");
		long total = 0;
		for (Movement movement : movements) {

			logger.debug("Date: {}", movement.getDate());
			logger.debug("Description: {}", movement.getDescription());
			logger.debug("Debit: {}", movement.getDebit());
			logger.debug("Credit: {}", movement.getCredit());
			logger.debug("Amount: {}", movement.getAmount());
			// total = total
			// + Long.parseLong(StringUtil
			// .stripControlCharacters(movement.getAmount())
			// .replaceAll("\\uc2a0", "").trim());

			total = total + Long.parseLong(movement.getAmount());
		}
		logger.debug("total: {}", total);
	}

	@Test
	public void testMovementVerification() throws Exception {

	}

	@Test
	public void testWriteCsvMovements() throws Exception {
		List<Movement> movements = new HtmlParserBancoSantander().getMovements(
				new FileOperations().read("BancoSantander.html"), "2012",
				"Enero");
		new FileOperations().writeCsvMovements(movements, "Cartola_Marzo.csv");
	}

	@Test
	public void testCsvContent() throws Exception {
		List<Movement> movements = new HtmlParserBancoSantander().getMovements(
				new FileOperations().read("BancoSantander.html"), "2012",
				"Enero");
		logger.debug(new FileOperations().csvMovementsContent(movements));
	}

	@Test
	public void testSpacesDelete() {
		assertEquals("bla", StringUtil.stripControlCharacters("   bla   ")
				.trim());
	}

	@Test
	public void testNumberFormat() throws Exception {
		assertEquals("123456", new Movement().formatNumber("123.456"));
	}

	@Test
	public void testSetAmountFromCredit() throws Exception {
		Movement movement = new Movement();
		movement.setCredit("123.456");
		assertEquals("123456", movement.getAmount());
	}

	@Test
	public void testSetAmountFromDebit() throws Exception {
		Movement movement = new Movement();
		movement.setDebit("123.456");
		assertEquals("-123456", movement.getAmount());
	}

	@Test
	public void testMonthToNumber() throws Exception {
		assertEquals("01", Utils.monthToNumber("Enero"));
	}

	@Test
	public void testFormatDateDicember() throws Exception {
		assertEquals("01/12/2011",
				Utils.formatDateWithoutYear("2012", "01/12", "01"));
	}

	@Test
	public void testFormatDateJanuary() throws Exception {
		assertEquals("01/01/2012",
				Utils.formatDateWithoutYear("2012", "01/01", "01"));
	}

	@Test
	public void testSplitMonths() throws Exception {
		assertEquals("Enero", Utils.splitMonth("Enero,Febrero,Marzo")[0]);
	}

	@Test
	public void testgetReconciliationDate() throws Exception {
		AbstractBank bank = new BancoChile();
		bank.getReconciliationDateFromHTML("2012",
				new FileOperations().read("BcoChile_TCredito_Marzo2012.html"),
				"03");
	}

	@Test
	public void testFormatDateLatinToAmerican() throws Exception {
		assertEquals("2012/04/29",
				Utils.formatDateLatinToAmerican("29/04/2012"));
	}

	@Test
	public void testGetNow() throws Exception {
		logger.debug(Utils.getNow());
	}

	@After
	public void tearDown() throws Exception {
	}

}