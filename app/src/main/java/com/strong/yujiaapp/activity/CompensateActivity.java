package com.strong.yujiaapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.strong.yujiaapp.R;
import com.strong.yujiaapp.base.BaseActivity;

/**
 * Created by Administrator on 2017/8/22.
 */

public class CompensateActivity extends BaseActivity {
    private LinearLayout ll_return;
    private LinearLayout ll_add;
    private TextView tv_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compenstate);
        getSupportActionBar().hide();
        initView();
        initData();
        initEvent();

    }

    public void initView(){

        ll_return = (LinearLayout) findViewById(R.id.ll_return);
        ll_add = (LinearLayout) findViewById(R.id.ll_add);
        tv_title = (TextView) findViewById(R.id.tv_title);


    }
    public void initData(){

        tv_title.setText("在线理赔");
    }
    public void initEvent(){

        ll_return.setOnClickListener(returnClickListener);
        ll_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(CompensateActivity.this,ClaimsActivity.class);
                startActivity(intent);
            }
        });
    }


}
