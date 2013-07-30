package jp.co.isken.tax.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jp.co.isken.tax.util.Util;

public class Contract {

	private static List<Contract> $contractList = new ArrayList<Contract>();
	private static int $count = 0;
	private int id;
	private Date contractDate;
	private Date effectiveDate;
	private Party customer;
	private CalTaxOption calType;

	public Contract(Date contractdate, Date effectiveDate, Party party,
			CalTaxOption calType) {
		id = $count++;
		this.contractDate = contractdate;
		this.effectiveDate = effectiveDate;
		this.customer = party;
		this.calType = calType;
		Contract.$contractList.add(this);
	}

	public String toString() {
		String contractd = Util.dateToString(contractDate);
		String effectived = Util.dateToString(effectiveDate);
		return id + " : " + contractd + ", " + effectived + ", "
				+ customer.getName() + ", " + calType.getName();
	}

	public Party getCustomer() {
		return customer;
	}

	public void setCustomer(Party customer) {
		this.customer = customer;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public static Iterator<Contract> iterator() {
		return $contractList.iterator();
	}

	public int getId() {
		return id;
	}

	public CalTaxOption getRoundOption() {
		return this.calType;
	}

	public static Contract getContract(int contractId) {
		for (Contract c : Contract.$contractList) {
			if (c.getId() == contractId) {
				return c;
			}
		}
		// TODO 事前チェックかExceptionに変更する
		return null;
	}

	public static void init() {
		$contractList = new ArrayList<Contract>();
		$count = 0;
	}
}
