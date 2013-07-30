package jp.co.isken.tax.exciseLibrary.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaxRate {

	private static List<TaxRate> $rateList = new ArrayList<TaxRate>();
	private static int count;
	private int id;
	private boolean isSave = false;
	private BigDecimal rate = new BigDecimal("0.00");
	private Tax tax;
	private Item item;
	private Term term;

	public TaxRate(Tax tax2, BigDecimal r, Item i, Term t) {
		tax = tax2;
		rate = r;
		item = i;
		term = t;
		id = count++;
		save();
	}

	public void save() {
		if (isSave == false) {
			isSave = true;
			$rateList.add(this);
		}
	}

	public static List<TaxRate> getTaxRates(Item item) {
		List<TaxRate> tlist = new ArrayList<TaxRate>();
		for (TaxRate t : $rateList) {
			if (t.getItem().equals(item)) {
				tlist.add(t);
			}
		}
		return tlist;
	}

	public Item getItem() {
		return item;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public static List<TaxRate> getTaxRates() {
		return $rateList;
	}

	public Term getTerm() {
		return term;
	}

	public static TaxRate getTaxRates(Item item, Date date) throws Exception {
		for (TaxRate t : $rateList) {
			if (t.getItem().equals(item)) {
				if(t.getTerm().isEffectiveDate(date)){
					return t;	
				}
			}
		}
		throw new Exception();
	}

	
}
