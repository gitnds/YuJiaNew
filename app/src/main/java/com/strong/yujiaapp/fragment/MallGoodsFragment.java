package com.strong.yujiaapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.strong.yujiaapp.R;

/**
 * Created by Administrator on 2017/8/23.
 */

public class MallGoodsFragment extends Fragment{

    private RecyclerView goods_recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_mall_goods, container, false);


        return view;
    }
}
