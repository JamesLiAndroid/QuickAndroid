package com.james.li.quickandroid.bean;

import lombok.Data;

/**
 * Created by jyj-lsy on 11/22/16 in zsl-tech.
 */

@Data
public class CardViewBean {
	int color;
	String content;

	public CardViewBean(int color, String content) {
		this.color = color;
		this.content = content;
	}
}
