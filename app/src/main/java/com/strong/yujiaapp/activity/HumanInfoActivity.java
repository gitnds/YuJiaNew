package com.strong.yujiaapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.strong.yujiaapp.R;
import com.strong.yujiaapp.base.BaseActivity;
import com.strong.yujiaapp.fragment.InfoRFragment;
import com.strong.yujiaapp.fragment.InfoSFragment;
import com.strong.yujiaapp.fragment.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

public class HumanInfoActivity extends BaseActivity {
    private LinearLayout ll_return;
    private TextView tv_title;
    private Button finish;
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private RadioButton rb_info_s, rb_info_r;
    private LinearLayout ll_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.humaninfo);
        getSupportActionBar().hide();
        initView();
        initData();
        initEvent();

    }

    public void initView() {

        ll_return = (LinearLayout) findViewById(R.id.ll_return);
        tv_title = (TextView) findViewById(R.id.tv_title);

        ll_add = (LinearLayout)findViewById(R.id.ll_add);

        finish = (Button) findViewById(R.id.finish);

        /**
         * RadioGroup部分
         */
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rb_info_s = (RadioButton) findViewById(R.id.rb_info_s);
        rb_info_r = (RadioButton) findViewById(R.id.rb_info_r);

        //RadioGroup选中状态改变监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_info_s:
                        viewPager.setCurrentItem(0, true);
                        break;
                    case R.id.rb_info_r:
                        viewPager.setCurrentItem(1, true);
                        break;


                }
            }
        });

        /**
         * ViewPager部分
         */
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        InfoSFragment infosfragment = new InfoSFragment();
        InfoRFragment inforfragment = new InfoRFragment();

        List<Fragment> alFragment = new ArrayList<Fragment>();

        alFragment.add(infosfragment);
        alFragment.add(inforfragment);

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
                        radioGroup.check(R.id.rb_info_s);
                        break;
                    case 1:
                        radioGroup.check(R.id.rb_info_r);
                        break;


                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        Intent intent = getIntent();
        String type = intent.getStringExtra("type");

        switch (type) {
            case "s":
                rb_info_s.setChecked(true);
                break;
            case "r":
                rb_info_r.setChecked(true);
                break;
        }

    }

    public void initData() {

        tv_title.setText("地址薄");
    }

    public void initEvent() {

        ll_return.setOnClickListener(returnClickListener);
        ll_add.setOnClickListener(addinfoClickListener);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String computer = "传回来的数据";
                Intent intent = new Intent(HumanInfoActivity.this, OrderActivity.class).putExtra("computer", computer);
                setResult(10, intent);
                finish();
            }
        });
    }

    View.OnClickListener addinfoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(HumanInfoActivity.this,"增加信息",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(HumanInfoActivity.this,AddInfoActivity.class);
            startActivityForResult(intent,1);
        }
    };


}
