package com.hiep.supermusic.client.activities.event;

import com.google.gwt.event.shared.GwtEvent;
import com.hiep.supermusic.shared.Song;

public class SelectSongEvent extends GwtEvent<SelectSongEventHandler> {

	public static Type<SelectSongEventHandler> TYPE = new Type<SelectSongEventHandler>();
	
	private Song song;
	
	public SelectSongEvent(Song song) {
		this.song = song;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<SelectSongEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SelectSongEventHandler handler) {
		handler.onUpdate(this);
	}

	public Song getSong() {
		return song;
	}
	
	public void setSong(Song song) {
		this.song = song;
	}
}
