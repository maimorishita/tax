package jp.co.isken.tax.run;

import java.math.BigDecimal;

import jp.co.isken.tax.entity.Account;
import jp.co.isken.tax.entity.CFAccount;
import jp.co.isken.tax.entity.CFEntry;
import jp.co.isken.tax.entity.CFTransaction;
import jp.co.isken.tax.entity.Contract;
import jp.co.isken.tax.entity.Entry;
import jp.co.isken.tax.entity.Party;
import jp.co.isken.tax.entity.Product;
import jp.co.isken.tax.entity.Transaction;
import jp.co.isken.tax.exciseLibrary.Item;
import jp.co.isken.tax.exciseLibrary.Tax;
import jp.co.isken.tax.exciseLibrary.TaxRate;
import jp.co.isken.tax.exciseLibrary.Term;
import jp.co.isken.tax.util.HardCode;
import jp.co.isken.tax.util.Util;

public class Initializer {

	public static void init() {
		Product nacburger = new Product("�i�b�N�o�[�K�[", 90);
		Product naccheeseburger = new Product("�i�b�N�`�[�Y�o�[�K�[", 120);
		Product nacpotato_s = new Product("�i�b�N�|�e�gS", 150);
		Product nacpotato_m = new Product("�i�b�N�|�e�gM", 220);
		Product naccola = new Product("�i�b�N�R�[��", 150);
		Product nacshake = new Product("�i�b�N�V�F�[�N", 200);

		// Category food = new Category("�t�[�h");
		// Category drink = new Category("�h�����N");

		// food.add(nacburger);
		// food.add(naccheeseburger);
		// food.add(nacpotato_s);
		// food.add(nacpotato_m);
		// drink.add(naccola);
		// drink.add(nacshake);

		Party p1 = new Party("SATO");
		Party p2 = new Party("GOTO");
		Party p3 = new Party("MUTO");
	}

	public static void test() {
		Contract.init();
		Product.init();
		Transaction.init();
		CFTransaction.init();
		Entry.init();
		CFEntry.init();
		Account.init();
		CFAccount.init();

		Item ei1 = new Item("�i�b�N�o�[�K�[");
		Item ei2 = new Item("�i�b�N�`�[�Y�o�[�K�[");
		Item ei3 = new Item("�i�b�N�|�e�gS");

		Term term = new Term(Util.stringToDate("20120401000000"),
				Util.stringToDate("21000401000000"));

		Tax tax = new Tax(HardCode.EXCISE);
		BigDecimal bd = new BigDecimal("0.05");
		TaxRate rate1 = new TaxRate(tax, bd, ei1, term);
		TaxRate rate2 = new TaxRate(tax, bd, ei2, term);
		TaxRate rate3 = new TaxRate(tax, bd, ei3, term);

		Product nacburger = new Product("�i�b�N�o�[�K�[", 90);
		Product naccheeseburger = new Product("�i�b�N�`�[�Y�o�[�K�[", 120);
		Product nacpotato_s = new Product("�i�b�N�|�e�gS", 150);

		Party p1 = new Party("SATO");
		Party p2 = new Party("GOTO");
		Party p3 = new Party("MUTO");

		Contract c = new Contract(Util.stringToDate("20130707000000"), p1,
				"�؎̂�", "�v���");

	}

}
