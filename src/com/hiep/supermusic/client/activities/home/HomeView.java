package com.hiep.supermusic.client.activities.home;

import java.util.List;

import com.hiep.supermusic.client.activities.basic.BasicView;
import com.hiep.supermusic.shared.Song;

public interface HomeView extends BasicView {

	void showListSong(List<Song> songs, String title);
}
