package com.hiep.supermusic.client.view;

import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;

public class SearchWidget extends HorizontalPanel {
	private TextBox searchBox = new TextBox();
	private Button btnSearch = new Button("Tìm");
	
	public SearchWidget() {
		super();
		add(searchBox);
		add(btnSearch);
		searchBox.getElement().setAttribute("placeholder", "Tên bài hát...");
		searchBox.setStyleName("search-box-style");
		btnSearch.setStyleName("btn-search-style");
		setCellVerticalAlignment(searchBox, HasVerticalAlignment.ALIGN_MIDDLE);
		setCellVerticalAlignment(btnSearch, HasVerticalAlignment.ALIGN_MIDDLE);
		setCellWidth(searchBox, "80%");
		setCellWidth(btnSearch, "20%");
		setWidth("100%");
	}
	
	public TextBox getSearchBox() {
		return searchBox;
	}
	
	public HasTapHandlers getBtnSearch() {
		return btnSearch;
	}
	
	public String getValue() {
		return searchBox.getValue();
	}
	
	public void setValue(String value) {
		searchBox.setValue(value);
	}
}
