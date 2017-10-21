package com.hiep.supermusic.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwtphonegap.client.connection.Connection;
import com.googlecode.mgwt.ui.client.MGWT;
import com.hiep.supermusic.client.view.Toaster;
import com.hiep.supermusic.shared.Song;

public class ClientUtils {
	
	public static int getScreenHeight() {
		return Window.getClientHeight()-50;
	}
	public static int getScreenWidth() {
		return Window.getClientWidth();
	}
	
	public static boolean isFireFoxBrowser() {
//		ClientUtils.log("User agent : " + Window.Navigator.getUserAgent());
		if(SuperMusic.phoneGap.isPhoneGapDevice())
			return false;
		if( Window.Navigator.getUserAgent().toLowerCase().contains("chrome")
			||  Window.Navigator.getUserAgent().toLowerCase().contains("webkit")
			||  Window.Navigator.getUserAgent().toLowerCase().contains("safari"))
			return false;
		else 
			return true;
	}
	
	public static native void log(String msg) /*-{
		$wnd.console.log(msg);
	}-*/;
	
	public  static String getValue(String key, JSONObject object) {
		String s = "";
		if(object.containsKey(key))
			try{
				s = object.get(key).isString().stringValue() ;
			}
			catch (Exception e) {
			}
		if(s.equalsIgnoreCase("null"))
			s = "";
		ClientUtils.log("Key: " + key + " value: " + s);
		return s;
	}
	
	public static native void showSplash() /*-{
		$wnd.splashShow();
	}-*/;
	
	public static native void hideSplash() /*-{
		$wnd.splashHide();
	}-*/;
	
	public static boolean isOnline() {
		return isOnline(false);
	}
	
	public static boolean isOnline(boolean mustOnline) {
		boolean isOnline = SuperMusic.phoneGap.getConnection().getType() != Connection.NONE;
		if (MGWT.getOsDetection().isIOs())
			isOnline &= SuperMusic.phoneGap.getConnection().getType() != Connection.UNKNOWN;
		if (!isOnline && mustOnline)
			Toaster.showToast("Bạn đang ở chế dộ offline, Vui lòng kiểm tra lại đường truyền internet!");
		return isOnline;
	}
	
	public static void dataURIToBase64(String dataURI,final int width, final int height, final AsyncCallback<String> callback) {
		final Image image = new Image(dataURI);
		image.setPixelSize(width, height);
		RootPanel.get().add(image);
		image.setVisible(false);
		image.addLoadHandler(new LoadHandler() {
			
			@Override
			public void onLoad(LoadEvent event) {
				ImageElement element = ImageElement.as(image.getElement());
//				element.setHeight(height);
//				element.setWidth(width);
				Canvas canvas = Canvas.createIfSupported();
				canvas.setCoordinateSpaceWidth(element.getWidth());
				canvas.setCoordinateSpaceHeight(element.getHeight());
				canvas.getContext2d().beginPath();
				canvas.getContext2d().drawImage(element, 0, 0);
				canvas.getContext2d().closePath();
//				canvas.setPixelSize(width, height);
				ClientUtils.log("Data uri: " + canvas.toDataUrl());
				image.removeFromParent();
				callback.onSuccess(canvas.toDataUrl());
				
			}
		});
		
		image.addErrorHandler(new ErrorHandler() {
			
			@Override
			public void onError(ErrorEvent event) {
				image.removeFromParent();
				callback.onFailure(new Throwable("Error convert to base64"));
			}
		});
	};
	
	public static native void composeSms(String mobile, String content) /*-{
		$wnd.plugins.socialsharing.shareViaSMS(content, mobile, function(msg) {console.log('ok: ' + msg)}, function(msg) {alert('error: ' + msg)})
	}-*/;
	
	public static Song covertLink(Song song) {
		if(song.getLinkDownload() == null || song.getLinkDownload().size() < 3) {
			return null;
		}
		String link = decodeUrl(song.getLinkDownload().get(0), song.getLinkDownload().get(1), song.getLinkDownload().get(2));
		song.setLinkDownload(splitLink(link));
		return song;
	}
	
	private static List<String> splitLink(String link) {
		String key = "/128/";
		if(link.contains("/32/")) {
			key = "/32/";
		} else if(link.contains("/320/")) {
			key = "/320/";
		} else if(link.contains("/m4a/")) {
			key = "/m4a/";
		} else if(link.contains("/flac/")) {
			key = "/flac/";
		}
		String endWith = "";
		endWith = link.substring(link.length() - 3);
		List<String> list = new ArrayList<String>();
    		list.add(link.replace(key, "/128/").replace("." + endWith, "%20[MP3%20128kbps].mp3"));
    		list.add(link.replace(key, "/320/").replace("." + endWith, "%20[MP3%20320kbps].mp3"));
    		list.add(link.replace(key, "/m4a/").replace("." + endWith, "%20[M4A%20500kbps].m4a"));
    		list.add(link.replace(key, "/flac/").replace("." + endWith, "%20[FLAC%20Lossless].flac"));
		return list;
	}
	
	public static native String decodeUrl(String do_main, String key, String file) /*-{
		return $wnd.decode_download_url(do_main, key, file);
	}-*/;
}
