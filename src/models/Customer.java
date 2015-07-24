package models;

import utils.DecimalRounder;

/**
 * Data model Customer
 * @author Barry Dempsey
 *
 */

public class Customer {
	private String userId;
	private String name;
	private float longitude;
	private float latitude;
	private double distanceFrom;
	
	public Customer() {
		
	}
	
	/**
	 * single argument Constructor
	 * @param userId
	 */
	public Customer(String userId) {
		this.userId = userId;
	}
	
	/**
	 * 4 Argument Constructor for Customer
	 * @param userId
	 * @param name
	 * @param longitude
	 * @param latitude
	 */
	public Customer(String userId, String name, 
			float longitude, float latitude) {
		this.userId = userId;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;		
	}

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public float getLongitude() {
		return longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	public double getDistanceFrom() {
		return distanceFrom;
	}

	public void setDistanceFrom(double distanceFrom) {
		this.distanceFrom = distanceFrom;
	}

	public String toString() {
		/*
		 * DecimalRounder will round to x decimal places
		 * but by concatenating the Strings here
		 * undoes this operation.
		 */
		return userId + ":\t " + name + "\t\t" + longitude + "\t\t" + 
				latitude + "\t\t" + DecimalRounder.rounder(2, distanceFrom) + " Kms";
	}
}
