package erodin2.htmlparser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import erodin2.bank.Movement;

public class HtmlParserBancoSantander extends HtmlParser {

	@Override
	public List<Movement> getMovements(String movementsHTML, String year,
			String month) {
		List<Movement> movements = new ArrayList<Movement>();
		Document doc = Jsoup.parse(movementsHTML);

		Element td_f = doc.select("[class=td_f]").first();
		Elements td_fs = td_f.parent().parent().select("[class=td_f]");
		for (int i = 0; i < td_fs.size(); i++) {
			Element td_fi = td_fs.get(i);

			String date = td_fi.ownText().trim();
			if (0 == i % 2 && !date.equals(" ")) {

				String description = td_fi.siblingElements()
						.select("[class=td_t]").get(0).ownText().trim();
				String debit = td_fi.siblingElements().select("[class=td_n]")
						.get(0).ownText().trim();
				String credit = td_fi.siblingElements().select("[class=td_n]")
						.get(1).ownText().trim();

				Movement movement = new Movement();
				movement.setDate(date + "/" + year);
				movement.setDescription(description.trim());
				if (debit.length() > credit.length()) {
					movement.setDebit(debit);
				} else {
					movement.setCredit(credit);
				}

				movements.add(movement);

			}
		}
		return movements;
	}

	public String ReconciliationDateFromHTML(String year, String sourceHtml,
			String month) {
		Document doc = Jsoup.parse(sourceHtml);
		// return doc.select("[class=textotabla]").get(33).ownText().trim();
		// TODO
		return null;
	}
}
