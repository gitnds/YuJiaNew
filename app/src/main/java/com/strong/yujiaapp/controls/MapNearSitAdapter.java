package com.strong.yujiaapp.controls;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.strong.yujiaapp.R;
import com.strong.yujiaapp.utils.OnItemClieckLinster;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/29.
 */

public class MapNearSitAdapter extends RecyclerView.Adapter<MapViewHolder>{
    @Override
    public void onBindViewHolder(final  MapViewHolder holder, final  int position) {
        //为textview 赋值
        holder.tv_near_group_name.setText(mDatas.get(position).get("name"));
        holder.tv_distance.setText(mDatas.get(position).get("dist"));
        if(onItemClieckLinster != null){

            //onitemclicklistener
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClieckLinster.onItemClickListener(holder.itemView , position,"城市");
                }
            });

            //onitemlongclicklistener
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    onItemClieckLinster.onItemLongClickListener(holder.itemView , position);
                    return false;
                }
            });
        }
    }

    private LayoutInflater inflater;
    private Context mContext;
    private List<Map<String,String>> mDatas;

    //创建构造参数
    public MapNearSitAdapter(Context context , List<Map<String,String>> datas){
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(context);
    }


    private OnItemClieckLinster onItemClieckLinster;
    public void setOnItemClieckLinster(OnItemClieckLinster listener){

        this.onItemClieckLinster = listener;
    }



    //创建ViewHolder
    @Override
    public MapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.map_near_sit_item , parent , false);
        MapViewHolder viewHolder = new MapViewHolder(view);
        return viewHolder;
    }



    @Override
    public int getItemCount() {
        //Log.i("TAG", "mDatas "+mDatas);

        return mDatas.size();

    }

    //新增item
    public void addData(int pos){
        notifyItemInserted(pos);
    }

    //移除item
    public void deleateData(int pos){
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }
}
class MapViewHolder extends RecyclerView.ViewHolder{

    TextView tv_near_detail_address;
    TextView tv_near_group_name;
    TextView tv_distance;

    public MapViewHolder(View view) {
        super(view);

        tv_near_detail_address = (TextView) view.findViewById(R.id.tv_near_detail_address);
        tv_near_group_name = (TextView) view.findViewById(R.id.tv_near_group_name);
        tv_distance = (TextView) view.findViewById(R.id.tv_distance);
       /* tv.setTextSize(20);*/


    }




}