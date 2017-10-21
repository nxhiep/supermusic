package com.hiep.supermusic.client.activities.play;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.hiep.supermusic.client.ClientUtils;
import com.hiep.supermusic.client.CssToken;
import com.hiep.supermusic.client.SuperMusic;
import com.hiep.supermusic.client.activities.basic.BasicViewImpl;
import com.hiep.supermusic.client.audio.AudioPlayer;
import com.hiep.supermusic.client.view.AudioPlayerPanel;
import com.hiep.supermusic.shared.Song;

public class PlayViewImpl extends BasicViewImpl implements PlayView{
	private static PlayViewImplUiBinder uiBinder = GWT
			.create(PlayViewImplUiBinder.class);
	
	interface PlayViewImplUiBinder extends UiBinder<Widget, PlayViewImpl> {
	}
	@UiField FlowPanel homeViewPanel, containerPanel;
	@UiField HorizontalPanel controlPanel;
	@UiField Image avatar;
	@UiField Button minPlayBtn;
	
	public PlayViewImpl() {	
		super();
		this.layout.getScrollPanel().setWidget(uiBinder.createAndBindUi(this));
		this.layout.getHeaderPanel().getLeftMenuButton().setVisible(false);
		this.layout.getHeaderPanel().getBackButton().setVisible(true);
		layout.getScrollPanel().refresh();
		SuperMusic.setBackgroundStyleName(CssToken.BACKGROUND_05);
		minPlayBtn.setText("Min");
		minPlayBtn.getElement().getStyle().setColor("white");
		minPlayBtn.getElement().getStyle().setBackgroundColor("rgb(27, 215, 27)");
		minPlayBtn.getElement().getStyle().setProperty("border", "0");
		minPlayBtn.getElement().getStyle().setProperty("paddingLeft", "20px");
		minPlayBtn.getElement().getStyle().setProperty("paddingRight", "20px");
		initHanlders();
	}
	
	private void initHanlders() {
	}

	@Override
	public void refreshView() {
		super.refreshView();
		layout.getScrollPanel().scrollTo(0, 0);
	}

	@Override
	public void genStream(String url) {
		AudioPlayer audioPlayer = AudioPlayerPanel.getAudioPlayer();
		audioPlayer.initAudio(url, ClientUtils.getScreenWidth());
		if(audioPlayer.isWidget() != null){
			containerPanel.add(audioPlayer.isWidget());
		}
	}
	
	@Override
	public void updateSong(Song song) {
		setTitleCenterPlay(song.getName(), song.getSinger());
	}
	
	@Override
	public HasTapHandlers getMinPlayBtn() {
		return minPlayBtn;
	}
	
	public void setTitleCenterPlay(String name, String singer) {
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setWidth("100%");
		verticalPanel.getElement().getStyle().setColor("#ffffff");
		HTML nameHTML = new HTML(name);
		nameHTML.getElement().getStyle().setFontSize(1.6, Unit.EM);
		nameHTML.setStyleName("dot-1");
		verticalPanel.add(nameHTML);
		verticalPanel.add(new HTML(singer));
		layout.getHeaderPanel().addWidgetCenter(verticalPanel);
	}
}
