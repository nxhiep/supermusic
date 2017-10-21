package com.hiep.supermusic.server;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.ObjectifyService;
import com.hiep.supermusic.client.DataService;
import com.hiep.supermusic.shared.Config;
import com.hiep.supermusic.shared.Song;

@SuppressWarnings("serial")
public class DataServiceImpl extends RemoteServiceServlet implements DataService {

	public static final Logger log = Logger.getLogger(DataServiceImpl.class.getName()); 
	private boolean testFunction = true;
	
	static {
		ObjectifyService.register(Song.class);
	}

	@Override
	public void save(Song song){
		ofy().save().entity(song).now();
	}
	
	public void saves(List<Song> songs){
		ofy().save().entities(songs).now();
	}
	
	private List<Song> getSongFakes(){
		List<Song> songs = new ArrayList<Song>();
		for(int i = 0; i < 10; i++) {
			Song song = new Song();
			song.setName("Bài hát số " + i + 1);
			song.setSinger("Anh sáng tác");
			song.setLink("http://127.0.0.1:8888/getsongfake");
			song.setAvatar("http://data.chiasenhac.com/data/cover/78/77827.jpg");
			List<String> linkDownload = new ArrayList<String>();
			linkDownload.add("http://127.0.0.1:8888/images/song.mp3");
			linkDownload.add("http://127.0.0.1:8888/images/song.mp3");
			linkDownload.add("http://127.0.0.1:8888/images/song.mp3");
			linkDownload.add("http://127.0.0.1:8888/images/song.mp3");
			song.setLinkDownload(linkDownload);
			songs.add(song);
		}
		return songs;
	}
	
	@Override
	public List<Song> searchSong(String name, String mode, String order, String cat, int page){
		if(testFunction) {
			return getSongFakes();
		}
		if(page < 1) page = 1;
		String urlStr = Config.API_CSN_SEARCH_URL + "name=" + name + "&mode=" + mode + "&order=" + order + "&cat=" + cat + "&page=" + page;
		String json = UrlFetcher.get(urlStr);
		List<Song> songs = new ArrayList<Song>();
		JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
			Song song = new Song();
			song.setKey(getStringObject("id", jsonObject));
			song.setName(getStringObject("name", jsonObject));
			song.setSinger(getStringObject("singer", jsonObject));
			song.setLink(getStringObject("link", jsonObject));
			songs.add(song);
		}
		return songs;
	}
	
	private String getStringObject(String key, JsonObject jsonObject) {
		String result = "";
		try {
			result = jsonObject.get(key).getAsString();
		} catch(Exception e) {}
		return result;
	}
	
	@Override
	public Song getSong(Song song){
		if(testFunction) {
			return getSongFakes().get(2);
		}
		if(song == null || song.getLink() == null || song.getLink().isEmpty()) {
			return null;
		}
		String urlStr = Config.API_CSN_GET_SONG_URL + "url=" + song.getLink();
		String json = UrlFetcher.get(urlStr);
		JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
		song.setAvatar(getStringObject("avatar", jsonObject));
		song.setLyric(getStringObject("lyric", jsonObject));
		song.setArtist(getStringObject("artist", jsonObject));
		JsonArray array = jsonObject.get("linkDownload").getAsJsonArray();
		for(int i = 0; i < array.size(); i++) {
			String item = array.get(i).getAsString();
			song.addLinkDownload(item);
		}
		return song;
	}
	
	@Override
	public Map<String, List<Song>> getSongs(){
		if(testFunction) {
			Map<String, List<Song>> songs = new HashMap<String, List<Song>>();
			songs.put("0", new ArrayList<Song>(getSongFakes().subList(0, 4)));
			songs.put("2", new ArrayList<Song>(getSongFakes().subList(4, getSongFakes().size() - 1)));
			return songs;
		}
		Map<String, List<Song>> songs = new HashMap<String, List<Song>>();
		String urlStr = Config.API_CSN_GET_SONGS_URL;
		String json = UrlFetcher.get(urlStr);
		JsonObject jsonObjectRoot = new JsonParser().parse(json).getAsJsonObject();
		for(Entry<String, JsonElement> entry : jsonObjectRoot.entrySet()) {
			String key = entry.getKey();
			JsonArray value = entry.getValue().getAsJsonArray();
			for(int i = 0;i < value.size(); i++) {
				JsonObject jsonObject = value.get(i).getAsJsonObject();
				Song song = new Song();
				song.setName(jsonObject.get("name").getAsString());
				song.setSinger(jsonObject.get("singer").getAsString());
				song.setAvatar(jsonObject.get("avatar").getAsString());
				song.setLink(jsonObject.get("link").getAsString());
				if(!songs.containsKey(key)) {
					songs.put(key, new ArrayList<Song>());
				}
				songs.get(key).add(song);
			}
		}
		return songs;
	}
}
