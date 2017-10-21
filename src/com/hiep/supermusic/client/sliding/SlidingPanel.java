package com.hiep.supermusic.client.sliding;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollEndEvent;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.hiep.supermusic.client.ClientUtils;
import com.hiep.supermusic.client.CssToken;
import com.hiep.supermusic.client.view.BhHeaderPanel;
import com.hiep.supermusic.client.view.VerticalTouchPanel;

public class SlidingPanel extends FlowPanel {
	private SlidingPanelUiBinder uiBinder = GWT
			.create(SlidingPanelUiBinder.class);
	
	interface SlidingPanelUiBinder extends UiBinder<Widget, SlidingPanel> {

	}
	@UiField protected VerticalTouchPanel slidingPanel;
	@UiField protected ScrollPanel scrollPanel;
	@UiField protected VerticalPanel mainPanel;
	protected boolean isShowing = false;
	protected static SlidingPanel currentSliding = null;
	protected int widthPanel = ClientUtils.getScreenWidth();
	protected BhHeaderPanel headerPanel = new BhHeaderPanel();
	
	public SlidingPanel() {
		this.add(uiBinder.createAndBindUi(this));
		headerPanel.getLeftMenuButton().setVisible(false);
		scrollPanel.setScrollingEnabledX(false);
		scrollPanel.setShowHorizontalScrollBar(false);
		scrollPanel.setShowVerticalScrollBar(false);
		slidingPanel.setWidth("100%");
		slidingPanel.setHeight("100%");
		this.setWidth("100%");
		this.setVisible(false);
		this.getElement().getStyle().setZIndex(999);
		setPosition();
		addHandler();
	}

	private void addHandler() {
		scrollPanel.addScrollEndHandler(new ScrollEndEvent.Handler() {
			
			@Override
			public void onScrollEnd(ScrollEndEvent event) {
				scrollPanel.refresh();
			}
		});
		
		headerPanel.getBackButton().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				hide();
			}
		});
	}
	
	public SlidingPanel(int width) {
		this();
		this.widthPanel = width;
		setSize();
	}
	
	public void show() {
		this.getStyleName().replaceAll(getAnimationCss(), "");
		this.setHeight(ClientUtils.getScreenHeight()+ "px");
		this.setVisible(true);
		this.setStyleName(getAnimationCss(), true);
		isShowing  = true;
		if(currentSliding!=null) 
			currentSliding.hide();
		currentSliding = this;
	}
	
	public void hide() {
		if(isShowing) {
			this.setVisible(false);
			isShowing  = false;
			currentSliding = null;
		}
	}

	public void hideAll() {
		
	}
	
	protected void refreshView() {
		
	}
	
	public boolean isShowing() {
		return isShowing;
	}
	
	protected String getAnimationCss() {
		return CssToken.SLIDE_LEFT;
	}
	
	protected void setSize(int width) {
		this.widthPanel = width;
		setSize();
	}
	
//	protected void setPixelSizePanel(int width, int height) {
//		scrollPanel.setPixelSize(widthPanel, height);
//		scrollPanel.refresh();
//	}
	
	protected void setSize() {
		scrollPanel.setWidth(widthPanel + "px");
		scrollPanel.refresh();
	}
	
	protected void setPosition() {
		this.getElement().getStyle().setPosition(Position.ABSOLUTE);
		this.getElement().getStyle().setTop(BhHeaderPanel.height+2, Unit.PX);
	}

	public BhHeaderPanel getHeaderPanel() {
		return headerPanel;
	}
	
	public HasTapHandlers getBackButton() {
		return headerPanel.getBackButton();
	}
	
	public static SlidingPanel getCurrentSliding() {
		return currentSliding;
	}
	
	public static void setCurrentSliding(SlidingPanel currentSliding) {
		SlidingPanel.currentSliding = currentSliding;
	}
	
	public static boolean hideSliding() {
		if (currentSliding != null && currentSliding.isShowing) {
			currentSliding.hide();
			return true;
		}
		return false;
	}

}