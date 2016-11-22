package com.james.li.quickandroid.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.james.li.quickandroid.R;
import com.james.li.quickandroid.bean.CardViewBean;
import java.util.List;

/**
 * Created by jyj-lsy on 11/22/16 in zsl-tech.
 */

public class CustomRecylerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

	private List<CardViewBean> beans;
	private LayoutInflater mInflater;
	private Context context;

	public static final int TYPE_ITEM = 0;  //普通Item View
	public static final int TYPE_FOOTER = 1;  //顶部FootView
	public static final int PULLUP_LOAD_MORE = 0;  // 上拉加载更多
	public static final int LOADING_MORE = 1;  // 加载更多
	public int load_more_status = 0; // FootView的加载状态
	public CustomRecylerAdapter(Context context) {
		this.context = context;
	}

	public CustomRecylerAdapter(List<CardViewBean> beans, Context context) {
		this.context = context;
		mInflater = LayoutInflater.from(context);
		this.beans = beans;

	}

	@Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if(viewType == TYPE_ITEM) {
			View view = mInflater.inflate(R.layout.item_card, parent, false);
			return new ItemCardViewHolder(view);
		} else if(viewType == TYPE_FOOTER) {
			View view = mInflater.inflate(R.layout.item_load_more, parent, false);
			return new FooterViewHolder(view);
		}
		return null;
	}

	@Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if(holder instanceof ItemCardViewHolder) {
			((ItemCardViewHolder)holder).itemCardView.setBackgroundColor(beans.get(position).getColor());
			((ItemCardViewHolder)holder).itemTv.setText(beans.get(position).getContent());
			holder.itemView.setTag(position);
		} else if(holder instanceof FooterViewHolder) {
			FooterViewHolder footViewHolder=(FooterViewHolder) holder;
			switch (load_more_status){
				case PULLUP_LOAD_MORE:
					footViewHolder.tvFooter.setText("上拉加载更多...");
					break;
				case LOADING_MORE:
					footViewHolder.tvFooter.setText("正在加载更多数据...");
					break;
			}
		}

	}

	@Override public int getItemCount() {
		return beans.size() + 1;
	}

	/**
	 * 进行判断是普通Item视图还是FootView视图
	 * @param position
	 * @return
	 */
	@Override
	public int getItemViewType(int position) {
		// 最后一个item设置为footerView
		if (position + 1 == getItemCount()) {
			return TYPE_FOOTER;
		} else {
			return TYPE_ITEM;
		}
	}

	// 刷新时向列表中添加数据的方法
	public void refresh(List<CardViewBean> beansNew) {
		beansNew.addAll(beans);
		beans.removeAll(beans);
		beans.addAll(beansNew);
		notifyDataSetChanged();
	}

	// 下拉加载时向列表中添加数据的方法
	public void loadMore(List<CardViewBean> beansNew) {
		beans.addAll(beansNew);
		notifyDataSetChanged();
	}

	/**
	 * //上拉加载更多
	 * PULLUP_LOAD_MORE=0;
	 * //正在加载中
	 * LOADING_MORE=1;
	 * //加载完成已经没有更多数据了
	 * NO_MORE_DATA=2;
	 * @param status
	 */
	public void changeMoreStatus(int status){
		load_more_status=status;
		notifyDataSetChanged();
	}

	public static class FooterViewHolder extends RecyclerView.ViewHolder {
		private TextView tvFooter;
		public FooterViewHolder(View view) {
			super(view);
			tvFooter = (TextView) view.findViewById(R.id.tv_footer);
		}
	}
	public static class ItemCardViewHolder extends RecyclerView.ViewHolder {
		private CardView itemCardView;
		private TextView itemTv;

		public ItemCardViewHolder(View itemView) {
			super(itemView);
			itemCardView = (CardView) itemView.findViewById(R.id.card_view);
			itemTv = (TextView) itemView.findViewById(R.id.item_tv);
		}
	}
}
