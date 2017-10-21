package com.hiep.supermusic.client.view;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.hiep.supermusic.client.ClientUtils;

public class PlaySongPanel extends HorizontalTouchPanel{

	private HorizontalPanel playingPanel = new HorizontalPanel();
	private ButtonIcon buttonPrev = new ButtonIcon("fa-backward");
	private ButtonIcon buttonNext = new ButtonIcon("fa-forward");
	private ButtonIcon buttonPlay = new ButtonIcon("fa-play", "fa-pause");//fa-pause
	public static final String ICON_IMAGED_EFAULT = "<i style=\"font-size:3em;color:white;margin-top:4px\" class=\"fa fa-music\" aria-hidden=\"true\"></i>";
	private HTML image = new HTML(ICON_IMAGED_EFAULT);
	private HTML buttonInfo = new HTML("<i style=\"font-size:3em;color:white;margin-top:5px;margin-left:2px\" class=\"fa fa-info-circle\" aria-hidden=\"true\"></i>");
	private boolean playing = false;
	
	public PlaySongPanel() {
		super();
		this.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		this.addStyleName("playSongPanel");
		playingPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		playingPanel.getElement().getStyle().setProperty("margin", "0 auto");
		playingPanel.add(buttonPrev);
		playingPanel.add(buttonPlay);
		playingPanel.add(buttonNext);
		image.setStyleName("icon-image-default");
		buttonInfo.setStyleName("icon-image-default");
		this.add(image);
		this.add(playingPanel);
		this.add(buttonInfo);
		this.setCellWidth(image, "50px");
		this.setCellWidth(buttonInfo, "50px");
		hide();
		initHandlers();
	}

	private void initHandlers() {
		buttonPrev.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		buttonPlay.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				ClientUtils.log("buttonPlay");
				setPlaying(playing);
			}
		});
		buttonNext.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public HorizontalPanel getPlayingPanel() {
		return playingPanel;
	}

	public ButtonIcon getButtonPrev() {
		return buttonPrev;
	}

	public ButtonIcon getButtonNext() {
		return buttonNext;
	}

	public ButtonIcon getButtonPlay() {
		return buttonPlay;
	}

	public HTML getImage() {
		return image;
	}
	
	public void setImageUrl(String url) {
		image.setHTML("<img width=\"100%\" height=\"100%\" src=\"" + url + "\"/>");
	}

	public HTML getButtonInfo() {
		return buttonInfo;
	}
	
	public void show() {
		this.setVisible(true);
	}
	
	public void hide() {
		this.setVisible(false);
	}

	public void setPlaying(boolean playing) {
		buttonPlay.setValue(playing);
	}
}
