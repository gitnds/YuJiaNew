package com.strong.yujiaapp.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.strong.yujiaapp.R;

/**
 * Created by Administrator on 2017/8/23.
 */

public class InfoSFragment extends Fragment{

    public Activity mActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        mActivity = getActivity();//获取当前fragment依赖的activity
        View view = inflater.inflate(R.layout.fragment_info_s, container, false);


        return view;
    }


}
