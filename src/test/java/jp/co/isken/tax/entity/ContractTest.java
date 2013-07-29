package jp.co.isken.tax.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import jp.co.isken.tax.run.Initializer;
import jp.co.isken.tax.util.Util;

import org.junit.Before;
import org.junit.Test;

public class ContractTest {
	
	@Before
	public void before(){
		Initializer.test();
	}

	@Test
	public void Œ_–ñ‚ğ‹L˜^‚·‚é() throws Exception {
		Date date = Util.stringToDate("20120103000000");
		new Contract(date, Party.getParty("SATO"), "Ø‚èÌ‚Ä", "Œvã“ú");
		Contract target = Contract.getContracat("SATO", date);
		String expected = "1 : 20120103000000, 20120103000000, SATO, Ø‚èÌ‚Ä, Œvã“ú";
		assertThat(target.toString(), is(expected));
	}
}
