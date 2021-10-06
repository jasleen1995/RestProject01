package com.example.ec.demos;


/*
 * Enumeration of the region of California  
 * 
 */
public enum Region {

	Central_Cost("Central Coast"), Southern_California("Southern California"), Northern_California("Northern California"), Varies("Varies");
	
	private String label;
	//label is a private member on which the enumerations are defined.
	
	//Constructor is kept private
	private Region(String label) {
		this.label = label;
	}
	
	public static Region findByLabel(String byLabel) {
		for(Region r: Region.values()) {
			if(r.label.equals(byLabel)) {
				return r;
			}
		}
		return null;
	}
	
	
}
