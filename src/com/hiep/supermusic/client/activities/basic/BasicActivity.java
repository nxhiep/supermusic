package com.hiep.supermusic.client.activities.basic;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.gwtphonegap.client.event.BackButtonPressedEvent;
import com.googlecode.gwtphonegap.client.event.BackButtonPressedHandler;
import com.googlecode.gwtphonegap.client.event.MenuButtonPressedEvent;
import com.googlecode.gwtphonegap.client.event.MenuButtonPressedHandler;
import com.googlecode.gwtphonegap.client.event.SearchButtonPressedEvent;
import com.googlecode.gwtphonegap.client.event.SearchButtonPressedHandler;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.hiep.supermusic.client.ClientUtils;
import com.hiep.supermusic.client.SuperMusic;
import com.hiep.supermusic.client.activities.ClientFactory;
import com.hiep.supermusic.client.activities.home.HomePlace;
import com.hiep.supermusic.client.activities.search.SearchPlace;
import com.hiep.supermusic.client.sliding.SlidingPanel;

public class BasicActivity extends MGWTAbstractActivity {
	
	protected final ClientFactory clientFactory;
	protected EventBus eventBus;
	protected Place place = null;
	protected Place previousPlace = null;
	
	public BasicActivity(ClientFactory clientFactory, Place place) {
		this.clientFactory = clientFactory;
		this.place = place;
  		if(place instanceof BasicPlace) {
  			previousPlace = ((BasicPlace) place).getPreviousPlace();
  		}
	}
	
	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		super.start(panel, eventBus);
		this.eventBus = eventBus;
		cancelAllHandlerRegistrations();
	}
	
	public void start(AcceptsOneWidget panel, final EventBus eventBus, final BasicView basicView) { 
		this.eventBus = eventBus;
		loadData();
		bind();
		if(basicView!=null) {
			basicView.refreshView();
			addHandlerRegistration(basicView.getLayout().getHeaderPanel().getBackButton().addTapHandler(new TapHandler() {
				
				@Override
				public void onTap(TapEvent event) {
					onBackButtonPressed();
				}
			}));
			
			//Add handler for leftmenu
			addHandlerRegistration(basicView.getLayout().getHeaderPanel().getLeftMenuButton().addTapHandler(
					new TapHandler() {
				
				@Override
				public void onTap(TapEvent event) {
//					BasicActivity.this.onLeftMenuPressed();
					if (basicView.getSlidingMenu().isShowing())
						basicView.getSlidingMenu().hide();
					else
						basicView.getSlidingMenu().show();
				}
			}));
			
			addHandlerRegistration(basicView.getSearchWidget().getBtnSearch().addTapHandler(
					new TapHandler() {
				
				@Override
				public void onTap(TapEvent event) {
					BasicActivity.this.onSearchButtonPressed(basicView.getSearchWidget().getValue());
				}
			}));
			
			addHandlerRegistration(basicView.getSearchWidget().getSearchBox().addKeyUpHandler(new KeyUpHandler() {
				
				@Override
				public void onKeyUp(KeyUpEvent event) {
					if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
						BasicActivity.this.onSearchButtonPressed(basicView.getSearchWidget().getValue());
					}
				}
			}));
			
			addHandlerRegistration(basicView.getSlidingMenu().getButtonHome().addTapHandler(new TapHandler() {
				
				@Override
				public void onTap(TapEvent event) {
					ClientUtils.log("getButtonHome");
				}
			}));
			addHandlerRegistration(basicView.getSlidingMenu().getButtonPlaying().addTapHandler(new TapHandler() {
							
				@Override
				public void onTap(TapEvent event) {
					ClientUtils.log("getButtonPlaying");
				}
			}));
			addHandlerRegistration(basicView.getSlidingMenu().getButtonSetting().addTapHandler(new TapHandler() {
				
				@Override
				public void onTap(TapEvent event) {
					ClientUtils.log("getButtonSetting");
				}
			}));
			addHandlerRegistration(basicView.getSlidingMenu().getButtonAbout().addTapHandler(new TapHandler() {
				
				@Override
				public void onTap(TapEvent event) {
					ClientUtils.log("getButtonAbout");
				}
			}));
		}
	}
	
	protected void bind() {
		addHandlerRegistration(SuperMusic.phoneGap.getEvent().getBackButton().addBackButtonPressedHandler(new BackButtonPressedHandler() {
			@Override
			public void onBackButtonPressed(BackButtonPressedEvent event) {
				BasicActivity.this.onBackButtonPressed();
			}
		}));
		addHandlerRegistration(SuperMusic.phoneGap.getEvent().getMenuButton().addMenuButtonPressedHandler(new MenuButtonPressedHandler() {
			@Override
			public void onMenuButtonPressed(MenuButtonPressedEvent event) {
				BasicActivity.this.onMenuButtonPressed();
			}
		}));
		addHandlerRegistration(SuperMusic.phoneGap.getEvent().getSearchButton().addSearchButtonHandler(new SearchButtonPressedHandler() {
			
			@Override
			public void onSearchButtonPressed(SearchButtonPressedEvent event) {
			}
		}));
	}
	
	protected void onSearchButtonPressed(String keySearch) {
		if(keySearch.isEmpty()) {
			Window.alert("Name empty!");
			return;
		}
		goTo(new SearchPlace(place, keySearch));
	}
	
	protected void onMenuButtonPressed() {
		
	}

	protected void loadData() {
		
	}
	
	protected void onBackButtonPressed() {
		if (SlidingPanel.hideSliding()) {
			return;
		}
		if(previousPlace != null)
			goTo(previousPlace);
		else 
			goTo(new HomePlace());
	}
	
	protected void onRefreshScrollPanel() {
	}
	
	protected void goTo(Place newPlace) {
		cancelAllHandlerRegistrations();
		clientFactory.getPlaceController().goTo(newPlace);
	}
}
