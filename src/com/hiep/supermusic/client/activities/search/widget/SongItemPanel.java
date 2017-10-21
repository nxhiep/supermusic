package com.hiep.supermusic.client.activities.search.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.touch.TouchPanel;
import com.hiep.supermusic.shared.Song;

public class SongItemPanel extends TouchPanel {

	private static SongItemPanelUiBinder uiBinder = GWT.create(SongItemPanelUiBinder.class);

	interface SongItemPanelUiBinder extends UiBinder<Widget, SongItemPanel> {
	}

	@UiField HTML namePanel, singerPanel;
	@UiField Button downloadBtn, playBtn;
	@UiField HTMLPanel mainItem;
	
	private Song song;
	
	public SongItemPanel() {
		this.add(uiBinder.createAndBindUi(this));
		playBtn.setHTML("<i class=\"fa fa-play-circle-o\" aria-hidden=\"true\"></i>");
		downloadBtn.setHTML("<i class=\"fa fa-download\" aria-hidden=\"true\"></i>");
	}

	public SongItemPanel(Song song) {
		this();
		this.song = song;
		namePanel.setText(song.getName());
		singerPanel.setText(song.getSinger());
	}
	
	public Button getDownloadBtn() {
		return downloadBtn;
	}
	
	public Button getPlayBtn() {
		return playBtn;
	}
	
	public HTML getNamePanel() {
		return namePanel;
	}
	
	public HTML getSingerPanel() {
		return singerPanel;
	}
	
	public Song getSong() {
		return song;
	}
	
	public void setSong(Song song) {
		this.song = song;
	}
	
	@Override
	public void addStyleName(String style) {
		mainItem.addStyleName(style);
	}
}
