package com.edu.gvn.vietlottery.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.edu.gvn.vietlottery.R;
import com.edu.gvn.vietlottery.entity.MegaListPrevious;

import java.util.ArrayList;

/**
 * Created by hnc on 30/11/2016.
 */

public class Mega645ListPreviousAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 0;
    private final int VIEW_LOAD = 1;

    private ArrayList<MegaListPrevious> datas;

    public Mega645ListPreviousAdapter(ArrayList<MegaListPrevious> datas) {
        this.datas = datas;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_mega_previous, parent, false);
            return new ListPreviousViewHolder(view);
        } else if (viewType == VIEW_LOAD) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loadmore, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListPreviousViewHolder) {
            ((ListPreviousViewHolder) holder).date.setText(datas.get(position).date);
            ((ListPreviousViewHolder) holder).result.setText(datas.get(position).result);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position) == null ? VIEW_LOAD : VIEW_ITEM;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    private class ListPreviousViewHolder extends RecyclerView.ViewHolder {
        TextView date, result;

        private ListPreviousViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.item_date);
            result = (TextView) itemView.findViewById(R.id.item_result);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        private LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.loading);
        }
    }

}
