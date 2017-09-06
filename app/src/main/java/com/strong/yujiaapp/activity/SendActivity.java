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
import com.strong.yujiaapp.fragment.MyFragmentPagerAdapter;
import com.strong.yujiaapp.fragment.SendCancelFragment;
import com.strong.yujiaapp.fragment.SendNotSignFragment;
import com.strong.yujiaapp.fragment.SendSignFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

public class SendActivity extends BaseActivity {
    private LinearLayout ll_return;
    private TextView tv_title;
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private RadioButton rb_not_sign, rb_signed, rb_canceled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        getSupportActionBar().hide();
        initView();
        initData();
        initEvent();
    }

    public void initView() {

        ll_return = (LinearLayout) findViewById(R.id.ll_return);
        tv_title = (TextView) findViewById(R.id.tv_title);

        /**
         * RadioGroup部分
         */
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rb_not_sign = (RadioButton) findViewById(R.id.rb_not_sign);
        rb_signed = (RadioButton) findViewById(R.id.rb_signed);
        rb_canceled = (RadioButton) findViewById(R.id.rb_canceled);

        //RadioGroup选中状态改变监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_not_sign:
                        viewPager.setCurrentItem(0, true);
                        break;
                    case R.id.rb_signed:
                        viewPager.setCurrentItem(1, true);
                        break;
                    case R.id.rb_canceled:
                        viewPager.setCurrentItem(2, true);
                        break;

                }
            }
        });

        /**
         * ViewPager部分
         */
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        SendNotSignFragment sendnotsignfragment = new SendNotSignFragment();
        SendSignFragment sendsignfragment = new SendSignFragment();
        SendCancelFragment sendcancelfragment = new SendCancelFragment();

        List<Fragment> alFragment = new ArrayList<Fragment>();

        alFragment.add(sendnotsignfragment);
        alFragment.add(sendsignfragment);
        alFragment.add(sendcancelfragment);

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
                        radioGroup.check(R.id.rb_not_sign);
                        break;
                    case 1:
                        radioGroup.check(R.id.rb_signed);
                        break;
                    case 2:
                        radioGroup.check(R.id.rb_canceled);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void initData() {

        tv_title.setText("我发出的");
    }

    public void initEvent() {

        ll_return.setOnClickListener(returnClickListener);
    }


}
