package jp.co.isken.tax.service;

import java.math.BigDecimal;
import java.util.List;

import jp.co.isken.tax.entity.Entry;
import jp.co.isken.tax.entity.Party;
import jp.co.isken.tax.entity.cashFlow.CFAccount;
import jp.co.isken.tax.entity.cashFlow.CFEntry;
import jp.co.isken.tax.entity.cashFlow.CFTransaction;
import jp.co.isken.tax.entity.transaction.Transaction;
import jp.co.isken.tax.exciseLibrary.service.CalExciseFacade;
import jp.co.isken.tax.util.HardCode;

public class CashFlow {

	public static void createCashFlowByEntries(List<Entry> entries)
			throws Exception {
		for (Entry each : entries) {
			CFTransaction cfTransaction = createCFTransaction(each);
			CFEntry cfEntry = createEntries(cfTransaction, each);
			createExciseEntry(cfEntry);
			cfTransaction.save();
		}
	}

	private static CFTransaction createCFTransaction(Entry entry) {
		return new CFTransaction(entry);
	}

	private static CFAccount getAccount(Party party) {
		CFAccount cfAccount = CFAccount.getAccount(HardCode.DAIKIN, party);
		return cfAccount;
	}

	private static CFAccount getExciseRateAccount(CFTransaction cfTransaction) {
		CFAccount cfAccount = CFAccount.getAccount(HardCode.EXCISE,
				cfTransaction.getCustomer());
		return cfAccount;
	}

	private static CFEntry createEntries(CFTransaction cfTransaction,
			Entry entry) throws Exception {
		BigDecimal subTotal = entry.getAmmount().multiply(
				entry.getAccount().getProduct().getPrice());
		return new CFEntry(cfTransaction,
				getAccount(cfTransaction.getCustomer()), subTotal);
	}

	public static void createExciseEntry(CFEntry taxableEntry) {
		String itemName = getItemName(taxableEntry);
		CFAccount account = getExciseRateAccount(taxableEntry.getTransaction());
		Transaction t = taxableEntry.getTransaction().getEntry()
				.getTransaction();
		BigDecimal taxAmount = null;
		try {
			taxAmount = getExciseAmount(taxableEntry, itemName, t);
		} catch (Exception e) {
			taxAmount = new BigDecimal("0.00");
		}
		new CFEntry(taxableEntry.getTransaction(), account, taxAmount);

	}

	private static String getItemName(CFEntry taxableEntry) {
		Entry entry = taxableEntry.getTransaction().getEntry();
		return entry.getAccount().getProduct().getItem().getName();
	}

	public static BigDecimal getExciseAmount(CFEntry taxableEntry,
			String itemName, Transaction t) throws Exception {
		return CalExciseFacade.getExciese(itemName, taxableEntry.getAmmount(),
				t.getWhenOccered(), t.getWhenNoticed(), t.getTransactionType()
						.getName(), t.getCanTax().getName(), t.getTaxableType()
						.getName());
	}

	public void saveTransaction(CFTransaction cfTransaction) {
		cfTransaction.save();
	}
}
