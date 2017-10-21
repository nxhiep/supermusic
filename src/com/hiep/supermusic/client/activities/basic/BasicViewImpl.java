package com.hiep.supermusic.client.activities.basic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.panel.flex.RootFlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollEndEvent;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollEndEvent.Handler;
import com.hiep.supermusic.client.sliding.MenuSliding;
import com.hiep.supermusic.client.view.BhHeaderPanel;
import com.hiep.supermusic.client.view.PlaySongPanel;
import com.hiep.supermusic.client.view.SearchWidget;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;

public class BasicViewImpl implements BasicView {
	private BasicViewImplUiBinder basicUiBinder = GWT
			.create(BasicViewImplUiBinder.class);

	interface BasicViewImplUiBinder extends UiBinder<Widget, Layout> {

	}
	
	protected final Layout layout;
	
	public static class Layout {
		private final BasicViewImpl basicView;
		@UiField
		protected RootFlexPanel mainPanel;
		@UiField
		protected BhHeaderPanel headerPanel;
		@UiField
		protected ScrollPanel scrollPanel;
		@UiField FlowPanel contentPanel;
		/**
		 * 
		 */
		public Layout(BasicViewImpl basicView) {
			this.basicView = basicView;
		}

		/**
		 * @return the mainPanel
		 */
		public RootFlexPanel getMainPanel() {
			return mainPanel;
		}

		/**
		 * @return the headerPanel
		 */
		public BhHeaderPanel getHeaderPanel() {
			return headerPanel;
		}

		/**
		 * @return the basicView
		 */
		public BasicViewImpl getBasicView() {
			return basicView;
		}

		public ScrollPanel getScrollPanel() {
			return scrollPanel;
		}
		
		public FlowPanel getContentPanel() {
			return contentPanel;
		}
	}
	
	public BasicViewImpl() {
		this.layout = new Layout(this);
		basicUiBinder.createAndBindUi(this.layout);
		layout.getHeaderPanel().getBackButton().setVisible(false);
		this.layout.getScrollPanel().addScrollEndHandler(new Handler() {
			@Override
			public void onScrollEnd(ScrollEndEvent event) {
				layout.getScrollPanel().refresh();
			}
		});
	}
	
	@Override
	public void setTitleCenter(String title) {
		layout.getHeaderPanel().setCenter(title);
	}
	
	private SearchWidget searchWidget = new SearchWidget();
	private MenuSliding slidingMenu = null;
	private PlaySongPanel playSongPanel = null;
	
	public void showSearchPanel() {
		layout.getHeaderPanel().addWidgetCenter(searchWidget);
		searchWidget.setValue("");
	}
	
	@Override
	public PlaySongPanel getPlaySongPanel() {
		if(playSongPanel == null) {
			playSongPanel = new PlaySongPanel();
			RootPanel.get().add(playSongPanel);
		}
		return playSongPanel;
	}
	
	@Override
	public MenuSliding getSlidingMenu() {
		if(slidingMenu == null) {
			slidingMenu = new MenuSliding();
			RootPanel.get().add(slidingMenu);
		}
		return slidingMenu;
	}

	@Override
	public SearchWidget getSearchWidget() {
		return searchWidget;
	}
	
	@Override
	public Widget asWidget() {
		return layout.getMainPanel();
	}

	@Override
	public Layout getLayout() {
		return layout;
	}

	@Override
	public FlowPanel getContentPanel() {
		return layout.getContentPanel();
	}
	
	@Override
	public void refreshView() {
		layout.getScrollPanel().scrollTo(0, 0);
	}
	
	@Override
	public int getViewId() {
		return 0;
	}
}
