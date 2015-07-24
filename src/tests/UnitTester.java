package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import models.Customer;

import org.junit.Test;

import utils.DecimalRounder;
import utils.FileWriterUtil;
import utils.SortByDistance;
import utils.SortByID;

import com.devstream.io.DataHolder;
import com.devstream.io.HttpConnector;

/**
 * JUnit tests for the application
 * @author Barry Dempsey
 *
 */


public class UnitTester{

	@Test
    public void test() throws Exception {
		/*
		 * test for the DecimalRounder utility
		 */
		assertEquals(2.0, DecimalRounder.rounder(1, 2.0448837844), 0.001);
		
		/*
		 * test the SortByID for null
		 */
		assertNull(null, SortByID.sortedList(null));
		
		/*
		 * test the SortByID Utility
		 */
		List<Customer>list = new ArrayList<>();
		list.add(new Customer("1"));
		list.add(new Customer("2"));
		list.add(new Customer("3"));
		list.add(new Customer("4"));
		list.add(new Customer("5"));
		assertEquals(list, SortByID.sortedList(list));
		
		/*
		 * test the SortByDistance for null
		 */
		assertNull(null, SortByDistance.sortedList(null));
		
		/*
		 * test the HTTP Connection does not return
		 * null response	
		 */
		String url = "https://gist.githubusercontent.com/brianw/"
				+ "19896c50afa89ad4dec3/raw/"
				+ "6c11047887a03483c50017c1d451667fd62a53ca/"
				+ "gistfile1.txt";
		ExecutorService service = Executors.newCachedThreadPool();
		Callable<String>callable = 
				new HttpConnector(url);
		Future<String>future = service.submit(callable);
		service.shutdown();
		assertNotNull(future.get());
		
		/*
		 * test the connection is closed after
		 * we get the response
		 */
		assertTrue(!DataHolder.getDataHolder().isConnected());
		
		/*
		 * test for customer list not null
		 */
		assertNotNull(DataHolder.getDataHolder().getCustomers());	
		
		/*
		 * test the file writing utility
		 * we have file write protection on the file
		 * so should turn true if the file does not yet exist
		 */
		assertTrue(FileWriterUtil.printToFile("Tester"));
    }
}