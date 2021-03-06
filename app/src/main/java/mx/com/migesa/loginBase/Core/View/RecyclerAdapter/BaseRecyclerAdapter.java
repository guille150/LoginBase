/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mx.com.migesa.loginBase.Core.View.RecyclerAdapter;

/**
 * Created by Guillermo Ortiz on 19/01/17.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import mx.com.migesa.loginBase.Core.View.Ui;


public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter.RecyclerViewHolder> {
    public interface ItemSelectedListener<T> {
        void onItemSelected(T t, int position);
    }

    private final List<T> items = new ArrayList<>();
    private ItemSelectedListener<T> itemSelectedListener;

    public void setItems(List<T> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public T getItemAt(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItemSelectedListener(ItemSelectedListener<T> itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }

    protected void onItemSelected(int position) {
        if (itemSelectedListener != null) {
            itemSelectedListener.onItemSelected(getItemAt(position), position);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, int position) {
        recyclerViewHolder.setItem(getItemAt(position), position);
    }

    public void addItem(T item) {
        items.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

    public List<T> getItems() {
        return new ArrayList<>(items);
    }

    public abstract class RecyclerViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Ui ui;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ui = new Ui(itemView.getContext(), itemView);
            itemView.setOnClickListener(this);
        }

        public Ui getUi() {
            return ui;
        }

        public abstract void setItem(T t, int position);

        @Override
        public void onClick(View v) {
            onItemSelected(getAdapterPosition());
        }

        protected T getItem() {
            return (T) getItemAt(getAdapterPosition());
        }
    }
}