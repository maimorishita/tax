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
	public void アカウントの作成ができる() {
		try {
			Account target = Account.getAccount("ナックバーガー");
		} catch (Exception e) {
			fail("");
		}
		Iterator<Account> iter = Account.iterator();
		iter.hasNext();
		assertThat(iter.next().getName(), is("ナックバーガー"));
		assertThat(iter.hasNext(), is(false));
	}

	@Test(expected = Exception.class)
	public void 品目が登録されていない場合は商流勘定は作成できない() throws Exception {
		Account target =Account.getAccount("ほげほげ");
	}

}
