package com.hiep.supermusic.client;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hiep.supermusic.shared.Song;

public interface DataServiceAsync {
	void save(Song song, AsyncCallback<Void> callback);

	void searchSong(String name, String mode, String order, String cat, int page, AsyncCallback<List<Song>> callback);

	void getSong(Song song, AsyncCallback<Song> callback);

	void getSongs(AsyncCallback<Map<String, List<Song>>> callback);
}
