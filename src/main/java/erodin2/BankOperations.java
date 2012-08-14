package erodin2;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import erodin2.bank.BancoChile;
import erodin2.bank.BancoSantander;
import erodin2.bank.Bank;
import erodin2.bank.Cartola;
import erodin2.bank.Movement;
import erodin2.file.FileOperations;

public class BankOperations {

	private static Logger logger = LoggerFactory
			.getLogger(BankOperations.class);

	public void locateBancodeChileMovements(String months) {
		Bank bank = new BancoChile();
		List<Cartola> cartolas = bank.getMovements(
				Messages.getString("Main.user.bchile"),
				Messages.getString("Main.password.bchile"),
				Utils.splitMonth(months),
				Messages.getString("Main.creditCard.bchile"));
		long total = 0;
		for (Cartola cartola : cartolas) {

			for (Movement movement : cartola.getMovements()) {
				logger.debug(movement.toString());

				// total = total
				// + Long.parseLong(StringUtil
				// .stripControlCharacters(movement.getAmount())
				// .replaceAll("\\uc2a0", "").trim());

				total = total + Long.parseLong(movement.getAmount());
			}
		}
		logger.debug("total: {}", total);

		String fileName = "cartolas"
				+ Messages.getString("Main.creditCard.bchile") + "_"
				+ Utils.getNow() + ".csv";
		new FileOperations().writeCsvCartolas(cartolas, fileName);
	}

	public void locateBancoSantanderMovements(String months) {
		Bank bank = new BancoSantander();
		List<Cartola> cartolas = bank.getMovements(
				Messages.getString("Main.user.bsantander"),
				Messages.getString("Main.password.bsantander"),
				Utils.splitMonth(months),
				Messages.getString("Main.creditCard.bsantander"));
		long total = 0;
		for (Cartola cartola : cartolas) {

			for (Movement movement : cartola.getMovements()) {
				logger.debug(movement.toString());

				// total = total
				// + Long.parseLong(StringUtil
				// .stripControlCharacters(movement.getAmount())
				// .replaceAll("\\uc2a0", "").trim());

				total = total + Long.parseLong(movement.getAmount());
			}
		}
		logger.debug("total: {}", total);

		String fileName = "cartolas"
				+ Messages.getString("Main.creditCard.bchile") + "_"
				+ Utils.getNow() + ".csv";
		new FileOperations().writeCsvCartolas(cartolas, fileName);
	}
}