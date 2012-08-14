package erodin2.bank.operation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import erodin2.Messages;
import erodin2.Utils;
import erodin2.bank.BancoSantander;
import erodin2.bank.AbstractBank;
import erodin2.bank.Cartola;
import erodin2.bank.Movement;
import erodin2.file.FileOperations;

public class BancoSantanderOperations extends AbstractBankOperations {

	private static Logger logger = LoggerFactory
			.getLogger(BancoSantanderOperations.class);

	@Override
	protected void locateMovementsInternal(String months) {
		AbstractBank bank = new BancoSantander();
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