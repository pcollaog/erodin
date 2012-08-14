package erodin2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import erodin2.bank.BancoChile;
import erodin2.bank.AbstractBank;
import erodin2.bank.Cartola;
import erodin2.bank.Movement;
import erodin2.file.FileOperations;
import erodin2.htmlparser.HtmlParserBancoChile;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Bank bank = new BancoChile();
		//
		// String month = "Diciembre 2011";
		// String year = "2011";
		// List<Cartola> cartolas = bank.getMovements(
		// Messages.getString("Main.user.bchile"),
		// Messages.getString("Main.password.bchile"), month);
		//
		// for (Cartola cartola : cartolas) {
		//
		// new FileOperations().writeCsvMovements(cartola.getMovements(),
		// "Cartola" + month + year + ".csv");
		// }

	}

	private static String getDataFromKeyboard(String message) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		System.out.println(message);
		try {
			while ((input = in.readLine()) != null && input.length() != 0)
				System.out.println(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// An empty line or Ctrl-Z terminates the program
		return input;
	}

}
