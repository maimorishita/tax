package jp.co.isken.tax.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.co.isken.tax.entity.Account;
import jp.co.isken.tax.entity.Contract;
import jp.co.isken.tax.entity.Entry;
import jp.co.isken.tax.entity.Product;
import jp.co.isken.tax.entity.Transaction;

public class Receipt {
	
	private Transaction t ;
	private List<Entry> entryList = new ArrayList<Entry>();

	public Receipt(Date _date, int contractId) {
		t = new Transaction(Contract.getContract(contractId), _date, _date);
	}

	public void addLineItem(int id, int quantity) throws Exception {
		Account a = Account.getAccount(Product.getProduct(id), t.getContract().getCustomer());
		BigDecimal bd = new BigDecimal("0.00");
		bd = BigDecimal.valueOf(quantity);
		Entry e = new Entry(t, a, bd);
		entryList.add(e);
	}
	
	public void save(){
		t.save();
		for(Entry e: entryList){
			e.save();
		}
	}

	public Transaction getTransaction() {
		return t;
	}

	public void total() throws Exception {
		CashFlow cf = new CashFlow(t);
		t.update();
	}

}
