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
		ContractFacade.saveContract(date, "SATRO", "�؎̂�", "�v���");
		String expected = Util.joinStrings(date.toString(), date.toString(),"SATRO", "�؎̂�", "�v���");
		assertThat(ContractFacade.getContract("SATRO", date).toString(),
				is(expected));
	}
}
