package jp.co.isken.tax.run;

import jp.co.isken.tax.entity.Contract;
import jp.co.isken.tax.entity.Item;
import jp.co.isken.tax.entity.Party;
import jp.co.isken.tax.entity.Product;
import jp.co.isken.tax.util.Util;



public class Initializer {
	
	public static void init() {
	    Product nacburger = new Product("�i�b�N�o�[�K�[", 90);
	    Product naccheeseburger = new Product("�i�b�N�`�[�Y�o�[�K�[", 120);
	    Product nacpotato_s = new Product("�i�b�N�|�e�gS", 150);
	    Product nacpotato_m = new Product("�i�b�N�|�e�gM", 220);
	    Product naccola = new Product("�i�b�N�R�[��", 150);
	    Product nacshake = new Product("�i�b�N�V�F�[�N", 200);

//	    Category food = new Category("�t�[�h");
//	    Category drink = new Category("�h�����N");

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
	    Product nacburger = new Product("�i�b�N�o�[�K�[", 90);
	    Product naccheeseburger = new Product("�i�b�N�`�[�Y�o�[�K�[", 120);
	    Product nacpotato_s = new Product("�i�b�N�|�e�gS", 150);

	    Party p1 = new Party("SATO");
	    Party p2 = new Party("GOTO");
	    Party p3 = new Party("MUTO");
	    
	    Contract c = new Contract(Util.stringToDate("20130707000000"), p1, "�؎̂�", "�v���");
	    
	    
	  }

}
