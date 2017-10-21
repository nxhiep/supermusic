package com.hiep.supermusic.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class ClientData {
	public static final DataServiceAsync DATA_SERVICE = GWT.create(DataService.class);
	
	public static void prepareDataService() {
		if(Window.Location.getHref().contains("8888")){
			ServiceDefTarget topicServiceDef = (ServiceDefTarget) DATA_SERVICE;
			topicServiceDef.setServiceEntryPoint("" + "/data");
		}
	}
}
