package com.strong.yujiaapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.strong.yujiaapp.R;
import com.strong.yujiaapp.base.BaseActivity;
import com.strong.yujiaapp.beanmodel.CityBean;
import com.strong.yujiaapp.controls.CityAdapter;
import com.strong.yujiaapp.controls.CountAdapter;
import com.strong.yujiaapp.controls.MyHotAdapter;
import com.strong.yujiaapp.controls.MyRecyclerAdapter;
import com.strong.yujiaapp.utils.CityData;
import com.strong.yujiaapp.utils.OnItemClieckLinster;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/22.
 */

public class SiteChoiceActivity extends BaseActivity {
    private LinearLayout ll_return;
    private TextView tv_title, tv_hot, tv_city, tv_site;
    private RecyclerView choice_recycler, choice_hot_recycler;
    private List<String> hot_mData, mData;
    private List<Map<String, String>> hot_mData2;
    private MyRecyclerAdapter mmyrecycleradapter;
    private MyHotAdapter hotmyrecycleradapter;
    private CityAdapter cityAdapter;
    private CountAdapter countAdapter;
    CityBean bean;
    int clickShengPosition;
    int clickCityPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_choice);
        getSupportActionBar().hide();
        initData();
        initView();
        tv_title.setText("选择省市区");
        initEvent();


    }

    public void initView() {

        ll_return = (LinearLayout) findViewById(R.id.ll_return);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_hot = (TextView) findViewById(R.id.tv_hot);
        tv_site = (TextView) findViewById(R.id.tv_site);
        tv_city = (TextView) findViewById(R.id.tv_city);

        choice_hot_recycler = (RecyclerView) findViewById(R.id.choice_hot_recycler);
        choice_recycler = (RecyclerView) findViewById(R.id.choice_recycler);

        hotmyrecycleradapter = new MyHotAdapter(this, hot_mData);
        mmyrecycleradapter = new MyRecyclerAdapter(this, bean.getData());

        choice_hot_recycler.setAdapter(hotmyrecycleradapter);//设置适配器
        choice_recycler.setAdapter(mmyrecycleradapter);//设置适配器


        //设置布局管理器 , 将布局设置成纵向
        LinearLayoutManager linerLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        /*mRecycleView.setLayoutManager(new LinearLayoutManager(this));*/
        choice_hot_recycler.setLayoutManager(new GridLayoutManager(this, 3));
        choice_recycler.setLayoutManager(new GridLayoutManager(this, 3));

        //设置分隔线
        //mRecycleView.addItemDecoration(new DividerItemDecoration(this , DividerItemDecoration.VERTICAL_LIST));

        //设置增加或删除条目动画
        choice_hot_recycler.setItemAnimator(new DefaultItemAnimator());


    }

    public void initData() {

        hot_mData = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            hot_mData.add("热门" + i);

        }
        Gson gson = new Gson();
        CityData cityData = new CityData();
        String json = cityData.getJson(SiteChoiceActivity.this);
        bean = gson.fromJson(json, CityBean.class);


    }

    public void initEvent() {

        ll_return.setOnClickListener(returnClickListener);

        hotmyrecycleradapter.setOnItemClieckLinster(new MyHotAdapter.OnItemClieckLinster() {
            @Override
            public void onItemClickListener(View view, int pos) {

                tv_hot.setText(hot_mData.get(pos));
                tv_city.setVisibility(View.VISIBLE);
                tv_city.setText(hot_mData.get(pos));
                tv_site.setText("选择区县");


                choice_hot_recycler.setVisibility(View.GONE);

                Toast.makeText(SiteChoiceActivity.this, "热门 click" + pos, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClickListener(View view, int pos) {
                Toast.makeText(SiteChoiceActivity.this, "热门 long click" + pos, Toast.LENGTH_SHORT).show();
            }
        });

        mmyrecycleradapter.setOnItemClieckLinster(new OnItemClieckLinster() {
            @Override
            public void onItemClickListener(View view, int pos, String name) {
                Toast.makeText(SiteChoiceActivity.this, "click" + pos, Toast.LENGTH_SHORT).show();
                cityAdapter = new CityAdapter(SiteChoiceActivity.this, bean.getData().get(pos).getCity());
                cityAdapter.setOnItemClieckLinster(new OnItemClieckLinster() {
                    @Override
                    public void onItemClickListener(View view, int pos, String name) {
                        Toast.makeText(SiteChoiceActivity.this, "click" + pos, Toast.LENGTH_SHORT).show();
                        countAdapter = new CountAdapter(SiteChoiceActivity.this, bean.getData().get(clickShengPosition).getCity().get(pos).getCounty());
                        choice_recycler.setAdapter(countAdapter);//设置适配器
                        clickCityPosition = pos;
                        tv_site.setText("选择区县");
                        tv_hot.setVisibility(View.VISIBLE);
                        tv_hot.setText(bean.getData().get(clickShengPosition).getName());
                        tv_city.setVisibility(View.VISIBLE);
                        tv_city.setText(bean.getData().get(clickShengPosition).getCity().get(pos).getName());
                        choice_hot_recycler.setVisibility(View.GONE);
                        countAdapter.setOnItemClieckLinster(new OnItemClieckLinster() {
                            @Override
                            public void onItemClickListener(View view, int pos, String name) {
                                Intent intent = new Intent(SiteChoiceActivity.this,SiteActivity.class);
                              //  Bundle b=new Bundle();
                              //  b.putStringArray("name",  new String[]{bean.getData().get(clickShengPosition).getName(),bean.getData().get(clickShengPosition).getCity().get(clickCityPosition).getName(),bean.getData().get(clickShengPosition).getCity().get(clickCityPosition).getCounty().get(pos)});
                                intent.putExtra("name",bean.getData().get(clickShengPosition).getName()+"@"+bean.getData().get(clickShengPosition).getCity().get(clickCityPosition).getName()+"@"+bean.getData().get(clickShengPosition).getCity().get(clickCityPosition).getCounty().get(pos));
                                setResult(10, intent);
                                finish();
                            }

                            @Override
                            public void onItemLongClickListener(View view, int pos) {
                                Toast.makeText(SiteChoiceActivity.this, "long click" + pos, Toast.LENGTH_SHORT).show();
                            }
                        });
                }

                    @Override
                    public void onItemLongClickListener(View view, int pos) {
                        Toast.makeText(SiteChoiceActivity.this, "long click" + pos, Toast.LENGTH_SHORT).show();
                    }
                });
                clickShengPosition = pos;
                choice_recycler.setAdapter(cityAdapter);//设置适配器
                tv_site.setText("选择城市");
                tv_hot.setVisibility(View.GONE);
                tv_city.setVisibility(View.VISIBLE);
                tv_city.setText(bean.getData().get(pos).getName());
                choice_hot_recycler.setVisibility(View.GONE);
            }


            @Override
            public void onItemLongClickListener(View view, int pos) {
                Toast.makeText(SiteChoiceActivity.this, "long click" + pos, Toast.LENGTH_SHORT).show();
            }
        });


    }


}
