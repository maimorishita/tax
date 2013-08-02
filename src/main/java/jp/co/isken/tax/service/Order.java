package jp.co.isken.tax.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.co.isken.tax.entity.Account;
import jp.co.isken.tax.entity.Contract;
import jp.co.isken.tax.entity.Entry;
import jp.co.isken.tax.entity.Product;
import jp.co.isken.tax.entity.transaction.CanTax;
import jp.co.isken.tax.entity.transaction.TaxableType;
import jp.co.isken.tax.entity.transaction.Transaction;
import jp.co.isken.tax.entity.transaction.TransactionType;

public class Order {
	
	private Transaction t ;
	private List<Entry> entryList = new ArrayList<Entry>();

	public Order(Date _date, int contractId) {
		t = new Transaction(Contract.getContract(contractId), _date, _date);
	}
	
	public void set(TransactionType tt, CanTax canTax, TaxableType taxableType) {
		t.setTransactionType(tt);
		t.setCanTax(canTax);
		t.setTaxableType(taxableType);
	}

	public void addLineItem(int productId, int quantity) throws Exception {
		Account account = getAccount(productId);
		Entry entry = createEntry(quantity, account);
		entryList.add(entry);
	}

	private Entry createEntry(int quantity, Account a) {
		BigDecimal bd = new BigDecimal("0.00");
		bd = BigDecimal.valueOf(quantity);
		Entry e = new Entry(t, a, bd);
		return e;
	}

	private Account getAccount(int productId) throws Exception {
		return Account.getAccount(Product.getProduct(productId), t.getContract().getCustomer());
	}
	
	public void save(){
		t.save();
		for(Entry e: entryList){
			e.save();
		}
	}

	public void total() throws Exception {
		CashFlow.createCashFlowByEntries(t.getEntries());
	}

	
	public Transaction getTransaction() {
		return t;
	}
}
