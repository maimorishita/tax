package jp.co.isken.tax.service;

import java.util.Date;
import java.util.Iterator;

import jp.co.isken.tax.entity.CalTaxOption;
import jp.co.isken.tax.entity.Contract;
import jp.co.isken.tax.entity.Party;

public class ContractFacade {

	public static void saveContract(Date contractedDate, Date effectiveDate,
			String party, int calTypeId) {
		Contract c = new Contract(contractedDate, effectiveDate,
				Party.getParty(party), CalTaxOption.getEnumById(calTypeId));
	}

	public static Contract getContracatByContractedDate(String partyName,
			Date date) throws Exception {
		Iterator<Contract> iter = Contract.iterator();
		while (iter.hasNext()) {
			Contract c = iter.next();
			if (c.getCustomer().getName().equals(partyName)
					&& c.getContractDate().equals(date)) {
				return c;
			}
		}
		throw new Exception();
	}
}
