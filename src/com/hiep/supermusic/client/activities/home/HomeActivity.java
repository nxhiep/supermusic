package com.hiep.supermusic.client.activities.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.hiep.supermusic.client.ClientData;
import com.hiep.supermusic.client.ClientUtils;
import com.hiep.supermusic.client.SuperMusic;
import com.hiep.supermusic.client.activities.ClientFactory;
import com.hiep.supermusic.client.activities.basic.BasicActivity;
import com.hiep.supermusic.client.activities.event.DownloadSongEvent;
import com.hiep.supermusic.client.activities.event.DownloadSongEventHandler;
import com.hiep.supermusic.client.activities.event.PlaySongEvent;
import com.hiep.supermusic.client.activities.event.PlaySongEventHandler;
import com.hiep.supermusic.client.activities.event.SelectSongEvent;
import com.hiep.supermusic.client.activities.event.SelectSongEventHandler;
import com.hiep.supermusic.client.activities.play.PlayPlace;
import com.hiep.supermusic.client.sliding.SlidingPanel;
import com.hiep.supermusic.client.view.Toaster;
import com.hiep.supermusic.shared.Song;

public class HomeActivity extends BasicActivity {

	private HomeView view;
	private boolean exitApp = false;
	private Timer exitAppTimer = new Timer() {

		@Override
		public void run() {
			exitApp = false;
		}
	};

	public HomeActivity(ClientFactory clientFactory, Place place) {
		super(clientFactory, place);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getHomeView();
		super.start(panel, eventBus, view);
		panel.setWidget(view);
	}

	@Override
	protected void bind() {
		super.bind();
		addHandlerRegistration(eventBus.addHandler(SelectSongEvent.TYPE, new SelectSongEventHandler() {
			
			@Override
			public void onUpdate(SelectSongEvent event) {
				ClientUtils.log("SelectSongEvent home " + event.getSong());
				goTo(new PlayPlace(place, event.getSong()));
			}
		}));
		addHandlerRegistration(eventBus.addHandler(DownloadSongEvent.TYPE, new DownloadSongEventHandler() {
					
			@Override
			public void onUpdate(DownloadSongEvent event) {
				//TODO: Download song
				ClientUtils.log("DownloadSongEvent " + event.getSong().getName());
			}
		}));
		addHandlerRegistration(eventBus.addHandler(PlaySongEvent.TYPE, new PlaySongEventHandler() {
			
			@Override
			public void onUpdate(PlaySongEvent event) {
				goTo(new PlayPlace(place, event.getSong(), true));
			}
		}));
	}

	private Map<String, List<Song>> mapSong = null;
	
	@Override
	protected void loadData() {
		if(mapSong == null) {
			mapSong = new HashMap<String, List<Song>>();
			ClientData.DATA_SERVICE.getSongs(new AsyncCallback<Map<String,List<Song>>>() {
				
				@Override
				public void onSuccess(Map<String, List<Song>> result) {
					mapSong = result;
					showSongs(result);
				}
				
				@Override
				public void onFailure(Throwable caught) {
				}
			});
		} else {
			showSongs(mapSong);
		}
	}

	private void showSongs(Map<String, List<Song>> result) {
		for(Map.Entry<String, List<Song>> entry : result.entrySet()) {
			if(entry.getKey().contains("0")) {
				view.showListSong(entry.getValue(), "Nhạc Việt Nam");
			}
			if(entry.getKey().contains("2")) {
				view.showListSong(entry.getValue(), "Nhạc US-UK");
			}
		}
	}

	@Override
	protected void onBackButtonPressed() {
		if (SlidingPanel.hideSliding()) {
			return;
		}
		askToExitApp();
	}

	private void askToExitApp() {
		if (exitApp) {
			SuperMusic.phoneGap.exitApp();
		} else {
			Toaster.showToast("Tap back again to exit", false, 3);
		}
		exitApp = !exitApp;
		exitAppTimer.schedule(3000);
	}
}
