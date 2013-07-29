package jp.co.isken.tax.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isken.tax.entity.CFAccount;
import jp.co.isken.tax.entity.CFEntry;
import jp.co.isken.tax.entity.CFTransaction;
import jp.co.isken.tax.entity.CanTax;
import jp.co.isken.tax.entity.Entry;
import jp.co.isken.tax.entity.Party;
import jp.co.isken.tax.entity.Transaction;
import jp.co.isken.tax.exciseLibrary.Item;
import jp.co.isken.tax.util.HardCode;

public class CashFlow {

	private List<CFTransaction> cfTransactions = new ArrayList<CFTransaction>();;

	public CashFlow(Transaction transaction) throws Exception {
		for (Entry each : transaction.getEntries()) {
			CFTransaction cfTransaction = createCFTransaction(each);
			createEntries(cfTransaction, each);
			cfTransaction.save();
			cfTransactions.add(cfTransaction);
		}
	}

	private CFTransaction createCFTransaction(Entry entry) {
		return new CFTransaction(entry);
	}

	private CFAccount getAccount(Party party) {
		CFAccount cfAccount = CFAccount.getAccount(HardCode.DAIKIN, party);
		return cfAccount;
	}

	private CFAccount getExciseRateAccount(CFTransaction cfTransaction) {
		CFAccount cfAccount = CFAccount.getAccount(HardCode.EXCISE,
				cfTransaction.getCustomer());
		return cfAccount;
	}

	private void createEntries(CFTransaction cfTransaction, Entry entry)
			throws Exception {
		BigDecimal subTotal = entry.getAmmount().multiply(
				entry.getAccount().getProduct().getPrice());
		new CFEntry(cfTransaction, getAccount(cfTransaction.getCustomer()),
				subTotal);
		createExciseEntry(subTotal, cfTransaction, entry);
	}

	public void createExciseEntry(BigDecimal bd, CFTransaction cfTransaction,
			Entry entry) throws Exception {
		if (entry.getTransaction().getCanTax().equals(CanTax.INTERNATIONAL)) {
			return;
		}
		BigDecimal rate = Item
				.getItemByName(
						entry.getAccount().getProduct().getItem().getName())
				.getTaxRate(cfTransaction.getWhenOccered()).getRate();
		CFAccount account = getExciseRateAccount(cfTransaction);
		new CFEntry(cfTransaction, account, bd.multiply(rate));
	}

	public void saveTransaction(CFTransaction cfTransaction) {
		cfTransaction.save();
	}

	public List<CFTransaction> getCFTransaction() {
		return cfTransactions;
	}
}
