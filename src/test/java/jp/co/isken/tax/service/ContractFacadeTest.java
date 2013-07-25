package jp.co.isken.tax.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import jp.co.isken.tax.util.Util;

import org.junit.Test;

public class ContractFacadeTest {

	@Test
	public void �_����L�^����() {
		Date date = Util.stringToDate("20120103000000");
		ContractFacade.saveContract(date, "SATO", "�؎̂�", "�v���");
		String expected = "0 : 20120103000000, 20120103000000, SATO, �؎̂�, �v���";
		assertThat(ContractFacade.getContract("SATO", date).toString(),
				is(expected));
	}
}
