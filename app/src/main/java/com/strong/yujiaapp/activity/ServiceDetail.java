package com.strong.yujiaapp.activity;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;

import com.strong.yujiaapp.R;

public class ServiceDetail extends Activity {
private ImageView ivDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);
        ivDetail  =  findViewById(R.id.image_detail);
     int ivID  =  getIntent().getIntExtra("imageId",0);
        ivDetail.setImageResource(ivID);
    }

}
