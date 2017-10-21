package com.hiep.supermusic.client.activities;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.mvp.client.AnimationMapper;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;
import com.hiep.supermusic.client.activities.home.HomePlace;

public class PhoneAnimationMapper implements AnimationMapper {

	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {
		if(oldPlace == null) {
			return Animations.FADE;
		}
		if(oldPlace instanceof HomePlace) {
			return Animations.SLIDE;
		}
		return Animations.SLIDE_REVERSE;
	}
}
