package jp.co.isken.tax.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import jp.co.isken.tax.util.Util;

import org.junit.Test;

public class ContractFacadeTest {

	@Test
	public void Œ_–ñ‚ğ‹L˜^‚·‚é() {
		Date date = Util.stringToDate("20120103000000");
		ContractFacade.saveContract(date, "SATO", "ØÌ‚Ä", "Œvã“ú");
		String expected = "0 : 20120103000000, 20120103000000, SATO, ØÌ‚Ä, Œvã“ú";
		assertThat(ContractFacade.getContract("SATO", date).toString(),
				is(expected));
	}
}
