package jp.co.isken.tax.entity;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import jp.co.isken.tax.entity.transaction.TransactionType;

import org.junit.Test;

public class TransactionTypeTest {

	@Test
	public void �����񂩂�Enum���擾����() {
		assertThat(TransactionType.getEnum("�̔�"), is(TransactionType.SALE));
	}
	
	@Test
	public void id����Enum�擾����() {
		assertThat(TransactionType.getEnumById(1), is(TransactionType.SALE));
	}

}
