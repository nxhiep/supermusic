package com.hiep.supermusic.client.activities.search;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.hiep.supermusic.client.CssToken;
import com.hiep.supermusic.client.SuperMusic;
import com.hiep.supermusic.client.activities.basic.BasicViewImpl;
import com.hiep.supermusic.client.activities.event.DownloadSongEvent;
import com.hiep.supermusic.client.activities.event.PlaySongEvent;
import com.hiep.supermusic.client.activities.event.SelectSongEvent;
import com.hiep.supermusic.client.activities.search.widget.SongItemPanel;
import com.hiep.supermusic.shared.Song;

public class SearchViewImpl extends BasicViewImpl implements SearchView{
	private static HomeViewImplUiBinder uiBinder = GWT
			.create(HomeViewImplUiBinder.class);
	
	interface HomeViewImplUiBinder extends UiBinder<Widget, SearchViewImpl> {
	}
	@UiField protected VerticalPanel contentPanel;
	
	public SearchViewImpl() {	
		super();
		this.layout.getScrollPanel().setWidget(uiBinder.createAndBindUi(this));
		this.layout.getHeaderPanel().getBackButton().setVisible(true);
		this.layout.getHeaderPanel().getLeftMenuButton().setVisible(false);
		layout.getScrollPanel().refresh();
		SuperMusic.setBackgroundStyleName(CssToken.BACKGROUND_02);
		showSearchPanel();
	}
	
	@Override
	public void showSongs(List<Song> songs) {
		contentPanel.clear();
		for (final Song song : songs) {
			SongItemPanel item = new SongItemPanel(song);
			contentPanel.add(item);
			item.addDomHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					SuperMusic.getClientFactory().getEventBus().fireEvent(new SelectSongEvent(song));
				}
			}, ClickEvent.getType());
			item.getDownloadBtn().addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					SuperMusic.getClientFactory().getEventBus().fireEvent(new DownloadSongEvent(song));
				}
			});
			item.getPlayBtn().addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					SuperMusic.getClientFactory().getEventBus().fireEvent(new PlaySongEvent(song));
				}
			});
		}
	}
}
