package com.hiep.supermusic.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableEvent;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableHandler;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutEvent;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutHandler;
import com.googlecode.gwtphonegap.client.event.OnlineEvent;
import com.googlecode.gwtphonegap.client.event.OnlineHandler;
import com.googlecode.gwtphonegap.client.event.PauseEvent;
import com.googlecode.gwtphonegap.client.event.PauseHandler;
import com.googlecode.gwtphonegap.client.event.ResumeEvent;
import com.googlecode.gwtphonegap.client.event.ResumeHandler;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.MGWTSettings.ViewPort;
import com.googlecode.mgwt.ui.client.widget.animation.AnimationWidget;
import com.hiep.supermusic.client.activities.AppPlaceHistoryMapper;
import com.hiep.supermusic.client.activities.ClientFactory;
import com.hiep.supermusic.client.activities.ClientFactoryImpl;
import com.hiep.supermusic.client.activities.PhoneActivityMapper;
import com.hiep.supermusic.client.activities.PhoneAnimationMapper;
import com.hiep.supermusic.client.activities.home.HomePlace;
import com.hiep.supermusic.client.view.AudioPlayerPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SuperMusic implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public static PhoneGap phoneGap = GWT.create(PhoneGap.class);
	public static ClientFactory clientFactory = new ClientFactoryImpl();

	public void onModuleLoad() {
		initializeJsniConnect(this);
		phoneGap.addHandler(new PhoneGapAvailableHandler() {
			@Override
			public void onPhoneGapAvailable(PhoneGapAvailableEvent event) {
				startApp();
			}
		});

		phoneGap.addHandler(new PhoneGapTimeoutHandler() {
			@Override
			public void onPhoneGapTimeout(PhoneGapTimeoutEvent event) {
				ClientUtils.log("Time out");
			}
		});

		phoneGap.getEvent().getOnlineHandler()
				.addOnlineHandler(new OnlineHandler() {

					@Override
					public void onOnlineEvent(OnlineEvent event) {
					}
				});
		phoneGap.getEvent().getResumeHandler()
				.addResumeHandler(new ResumeHandler() {
					@Override
					public void onResumeEvent(ResumeEvent event) {
						ClientUtils.log("Resume event");
					}
				});
		phoneGap.getEvent().getPauseHandler()
				.addPauseHandler(new PauseHandler() {
					@Override
					public void onPause(PauseEvent event) {
						ClientUtils.log("Pause event");
					}
				});

		phoneGap.initializePhoneGap();
	}
	
	private void startApp() {
		ViewPort viewPort = new MGWTSettings.ViewPort();
		MGWTSettings settings = new MGWTSettings();
		settings.setViewPort(viewPort);
		viewPort.setUserScaleAble(false).setMinimumScale(1.0)
				.setMinimumScale(1.0).setMaximumScale(1.0);
		settings.setFullscreen(true);
		settings.setPreventScrolling(true);
		settings.setViewPort(viewPort);
		MGWT.applySettings(settings);
		createDisplay(clientFactory);
		goToHome();
	}
	
	public void goToHome() {
		AppPlaceHistoryMapper historyMapper = GWT
				.create(AppPlaceHistoryMapper.class);
		final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
				historyMapper);
		historyHandler.register(clientFactory.getPlaceController(),
				clientFactory.getEventBus(), new HomePlace());
		historyHandler.handleCurrentHistory();
	}

	private static final String BODY_ID_MAIN = "body-main";
	
	private void createDisplay(ClientFactory clientFactory) {
		AnimationWidget display = new AnimationWidget();
		PhoneActivityMapper activityMapper = new PhoneActivityMapper(
				clientFactory);
		PhoneAnimationMapper animationMapper = new PhoneAnimationMapper();
		AnimatingActivityManager activityManager = new AnimatingActivityManager(
				activityMapper, animationMapper, clientFactory.getEventBus());
		activityManager.setDisplay(display);
		display.setStyleName(CssToken.BACKGROUND_04, true);
		display.getElement().setId(BODY_ID_MAIN);
		RootPanel.get().add(display);
		RootPanel.get().add(AudioPlayerPanel.PLAY_SONG_PANEL);
	}

	public static ClientFactory getClientFactory() {
		return clientFactory;
	}
	
	public static native void setBackgroundStyleNames(String bodyId, String style, JavaScriptObject styleNames) /*-{
		var elem = $wnd.document.getElementById(bodyId);
		if(!!elem){
			var styleInput = elem.className;
			for(var i = 0; i < styleNames.length; i++){
				styleInput = styleInput.replace(new RegExp(styleNames[i], 'g'), '');
			}
			styleInput += " " + style;
			elem.className = styleInput;
		}
	}-*/;
	
	public static void setBackgroundStyleName(String style){
		List<String> styleNames = new ArrayList<String>(Arrays.asList(CssToken.BACKGROUND_01, CssToken.BACKGROUND_02,
				CssToken.BACKGROUND_03, CssToken.BACKGROUND_04, CssToken.BACKGROUND_05, CssToken.BACKGROUND_MAIN));
		JSONArray jsonArray = new JSONArray();
		int index = 0;
		for (String item : styleNames) {
			jsonArray.set(index, new JSONString(item));
			index++;
		}
		setBackgroundStyleNames(BODY_ID_MAIN, style, jsonArray.getJavaScriptObject());
	}

	private native void initializeJsniConnect(SuperMusic thiz) /*-{
		
	}-*/;
}
