package jp.co.isken.tax.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.util.Iterator;

import jp.co.isken.tax.run.Initializer;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {

	@Before
	public void setUp() {
		Initializer.test();
	}

	@Test
	public void �A�J�E���g�̍쐬���ł���() {
		try {
			Account target = Account.getAccount("�i�b�N�o�[�K�[");
		} catch (Exception e) {
			fail("");
		}
		Iterator<Account> iter = Account.iterator();
		iter.hasNext();
		assertThat(iter.next().getName(), is("�i�b�N�o�[�K�["));
		assertThat(iter.hasNext(), is(false));
	}

	@Test(expected = Exception.class)
	public void �i�ڂ��o�^����Ă��Ȃ��ꍇ�͏�������͍쐬�ł��Ȃ�() throws Exception {
		Account target =Account.getAccount("�ق��ق�");
	}

}
