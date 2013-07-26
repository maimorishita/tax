package jp.co.isken.tax.service;

import java.math.BigDecimal;
import java.util.Map;

import jp.co.isken.tax.entity.Account;
import jp.co.isken.tax.entity.CFAccount;
import jp.co.isken.tax.entity.CFEntry;
import jp.co.isken.tax.entity.CFTransaction;
import jp.co.isken.tax.entity.Transaction;
import jp.co.isken.tax.util.HardCode;

public class CashFlow {

	private CFTransaction cfTransaction;

	public CashFlow(Transaction transaction) {
		createCFTransaction(transaction);
		CFAccount cfAccount = getAccount();
		createEntries(transaction, cfAccount);
		saveTransaction();
	}

	public void createCFTransaction(Transaction transaction) {
		cfTransaction = new CFTransaction(transaction);
	}

	public CFAccount getAccount() {
		CFAccount cfAccount = CFAccount.getAccount(HardCode.DAIKIN);
		return cfAccount;
	}

	public void createEntries(Transaction transaction, CFAccount cfAccount) {
		Map<Account, BigDecimal> map = TransactionFacade.subTotal(transaction);
		for (Account account : map.keySet()) {
			CFEntry cfEntry = new CFEntry(cfTransaction, cfAccount,
					map.get(account));
			cfEntry.save();
		}
	}

	public void saveTransaction() {
		cfTransaction.save();
	}

	public CFTransaction getCFTransaction() {
		return cfTransaction;
	}
}
