package com.strong.yujiaapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.strong.yujiaapp.LoadActivity;
import com.strong.yujiaapp.LoginActivity;
import com.strong.yujiaapp.R;
import com.strong.yujiaapp.activity.FeedbackActivity;
import com.strong.yujiaapp.activity.HumanInfoActivity;
import com.strong.yujiaapp.activity.MallActivity;
import com.strong.yujiaapp.activity.SendActivity;

/**
 * Created by Administrator on 2017/8/23.
 */

public class MyFragment extends Fragment implements View.OnClickListener {

    public Activity mActivity;
    private LinearLayout ll_return, ll_logout,ll_mall;
    private TextView tv_title, tv_myorder, tv_myloc, tv_myset, tv_mymessage, tv_myopinion;
    private Intent mIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mActivity = getActivity();//获取当前fragment依赖的activity
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        ll_return = view.findViewById(R.id.ll_return);
        ll_return.setVisibility(View.INVISIBLE);
        ll_logout = view.findViewById(R.id.ll_logout);
        tv_title = view.findViewById(R.id.tv_title);
        tv_myorder = view.findViewById(R.id.tv_myorder);
        tv_myloc = view.findViewById(R.id.tv_myloc);
        tv_myset = view.findViewById(R.id.tv_myset);
        tv_mymessage = view.findViewById(R.id.tv_mymessage);
        tv_myopinion = view.findViewById(R.id.tv_myopinion);
        ll_mall = view.findViewById(R.id.ll_mall);


        initData();
        initEvent();

        return view;
    }


    public void initData() {

        tv_title.setText("我");
    }

    public void initEvent() {

        tv_myorder.setOnClickListener(this);
        tv_myloc.setOnClickListener(this);
        tv_myset.setOnClickListener(this);
        tv_mymessage.setOnClickListener(this);
        tv_myopinion.setOnClickListener(this);
        ll_logout.setOnClickListener(this);
        ll_mall.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_mall:
                mIntent = new Intent();
                mIntent.setClass(mActivity, MallActivity.class);
                startActivity(mIntent);
                break;
            case R.id.tv_myorder:
                mIntent = new Intent();
                mIntent.setClass(mActivity, SendActivity.class);
                startActivity(mIntent);
                break;
            case R.id.tv_myloc:
                mIntent = new Intent();
                mIntent.putExtra("type", "s");
                mIntent.setClass(mActivity, HumanInfoActivity.class);
                startActivity(mIntent);
                break;
            case R.id.tv_myset:
                mIntent = new Intent();
                mIntent.setClass(mActivity, LoadActivity.class);
                startActivity(mIntent);
                break;
            case R.id.tv_mymessage:
                mIntent = new Intent();
                mIntent.setClass(mActivity, LoadActivity.class);
                startActivity(mIntent);
                break;
            case R.id.tv_myopinion:
                mIntent = new Intent();
                mIntent.setClass(mActivity, FeedbackActivity.class);
                startActivity(mIntent);
                break;
            case R.id.ll_logout:
                mIntent = new Intent();
                mIntent.setClass(mActivity, LoginActivity.class);
                mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mIntent);
                break;
        }
    }

}
