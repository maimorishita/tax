package jp.co.isken.tax.entity;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import jp.co.isken.tax.entity.transaction.TransactionType;

import org.junit.Test;

public class TransactionTypeTest {

	@Test
	public void •¶Žš—ñ‚©‚çEnum‚ðŽæ“¾‚·‚é() {
		assertThat(TransactionType.getEnum("”Ì”„"), is(TransactionType.SALE));
	}
	
	@Test
	public void id‚©‚çEnumŽæ“¾‚·‚é() {
		assertThat(TransactionType.getEnumById(1), is(TransactionType.SALE));
	}

}
