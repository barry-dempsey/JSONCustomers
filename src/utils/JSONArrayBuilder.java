package utils;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Takes a String [] of JSONObject formed
 * data and converts this to a String and
 * finally to a JSONArray of JSONObjects
 * @author Barry Dempsey
 *
 */

public class JSONArrayBuilder {
	
	public static JSONArray constructJSONArrayFromArray(String [] data) {
		if(data == null) return null;
		try {
			String asJSONString = Arrays.toString(data);	
			return new JSONArray(asJSONString);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
