package com.strong.yujiaapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.strong.yujiaapp.R;
import com.strong.yujiaapp.base.BaseActivity;

/**
 * Created by Administrator on 2017/8/22.
 */

public class OrderActivity extends BaseActivity {

    private LinearLayout ll_return, ll_order, ll_calculation;
    private TextView tv_title, tv_hint_add_s, tv_hint_add_r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getSupportActionBar().hide();

        initView();
        initData();
        initEvent();
    }

    public void initView() {

        ll_return = (LinearLayout) findViewById(R.id.ll_return);
        ll_order = (LinearLayout) findViewById(R.id.ll_order);
        ll_calculation = (LinearLayout) findViewById(R.id.ll_calculation);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_hint_add_s = (TextView) findViewById(R.id.tv_hint_add_s);
        tv_hint_add_r = (TextView) findViewById(R.id.tv_hint_add_r);

    }

    public void initData() {

        tv_title.setText("我要下单");
    }

    public void initEvent() {

        ll_return.setOnClickListener(returnClickListener);
        //添加发货人
        tv_hint_add_s.setOnClickListener(add_s_ClickListener);
        //添加收货人
        tv_hint_add_r.setOnClickListener(add_r_ClickListener);
        //下单
        ll_order.setOnClickListener(orderClickListener);
        //计算时效运费
        ll_calculation.setOnClickListener(calculationClickListener);
    }


    View.OnClickListener add_s_ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtra("type", "s");
            intent.setClass(OrderActivity.this, HumanInfoActivity.class);
            startActivityForResult(intent, 1);
        }
    };

    View.OnClickListener add_r_ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtra("type", "r");
            intent.setClass(OrderActivity.this, HumanInfoActivity.class);
            startActivityForResult(intent, 1);
        }
    };

    View.OnClickListener orderClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(OrderActivity.this, "下单！！！", Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener calculationClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(OrderActivity.this, "计算！！！", Toast.LENGTH_SHORT).show();
        }
    };

    //activity回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 10) {
            Toast.makeText(this, data.getStringExtra("computer"), Toast.LENGTH_LONG).show();
        }
    }
}
