package jp.co.isken.tax.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import jp.co.isken.tax.run.Initializer;
import jp.co.isken.tax.util.Util;

import org.junit.Before;
import org.junit.Test;

public class ContractFacadeTest {

	@Before
	public void setUp() {
		Initializer.test();
	}

	@Test
	public void Œ_–ñ‚ğ‹L˜^‚·‚é() throws Exception {
		Date date1 = Util.stringToDate("20120103000000");
		Date date2 = Util.stringToDate("20120104000000");
		ContractFacade.saveContract(date1, date2, "SATO", "ØÌ‚Ä");
		String expected = "1 : 20120103000000, 20120104000000, SATO, ØÌ‚Ä";
		assertThat(ContractFacade.getContracatByContractedDate("SATO", date1).toString(),
				is(expected));
	}
}
