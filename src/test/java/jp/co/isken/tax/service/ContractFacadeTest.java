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
	public void �_����L�^����() throws Exception {
		Date date = Util.stringToDate("20120103000000");
		ContractFacade.saveContract(date, "SATO", "�؎̂�", "�v���");
		String expected = "1 : 20120103000000, 20120103000000, SATO, �؎̂�, �v���";
		assertThat(ContractFacade.getContract("SATO", date).toString(),
				is(expected));
	}
}
