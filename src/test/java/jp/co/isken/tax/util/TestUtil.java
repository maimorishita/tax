package jp.co.isken.tax.util;

public class TestUtil {
	public static String joinStrings(String... strs) {
		String newLine = System.getProperty("line.separator");
		String result = "";
		for (String s : strs) {
			result += s + newLine;
		}
		return result;
	}
}
