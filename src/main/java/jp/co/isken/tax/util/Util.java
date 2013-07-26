package jp.co.isken.tax.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static Date stringToDate(String str) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = null;
		try {
			date = sdf1.parse(str);
		} catch (ParseException e) {
			// atode
		}
		return date;
	}

	public static String dateToString(Date date) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = null;
		return str = sdf1.format(date);
	}

	public static String joinStrings(String... strs) {
		String newLine = System.getProperty("line.separator");
		String result = "";
		for (String s : strs) {
			result += s + newLine;
		}
		return result;
	}

	public static Date getDate() {
		return Util.stringToDate("20130505000000");
	}
}
