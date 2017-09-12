package com.strong.yujiaapp.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.strong.yujiaapp.R;
import com.strong.yujiaapp.base.BaseActivity;

public class AddInfoActivity extends BaseActivity {

    private LinearLayout ll_return, ll_loc,ll_hold;
    private TextView tv_title;
    private Button bt_contact;
    private EditText et_contact_man,et_contact_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinfo);
        getSupportActionBar().hide();
//hfkdjs
        initView();
        initData();
        initEvent();

    }

    public void initView() {
        ll_return = (LinearLayout) findViewById(R.id.ll_return);
        ll_loc = (LinearLayout) findViewById(R.id.ll_loc);
        ll_hold = (LinearLayout) findViewById(R.id.ll_hold);
        et_contact_man = (EditText) findViewById(R.id.et_contact_man);
        et_contact_phone = (EditText) findViewById(R.id.et_contact_phone);
        tv_title = (TextView) findViewById(R.id.tv_title);
        bt_contact = (Button) findViewById(R.id.bt_contact);
    }

    public void initData() {

        tv_title.setText("新增地址");
    }

    public void initEvent() {

        ll_return.setOnClickListener(returnClickListener);
        //选择省市区
        ll_loc.setOnClickListener(locClickListener);
        //保存
        ll_hold.setOnClickListener(holdClickListener);
        bt_contact.setOnClickListener(contactListener);
    }

    View.OnClickListener locClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    View.OnClickListener contactListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Uri uri = Uri.parse("content://contacts/people");
            Intent intent = new Intent(Intent.ACTION_PICK, uri);
            startActivityForResult(intent, 0);
        }
    };

    View.OnClickListener holdClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    /*
 * 跳转联系人列表的回调函数
 * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 0:
                if(data==null)
                {
                    return;
                }
                //处理返回的data,获取选择的联系人信息
                Uri uri=data.getData();
                String[] contacts=getPhoneContacts(uri);
                et_contact_man.setText(contacts[0]);
                et_contact_phone.setText(contacts[1]);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private String[] getPhoneContacts(Uri uri){
        String[] contact=new String[2];
        //得到ContentResolver对象
        ContentResolver cr = getContentResolver();
        //取得电话本中开始一项的光标
        Cursor cursor=cr.query(uri,null,null,null,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            //取得联系人姓名
            int nameFieldColumnIndex=cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            contact[0]=cursor.getString(nameFieldColumnIndex);
            //取得电话号码
            String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
            if(phone.getCount()==0){
                Toast.makeText(AddInfoActivity.this, "电话或人名不存在", Toast.LENGTH_SHORT).show();
            }else{
                phone.moveToFirst();
                contact[1] = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            phone.close();
            cursor.close();
        }
        else
        {
            return null;
        }
        return contact;
    }
}
