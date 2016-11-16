package com.tieorange.orderlistlibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tieorange on 16/10/2016.
 */

public class AdapterOrderItem extends RecyclerView.Adapter<AdapterOrderItem.ViewHolder> {

    private final Context mContext;
    private List<Product> mProducts = new ArrayList<>();

    public AdapterOrderItem(Context context, List<Product> productList) {
        mContext = context;
        this.mProducts = new ArrayList<>(productList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = mProducts.get(position);
        holder.name.setText(product.name);
        holder.price.setText(product.getStringPrice());
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
        }
    }
}
