package com.hiep.supermusic.client.view;

import com.hiep.supermusic.client.audio.AudioPlayer;
import com.hiep.supermusic.client.audio.MobileAudioPlayer;
import com.hiep.supermusic.shared.Song;

public class AudioPlayerPanel {

	public static PlaySongPanel PLAY_SONG_PANEL = new PlaySongPanel();
	private static AudioPlayer audioPlayer = null;
	private static Song song = null;
	
	public AudioPlayerPanel() {
	}
	
	public static AudioPlayer getAudioPlayer() {
		if(audioPlayer == null) {
			audioPlayer = new MobileAudioPlayer();
		}
		return audioPlayer;
	}
	
	public static void showPlayPanel(Song song) {
		AudioPlayerPanel.song = song;
		if(song != null) {
			PLAY_SONG_PANEL.setImageUrl(song.getAvatar());
			PLAY_SONG_PANEL.setPlaying(getAudioPlayer().isPlaying());
			PLAY_SONG_PANEL.show();
			if(!getAudioPlayer().isPlaying()) {
				getAudioPlayer().play();
			}
		}
	}
	
	public static Song getSong() {
		return song;
	}
	
	public static void setSong(Song song) {
		AudioPlayerPanel.song = song;
	}
}
