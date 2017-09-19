package com.strong.yujiaapp.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.strong.yujiaapp.R;
import com.strong.yujiaapp.activity.BuiduActivity;
import com.strong.yujiaapp.controls.MapNearSitAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/23.
 */

public class MapNearFragment extends Fragment{
Activity activity;
    TextView textView;
    RecyclerView near_group_recycler;
    MapNearSitAdapter mapNearAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.map_near_sit_fragment, container, false);
        near_group_recycler =  view.findViewById(R.id.near_group_recycler);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        activity = (Activity) getContext();
        super.onCreate(savedInstanceState);

    }




    public void initData() {
        BuiduActivity baiduActivity = (BuiduActivity) activity;
       List<Map<String,String>> maps = baiduActivity.getMapFragement().sort;
        mapNearAdapter = new MapNearSitAdapter(activity, maps);

        near_group_recycler.setAdapter(mapNearAdapter);//设置适配器
        near_group_recycler.setLayoutManager(new GridLayoutManager(activity, 1));
        //设置增加或删除条目动画
        near_group_recycler.setItemAnimator(new DefaultItemAnimator());

    }
}
