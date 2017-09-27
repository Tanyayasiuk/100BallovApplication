package com.yasiuk.studying.a100ballovapplication.schedule;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasiuk.studying.a100ballovapplication.R;
import com.yasiuk.studying.a100ballovapplication.databinding.ItemRvScheduleBinding;

import java.util.List;


public class ScheduleRVAdapter extends RecyclerView.Adapter<ScheduleRVAdapter.Holder>{

    private Context context;
    private List<ItemViewModel> items;

    public ScheduleRVAdapter(Context context, List<ItemViewModel> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ScheduleRVAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_schedule, parent, false);
        return new Holder(root);
    }

    @Override
    public void onBindViewHolder(ScheduleRVAdapter.Holder holder, int position) {
        holder.binding.setItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class Holder extends RecyclerView.ViewHolder{

        ItemRvScheduleBinding binding;

        public Holder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
