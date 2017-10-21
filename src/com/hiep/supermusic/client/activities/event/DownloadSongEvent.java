package com.hiep.supermusic.client.activities.event;

import com.google.gwt.event.shared.GwtEvent;
import com.hiep.supermusic.shared.Song;

public class DownloadSongEvent extends GwtEvent<DownloadSongEventHandler> {

	public static Type<DownloadSongEventHandler> TYPE = new Type<DownloadSongEventHandler>();
	
	private Song song;
	
	public DownloadSongEvent(Song song) {
		this.song = song;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DownloadSongEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(DownloadSongEventHandler handler) {
		handler.onUpdate(this);
	}

	public Song getSong() {
		return song;
	}
	
	public void setSong(Song song) {
		this.song = song;
	}
}
