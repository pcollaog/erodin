package erodin2.htmlparser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import erodin2.Utils;
import erodin2.bank.Movement;

public class HtmlParserBancoChile extends HtmlParser {
	@Override
	public List<Movement> getMovements(String movementsHTML, String year,
			String month) {
		List<Movement> movements = new ArrayList<Movement>();
		Document doc = Jsoup.parse(movementsHTML);

		getAmount(movements, doc, year, month);
		// getCredit(movements, doc);

		return movements;
	}

	private void getAmount(List<Movement> movements, Document doc, String year,
			String month) {
		Element table = doc.select("[name=form1]").first().children()
				.select("table").get(6).children().select("table").get(0)
				.children().select("table").get(5);
		Elements trs = table.getElementsByTag("tr");
		// System.out.println(trs.get(11));
		String origin = "Nacional";
		for (int i = 4; i < trs.size(); i++) {

			try {
				String date = trs.get(i).children().select("td").get(1)
						.select("div").first().children().first().ownText();
				String description = trs.get(i).children().select("td").get(2)
						.children().first().ownText();
				String amount = trs.get(i).children().select("td").get(4)
						.children().first().ownText();
				String nroDocto = trs.get(i).children().select("td").get(0)
						.select("div").first().children().first().ownText();

				Movement movement = new Movement();
				movement.setDate(Utils.formatDateWithoutYear(year, date, month));
				movement.setDescription(description.trim());
				if (4 == i) {
					movement.setCredit(amount.replaceAll("-", "").trim());
				} else {
					movement.setDebit(amount);
				}
				movement.setNroDocto(nroDocto);
				movement.setOrigin(origin);
				movements.add(movement);
			} catch (NullPointerException e) {
				if (i > 11) {
					origin = "Internacional";
				}
			} catch (IndexOutOfBoundsException e) {
				// e.printStackTrace();
			}

		}
	}

	public String ReconciliationDateFromHTML(String year, String sourceHtml,
			String month) {
		Document doc = Jsoup.parse(sourceHtml);
		return doc.select("[class=textotabla]").get(33).ownText().trim();
	}

}
