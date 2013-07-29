package jp.co.isken.tax.exciseLibrary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Item {

	private int id;
	private boolean isSave = false;
	private String name;
	private static List<Item> $itemList = new ArrayList<Item>();
	private static int count = 0;

	public Item(String name) {
		this.name = name;
		this.id = count++;
		save();
	}

	public void save() {
		if (isSave == false) {
			isSave = true;
			$itemList.add(this);
		}
	}

	public String getName() {
		return name;
	}

	public static Item getItemByName(String name) throws Exception {
		for (Item i : $itemList) {
			if (i.getName().equals(name)) {
				return i;
			}
		}
		throw new Exception();

	}

	public List<TaxRate> getTaxRates() {
		return TaxRate.getTaxRates(this);
	}

	public TaxRate getTaxRate(Date date) throws Exception {
		return TaxRate.getTaxRates(this, date);
	}
	
	public static void init() {
		$itemList = new ArrayList<Item>();
		count = 0;
	}
}
