package utils;

/**
 * Utility for rounding float values
 * Barry Dempsey
 */

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecimalRounder {
	
	/**
	 * round the value to the x decimal places
	 * @param places
	 * @param d
	 * @return the rounded float
	 */
	
	public static double rounder(int places, double d) {
		BigDecimal bd = new BigDecimal(d).setScale(places, RoundingMode.HALF_EVEN);
		return bd.floatValue();
	}

}
