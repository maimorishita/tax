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
	public void �A�J�E���g�̍쐬���ł���() throws Exception {
		try {
			Account target = Account.getAccount("�i�b�N�o�[�K�[",
					Party.getParty("SATO"));
		} catch (Exception e) {
			fail("");
		}
		Iterator<Account> iter = Account.iterator();
		iter.hasNext();
		Account account = iter.next();
		assertThat(account.getName(), is("�i�b�N�o�[�K�["));
		assertThat(account.getProduct(),
				is(Product.getProductByName("�i�b�N�o�[�K�[")));
		assertThat(account.getParty(), is(Party.getParty("SATO")));
		assertThat(iter.hasNext(), is(false));
	}

	@Test(expected = Exception.class)
	public void �i�ڂ��o�^����Ă��Ȃ��ꍇ�͏�������͍쐬�ł��Ȃ�() throws Exception {
		Account target = Account.getAccount("�ق��ق�", Party.getParty("SATO"));
	}

}
