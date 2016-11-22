package com.james.li.quickandroid.bean;

import android.util.Log;
import com.james.li.quickandroid.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jyj-lsy on 11/22/16 in zsl-tech.
 */

public class CardDataUtils {
		public static List<CardViewBean> getCardViewDatas(){
			List<CardViewBean> beans=new ArrayList<CardViewBean>();
			int[] colors=new int[]{
				R.color.color_0,R.color.color_1,
				R.color.color_2,R.color.color_3,R.color.color_4,
				R.color.color_5,R.color.color_6,R.color.color_7,
				R.color.color_8,R.color.color_9,R.color.color_10,};
			for(int i=0;i<11;i++){
				beans.add(new CardViewBean(colors[i], "CardView测试Item"+i));
			}
			Log.d("TAG", "beans::"+beans);
			return beans;
		}

	public static List<CardViewBean>  getCardViewDownDatas(int limit){
		List<CardViewBean> beans=new ArrayList<CardViewBean>();
		int[] colors=new int[]{
			R.color.color_0,R.color.color_1,
			R.color.color_2,R.color.color_3,R.color.color_4,
			R.color.color_5,R.color.color_6,R.color.color_7,
			R.color.color_8,R.color.color_9,R.color.color_10,};
		for(int i=0;i < limit;i++){
			int custom = createRandom(4, 40);
			beans.add(new CardViewBean(colors[i], "上拉加载添加Item"+custom));
		}
		Log.d("TAG", "beans::"+beans);
		return beans;
	}

	public static List<CardViewBean>  getCardViewLimitDatas(int limit){
		List<CardViewBean> beans=new ArrayList<CardViewBean>();
		int[] colors=new int[]{
			R.color.color_0,R.color.color_1,
			R.color.color_2,R.color.color_3,R.color.color_4,
			R.color.color_5,R.color.color_6,R.color.color_7,
			R.color.color_8,R.color.color_9,R.color.color_10,};
		for(int i=0;i < limit;i++){
			int custom = createRandom(4, 40);
			beans.add(new CardViewBean(colors[i], "下拉刷新添加Item"+custom));
		}
		Log.d("TAG", "beans::"+beans);
		return beans;
	}

	private static int createRandom(int min, int max) {
		Random random = new Random();
		int s = random.nextInt(max)  %  (max - min + 1) + min;
		return s;
	}

}
