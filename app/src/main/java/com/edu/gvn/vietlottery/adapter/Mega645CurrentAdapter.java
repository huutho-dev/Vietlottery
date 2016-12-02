package com.edu.gvn.vietlottery.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edu.gvn.vietlottery.R;
import com.edu.gvn.vietlottery.entity.sub.Mega645Prize;

import java.util.ArrayList;

/**
 * Created by hnc on 29/11/2016.
 */

public class Mega645CurrentAdapter extends RecyclerView.Adapter<Mega645CurrentAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Mega645Prize> datas;

    public Mega645CurrentAdapter(Context context, ArrayList<Mega645Prize> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mega645,parent,false)) ;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == 0) {
            holder.txtGiaTriGiai.setTypeface(null, Typeface.BOLD);
            holder.txtSoLuongGiai.setTypeface(null, Typeface.BOLD);
            holder.txtTrungKhop.setTypeface(null, Typeface.BOLD);
            holder.txtTenGiai.setTypeface(null, Typeface.BOLD);
        }
        if (position!=0){
            holder.txtTenGiai.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }
        holder.txtTenGiai.setText(datas.get(position).tenGiaiThuong);
        holder.txtSoLuongGiai.setText(datas.get(position).soLuongGiai);
        holder.txtTrungKhop.setText(datas.get(position).trungKhop);
        holder.txtGiaTriGiai.setText(datas.get(position).giaTriGiai);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenGiai, txtTrungKhop, txtSoLuongGiai, txtGiaTriGiai;

        public ViewHolder(View itemView) {
            super(itemView);
            txtGiaTriGiai = (TextView) itemView.findViewById(R.id.txt_gia_tri_giai);
            txtSoLuongGiai = (TextView) itemView.findViewById(R.id.txt_so_luong_giai);
            txtTrungKhop = (TextView) itemView.findViewById(R.id.txt_trung_khop);
            txtTenGiai = (TextView) itemView.findViewById(R.id.txt_ten_giai);
        }
    }
}
