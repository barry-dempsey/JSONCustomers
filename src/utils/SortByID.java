package utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import models.Customer;

/**
 * Utility for sorting Lists by
 * user ID
 * @author Barry Dempsey
 *
 */

public class SortByID {
	
	public static ArrayList<Customer>sortedList(List<Customer>customers) {
		if(customers == null) return null;
		
		Set<Customer>set = new TreeSet<Customer>(new Comparator<Customer>() {

			@Override
			public int compare(Customer o1, Customer o2) {
				/*
				 * use of auto-boxing here to Integer
				 * so we can use the compareTo on Integer
				 * objects
				 */
				Integer id1 = Integer.parseInt(o1.getUserId());
				Integer id2 = Integer.parseInt(o2.getUserId());
				if(id1.compareTo(id2) == 0)
					return 0;
				else if(id1.compareTo(id2) < 0) 
					return -1;
				else
					return 1;
			}		
		});
		set.addAll(customers);
		final ArrayList<Customer>sortedList = new ArrayList<Customer>(set);
		return sortedList;
	}
}
