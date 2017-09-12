package com.strong.yujiaapp.activity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.strong.yujiaapp.R;
import com.strong.yujiaapp.base.BaseActivity;
import com.strong.yujiaapp.controls.MyListView;
import com.strong.yujiaapp.utils.RecordSQLiteOpenHelper;

/**
 * Created by Administrator on 2017/8/22.
 */

public class CargoActivity extends BaseActivity {
    private LinearLayout ll_return,ll_clear;
    private TextView tv_title;
    private EditText et_search;
    private TextView tv_tip;
    private MyListView listView;
    private RecordSQLiteOpenHelper helper = new RecordSQLiteOpenHelper(this);
    private SQLiteDatabase db;
    private BaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo);
        getSupportActionBar().hide();
        initView();
        initData();
        initEvent();
    }

    public void initView(){

        ll_return = (LinearLayout) findViewById(R.id.ll_return);
        tv_title = (TextView) findViewById(R.id.tv_title);
        et_search = (EditText) findViewById(R.id.et_search);
        tv_tip = (TextView) findViewById(R.id.tv_tip);
        listView = (com.strong.yujiaapp.controls.MyListView) findViewById(R.id.listView);
        ll_clear = (LinearLayout) findViewById(R.id.ll_clear);

    }
    public void initData(){

        tv_title.setText("货物跟踪");
        // 第一次进入查询所有的历史记录
        queryData("");
    }
    public void initEvent(){

        ll_return.setOnClickListener(returnClickListener);

        // 清空搜索历史
        ll_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
                queryData("");
            }
        });

        // 搜索框的键盘搜索键点击回调
        et_search.setOnKeyListener(new View.OnKeyListener() {
            // 输入完后按键盘上的搜索键
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {// 修改回车键功能
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                    boolean hasData = hasData(et_search.getText().toString().trim());
                    if (!hasData) {
                        insertData(et_search.getText().toString().trim());
                        queryData("");
                    }

                    Toast.makeText(CargoActivity.this, "搜索搜索!", Toast.LENGTH_SHORT).show();

                }
                return false;
            }
        });

        // 搜索框的文本变化实时监听
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               /* if (s.toString().trim().length() == 0) {
                    tv_tip.setText("搜索历史");
                } else {
                    tv_tip.setText("搜索结果");
                }*/
                String tempName = et_search.getText().toString();
                // 根据tempName去模糊查询数据库中有没有数据
                queryData(tempName);

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.text1);
                String coderecord = textView.getText().toString();
                et_search.setText(coderecord);
                Toast.makeText(CargoActivity.this, coderecord, Toast.LENGTH_SHORT).show();

            }
        });
    }
    /**
     * 插入数据
     */
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(coderecord) values('" + tempName + "')");
        db.close();
    }

    /**
     * 模糊查询数据
     */
    private void queryData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,coderecord from records where coderecord like '%" + tempName + "%' order by id desc ", null);
        // 创建adapter适配器对象
        adapter = new SimpleCursorAdapter(this, R.layout.cargo_list_item, cursor, new String[] { "coderecord" },
                new int[] { R.id.text1 }, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 设置适配器
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    /**
     * 检查数据库中是否已经有该条记录
     */
    private boolean hasData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,coderecord from records where coderecord =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();
    }

    /**
     * 清空数据
     */
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }
}