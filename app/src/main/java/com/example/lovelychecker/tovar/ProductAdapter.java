package com.example.lovelychecker.tovar;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lovelychecker.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Product> listRecyclerItem;

    public ProductAdapter(Context context, List<Product> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        private TextView price;

        private TextView score;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            score = itemView.findViewById(R.id.score);
            name = itemView.findViewById(R.id.prodName);
            price = itemView.findViewById(R.id.prodPrice);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);

                return new ItemViewHolder((layoutView));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:

                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                Product product = listRecyclerItem.get(i);
                itemViewHolder.name.setText(product.getTitle());//Имя сюда
                itemViewHolder.score.setText(String.valueOf(product.getAverageRating()));//Сюда среднюю оценку
                itemViewHolder.price.setText(String.format(String.valueOf(product.getFromPrice())+"₽ - "+product.getToPrice()+"₽"));//Сюда цену
        }

    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}