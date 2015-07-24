package com.devstream.io;
import java.util.ArrayList;
import java.util.List;

import models.Customer;

import org.json.JSONArray;

/**
 * Singleton pattern to persist
 * Customer data during runtime
 * @author Barry Dempsey
 *
 */


public class DataHolder {
	private static DataHolder dataHolder;
	private JSONArray apiResponse;
	private ArrayList<Customer>customers = new ArrayList<>();
	private boolean isConnected = false;
	
	private DataHolder() {

	}
	
	/**
	 * Thread-safe static DataHolder instance
	 * @return a singleton instance of DataHolder
	 */
	public static synchronized DataHolder getDataHolder() {
		if(dataHolder == null) {
			dataHolder = new DataHolder();
		}
		return dataHolder;
	}

	public JSONArray getApiResponse() {
		return apiResponse;
	}

	public void setApiResponse(JSONArray apiResponse) {
		this.apiResponse = apiResponse;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public static void setDataHolder(DataHolder dataHolder) {
		DataHolder.dataHolder = dataHolder;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}
}
