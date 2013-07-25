package jp.co.isken.tax.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import jp.co.isken.tax.util.Util;

import org.junit.Test;

public class ContractTest {

	@Test
	public void �_����L�^����() {
		Date date = Util.stringToDate("20120103000000");
		new Contract(date, Party.getParty("SATO"), "�؎̂�", "�v���");
		Contract target = Contract.getContracat("SATO", date);
		String expected = "0 : 20120103000000, 20120103000000, SATO, �؎̂�, �v���";
		assertThat(target.toString(), is(expected));
	}
}
