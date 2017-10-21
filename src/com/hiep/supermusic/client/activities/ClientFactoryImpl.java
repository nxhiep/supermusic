package com.hiep.supermusic.client.activities;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.hiep.supermusic.client.activities.basic.BasicView;
import com.hiep.supermusic.client.activities.home.HomeView;
import com.hiep.supermusic.client.activities.home.HomeViewImpl;
import com.hiep.supermusic.client.activities.play.PlayView;
import com.hiep.supermusic.client.activities.play.PlayViewImpl;
import com.hiep.supermusic.client.activities.search.SearchView;
import com.hiep.supermusic.client.activities.search.SearchViewImpl;

public class ClientFactoryImpl implements ClientFactory {
	private SimpleEventBus eventBus;
	private PlaceController placeController;
	private BasicView basicView;
	private HomeView homeView;
	private PlayView playView;
	private SearchView searchView;
	
	public ClientFactoryImpl() {
		eventBus = new SimpleEventBus();
		placeController = new PlaceController(eventBus);
	}
	
	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public HomeView getHomeView() {
		if(homeView==null){
			homeView= new HomeViewImpl();
		}
		basicView = homeView;
		return homeView;
	}

	@Override
	public BasicView getBasicView() {
		return basicView;
	}
	
	@Override
	public SearchView getSearchView() {
		if(searchView == null) {
			searchView = new SearchViewImpl();
		}
		return searchView;
	}
	
	@Override
	public PlayView getPlayView() {
		if(playView==null){
			playView= new PlayViewImpl();
		}
		return playView;
	}
}
