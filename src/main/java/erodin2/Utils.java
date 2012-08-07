package erodin2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public static String monthToNumber(String month) {
		if (month.equalsIgnoreCase("Enero")) {
			return "01";
		} else if (month.equalsIgnoreCase("Febrero")) {
			return "02";
		} else if (month.equalsIgnoreCase("Marzo")) {
			return "03";
		} else if (month.equalsIgnoreCase("Abril")) {
			return "004";
		} else if (month.equalsIgnoreCase("Mayo")) {
			return "05";
		} else if (month.equalsIgnoreCase("Junio")) {
			return "06";
		} else if (month.equalsIgnoreCase("Julio")) {
			return "07";
		} else if (month.equalsIgnoreCase("Agosto")) {
			return "08";
		} else if (month.equalsIgnoreCase("Septiembre")) {
			return "09";
		} else if (month.equalsIgnoreCase("Octubre")) {
			return "10";
		} else if (month.equalsIgnoreCase("Noviembre")) {
			return "11";
		} else if (month.equalsIgnoreCase("Diciembre")) {
			return "12";
		}

		return "00";
	}

	public static String formatDateWithoutYear(String year, String date,
			String monthNumber) {
		String movementMonth = date.split("/")[1];
		if (Integer.parseInt(movementMonth) > Integer.parseInt(monthNumber)) {
			Integer newYear = Integer.parseInt(year) - 1;
			return date + "/" + newYear.toString();
		} else {
			return date + "/" + year;
		}

	}

	public static String formatDateLatinToAmerican(String date) {
		return date;

	}

	public static String[] splitMonth(String months) {
		String[] monthSplited = months.split(",");
		return monthSplited;
	}

	public static String getNow() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		Date now = new Date();
		return dateFormat.format(now);
	}
}
