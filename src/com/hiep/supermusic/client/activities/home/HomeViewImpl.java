package com.hiep.supermusic.client.activities.home;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.hiep.supermusic.client.ClientUtils;
import com.hiep.supermusic.client.CssToken;
import com.hiep.supermusic.client.SuperMusic;
import com.hiep.supermusic.client.activities.basic.BasicViewImpl;
import com.hiep.supermusic.client.activities.event.DownloadSongEvent;
import com.hiep.supermusic.client.activities.event.PlaySongEvent;
import com.hiep.supermusic.client.activities.event.SelectSongEvent;
import com.hiep.supermusic.client.activities.search.widget.SongItemPanel;
import com.hiep.supermusic.shared.Song;

public class HomeViewImpl extends BasicViewImpl implements HomeView{
	private static HomeViewImplUiBinder uiBinder = GWT
			.create(HomeViewImplUiBinder.class);
	
	interface HomeViewImplUiBinder extends UiBinder<Widget, HomeViewImpl> {
	}
	@UiField protected FlowPanel homeViewPanel;
	
	public HomeViewImpl() {	
		super();
		this.layout.getScrollPanel().setWidget(uiBinder.createAndBindUi(this));
		this.layout.getHeaderPanel().getBackButton().setVisible(false);
		layout.getScrollPanel().refresh();
		SuperMusic.setBackgroundStyleName(CssToken.BACKGROUND_04);
		showSearchPanel();
	}
	
	@Override
	public void refreshView() {
		super.refreshView();
		homeViewPanel.clear();
	}
	
	@Override
	public void showListSong(List<Song> songs, String title) {
		VerticalPanel flowPanel = new VerticalPanel();
		flowPanel.setWidth("100%");
		HTML titleHtml = new HTML(title);
		titleHtml.setStyleName("title-home");
		flowPanel.add(titleHtml);
		for (final Song song : songs) {
			SongItemPanel songItemPanel = new SongItemPanel(song);
			songItemPanel.addStyleName("song-item-panel-v2");
			songItemPanel.addDomHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					ClientUtils.log("Song " + song);
					SuperMusic.getClientFactory().getEventBus().fireEvent(new SelectSongEvent(song));
				}
			}, ClickEvent.getType());
			songItemPanel.getDownloadBtn().addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					SuperMusic.getClientFactory().getEventBus().fireEvent(new DownloadSongEvent(song));
				}
			});
			songItemPanel.getPlayBtn().addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					SuperMusic.getClientFactory().getEventBus().fireEvent(new PlaySongEvent(song));
				}
			});
			flowPanel.add(songItemPanel);
		}
		homeViewPanel.add(flowPanel);
	}
}
