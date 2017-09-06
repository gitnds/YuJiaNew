package com.strong.yujiaapp.controls;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.strong.yujiaapp.R;
import com.strong.yujiaapp.beanmodel.CityBean;
import com.strong.yujiaapp.utils.OnItemClieckLinster;

import java.util.List;

/**
 * Created by Administrator on 2017/8/29.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private LayoutInflater inflater;
    private Context mContext;
    private List<CityBean.Data> mDatas;

    //创建构造参数
    public MyRecyclerAdapter(Context context , List<CityBean.Data> datas){
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
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_site_choice , parent , false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    //绑定ViewHolder
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //为textview 赋值
        holder.tv.setText(mDatas.get(position).getName());

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
class MyViewHolder extends RecyclerView.ViewHolder{

    TextView tv;


    public MyViewHolder(View itemView) {
        super(itemView);

        tv = (TextView) itemView.findViewById(R.id.recycle_tv);

       /* tv.setTextSize(20);*/


    }




}