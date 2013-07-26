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
	public void 契約を記録する() throws Exception {
		Date date = Util.stringToDate("20120103000000");
		ContractFacade.saveContract(date, "SATO", "切捨て", "計上日");
		String expected = "1 : 20120103000000, 20120103000000, SATO, 切捨て, 計上日";
		assertThat(ContractFacade.getContract("SATO", date).toString(),
				is(expected));
	}
}
