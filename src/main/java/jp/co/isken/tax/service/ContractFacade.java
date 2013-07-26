package jp.co.isken.tax.service;

import java.util.Date;

import jp.co.isken.tax.entity.Contract;
import jp.co.isken.tax.entity.Party;

public class ContractFacade {

	public static void saveContract(Date date, String party, String calType,
			String baseDateType) {
		Contract c = new Contract(date, Party.getParty(party), calType,baseDateType);

	}

	public static Contract getContract(String partyName, Date date) {
		return Contract.getContracat(partyName, date);
	}
}
