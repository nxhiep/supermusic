package com.hiep.supermusic.client.activities;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.hiep.supermusic.client.activities.basic.BasicView;
import com.hiep.supermusic.client.activities.home.HomeView;
import com.hiep.supermusic.client.activities.play.PlayView;
import com.hiep.supermusic.client.activities.search.SearchView;

public interface ClientFactory {
	PlaceController getPlaceController();

	EventBus getEventBus();

	BasicView getBasicView();
	
	HomeView getHomeView();

	PlayView getPlayView();

	SearchView getSearchView();
}
