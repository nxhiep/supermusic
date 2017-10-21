package com.hiep.supermusic.client.activities.search;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.hiep.supermusic.client.ClientData;
import com.hiep.supermusic.client.activities.ClientFactory;
import com.hiep.supermusic.client.activities.basic.BasicActivity;
import com.hiep.supermusic.client.activities.event.DownloadSongEvent;
import com.hiep.supermusic.client.activities.event.DownloadSongEventHandler;
import com.hiep.supermusic.client.activities.event.PlaySongEvent;
import com.hiep.supermusic.client.activities.event.PlaySongEventHandler;
import com.hiep.supermusic.client.activities.event.SelectSongEvent;
import com.hiep.supermusic.client.activities.event.SelectSongEventHandler;
import com.hiep.supermusic.client.activities.play.PlayPlace;
import com.hiep.supermusic.shared.Song;

public class SearchActivity extends BasicActivity {

	private SearchView view;
	private String keySearch = "";

	public SearchActivity(ClientFactory clientFactory, Place place) {
		super(clientFactory, place);
		keySearch = ((SearchPlace) place).getKeySearch();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getSearchView();
		super.start(panel, eventBus, view);
		panel.setWidget(view);
	}

	@Override
	protected void bind() {
		super.bind();
		addHandlerRegistration(eventBus.addHandler(SelectSongEvent.TYPE, new SelectSongEventHandler() {
			
			@Override
			public void onUpdate(SelectSongEvent event) {
				goTo(new PlayPlace(place, event.getSong()));
			}
		}));
		addHandlerRegistration(eventBus.addHandler(DownloadSongEvent.TYPE, new DownloadSongEventHandler() {
					
			@Override
			public void onUpdate(DownloadSongEvent event) {
				// TODO: Download song
			}
		}));
		addHandlerRegistration(eventBus.addHandler(PlaySongEvent.TYPE, new PlaySongEventHandler() {
			
			@Override
			public void onUpdate(PlaySongEvent event) {
				goTo(new PlayPlace(place, event.getSong(), true));
			}
		}));
	}

	@Override
	protected void loadData() {
		searchSong(keySearch);
		view.getSearchWidget().setValue(keySearch);
	}
	
	private void searchSong(String name) {
		if(name.isEmpty()) {
			Window.alert("Name empty!");
			return;
		}
		name = name.replace(" ", "+");
		ClientData.DATA_SERVICE.searchSong(name, "", "", "", 0, new AsyncCallback<List<Song>>() {
			
			@Override
			public void onSuccess(List<Song> result) {
				view.showSongs(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
}
