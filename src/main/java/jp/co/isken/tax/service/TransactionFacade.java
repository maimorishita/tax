package jp.co.isken.tax.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import jp.co.isken.tax.entity.Account;
import jp.co.isken.tax.entity.Entry;
import jp.co.isken.tax.entity.transaction.Transaction;

public class TransactionFacade {

	public static Map<Account, BigDecimal> subTotal(Transaction t) {
		Map<Account, BigDecimal> map = new HashMap<Account, BigDecimal>();
		for (Entry entry : t.getEntries()) {
			Account account = entry.getAccount();
			if (map.containsKey(account)) {
				BigDecimal tmp = map.get(account);
				tmp = tmp.add(entry.getAmmount());
				map.put(account, tmp);
			} else {
				BigDecimal subtotal = entry.getAmmount().multiply(
						entry.getAccount().getProduct().getPrice());
				map.put(account, subtotal);
			}
		}
		return map;
	}
}
