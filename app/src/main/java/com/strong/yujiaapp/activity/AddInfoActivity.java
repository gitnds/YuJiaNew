package com.strong.yujiaapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.strong.yujiaapp.R;
import com.strong.yujiaapp.base.BaseActivity;

public class AddInfoActivity extends BaseActivity {

    private LinearLayout ll_return, ll_loc,ll_hold;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinfo);
        getSupportActionBar().hide();
//hfkdjs
        initView();
        initData();
        initEvent();

    }

    public void initView() {
        ll_return = (LinearLayout) findViewById(R.id.ll_return);
        ll_loc = (LinearLayout) findViewById(R.id.ll_loc);
        ll_hold = (LinearLayout) findViewById(R.id.ll_hold);
        tv_title = (TextView) findViewById(R.id.tv_title);
    }

    public void initData() {

        tv_title.setText("新增地址");
    }

    public void initEvent() {

        ll_return.setOnClickListener(returnClickListener);
        //选择省市区
        ll_loc.setOnClickListener(locClickListener);
        //保存
        ll_hold.setOnClickListener(holdClickListener);
    }

    View.OnClickListener locClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener holdClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
