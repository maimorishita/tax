package jp.co.isken.tax.service;

import java.util.Iterator;
import java.util.Vector;

import jp.co.isken.tax.entity.Party;
import jp.co.isken.tax.entity.Product;

public class PartyFacade {

	public static Iterator iterator() {
		  Vector<Party> retval = new Vector<Party>();
		    Iterator<Party> iter = Party.iterator();
		    while(iter.hasNext()){
		      retval.add(iter.next());
		    }
		    return retval.iterator();
	}
}
