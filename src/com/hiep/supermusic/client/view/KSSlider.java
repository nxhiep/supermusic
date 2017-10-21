package com.hiep.supermusic.client.view;

import com.googlecode.mgwt.ui.client.util.CssUtil;
import com.googlecode.mgwt.ui.client.widget.input.slider.Slider;
import com.hiep.supermusic.client.ClientUtils;

public class KSSlider extends Slider {

	private int value = 0;

	public KSSlider() {
		super();
		pointer.addClassName("KSSlider-pointer");
		// pointer.setInnerText("|||");
		bar.addClassName("KSSlider-bar");
		setValue(0);
	}

	private void setPos(int value) {
		ClientUtils.log("Set pos: " + value);
		int width = bar.getOffsetWidth();
		int sliderPos = value * width / getMax();
		CssUtil.translate(pointer, sliderPos, 0);
	}

	//
	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public void setValue(Integer value) {
		if (value == null) {
			throw new IllegalArgumentException("value can not be null");
		}

		if (value < 0) {
			throw new IllegalArgumentException("value >= 0");
		}

		if (value > getMax()) {
			throw new IllegalArgumentException("value > max");
		}
		this.value = value;
		setPos(value);
	}
}