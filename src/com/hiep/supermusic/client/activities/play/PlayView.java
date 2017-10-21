package com.hiep.supermusic.client.activities.play;

import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.hiep.supermusic.client.activities.basic.BasicView;
import com.hiep.supermusic.shared.Song;

public interface PlayView extends BasicView {
	void genStream(String url);

	HasTapHandlers getMinPlayBtn();

	void updateSong(Song song);
}
