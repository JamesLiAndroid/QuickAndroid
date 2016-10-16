package com.james.li.quickandroid.Base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

/**
 * Created by tangyangkai on 16/5/4.
 */
public abstract class BaseFragment extends Fragment {

    //protected BaseActivity mActivity;
    protected Context context;

    protected LayoutInflater inflater;

    private View contentView;

    private ViewGroup container;

    //protected abstract void initView(View view, Bundle savedInstanceState);

    //获取fragment布局文件ID
   // protected abstract int getLayoutId();

    //获取宿主Activity
//    protected BaseActivity getHoldingActivity() {
//        return mActivity;
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // this.mActivity = (BaseActivity) activity;
        context = activity;
    }

//    //添加fragment
//    protected void addFragment(BaseFragment fragment) {
//        if (null != fragment) {
//            getHoldingActivity().addFragment(fragment);
//        }
//    }
//
//    //移除fragment
//    protected void removeFragment() {
//        getHoldingActivity().removeFragment();
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        onRestoreView(savedInstanceState);
        if (contentView == null)
            return super.onCreateView(inflater, container, savedInstanceState);
        return contentView;
       // initView(contentView, savedInstanceState);
    }

    /**
     * 设置显示View
     * @param layoutResID
     */
    public void setContentView(int layoutResID) {
        setContentView((ViewGroup) inflater.inflate(layoutResID, container, false));
    }

    public void setContentView(View view) {
        contentView = view;
    }

    public View getContentView() {
        return contentView;
    }

    public View findViewById(int id) {
        if (contentView != null)
            return contentView.findViewById(id);
        return null;
    }

    /**
     * 恢复Fragment状态时需要重新调用
     * @param savedInstanceState
     */
    protected abstract void onRestoreView(Bundle savedInstanceState);


    // http://stackoverflow.com/questions/15207305/getting-the-error-java-lang-illegalstateexception-activity-has-been-destroyed
    @Override
    public void onDetach() {
        Log.d("TAG", "onDetach() : ");
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        contentView = null;
        container = null;
        inflater = null;
    }
}
