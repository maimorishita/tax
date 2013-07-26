package jp.co.isken.tax.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jp.co.isken.tax.util.Util;

public class Contract {

	private static List<Contract> $contractList = new ArrayList<Contract>();
	private static int count = 0;
	private int id;
	private Date contractDate;
	private Date effectiveDate;
	private Party customer;
	private String calType;
	private String baseDateType;

	public Contract(Date date, Party party, String calType, String baseDateType) {
		id = count++;
		setContractDate(date);
		effectiveDate = date;
		setCustomer(party);
		this.calType = calType;
		this.baseDateType = baseDateType;
		Contract.$contractList.add(this);
	}

	public static Contract getContracat(String name, Date date) throws Exception {
		for (Contract c : Contract.$contractList) {
			if (c.getCustomer().getName().equals(name)
					&& c.getContractDate().equals(date)) {
				return c;
			}
		}
		throw new Exception();
	}

	public String toString() {
		String contractd = Util.dateToString(contractDate);
		String effectived = Util.dateToString(effectiveDate);
		return id + " : " + contractd + ", " + effectived + ", "
				+ customer.getName() + ", " + calType + ", " + baseDateType;
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

	public static Contract getContract(int contractId) {
		for (Contract c : Contract.$contractList) {
			if (c.getId() == contractId){
				return c;
			}
		}
		// TODO 事前チェックかExceptionに変更する
		return null;
	}

	public static void init() {
		$contractList = new ArrayList<Contract>();
		count = 0;
	}
}
