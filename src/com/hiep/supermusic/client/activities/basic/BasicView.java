package com.hiep.supermusic.client.activities.basic;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.hiep.supermusic.client.activities.basic.BasicViewImpl.Layout;
import com.hiep.supermusic.client.sliding.MenuSliding;
import com.hiep.supermusic.client.view.PlaySongPanel;
import com.hiep.supermusic.client.view.SearchWidget;

public interface BasicView extends IsWidget{
	Layout getLayout();
	FlowPanel getContentPanel();
	void refreshView();
	int getViewId();
	SearchWidget getSearchWidget();
	MenuSliding getSlidingMenu();
	void setTitleCenter(String title);
	PlaySongPanel getPlaySongPanel();
}
