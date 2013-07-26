package jp.co.isken.tax.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.co.isken.tax.entity.Account;
import jp.co.isken.tax.entity.Contract;
import jp.co.isken.tax.entity.Entry;
import jp.co.isken.tax.entity.Product;
import jp.co.isken.tax.entity.Transaction;
import jp.co.isken.tax.exciseLibrary.CalExciseService;

public class Receipt {
	
	private Transaction t ;
	private List<Entry> entryList = new ArrayList<Entry>();

	public Receipt(Date _date, int contractId) {
		t = new Transaction(Contract.getContract(contractId), _date, _date);
	}

	public void addLineItem(int id, int quantity) throws Exception {
		Account a = Account.getAccount(Product.getProduct(id));
		Entry e = new Entry(t, a, quantity);
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
		t.setCFTransaction(cf.getCFTransaction());
		t.update();
		CalExciseService.createExciseTransaction(t);
	}

}
