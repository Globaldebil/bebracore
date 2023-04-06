package com.example.lovelychecker.tovar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lovelychecker.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context context;
    List<Product> products;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ProductItem = LayoutInflater.from(context).inflate(R.layout.module_tovar, parent, false);
        return new ProductViewHolder(ProductItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.prodName.setText(products.get(position).getName());
        holder.prodPrice.setText(String.format(String.valueOf(products.get(position).getPrice()+"₽")));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static final class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView prodName;
        TextView prodPrice;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            prodName = itemView.findViewById(R.id.prodName);
            prodPrice = itemView.findViewById(R.id.prodPrice);
        }
    }
}

