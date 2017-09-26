package com.example.studying.a100ballovapplication.news;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studying.a100ballovapplication.R;
import com.example.studying.a100ballovapplication.databinding.ItemRvNewsBinding;
import com.example.studying.a100ballovapplication.databinding.ItemRvScheduleBinding;
import com.example.studying.a100ballovapplication.schedule.ItemViewModel;

import java.util.List;


public class NewsRVAdapter extends RecyclerView.Adapter<NewsRVAdapter.Holder>{

    private Context context;
    private List<NewsItemViewModel> items;

    public NewsRVAdapter(Context context, List<NewsItemViewModel> items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public NewsRVAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("SSS", "NewsRVAdapter - onCreateViewHolder");
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_news, parent, false);
        return new Holder(root);
    }

    @Override
    public void onBindViewHolder(NewsRVAdapter.Holder holder, int position) {
        Log.e("SSS", "NewsRVAdapter - onBindViewHolder");
        holder.binding.setItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class Holder extends RecyclerView.ViewHolder{

        ItemRvNewsBinding binding;

        public Holder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

}
