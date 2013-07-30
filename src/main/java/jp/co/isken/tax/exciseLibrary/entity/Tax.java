package jp.co.isken.tax.exciseLibrary.entity;

import java.util.ArrayList;
import java.util.List;

public class Tax {

	private int id;
	private boolean isSave = false;
	private String name;
	private static List<Tax> $taxList = new ArrayList<Tax>();
	private static int count = 0;

	public Tax(String n) {
		name = n;
		id = count++;
	}

	public void save() {
		if (isSave == false) {
			isSave = true;
			$taxList.add(this);
		}
	}
}
