package com.strong.yujiaapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.strong.yujiaapp.R;
import com.strong.yujiaapp.fragment.MapFragment;
import com.strong.yujiaapp.fragment.MapNearFragment;

public class BuiduActivity extends FragmentActivity implements View.OnClickListener {
    private static final String FRAGNEBT_MAP = "fragment_map";
    private static final String FRAGNEBT_NEAR = "fragment_sit";
    private TextView tv_title;
    private ImageView search, bt_baiduMap;
    private FragmentTransaction beginTransaction;
    private FragmentManager fm;
    public final String MapDisplay = "baiduiMap";
    public final String NearMapDisplay = "near";
    String type = MapDisplay;
    Fragment mapFragment,nearFragment;
    private Fragment currentFragment=new Fragment();

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search:

                break;
            case R.id.bt_baiduMap:
                if (type.equals(NearMapDisplay)) {
                  /*  FragmentTransaction ft = fm.beginTransaction();
                    ft.hide(new MapNearFragment()).add(R.id.fl_map, new MapFragment(), FRAGNEBT_MAP).commit();
                      ;*/
                  /*  */
                    showFragment(mapFragment);
                  /*  FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fl_map, mapFragment, FRAGNEBT_MAP).commit();*/
                    type = MapDisplay;
                } else {
                   /* FragmentTransaction ft = fm.beginTransaction();
                    ft.hide(new MapFragment()).add(R.id.fl_map, new MapNearFragment(), FRAGNEBT_NEAR).commit();
                     */
                   /*
                   */
                    showFragment(nearFragment);
                  /*  FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fl_map, nearFragment, FRAGNEBT_NEAR).commit();*/
                    type = NearMapDisplay;
                }
                break;
        }
    }

    private void showFragment(Fragment fragment) {
        if (currentFragment != fragment) {
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.hide(currentFragment);
            currentFragment = fragment;
            if (!fragment.isAdded()) {
                transaction.add(R.id.fl_map, fragment).show(fragment).commit();
            } else {
                transaction.show(fragment).commit();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_buidu);
        initFragments();
    }

    public void initFragments() {
        fm = getSupportFragmentManager();
        beginTransaction = fm.beginTransaction();
        mapFragment = new MapFragment();
        nearFragment = new MapNearFragment();
        showFragment(mapFragment);
        beginTransaction.commit();
        tv_title = (TextView) findViewById(R.id.tv_title);
        search = (ImageView) findViewById(R.id.search);
        bt_baiduMap = (ImageView) findViewById(R.id.bt_baiduMap);
        search.setVisibility(View.VISIBLE);
        bt_baiduMap.setVisibility(View.VISIBLE);
        search.setOnClickListener(this);
        bt_baiduMap.setOnClickListener(this);
        tv_title.setText("网点查询");

    }

}
