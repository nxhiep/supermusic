package com.hiep.supermusic.client.activities.event;

import com.google.gwt.event.shared.GwtEvent;
import com.hiep.supermusic.shared.Song;

public class PlaySongEvent extends GwtEvent<PlaySongEventHandler> {

	public static Type<PlaySongEventHandler> TYPE = new Type<PlaySongEventHandler>();
	
	private Song song;
	
	public PlaySongEvent(Song song) {
		this.song = song;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<PlaySongEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PlaySongEventHandler handler) {
		handler.onUpdate(this);
	}

	public Song getSong() {
		return song;
	}
	
	public void setSong(Song song) {
		this.song = song;
	}
}
