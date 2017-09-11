package com.strong.yujiaapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.strong.yujiaapp.R;
import com.strong.yujiaapp.base.BaseActivity;
import com.strong.yujiaapp.beanmodel.ServiceData;
import com.strong.yujiaapp.controls.GrideViewServiceAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

public class ServiceActivity extends BaseActivity {
    private LinearLayout ll_return;
    private TextView tv_title;
    private GridView gv_service;
    private GrideViewServiceAdapter serviceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        getSupportActionBar().hide();
        initView();
        initData();
        initEvent();
    }

    public void initView(){

        ll_return = (LinearLayout) findViewById(R.id.ll_return);
        tv_title = (TextView) findViewById(R.id.tv_title);
        gv_service = (GridView) findViewById(R.id.service_gridview);

    }
    public void initData(){

        tv_title.setText("服务介绍");
        // 列表
        serviceAdapter = new GrideViewServiceAdapter(ServiceActivity.this, getData());
        gv_service.setAdapter(serviceAdapter);
    }
    private List<ServiceData> getData() {
        List<ServiceData> list = new ArrayList<ServiceData>();
        ServiceData service1 = new ServiceData();
        service1.setImageId(R.mipmap.bannertwo);
        service1.setMessage("专线城运站点服务");
        list.add(service1);
        ServiceData service2 = new ServiceData();
        service2.setMessage("电商业务");
        service2.setImageId(R.mipmap.bannertwo);
        list.add(service1);
        ServiceData service3 = new ServiceData();
        service3.setImageId(R.mipmap.bannertwo);
        service3.setMessage("仓储服务");
        list.add(service3);
        ServiceData service4 = new ServiceData();
        service4.setImageId(R.mipmap.bannertwo);
        service4.setMessage("三方货运");
        list.add(service4);
        ServiceData service5 = new ServiceData();
        service5.setMessage("城市配送");
        service5.setImageId(R.mipmap.bannertwo);
        list.add(service5);
        ServiceData service6 = new ServiceData();
        service6.setMessage("代收货款");
        service6.setImageId(R.mipmap.bannertwo);
        list.add(service6);
        ServiceData service7 = new ServiceData();
        service7.setMessage("手持机开单");
        service7.setImageId(R.mipmap.bannertwo);
        list.add(service7);
        ServiceData service8 = new ServiceData();
        service8.setMessage("代签回单");
        service8.setImageId(R.mipmap.bannertwo);
        list.add(service8);
        ServiceData service9 = new ServiceData();
        service9.setMessage("网上下单");
        service9.setImageId(R.mipmap.bannertwo);
        list.add(service9);
        ServiceData service10 = new ServiceData();
        service10.setMessage("保价运输");
        service10.setImageId(R.mipmap.bannertwo);
        list.add(service10);
        return list;
    }
    public void initEvent(){

        ll_return.setOnClickListener(returnClickListener);
        gv_service.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RelativeLayout llParentView = (RelativeLayout) view;
                TextView serviceName = (TextView) llParentView
                        .findViewById(R.id.ItemText);
              String srname =   serviceName.getText().toString().trim();
                Intent intent = new Intent(ServiceActivity.this,
                        ServiceDetail.class);
                if (srname.equals("专线城运站点服务")) {
                    intent.putExtra("imageId",R.mipmap.bannertwo);
                } else if (srname.equals("电商业务")) {
                } else if (srname.equals("仓储服务")) {
                } else if (srname.equals("三方货运")) {
                } else if (srname.equals("城市配送")) {
                }else if (srname.equals("代收货款")) {
                } else if (srname.equals("手持机开单")) {
                }else if (srname.equals("代签回单")) {
                } else if (srname.equals("网上下单")) {
                } else if (srname.equals("保价运输")) {

                }
                startActivity(intent);
            }
        });
    }

}
