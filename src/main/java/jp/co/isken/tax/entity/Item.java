package jp.co.isken.tax.entity;

import java.util.ArrayList;
import java.util.List;

public class Item {

	private int id;
	private boolean isSave = false;
	private String name;
	private static List<Item> $itemList = new ArrayList<Item>();
	private static int count = 0;

	public Item(String name) {
		this.name = name;
		id = count++;
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
}
