package jp.co.isken.tax.service;

import java.util.Iterator;
import java.util.Vector;

import jp.co.isken.tax.entity.Party;
import jp.co.isken.tax.entity.Product;

public class CustomerFacade {

	private Party customer;

	  protected CustomerFacade(Party customer ){
	    this.customer = customer;
	  }


	  public String toString(){
	    return this.customer.getId() + "Å@" + this.customer.getName();
	  }
}
