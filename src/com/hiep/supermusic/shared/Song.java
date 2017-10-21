package com.hiep.supermusic.shared;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Song implements IBasic {
	private static final long serialVersionUID = 1L;
	@Id Long id;
	@Index String key;
	@Index private String name;
	@Index private String singer;// ca si
	private String avatar; // anh music
	private String link;
	private String lyric;// loi bai hat
	@Index private String artist;// tac gia
	private String quality;// chat luong
	private List<String> linkDownload = new ArrayList<String>();
	@Index int status;
	
	@Ignore private boolean hasData = false;

	public Song() {
	}
	
	public Song(String key, String name, String singer){ 
		setKey(key);
		setName(name);
		setSinger(singer);
		setLink(link);
		setLyric(lyric);
		setArtist(artist);
		setQuality(quality);
	}
	
	@Override
	public Object getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLyric() {
		return lyric;
	}

	public void setLyric(String lyric) {
		this.lyric = lyric;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public List<String> getLinkDownload() {
		return linkDownload;
	}

	public void setLinkDownload(List<String> linkDownload) {
		this.linkDownload = linkDownload;
	}
	
	public void addLinkDownload(String item) {
		if(this.linkDownload == null) this.linkDownload = new ArrayList<String>();
		this.linkDownload.add(item);
	}
	
	public void setHasData(boolean hasData) {
		this.hasData = hasData;
	}
	
	public boolean isHasData() {
		return hasData;
	}
	
	public String getLink320Kbps() {
		if(linkDownload.size() >= 2)
			return linkDownload.get(1);
		return linkDownload.get(0);
	}
	
	public String getLink128Kbps() {
		return linkDownload.get(0);
	}
	
	public String getLink500Kbps() {
		if(linkDownload.size() >= 3)
			return linkDownload.get(2);
		return linkDownload.get(0);
	}
	
	public String getLinkLossless() {
		if(linkDownload.size() >= 4)
			return linkDownload.get(3);
		return linkDownload.get(0);
	}
}
