package utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import models.Customer;

/**
 * Utility class used for sorting Lists
 * by their distance from a static location
 * @author Barry Dempsey
 *
 */


public class SortByDistance {
	
	public static ArrayList<Customer>sortedList(List<Customer>customers) {
		if(customers == null) return null;
		
		Set<Customer>set = new TreeSet<Customer>(new Comparator<Customer>() {

			@Override
			public int compare(Customer o1, Customer o2) {
				Double distance1 = o1.getDistanceFrom();
				Double distance2 = o2.getDistanceFrom();
				if(distance1.compareTo(distance2) == 0)
					return 0;
				else if(distance1.compareTo(distance2) < 0) 
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
