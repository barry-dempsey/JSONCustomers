package views;

import java.util.List;

import models.Customer;

/**
 * Display all data to the user
 * @author Barry Dempsey
 *
 */

public class CustomerView {
	private List<Customer>customers;
	
	public CustomerView(List<Customer>customers) {
		this.customers = customers;
	}
	
	public void displayCustomers(){
		/*
		 * System out is used here only for
		 * demonstration purposes. Normally we would 
		 * populate a listview (Android) with
		 * the List of customers
		 */
		System.out.println("User ID" + "\t\tName" + 
					" \t\t\tLong." + " \t\t\tLat" + 
					" \t\t\tDistance" + "\n");
		for(Customer c : customers)
			System.out.println("ID: " + c);
	}
}
