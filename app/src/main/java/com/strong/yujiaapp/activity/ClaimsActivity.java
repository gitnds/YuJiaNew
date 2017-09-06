package com.strong.yujiaapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.strong.yujiaapp.R;
import com.strong.yujiaapp.base.BaseActivity;
import com.strong.yujiaapp.controls.DialogAg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

public class ClaimsActivity extends BaseActivity implements View.OnClickListener{
    List<String> list ;
    String[] goodsName = {"药品","服装","电脑","厨具","工具","配件","电器","家具","电脑","乐器","日用品","食品","书","行李","其他"};
    String[] accident = {"丢失","火灾","交通肇事","破损","其他"};
    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.goods_name_linear){
            list = new ArrayList<String>();
            for (int i=0;i<goodsName.length;i++) {
                list.add(goodsName[i]);
            }
            DialogAg.getListDialog(ClaimsActivity.this,list,metrics,tv_goods_name);
        }else if (view.getId()==R.id.accident_linear){
            list  = new ArrayList<String>();
            for (int i=0;i<accident.length;i++) {
                list.add(accident[i]);
            }
            DialogAg.getListDialog(ClaimsActivity.this,list,metrics,tv_accident);
        }
    }

    private LinearLayout ll_return,accident_linear,goods_name_linear;

    private TextView tv_title,tv_goods_name,tv_accident;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claimsapplication);
        getSupportActionBar().hide();
        initView();
        initData();
        initEvent();

    }

    public void initView(){

        ll_return = (LinearLayout) findViewById(R.id.ll_return);
        goods_name_linear = (LinearLayout) findViewById(R.id.goods_name_linear);
        accident_linear = (LinearLayout) findViewById(R.id.accident_linear);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_goods_name = (TextView) findViewById(R.id.tv_goods_name);
        tv_accident = (TextView) findViewById(R.id.tv_accident);


    }
    public void initData(){

        tv_title.setText("理赔申请");
    }
    public void initEvent(){

        ll_return.setOnClickListener(returnClickListener);
        goods_name_linear.setOnClickListener(this);
        accident_linear.setOnClickListener(this);

    }


}
