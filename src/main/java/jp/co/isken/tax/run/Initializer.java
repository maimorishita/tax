package jp.co.isken.tax.run;

import jp.co.isken.tax.entity.Contract;
import jp.co.isken.tax.entity.Item;
import jp.co.isken.tax.entity.Party;
import jp.co.isken.tax.entity.Product;
import jp.co.isken.tax.entity.Transaction;
import jp.co.isken.tax.util.Util;



public class Initializer {
	
	public static void init() {
	    Product nacburger = new Product("ナックバーガー", 90);
	    Product naccheeseburger = new Product("ナックチーズバーガー", 120);
	    Product nacpotato_s = new Product("ナックポテトS", 150);
	    Product nacpotato_m = new Product("ナックポテトM", 220);
	    Product naccola = new Product("ナックコーラ", 150);
	    Product nacshake = new Product("ナックシェーク", 200);

//	    Category food = new Category("フード");
//	    Category drink = new Category("ドリンク");

//	    food.add(nacburger);
//	    food.add(naccheeseburger);
//	    food.add(nacpotato_s);
//	    food.add(nacpotato_m);
//	    drink.add(naccola);
//	    drink.add(nacshake);

	    Party p1 = new Party("SATO");
	    Party p2 = new Party("GOTO");
	    Party p3 = new Party("MUTO");
	  }
	
	public static void test() {	
		Contract.init();
		Product.init();
		Transaction.init();
		
		
	    Product nacburger = new Product("ナックバーガー", 90);
	    Product naccheeseburger = new Product("ナックチーズバーガー", 120);
	    Product nacpotato_s = new Product("ナックポテトS", 150);

	    Party p1 = new Party("SATOR");
	    Party p2 = new Party("GOTO");
	    Party p3 = new Party("MUTO");
	    
	    Contract c = new Contract(Util.stringToDate("20130707000000"), p1, "切捨て", "計上日");
	    
	    
	  }

}
