package com.strong.yujiaapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.strong.yujiaapp.R;
import com.strong.yujiaapp.base.BaseActivity;
import com.strong.yujiaapp.fragment.MallGoodsFragment;
import com.strong.yujiaapp.fragment.MallRecordFragment;
import com.strong.yujiaapp.fragment.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

public class MallActivity extends BaseActivity {
    private LinearLayout ll_return;
    private TextView tv_title;
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private RadioButton rb_goods, rb_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
        getSupportActionBar().hide();
        initView();
        initData();
        initEvent();

    }

    public void initView(){

        ll_return = (LinearLayout) findViewById(R.id.ll_return);
        tv_title = (TextView) findViewById(R.id.tv_title);

        /**
         * RadioGroup部分
         */
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rb_goods = (RadioButton) findViewById(R.id.rb_goods);
        rb_record = (RadioButton) findViewById(R.id.rb_record);


        //RadioGroup选中状态改变监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_goods:
                        viewPager.setCurrentItem(0, true);
                        break;
                    case R.id.rb_record:
                        viewPager.setCurrentItem(1, true);
                        break;


                }
            }
        });

        /**
         * ViewPager部分
         */
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        MallGoodsFragment mallgoodsfragment = new MallGoodsFragment();
        MallRecordFragment mallrecordfragment = new MallRecordFragment();


        List<Fragment> alFragment = new ArrayList<Fragment>();

        alFragment.add(mallgoodsfragment);
        alFragment.add(mallrecordfragment);

        //ViewPager设置适配器
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), alFragment));
        //ViewPager显示第一个Fragment
        viewPager.setCurrentItem(0);
        //ViewPager页面切换监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radioGroup.check(R.id.rb_goods);
                        break;
                    case 1:
                        radioGroup.check(R.id.rb_record);
                        break;


                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    public void initData(){

        tv_title.setText("积分商城");
    }
    public void initEvent(){

        ll_return.setOnClickListener(returnClickListener);
    }


}
