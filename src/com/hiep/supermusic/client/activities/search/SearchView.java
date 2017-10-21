package com.hiep.supermusic.client.activities.search;

import java.util.List;

import com.hiep.supermusic.client.activities.basic.BasicView;
import com.hiep.supermusic.shared.Song;

public interface SearchView extends BasicView {

	void showSongs(List<Song> songs);
}
