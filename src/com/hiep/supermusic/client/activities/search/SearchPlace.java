package com.hiep.supermusic.client.activities.search;

import com.google.gwt.place.shared.Place;
import com.hiep.supermusic.client.activities.basic.BasicPlace;

public class SearchPlace extends BasicPlace {
	
	private String keySearch = "";
	
	public SearchPlace() {
		super();
	}
	
	public SearchPlace(Place place, String key) {
		super(place);
		this.keySearch = key;
	}
	
	public String getKeySearch() {
		return keySearch;
	}
	
	public void setKeySearch(String keySearch) {
		this.keySearch = keySearch;
	}
}
