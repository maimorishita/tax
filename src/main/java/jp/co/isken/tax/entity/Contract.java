package jp.co.isken.tax.entity;

import java.util.Date;
import java.util.Vector;

import jp.co.isken.tax.util.Util;

public class Contract {

	private static Vector<Contract> instances = new Vector<Contract>();
	private static int count = 0;
	private int id ;
	private Date contractDate;
	private Date effectiveDate;
	private Party customer;
	private String calType;
	private String baseDateType;

	public Contract(Date date, String party, String calType, String baseDateType) {
		id = count++;
		setContractDate(date);
		effectiveDate = date;
		setCustomer(Party.getParty(party));
		this.calType = calType;
		this.baseDateType = baseDateType;
	}

	public void save() {
		Contract.instances.add(this);
	}

	public static Contract getContracat(String name, Date date) {
		for(Contract c: Contract.instances){
			if(c.getCustomer().getName().equals(name) && c.getContractDate().equals(date)){
				return c;
			}
		}
		//TODO 事前チェックかExceptionに変更する
		return null;
	}
	
	public String toString(){
		return Util.joinStrings(contractDate.toString(), effectiveDate.toString(), customer.getName(), calType, baseDateType);
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

}
