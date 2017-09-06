package com.strong.yujiaapp.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.strong.yujiaapp.R;
import com.strong.yujiaapp.base.BaseActivity;

/**
 * Created by Administrator on 2017/8/22.
 */

public class CargoActivity extends BaseActivity {
    private LinearLayout ll_return;
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo);
        getSupportActionBar().hide();
        initView();
        initData();
        initEvent();

    }

    public void initView(){

        ll_return = (LinearLayout) findViewById(R.id.ll_return);
        tv_title = (TextView) findViewById(R.id.tv_title);

    }
    public void initData(){

        tv_title.setText("货物跟踪");
    }
    public void initEvent(){

        ll_return.setOnClickListener(returnClickListener);
    }


}
