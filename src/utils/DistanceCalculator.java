package utils;

/**
 * Utility for calculating distance between
 * 2 geographical points using longitude and 
 * latitude.
 * @author Barry Dempsey
 *
 */

public class DistanceCalculator {
	
	/**
	 * Gets the distance between 2 geo points
	 * @param fromLat
	 * @param fromLng
	 * @param lat
	 * @param lng
	 * @return the distance in KM's
	 */
	
	public static double distanceBetween(float fromLat, float fromLng, 
			float lat, float lng) {
		/*
		 * we use an off-set to allow for 
		 * non-'as-the-crow-flies' measurements
		 * (allow for building's etc.
		 */
		final double OFFSET = 0.69;
		double distance = 
				((Math.acos(Math.sin(fromLat * Math.PI / 180) * Math.sin(lat * Math.PI / 180) 
						+ Math.cos(fromLat * Math.PI / 180) 
						* Math.cos(lat * Math.PI / 180) * Math.cos((fromLng - lng) 
								* Math.PI / 180)) * 180 / Math.PI) * 60 * 1.1515 * 1.609344);
				return distance / OFFSET; 		
	}
}
