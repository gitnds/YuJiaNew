package com.strong.yujiaapp.controls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.strong.yujiaapp.R;
import com.strong.yujiaapp.beanmodel.ServiceData;

import java.util.List;

/**
 * Created by Administrator on 2017-09-11.
 */

public class GrideViewServiceAdapter extends BaseAdapter{
    private List<ServiceData> listItems; // 信息集合
    private LayoutInflater listContainer; // 视图容器
    private Context context;
    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public final class ListItemView {
        public TextView txt_main_name;
        public ImageView txt_main_view;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListItemView listItemView = null;
        if (view == null) {
            listItemView = new ListItemView();
            // 获取list_item布局文件的视图
            view = listContainer.inflate(R.layout.grid_view_item, null);
            // 获取控件对象
            listItemView.txt_main_name = (TextView) view
                    .findViewById(R.id.ItemText);
            listItemView.txt_main_view = (ImageView) view
                    .findViewById(R.id.ItemImage);
            // 设置控件集到convertView
            view.setTag(listItemView);
        } else {
            listItemView = (ListItemView) view.getTag();
        }
        listItemView.txt_main_name.setText(listItems.get(i).getMessage());
        listItemView.txt_main_view.setImageResource(listItems.get(i).getImageId());
        return view;
    }

    public GrideViewServiceAdapter(Context context, List<ServiceData> listItems) {
        listContainer = LayoutInflater.from(context);
        this.listItems = listItems;
        this.context = context;
    }
}
