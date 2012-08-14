package erodin2.bank;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Movement {

	private String _date;

	private String _description;

	private String _amount;

	private String _category;

	private String _credit;

	private String _debit;

	private String _origin;

	private String _nroDocto;

	public String getDate() {
		return _date;
	}

	public void setDate(String date) {
		_date = date.replaceAll("\\u00A0", "");
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description.trim().replaceAll("\\u00A0", " ").trim();
	}

	public String getCategory() {
		return _category;
	}

	public void setCategory(String category) {
		_category = category.trim().replaceAll("\\u00A0", "");
	}

	public String getCredit() {
		return _credit;
	}

	public void setCredit(String credit) {

		_credit = formatNumber(credit);
		setAmount(getCredit());
	}

	public String getDebit() {
		return _debit;
	}

	public void setDebit(String debit) {
		_debit = formatNumber(debit);
		setAmount("-" + getDebit());
	}

	public String getOrigin() {
		return _origin;
	}

	public void setOrigin(String origin) {
		_origin = origin.trim().replaceAll("\\u00A0", "");
	}

	public String getAmount() {
		return _amount;
	}

	// Amout is setting through credit and debit
	private void setAmount(String amount) {
		_amount = amount;
	}

	public String getNroDocto() {
		return _nroDocto;
	}

	public void setNroDocto(String nroDocto) {
		_nroDocto = nroDocto;
	}

	public String formatNumber(String number) {
		String formattedAmount = number.trim();

		if (number.contains(".")) {
			formattedAmount = number.replace(".", "");
		}
		return formattedAmount.replaceAll("\\u00A0", "");
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
