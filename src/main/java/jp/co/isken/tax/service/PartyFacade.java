package jp.co.isken.tax.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isken.tax.entity.Party;

public class PartyFacade {

	public static Iterator<Party> iterator() {
		  List<Party> retval = new ArrayList<Party>();
		    Iterator<Party> iter = Party.iterator();
		    while(iter.hasNext()){
		      retval.add(iter.next());
		    }
		    return retval.iterator();
	}
}
