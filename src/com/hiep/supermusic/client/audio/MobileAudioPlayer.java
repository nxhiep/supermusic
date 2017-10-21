package com.hiep.supermusic.client.audio;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtphonegap.client.media.MediaStatus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.hiep.supermusic.client.ClientUtils;
import com.hiep.supermusic.client.CssToken;
import com.hiep.supermusic.client.resourse.BHClientBundleBaseTheme;
import com.hiep.supermusic.client.view.BHTouchImage;
import com.hiep.supermusic.client.view.KSSlider;

public class MobileAudioPlayer extends AudioPlayer{
	private HorizontalPanel hPanel;
	private KSSlider timeSlider = new KSSlider();
	private BHTouchImage playButton = null;
	private HTML infoHtml;
	private int currentStatus = MediaStatus.MEDIA_NONE;
	private boolean initSlide = false;
	private  JavaScriptObject currentAudio;
	private  String currentAudioUrl;
	
	public MobileAudioPlayer() {
		hPanel = new HorizontalPanel();
		playButton = new BHTouchImage(BHClientBundleBaseTheme.IMPL.getBHMGWTClientBundle().playMedia(),
				BHClientBundleBaseTheme.IMPL.getBHMGWTClientBundle().pauseMedia());
		playButton.setPixelSize(35, 35);
		infoHtml = new HTML();
		timeSlider.getElement().setId("timeSlider");
		playButton.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				play();
			}
		});
		hPanel.setSpacing(5);
		hPanel.setStyleName(CssToken.AUDIO_PLAYER_PANEL, true);
		playButton.setStyleName(CssToken.AUDIO_PLAYER_PLAYBUTTON, true);
		hPanel.add(playButton);
		hPanel.add(timeSlider);
		hPanel.setCellWidth(playButton, "35px");
		timeSlider.setWidth(ClientUtils.getScreenWidth()-70 + "px");
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel.setCellVerticalAlignment(timeSlider, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel.setCellVerticalAlignment(infoHtml, HasVerticalAlignment.ALIGN_MIDDLE);
	}
	
	public void play() {
		playButton.setActive(!playButton.isActive());
		switch (currentStatus) {
		case MediaStatus.MEDIA_NONE:
			playAudio(currentAudio);
			break;
		case MediaStatus.MEDIA_PAUSED:
			playAudio(currentAudio);
			break;
		case MediaStatus.MEDIA_RUNNING:
			pauseAudio(currentAudio);
			break;
		case MediaStatus.MEDIA_STOPPED:
			playAudio(currentAudio);
			break;
		}
	}

	private int getCurrentStatus() {
		return 0;
	}
	
	@Override
	public void initAudio(final String url,final int width) {
		if(timeSlider != null)
			timeSlider.setValue(0);
		infoHtml.setHTML("0/0");
		String newUrl = "";
		if(url.startsWith("file://"))
			newUrl = url.replace("file://", "");
		if(url.startsWith("localhost"))
			newUrl = url.replace("localhost", "");
		stopAudio();
		ClientUtils.log("Init audio " + newUrl);
		initSlide = false;
		this.currentAudio = getAudio(url);
		this.currentAudioUrl = url;
		initView(width);
	}
	
	private void setMaxSlide() {
		if(!initSlide) {
			timeSlider.setMax((int)getDuration(currentAudio));
			initSlide = true;
		}
	}
	
	private void initView(int width) {
		if(width > 0)
			hPanel.setWidth(width + "px");
		playButton.setActive(false);
	}

	@Override
	public boolean stopAudio() {
		if(currentAudio!=null) {
			ClientUtils.log("Stoped audio");
			stopAudio(currentAudio);
			playButton.setActive(false);
			currentStatus = MediaStatus.MEDIA_NONE;
			return true;
		}
		return false;
	}
	
	@Override
	public Widget isWidget() {
		return hPanel;
	}
	
	private void play(String url) {
		this.currentAudioUrl = url;
		this.currentAudio = getAudio(currentAudioUrl);
		playAudio(currentAudio);
	}
	
	private void pause() {
		pauseAudio(currentAudio);
	}
	
	private native JavaScriptObject getAudio(String audioUrl) /*-{
		$wnd.console.log('get new audio with url: ' + audioUrl);
		var audio = new Audio(audioUrl);
		return audio;
	}-*/;
	
	private native void stopAudio(JavaScriptObject audio) /*-{
		if(audio){
			audio.pause();
			audio.currentTime = 0;
			audio = null;
		}
	}-*/;
	
	private native void pauseAudio(JavaScriptObject myaudio) /*-{
		myaudio.pause();
	}-*/;
	
	private native void playAudio(JavaScriptObject myaudio) /*-{
		var app = this;
		 $wnd.console.log('playAudio');
		myaudio.play();
		myaudio.addEventListener("timeupdate", function() {
			 app.@com.hiep.supermusic.client.audio.MobileAudioPlayer::onTimeUpdate()();
		}, false);
		myaudio.addEventListener("error", function() {
			 $wnd.console.log('myaudio ERROR');
		}, false);
		myaudio.addEventListener("canplay", function() {
			 $wnd.console.log('myaudio CAN PLAY: ' + myaudio.duration);
			 app.@com.hiep.supermusic.client.audio.MobileAudioPlayer::setMaxSlide()();
		}, false);
		myaudio.addEventListener("waiting", function() {
			 $wnd.console.log('audio WAITING');
			 app.@com.hiep.supermusic.client.audio.MobileAudioPlayer::setMaxSlide()();
		}, false);
		myaudio.addEventListener("pause", function() {
			 $wnd.console.log('audio PAUSE');
			 app.@com.hiep.supermusic.client.audio.MobileAudioPlayer::onPause()();
		}, false);
		myaudio.addEventListener("playing", function() {
			 $wnd.console.log('audio playing');
			 app.@com.hiep.supermusic.client.audio.MobileAudioPlayer::onPlay()();
		}, false);
		myaudio.addEventListener("ended", function() {
			 $wnd.console.log('myaudio ENDED');
			 app.@com.hiep.supermusic.client.audio.MobileAudioPlayer::onEnded()();
			 app.@com.hiep.supermusic.client.audio.MobileAudioPlayer::stopAudio()();
		}, false);
	}-*/;
	
	private native double getDuration(JavaScriptObject myaudio) /*-{
		if(myaudio) {
//			$wnd.console.log("Duration: " + myaudio.duration);
			return myaudio.duration;
		}
		else return 0;
	}-*/;
	
	private native double getCurrentTime(JavaScriptObject myaudio) /*-{
		if(myaudio)
			return myaudio.currentTime;
		else return 0;
	}-*/;
	
	private void onPlay() {
		currentStatus = MediaStatus.MEDIA_RUNNING;
		setMaxSlide();
	}
	
	private void onPause() {
		currentStatus = MediaStatus.MEDIA_PAUSED;
	}
	
	private void onEnded() {
		currentStatus = MediaStatus.MEDIA_STOPPED;
	}
	
	private void onTimeUpdate() {
		int currentTime = (int) getCurrentTime(currentAudio);
		timeSlider.setValue(currentTime);
	}
	
	@Override
	public boolean isPlaying() {
		return (currentStatus == MediaStatus.MEDIA_RUNNING);
	}
}
