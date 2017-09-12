package com.strong.yujiaapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.strong.yujiaapp.R;
import com.strong.yujiaapp.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactActivity extends BaseActivity {
    private ListView listView;
    private ArrayList<HashMap<String, String>> contactList;
    private TextView tv_title;
    private LinearLayout ll_return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        listView = (ListView) findViewById(R.id.contact_list);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("选择联系人");
        if (ContextCompat.checkSelfPermission(ContactActivity.this,android.Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) ContactActivity.this,
                    new String[]{android.Manifest.permission.READ_CONTACTS},
                    1);
        }
        contactList = findContacts();
        ll_return = (LinearLayout) findViewById(R.id.ll_return);
        ll_return.setOnClickListener(returnClickListener);
        listView.setAdapter(new ContactAdapter());
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String phoneNumber = contactList.get(position).get("phone");
                String name =	contactList.get(position).get("name");
                Intent intent = new Intent();
                intent.putExtra("phone", phoneNumber);
                intent.putExtra("name", name);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }

        });
    }

    class ContactAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return contactList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return contactList.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(ContactActivity.this,
                        R.layout.contact_list_item, null);
                holder.tvName = (TextView) convertView
                        .findViewById(R.id.tv_name);
                holder.tvPhone = (TextView) convertView
                        .findViewById(R.id.tv_phone);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String nameString = contactList.get(position).get("name");
            String phone = contactList.get(position).get("phone");
            holder.tvName.setText(nameString);
            holder.tvPhone.setText(phone);
            return convertView;
        }

    }

    class ViewHolder {
        public TextView tvName;
        public TextView tvPhone;
    }

    private ArrayList<HashMap<String, String>> findContacts() {
        Uri rawcontactUri = Uri
                .parse("content://com.android.contacts/raw_contacts");
        Uri dataUri = Uri.parse("content://com.android.contacts/data");

        ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
        Cursor rawContactsCursor = getContentResolver().query(rawcontactUri,
                new String[] { "contact_id" }, null, null, null);
        if (rawContactsCursor != null) {
            while (rawContactsCursor.moveToNext()) {
                String contactId = rawContactsCursor.getString(0);
                System.out.println("联系人ID" + contactId);
                Cursor dataCursor = getContentResolver().query(dataUri,
                        new String[] { "data1", "mimetype" }, "contact_id=?",
                        new String[] { contactId }, null);
                if (dataCursor != null) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    while (dataCursor.moveToNext()) {
                        String dataString = dataCursor.getString(0);
                        String mimetype = dataCursor.getString(1);
                        System.out.println(contactId + ";" + dataString + ";"
                                + mimetype);
                        if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
                            map.put("phone", dataString);
                        } else if ("vnd.android.cursor.item/name"
                                .equals(mimetype)) {
                            map.put("name", dataString);
                        }

                    }
                    contactList.add(map);
                    dataCursor.close();
                }

            }
            rawContactsCursor.close();
        }
        return contactList;
    }
}
