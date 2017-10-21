package com.hiep.supermusic.client.audio;

import com.google.gwt.user.client.ui.Widget;

public abstract class AudioPlayer {

	public void initAudio(String url, int width){
	}
	
	public boolean stopAudio(){
		return false;
	}
	
	public Widget isWidget() {
		return null;
	}
	
	public void play(){
	}

	public boolean isPlaying() {
		return false;
	}
}
