package com.game24h.vietlottery.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edu.gvn.vietlottery.R;
import com.game24h.vietlottery.entity.sub.Max4dPrize;

import java.util.ArrayList;


public class Max4DListPreviousAdapter extends RecyclerView.Adapter<Max4DListPreviousAdapter.ViewHolder> {

    private ArrayList<Max4dPrize> datas;

    public Max4DListPreviousAdapter(ArrayList<Max4dPrize> datas) {
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_previous_max, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.kyQuayThuong.setText(datas.get(position).kyQuayThuong);
        holder.ngayQuayThuong.setText(datas.get(position).ngayQuayThuong);
        holder.giaiNhat.setText(datas.get(position).giaiNhat);
        holder.giaiNhi.setText(datas.get(position).giaiNhi1 +"\t\t\t" +datas.get(position).giaiNhi2);
        holder.giaiBa.setText(datas.get(position).giaiBa1 +"\t\t\t" + datas.get(position).giaiBa2 +"\t\t\t" + datas.get(position).giaiBa3);
        holder.giaiKK1.setText(datas.get(position).giaiKK1);
        holder.giaiKK2.setText(datas.get(position).giaiKK2);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ngayQuayThuong,kyQuayThuong;
        TextView giaiNhat, giaiNhi,giaiBa,giaiKK1,giaiKK2;
        public ViewHolder(View itemView) {
            super(itemView);
            kyQuayThuong = (TextView) itemView.findViewById(R.id.txt_time_number);
            ngayQuayThuong = (TextView) itemView.findViewById(R.id.txt_time_date);

            giaiNhat = (TextView) itemView.findViewById(R.id.txt_giai_nhat);
            giaiNhi = (TextView) itemView.findViewById(R.id.txt_giai_nhi);
            giaiBa = (TextView) itemView.findViewById(R.id.txt_giai_ba);
            giaiKK1 = (TextView) itemView.findViewById(R.id.txt_giai_kk1);
            giaiKK2 = (TextView) itemView.findViewById(R.id.txt_giai_kk2);
        }
    }
}
