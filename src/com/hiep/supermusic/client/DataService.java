package com.hiep.supermusic.client;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hiep.supermusic.shared.Song;

@RemoteServiceRelativePath("data")
public interface DataService extends RemoteService {

	void save(Song song);

	List<Song> searchSong(String name, String mode, String order, String cat, int page);

	Song getSong(Song song);

	Map<String, List<Song>> getSongs();
}
