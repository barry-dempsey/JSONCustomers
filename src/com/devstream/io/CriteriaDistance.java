package com.devstream.io;

import java.util.ArrayList;
import java.util.List;

import models.Customer;
import utils.Criteria;

/**
 * Use of the Criteria/Filter Pattern
 * to sort the list of customers who are within
 * 100 Km
 * @author Barry Dempsey
 *
 */

public class CriteriaDistance implements Criteria {
	private final Double allowedDistance = 100.0;

	@Override
	public List<Customer> meetCriteria(List<Customer> customers) {
		List<Customer>filteredList = new ArrayList<>();
		for(Customer c : customers){
			/*
			 * auto-box to Double type so we can use
			 * the compareTo method.
			 */
			Double distance = c.getDistanceFrom();
			if(distance.compareTo(allowedDistance) < 0)
				filteredList.add(c);
		}
		return filteredList;
	}
}
