package com.yasiuk.studying.a100ballovapplication.books;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yasiuk.studying.a100ballovapplication.R;
import com.yasiuk.studying.a100ballovapplication.databinding.ItemRvBookBinding;

import java.util.List;

import io.reactivex.functions.Consumer;


public class BooksRVAdapter extends RecyclerView.Adapter<BooksRVAdapter.Holder>{

    private AppCompatActivity activity;
    private List<BooksItemViewModel> items;

    public BooksRVAdapter(AppCompatActivity activity, List<BooksItemViewModel> items) {
        this.activity = activity;
        this.items = items;
    }


    @Override
    public BooksRVAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_book, parent, false);
        return new Holder(root);
    }

    @Override
    public void onBindViewHolder(BooksRVAdapter.Holder holder, final int position) {
        holder.binding.setItem(items.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open new fragment with webView displaying the single paragraph
                Toast.makeText(activity, "Opening pdf...", Toast.LENGTH_SHORT).show();
                RxPermissions rxPermissions = new RxPermissions(activity);
                rxPermissions
                        .request(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean){
                                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(items.get(position).url)));
                                } else {
                                    Toast.makeText(activity, "Доступ запрещен", Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }
                        });

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class Holder extends RecyclerView.ViewHolder{

        ItemRvBookBinding binding;

        public Holder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

}
