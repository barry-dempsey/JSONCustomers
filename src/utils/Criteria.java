package utils;

import java.util.List;

import models.Customer;

public interface Criteria {
	
	List<Customer>meetCriteria(List<Customer>customers);

}
