package erodin2.bank;

import java.util.List;

public class Cartola {

	private List<Movement> _movements;

	private String _totalDebit;

	private String _totalCredit;

	private String _initialAmount;

	private String _finalAmount;

	private String _month;

	private String _year;

	private String _reconciliationDate;

	public String getReconciliationDate() {
		return _reconciliationDate;
	}

	public void setReconciliationDate(String reconciliationDate) {
		_reconciliationDate = reconciliationDate.trim()
				.replaceAll("\\u00A0", " ").trim();
	}

	public List<Movement> getMovements() {
		return _movements;
	}

	public void setMovements(List<Movement> movements) {
		_movements = movements;
	}

	public String getTotalDebit() {
		return _totalDebit;
	}

	public void setTotalDebit(String totalDebit) {
		_totalDebit = formatNumber(totalDebit);
	}

	public String getTotalCredit() {
		return _totalCredit;
	}

	public void setTotalCredit(String totalCredit) {
		_totalCredit = formatNumber(totalCredit);
	}

	public String getInitialAmount() {
		return _initialAmount;
	}

	public void setInitialAmount(String initialAmount) {
		_initialAmount = formatNumber(initialAmount);
	}

	public String getFinalAmount() {
		return _finalAmount;
	}

	public void setFinalAmount(String finalAmount) {
		_finalAmount = formatNumber(finalAmount);
	}

	public String getMonth() {
		return _month;
	}

	public void setMonth(String month) {
		_month = month;
	}

	public String getYear() {
		return _year;
	}

	public void setYear(String year) {
		_year = year;
	}

	public String formatNumber(String number) {
		String formattedAmount = number.trim();

		if (number.contains(".")) {
			formattedAmount = number.replace(".", "");
		}
		return formattedAmount.replaceAll("\\u00A0", "");
	}
}
