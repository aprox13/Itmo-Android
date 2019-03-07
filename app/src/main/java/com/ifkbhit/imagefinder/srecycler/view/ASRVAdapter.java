package com.ifkbhit.imagefinder.srecycler.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class ASRVAdapter<VHType extends ASRVViewHolder, DataType> extends RecyclerView.Adapter<VHType> {


    private List<DataType> items = new ArrayList<>();

    public ASRVAdapter(List<DataType> data) {
        this.items.clear();
        this.items.addAll(data);
        Log.d(getClass().getSimpleName(), "Create ASRVAdapter with size of data " + data.size());
    }

    public DataType getItem(int position) {
        return items.get(position);
    }


    @NonNull
    @Override
    abstract public VHType onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType);

    @Override
    public void onBindViewHolder(@NonNull VHType vh, int position) {
        vh.setData(getItem(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public DataType remove(int position) {
        DataType removed = items.remove(position);
        notifyItemRemoved(position);
        return removed;
    }

    public void insert(int position, DataType data) {
        items.add(position, data);
        notifyItemInserted(position);
    }

    public void add(DataType data) {
        items.add(data);
        notifyItemInserted(items.size() - 1);
    }


}
