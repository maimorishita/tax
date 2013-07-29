package jp.co.isken.tax.entity;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TransactionTypeTest {

	@Test
	public void test() {
		assertThat(TransactionType.getEnum("”Ì”„"), is(TransactionType.SALE));
	}

}
