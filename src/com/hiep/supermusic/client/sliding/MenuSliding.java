package com.hiep.supermusic.client.sliding;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEndEvent;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEndHandler;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEvent.DIRECTION;
import com.googlecode.mgwt.ui.client.util.CssUtil;
import com.googlecode.mgwt.ui.client.widget.touch.TouchPanel;
import com.hiep.supermusic.client.ClientUtils;
import com.hiep.supermusic.client.view.ButtonIcon;

public class MenuSliding extends SlidingPanel {
	private TouchPanel touchPanel = new TouchPanel();
	private int widthMenu = 250;
	private final int delayMillis = 500;
	private final ButtonIcon buttonHome = new ButtonIcon("Home", "fa-home");
	private final ButtonIcon buttonPlaying = new ButtonIcon("Playing", "fa-play");
	private final ButtonIcon buttonAbout = new ButtonIcon("About", "fa-user");
	private final ButtonIcon buttonSetting = new ButtonIcon("Setting", "fa-gear");
	
	public MenuSliding() {
		super();
		
		mainPanel.add(buttonHome);
		mainPanel.add(buttonPlaying);
		mainPanel.add(buttonAbout);
		mainPanel.add(buttonSetting);
		
		slidingPanel.setHeight("100%");
		slidingPanel.setWidth(widthMenu + "px");
		this.setStyleName("leftMenuSliding", true);
		touchPanel.setWidth(ClientUtils.getScreenWidth()-widthMenu -15 + "px");
		touchPanel.setHeight(ClientUtils.getScreenHeight() + 50 + "px");
		this.add(touchPanel);
		touchPanel.getElement().getStyle().setProperty("float", "left");
		slidingPanel.getElement().getStyle().setProperty("float", "left");
		scrollPanel.setWidth(widthMenu + "px");
		scrollPanel.getElement().getStyle().setBackgroundColor("white");
		scrollPanel.getElement().getStyle().setBackgroundImage("url('../images/bg-2.jpg')");
		touchPanel.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				hide();
			}
		});
		
		touchPanel.addSwipeEndHandler(new SwipeEndHandler() {
			
			@Override
			public void onSwipeEnd(SwipeEndEvent event) {
				if(event.getDirection() == DIRECTION.RIGHT_TO_LEFT) {
					hide();
				}
			}
		});
		scrollPanel.refresh();
		CssUtil.setTransitionDuration(RootPanel.get().getElement(), delayMillis);
	}

	@Override
	public void show() {
		super.show();
		scrollPanel.setHeight(ClientUtils.getScreenHeight() + 50+ "px");
		CssUtil.translate(RootPanel.get().getElement(), widthMenu, 0);
		scrollPanel.refresh();
	}

	@Override
	public void hide() {
		if(isShowing) {
			new Timer() {
				
				@Override
				public void run() {
					setVisible(false);
				}
			}.schedule(delayMillis);
			isShowing  = false;
			currentSliding = null;
		}
		CssUtil.translate(RootPanel.get().getElement(), 0, 0);
	}
	
	@Override
	protected String getAnimationCss() {
		return "xyz";
	}
	
	@Override
	protected void setPosition() {
		this.getElement().getStyle().setPosition(Position.ABSOLUTE);
		this.getElement().getStyle().setTop(0, Unit.PX);
		this.getElement().getStyle().setLeft(-250, Unit.PX);
	}
	
	public static void clearAll() {
		if(SlidingPanel.currentSliding != null) {
			SlidingPanel.currentSliding.removeFromParent();
		}
	}
	
	public HasTapHandlers getButtonHome() {
		return buttonHome;
	}

	public HasTapHandlers getButtonPlaying() {
		return buttonPlaying;
	}

	public HasTapHandlers getButtonAbout() {
		return buttonAbout;
	}

	public HasTapHandlers getButtonSetting() {
		return buttonSetting;
	}
}
