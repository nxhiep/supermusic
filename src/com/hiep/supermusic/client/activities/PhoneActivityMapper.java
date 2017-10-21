package com.hiep.supermusic.client.activities;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.hiep.supermusic.client.activities.home.HomeActivity;
import com.hiep.supermusic.client.activities.home.HomePlace;
import com.hiep.supermusic.client.activities.play.PlayActivity;
import com.hiep.supermusic.client.activities.play.PlayPlace;
import com.hiep.supermusic.client.activities.search.SearchActivity;
import com.hiep.supermusic.client.activities.search.SearchPlace;

public class PhoneActivityMapper implements ActivityMapper {
	
	private ClientFactory clientFactory;
	
	public PhoneActivityMapper(ClientFactory clientFactory){
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace)
			return new HomeActivity(clientFactory, place);
		if(place instanceof SearchPlace)
			return new SearchActivity(clientFactory, place);
		if(place instanceof PlayPlace)
			return new PlayActivity(clientFactory, place);
		return new HomeActivity(clientFactory, place);
	}
}
