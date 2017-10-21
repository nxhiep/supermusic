package com.hiep.supermusic.client.activities.play;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.hiep.supermusic.client.ClientData;
import com.hiep.supermusic.client.ClientUtils;
import com.hiep.supermusic.client.activities.ClientFactory;
import com.hiep.supermusic.client.activities.basic.BasicActivity;
import com.hiep.supermusic.client.activities.home.HomePlace;
import com.hiep.supermusic.client.view.AudioPlayerPanel;
import com.hiep.supermusic.shared.Song;

public class PlayActivity extends BasicActivity {

	private PlayView view;
	private Song song;
	private Place previousPlace;
	private boolean playNow = false;

	public PlayActivity(ClientFactory clientFactory, Place place) {
		super(clientFactory, place);
		song = ((PlayPlace) place).getSong();
		previousPlace = ((PlayPlace) place).getPreviousPlace();
		playNow = ((PlayPlace) place).isPlayNow();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getPlayView();
		super.start(panel, eventBus, view);
		panel.setWidget(view);
	}

	@Override
	protected void bind() {
		super.bind();
		
		addHandlerRegistration(view.getMinPlayBtn().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				AudioPlayerPanel.showPlayPanel(song);
			}
		}));
	}

	@Override
	protected void loadData() {
		if(song == null) {
			Window.alert("song null");
			return;
		}
		view.updateSong(song);
		ClientData.DATA_SERVICE.getSong(song, new AsyncCallback<Song>() {
			
			@Override
			public void onSuccess(Song result) {
				if(result.getLinkDownload().size() > 3 && (result.getLinkDownload().get(0).endsWith(".mp3") 
						|| result.getLinkDownload().get(0).endsWith(".mp4") || result.getLinkDownload().get(0).endsWith(".m4a") || result.getLinkDownload().get(0).endsWith(".flac"))) {
				} else {
					result = ClientUtils.covertLink(result);
				}
				if(result == null) {
					Window.alert("Link bài hát đang lỗi!!!");
					if(previousPlace != null)
						goTo(previousPlace);
					else 
						goTo(new HomePlace());
					return;
				}
				song = result;
				ClientUtils.log("result " + result.getLinkDownload().toString());
				view.genStream(result.getLink128Kbps());
				if(playNow)
					AudioPlayerPanel.showPlayPanel(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
}
