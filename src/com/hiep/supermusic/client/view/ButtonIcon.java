package com.hiep.supermusic.client.view;

import com.google.gwt.user.client.ui.HTML;
import com.googlecode.mgwt.ui.client.widget.touch.TouchPanel;

public class ButtonIcon extends TouchPanel {
	private HTML html = new HTML();
	private String title = "";
	private String icon = "";
	private String changeIcon = "";
	
	public ButtonIcon(String title, String icon) {
		super();
		this.title = title;
		this.icon = icon;
		setInfo(title, icon);
		this.add(html);
		this.setStyleName("button-icon-main");
	}
	
	public ButtonIcon(String icon, String changeIcon, boolean playing) {
		this.icon = icon;
		this.changeIcon = changeIcon;
		setValue(playing);
	}
	
	public ButtonIcon(String icon) {
		this("", icon);
	}
	
	public void setInfo(String title, String icon) {
		html.setHTML("<div class=\"icon_\"><i class=\"fa " + icon + "\" aria-hidden=\"true\"></i></div><div class=\"name_\">" + title + "</div>");
	}
	
	public void setValue(boolean value) {
		if(value) {
			setInfo(title, icon);
		} else {
			setInfo(title, changeIcon);
		}
	}
	
	public String getTitleText(){
		return title;
	}
	
	public String getIcon() {
		return icon;
	}
}
