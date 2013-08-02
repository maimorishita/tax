package jp.co.isken.tax.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.util.Iterator;

import jp.co.isken.tax.util.Initializer;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {

	@Before
	public void setUp() {
		Initializer.test();
	}

	@Test
	public void アカウントの作成ができる() throws Exception {
		try {
			Account target = Account.getAccount("ナックバーガー",
					Party.getParty("SATO"));
		} catch (Exception e) {
			fail("");
		}
		Iterator<Account> iter = Account.iterator();
		iter.hasNext();
		Account account = iter.next();
		assertThat(account.getName(), is("ナックバーガー"));
		assertThat(account.getProduct(),
				is(Product.getProductByName("ナックバーガー")));
		assertThat(account.getParty(), is(Party.getParty("SATO")));
		assertThat(iter.hasNext(), is(false));
	}

	@Test(expected = Exception.class)
	public void 品目が登録されていない場合は商流勘定は作成できない() throws Exception {
		Account target = Account.getAccount("ほげほげ", Party.getParty("SATO"));
	}

}
