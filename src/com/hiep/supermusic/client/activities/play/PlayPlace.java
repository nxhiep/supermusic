package com.hiep.supermusic.client.activities.play;

import com.google.gwt.place.shared.Place;
import com.hiep.supermusic.client.activities.basic.BasicPlace;
import com.hiep.supermusic.shared.Song;

public class PlayPlace extends BasicPlace {
	
	private Song song = null;
	private boolean playNow = false;
	
	public PlayPlace() {
		super();
	}
	
	public PlayPlace(Place place, Song song) {
		super(place);
		this.song = song;
	}
	public PlayPlace(Place place, Song song, boolean isPlayNow) {
		this(place, song);
		this.playNow = isPlayNow;
	}

	public Song getSong() {
		return song;
	}
	
	public void setSong(Song song) {
		this.song = song;
	}
	
	public boolean isPlayNow() {
		return playNow;
	}
	
	public void setPlayNow(boolean playNow) {
		this.playNow = playNow;
	}
}
