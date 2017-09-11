package com.strong.yujiaapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.strong.yujiaapp.activity.RegistAgree;
import com.strong.yujiaapp.activity.SiteChoiceActivity;
import com.strong.yujiaapp.base.BaseActivity;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class Register extends BaseActivity {
    private LinearLayout ll_return;
    private Button btn_register_sms, bt_get_sms;
    private TextView tv_title,tv_register_agree;
    private EditText et_phone_number, et_send_sms;
    EventHandler eventHandler;
    String myPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initEvent();
        ll_return.setOnClickListener(returnClickListener);
    }

    public void initView() {

        ll_return = (LinearLayout) findViewById(R.id.ll_return);
        btn_register_sms = (Button) findViewById(R.id.btn_register_sms);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_register_agree = (TextView) findViewById(R.id.tv_register_agree);
        bt_get_sms = (Button) findViewById(R.id.bt_get_sms);
        et_phone_number = (EditText) findViewById(R.id.et_phone_number);
        et_send_sms = (EditText) findViewById(R.id.et_send_sms);
        tv_title.setText("注册");
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable) data;
                    final String msg = throwable.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        //    Toast.makeText(Register.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        // 处理你自己的逻辑
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Register.this, "发送成功~", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                            //验证成功调用保存账号密码接口
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Register.this, "验证成功~", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
            }
        };
        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    public void initEvent() {

        ll_return.setOnClickListener(returnClickListener);
        btn_register_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sendSms = et_send_sms.getText().toString().trim();
                SMSSDK.submitVerificationCode("86", myPhone, sendSms);
                Intent intent = new Intent();
                intent.setClass(Register.this, SiteChoiceActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        bt_get_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPhone = et_phone_number.getText().toString().trim();
                SMSSDK.getVerificationCode("86", myPhone);
                bt_get_sms.setEnabled(false);
                timer.start();
            }
        });
        tv_register_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Register.this, RegistAgree.class);
                startActivityForResult(intent, 1);
            }
        });
    }
    private CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            bt_get_sms.setText((millisUntilFinished / 1000) + "秒后可重发");
        }

        @Override
        public void onFinish() {
            bt_get_sms.setEnabled(true);
            bt_get_sms.setText("获取验证码");
        }
    };

}
