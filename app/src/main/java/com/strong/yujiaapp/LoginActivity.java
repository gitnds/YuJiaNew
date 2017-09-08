package com.strong.yujiaapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.strong.yujiaapp.data.ConfigInfo;
import com.strong.yujiaapp.utils.WebServiceConnection;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/8/22.
 */

public class LoginActivity extends AppCompatActivity {

    private Button btn_login,btn_register;
    private EditText et_Account, et_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        initView();
        initEvent();
    }

    public void initView() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
        //设置透明度
        /*View v = findViewById(R.id.btn_login);
        v.getBackground().setAlpha(100);*/
        et_Account = (EditText) findViewById(R.id.et_Account);
        et_Password = (EditText) findViewById(R.id.et_Password);
    }

    public void initEvent() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
              /*  Thread threadRun = new Thread(login);
                threadRun.start();*/

            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, Register.class);
                startActivity(intent);
              /*  Thread threadRun = new Thread(login);
                threadRun.start();*/

            }
        });
    }

    Runnable login = new Runnable() {
        public void run() {
            try {
                String reqStr = et_Account.getText().toString() + "@"
                        + et_Password.getText().toString() + "@"
                        + "864505020989904";
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("user", reqStr);
                String result = WebServiceConnection.GetString(
                        ConfigInfo.LOGIN_USERLOGIN, params);

                Message msg = new Message();
                Bundle data = new Bundle();
                data.putString("result", result);
                msg.setData(data);
                msg.what = 1;
                handler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 登录
     */
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {// 登录

                String result = msg.getData().getString("result");
                Toast.makeText(LoginActivity.this, result, Toast.LENGTH_LONG).show();


            }
        }
    };

}
