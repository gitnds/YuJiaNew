package com.strong.yujiaapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.strong.yujiaapp.R;
import com.strong.yujiaapp.activity.CargoActivity;
import com.strong.yujiaapp.activity.CompensateActivity;
import com.strong.yujiaapp.activity.OrderActivity;
import com.strong.yujiaapp.activity.PriceActivity;
import com.strong.yujiaapp.activity.ReceiveActivity;
import com.strong.yujiaapp.activity.SendActivity;
import com.strong.yujiaapp.activity.ServiceActivity;
import com.strong.yujiaapp.activity.SiteActivity;
import com.strong.yujiaapp.controls.DialogAg;
import com.strong.yujiaapp.controls.MyDialog;

import zxing.activity.CaptureActivity;

import static android.app.Activity.RESULT_OK;


/**
 * Created by Administrator on 2017/8/23.
 */

public class HomeFragment extends Fragment {

    public Activity mActivity;
    private RelativeLayout ll_1;
    private LinearLayout ll_2, ll_3, ll_4, ll_5, ll_6, ll_7, ll_8, ll_9;
    private ImageButton btn_call, btn_scan_barcode;
    private EditText et_first_code;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity = getActivity();//获取当前fragment依赖的activity
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ll_1 = view.findViewById(R.id.ll_1);
        ll_2 = view.findViewById(R.id.ll_2);
        ll_3 = view.findViewById(R.id.ll_3);
        ll_4 = view.findViewById(R.id.ll_4);
        ll_5 = view.findViewById(R.id.ll_5);
        ll_6 = view.findViewById(R.id.ll_6);
        ll_7 = view.findViewById(R.id.ll_7);
        ll_8 = view.findViewById(R.id.ll_8);
        ll_9 = view.findViewById(R.id.ll_9);

        et_first_code = view.findViewById(R.id.et_first_code);

        btn_scan_barcode = view.findViewById(R.id.btn_scan_barcode);
        btn_call = view.findViewById(R.id.btn_call);

        initEvent();

        return view;
    }

    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_1:
                    Intent intent1 = new Intent(mActivity, OrderActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.ll_2:
                    Intent intent2 = new Intent(mActivity, CargoActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.ll_3:
                    Intent intent3 = new Intent(mActivity, SiteActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.ll_4:
                    Intent intent4 = new Intent(mActivity, PriceActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.ll_5:
                    Intent intent5 = new Intent(mActivity, SendActivity.class);
                    startActivity(intent5);
                    break;
                case R.id.ll_6:
                    Intent intent6 = new Intent(mActivity, ReceiveActivity.class);
                    startActivity(intent6);
                    break;
                case R.id.ll_7:
                    Intent intent7 = new Intent(mActivity, ServiceActivity.class);
                    startActivity(intent7);
                    break;
                case R.id.ll_8:
                    DialogAg.getPhoneDialog(mActivity, "400-1577-666");
                    break;
                case R.id.ll_9:
                    Intent intent9 = new Intent(mActivity, CompensateActivity.class);
                    startActivity(intent9);
                    break;
                case R.id.btn_scan_barcode:
                    // 打开扫描界面扫描条形码或二维码
                    Intent openCameraIntent = new Intent(mActivity,
                            CaptureActivity.class);
                    startActivityForResult(openCameraIntent, 0);
                    break;
                case R.id.btn_call:
                    DialogAg.getPromptDialog(mActivity, "哇偶哇偶哇偶");
                    break;
                default:
                    break;
            }
        }
    }

    public void initEvent() {

        ll_1.setOnClickListener(new MyClickListener());
        ll_2.setOnClickListener(new MyClickListener());
        ll_3.setOnClickListener(new MyClickListener());
        ll_4.setOnClickListener(new MyClickListener());
        ll_5.setOnClickListener(new MyClickListener());
        ll_6.setOnClickListener(new MyClickListener());
        ll_7.setOnClickListener(new MyClickListener());
        ll_8.setOnClickListener(new MyClickListener());
        ll_9.setOnClickListener(new MyClickListener());
        btn_scan_barcode.setOnClickListener(new MyClickListener());
        btn_call.setOnClickListener(new MyClickListener());

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 处理扫描结果（在界面上显示）
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            et_first_code.setText(scanResult);


        }
    }

    private void showLoginDialog() {

        View view = getView().inflate(mActivity,R.layout.dialog_ask, null);
        final TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvTitle.setText("删除地址");
        final TextView btnYes = (TextView) view.findViewById(R.id.f_quchecbutton_btn_queding);
        final TextView btlNo = (TextView) view.findViewById(R.id.f_quchecbutton_btn_quxiao);
        final MyDialog builder = new MyDialog(mActivity,0,0,view,R.style.DialogTheme);

        builder.setCancelable(false);
        builder.show();
        //设置对话框显示的View
        //点击确定是的监听
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(mActivity, "确定按钮。。。", Toast.LENGTH_SHORT).show();
                builder.cancel();
            }
        });


        btlNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "取消按钮。。。", Toast.LENGTH_SHORT).show();
                builder.cancel();
            }
        });

    }

}
