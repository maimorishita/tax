package jp.co.isken.tax.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import jp.co.isken.tax.entity.transaction.TransactionType;

import org.junit.Test;

public class TransactionTypeTest {

	@Test
	public void 文字列からEnumを取得する() {
		assertThat(TransactionType.getEnum("販売"), is(TransactionType.SALE));
	}
	
	@Test
	public void idからEnum取得する() {
		assertThat(TransactionType.getEnumById(1), is(TransactionType.SALE));
	}

}
