package jp.co.isken.tax.entity;

import java.util.Vector;

public class Item {


	private boolean isSave = false;
	private String name;
	private static Vector<Item> $itemList = new  Vector<Item>();

	public Item(String name) {
		this.name = name;
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

//	public List<TaxRate> getTaxRates() {
//		return TaxRate.getTaxRates(this);
//	}
}
