package erodin2.htmlparser;

import java.util.List;

import erodin2.bank.Movement;

public abstract class HtmlParser {

	public abstract List<Movement> getMovements(String movementsHTML,
			String year, String month);

}