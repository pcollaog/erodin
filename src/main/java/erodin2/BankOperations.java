package erodin2;

import java.util.List;

import erodin2.bank.BancoChile;
import erodin2.bank.BancoSantander;
import erodin2.bank.Bank;
import erodin2.bank.Cartola;
import erodin2.bank.Movement;
import erodin2.file.FileOperations;

public class BankOperations {

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

				System.out.println("Date: " + movement.getDate());
				System.out.println("Description: " + movement.getDescription());
				System.out.println("Debit: " + movement.getDebit());
				System.out.println("Credit: " + movement.getCredit());
				System.out.println("Amount: " + movement.getAmount());
				// total = total
				// + Long.parseLong(StringUtil
				// .stripControlCharacters(movement.getAmount())
				// .replaceAll("\\uc2a0", "").trim());

				total = total + Long.parseLong(movement.getAmount());

				System.out.println("---");
			}
		}
		System.out.println("total: " + total);

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

				System.out.println("Date: " + movement.getDate());
				System.out.println("Description: " + movement.getDescription());
				System.out.println("Debit: " + movement.getDebit());
				System.out.println("Credit: " + movement.getCredit());
				System.out.println("Amount: " + movement.getAmount());
				// total = total
				// + Long.parseLong(StringUtil
				// .stripControlCharacters(movement.getAmount())
				// .replaceAll("\\uc2a0", "").trim());

				total = total + Long.parseLong(movement.getAmount());

				System.out.println("---");
			}
		}
		System.out.println("total: " + total);

		String fileName = "cartolas"
				+ Messages.getString("Main.creditCard.bchile") + "_"
				+ Utils.getNow() + ".csv";
		new FileOperations().writeCsvCartolas(cartolas, fileName);
	}
}
