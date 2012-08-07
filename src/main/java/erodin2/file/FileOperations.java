package erodin2.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import erodin2.bank.Cartola;
import erodin2.bank.Movement;

public class FileOperations {

	public void write(String htmlContent, String htmlName) {
		// get which file you want to read and write
		File file = new File(htmlName);
		try {
			// check whether the file is existed or not
			if (file.exists()) {
				// create a new file if the file is not existed
				file.createNewFile();
			}
			// new a writer and point the writer to the file
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			// writer the content to the file
			writer.write(htmlContent);
			// always remember to close the writer
			writer.close();
			writer = null;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String read(String fileName) {
		// get which file you want to read and write
		File file = new File(fileName);
		String fileContent = "";
		try {

			// new a reader and point the reader to the file
			BufferedReader reader = new BufferedReader(new FileReader(file));

			String line = "";
			//
			// System.out
			// .println("Content of the file\n================================");
			while ((line = reader.readLine()) != null) {
				fileContent = fileContent + line;
			}
			reader.close();
			reader = null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println(fileContent);
		return fileContent;
	}

	public void writeCsvMovements(List<Movement> movements, String fileName) {
		// get which file you want to read and write
		File file = new File(fileName);
		try {
			// check whether the file is existed or not
			if (file.exists()) {
				// create a new file if the file is not existed
				file.createNewFile();
			}
			// new a writer and point the writer to the file
			BufferedWriter fileContent = new BufferedWriter(
					new FileWriter(file));

			// writer the content to the file
			fileContent.write(csvMovementsContent(movements));
			// always remember to close the writer
			fileContent.close();
			fileContent = null;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeCsvCartolas(List<Cartola> cartolas, String fileName) {
		// get which file you want to read and write
		File file = new File(fileName);
		try {
			// check whether the file is existed or not
			if (file.exists()) {
				// create a new file if the file is not existed
				file.createNewFile();
			}
			// new a writer and point the writer to the file
			BufferedWriter fileContent = new BufferedWriter(
					new FileWriter(file));

			// writer the content to the file
			fileContent.write(csvCartolasContent(cartolas));
			// always remember to close the writer
			fileContent.close();
			fileContent = null;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String csvCartolasContent(List<Cartola> cartolas) {
		String csvContent = "Date,Description,Amount,Debit,Credit,Origin,Category,ReconciliationDate\n";
		for (Cartola cartola : cartolas) {
			for (Movement movement : cartola.getMovements()) {
				csvContent = csvContent + movement.getDate() + ","
						+ movement.getDescription() + ","
						+ movement.getAmount() + "," + movement.getDebit()
						+ "," + movement.getCredit() + ","
						+ movement.getOrigin() + "," + movement.getCategory()
						+ "," + cartola.getReconciliationDate() + "\n";
			}
		}
		return csvContent;
	}

	public String csvMovementsContent(List<Movement> movements) {
		String csvContent = "Date,Description,Amount,Debit,Credit,Origin,Category\n";
		for (Movement movement : movements) {
			csvContent = csvContent + movement.getDate() + ","
					+ movement.getDescription() + "," + movement.getAmount()
					+ "," + movement.getDebit() + "," + movement.getCredit()
					+ "," + movement.getOrigin() + "," + movement.getCategory()
					+ "\n";
		}
		return csvContent;
	}

}
