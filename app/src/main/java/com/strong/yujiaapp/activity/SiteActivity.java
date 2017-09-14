package com.strong.yujiaapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.strong.yujiaapp.R;
import com.strong.yujiaapp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

public class SiteActivity extends BaseActivity {
    private LinearLayout ll_return;
    private TextView tv_title;
    private RecyclerView rc_history;
    private EditText et_choice_city;
    private List<String> hData;
    private  ImageView bt_baiduMap;
    String[] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        getSupportActionBar().hide();
        initData();
        initView();
        initEvent();
    }

    public void initView() {

        ll_return = (LinearLayout) findViewById(R.id.ll_return);
        rc_history = (RecyclerView) findViewById(R.id.rc_history);
        tv_title = (TextView) findViewById(R.id.tv_title);
        bt_baiduMap = (ImageView) findViewById(R.id.bt_baiduMap);
        bt_baiduMap.setVisibility(View.VISIBLE);
        tv_title.setText("网点查询");
        et_choice_city = (EditText) findViewById(R.id.et_choice_city);

    }

    public void initData() {

        hData = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            hData.add("历史" + i);

        }
    }

    public void initEvent() {

        ll_return.setOnClickListener(returnClickListener);
        et_choice_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(SiteActivity.this, SiteChoiceActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        bt_baiduMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(SiteActivity.this, BuiduActivity.class);
                startActivity(intent);
            }
        });
    }

    //activity回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 10) {
            String[] array = data.getStringExtra("name").split("@");
            if (array != null) {

                et_choice_city.setText(array[0] + "-" + array[1] + "-" + array[2]);
            }
        }
    }

}
