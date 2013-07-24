package jp.co.isken.tax.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import jp.co.isken.tax.util.Util;

import org.junit.Test;

public class ContractTest {

	@Test
	public void 契約を記録する() {
		Date date = Util.stringToDate("20120103000000");
		Contract c = new Contract(date, "SATRO", "切捨て", "計上日");
		c.save();
		Contract target = Contract.getContracat("SATRO", date);
		String expected = Util.joinStrings(date.toString(),date.toString(), "SATRO", "切捨て", "計上日");
		assertThat(target.toString(), is(expected));
	}
}
