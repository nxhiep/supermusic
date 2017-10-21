package com.hiep.supermusic.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface IBasic extends Serializable, IsSerializable{
	public Object getId();
	public int getStatus();
}
