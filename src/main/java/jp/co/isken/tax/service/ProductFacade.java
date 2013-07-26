package jp.co.isken.tax.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isken.tax.entity.Product;

public class ProductFacade {

	 private Product product;

	  protected ProductFacade( Product product ){
	    this.product = product;
	  }

	  public static Iterator<Product> iterator(){
	    List<Product> retval = new ArrayList<Product>();
	    Iterator<Product> iter = Product.iterator();
	    while(iter.hasNext()){
	      retval.add(iter.next());
	    }
	    return retval.iterator();
	  }

	  public String toString(){
	    return this.product.getId() + "Å@" + this.product.getName() + "Å@" + this.product.getPrice();
	  }

}
