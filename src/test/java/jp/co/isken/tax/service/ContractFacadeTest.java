package jp.co.isken.tax.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import jp.co.isken.tax.util.Util;

import org.junit.Test;

public class ContractFacadeTest {

	@Test
	public void 契約を記録する() {
		Date date = Util.stringToDate("20120103000000");
		ContractFacade.saveContract(date, "SATRO", "切捨て", "計上日");
		String expected = Util.joinStrings(date.toString(), date.toString(),"SATRO", "切捨て", "計上日");
		assertThat(ContractFacade.getContract("SATRO", date).toString(),
				is(expected));
	}
}
