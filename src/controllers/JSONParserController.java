package controllers;
import java.util.ArrayList;
import java.util.List;

import models.Customer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import utils.Criteria;
import utils.DistanceCalculator;
import utils.FileWriterUtil;
import utils.SortByDistance;
import utils.SortByID;
import views.CustomerView;

import com.devstream.io.CriteriaDistance;
import com.devstream.io.DataHolder;

/**
 * Parse JSONArray data from
 * the response from the server. 
 * @author Barry Dempsey
 *
 */

public class JSONParserController {
	private static final float [] userCoords 
		= new float []{53.3381985f, -6.2592576f};
	private static CustomerView view;
	
	/**
	 * Takes a JSONArray as parameter
	 * and creates JOSNObjects from this
	 * @param jArray
	 */
	
	public static void parseTheResponse(JSONArray jArray) {
		/*
		 * we persist this response in the DataHolder
		 * should we need the original response
		 */
		DataHolder.getDataHolder().setApiResponse(jArray);
		Customer customer = null;
		CustomerView view = null;
		JSONObject obj = null;
		List<Customer>customers = new ArrayList<Customer>();
		for(int i = 0; i < jArray.length(); i++){
			try {
				obj = jArray.getJSONObject(i);
				/*
				 * create Customer objects and set
				 * their properties from the JSONObject
				 * data. Then add these object to a List
				 */
				customer = new Customer();
				customer.setName(obj.getString("name"));
				customer.setUserId(String.valueOf(obj.getInt("user_id")));
				float lat = (float)obj.getDouble("latitude");
				float lon = (float)obj.getDouble("longitude");
				customer.setLatitude(lat);
				customer.setLongitude(lon);
				double distance = DistanceCalculator.distanceBetween(
						userCoords[0], userCoords[1], lat, lon);
				customer.setDistanceFrom(distance);
				customers.add(customer);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		/*
		 * use the view object to show the results
		 */
		view = new CustomerView(SortByDistance.sortedList(customers));
		System.out.println("/// SORTED LIST BY DISTANCE ///\n");
		view.displayCustomers();
		
		System.out.println("\n/// SORTED WITHIN 100KM ///\n");
		/*
		 * use of the Criteria/Filter pattern to
		 * sort the list of customers who are less than
		 * 100Km from our office
		 */
		Criteria criteria = new CriteriaDistance();
		List<Customer>sortedList = criteria.meetCriteria(customers);
		view = new CustomerView(SortByID.sortedList(sortedList));
		view.displayCustomers();
		/*
		 * lets persist this new sorted list in the DataHolder
		 */
		DataHolder.getDataHolder().setCustomers(SortByID.sortedList(sortedList));
		/*
		 * Print the invitee List to a text doc.
		 */
		StringBuilder sb = new StringBuilder();
		for(Customer c : SortByID.sortedList(sortedList)){
			sb.append(c.toString() + "\n");
		}
		FileWriterUtil.printToFile(sb.toString());
	}
}
