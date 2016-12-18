package com.game24h.vietlottery.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.game24h.vietlottery.entity.MegaListPrevious;
import com.game24h.vietlottery.entity.RecyclerItemOnClickListener;
import com.edu.gvn.vietlottery.R;

import java.util.ArrayList;

public class Mega645ListPreviousAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 0;
    private final int VIEW_LOAD = 1;

    private ArrayList<MegaListPrevious> datas;
    private RecyclerItemOnClickListener itemOnClickListener ;
    public Mega645ListPreviousAdapter(ArrayList<MegaListPrevious> datas,RecyclerItemOnClickListener itemOnClickListener) {
        this.datas = datas;
        this.itemOnClickListener = itemOnClickListener;
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ListPreviousViewHolder) {
            ((ListPreviousViewHolder) holder).date.setText(datas.get(position).date);
            ((ListPreviousViewHolder) holder).result.setText(datas.get(position).result);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemOnClickListener.onItemClick(view,position);
                }
            });

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
