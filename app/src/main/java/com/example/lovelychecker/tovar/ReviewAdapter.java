package com.example.lovelychecker.tovar;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lovelychecker.R;
import com.example.lovelychecker.RetrofitClientInstance;
import com.example.lovelychecker.interfaceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE = 1;
    private final Context context;
    private final List<ReviewResponse> listRecyclerItem;

    public ReviewAdapter(Context context, List<ReviewResponse> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private String id;
        private TextView nickname;

        ImageView image;

        private TextView rat;

        private TextView zagolovok;

        private TextView opisanie;

        private ImageView like;
        private TextView likeCount;

        private ImageView dislike;
        private TextView dislikeCount;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.avatar);
            nickname = itemView.findViewById(R.id.nick);
            rat = itemView.findViewById(R.id.rati);
            zagolovok = itemView.findViewById(R.id.zag);
            opisanie = itemView.findViewById(R.id.op);
            like = itemView.findViewById(R.id.like);
            likeCount = itemView.findViewById(R.id.likeCount);
            dislike = itemView.findViewById(R.id.dislike);
            dislikeCount = itemView.findViewById(R.id.dislikeCount);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_review, viewGroup, false);

                return new ItemViewHolder((layoutView));
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:

                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                ReviewResponse review = listRecyclerItem.get(i);
                //Сюды писать отзывы
                itemViewHolder.rat.setText(review.getRating() + "/5"); //Вот сюда пихать рейтин, на который оценил пользователь
                itemViewHolder.opisanie.setText(review.getText()); //Вот сюда пихать описание товара, которое написал пользователь
                itemViewHolder.zagolovok.setText(review.getTitle()); //Вот сюда пихать заголовок( текст который был выделен)
                itemViewHolder.nickname.setText(review.getUsername()); //Вот сюда пихать никнейм пользователя
                itemViewHolder.image.setImageResource(R.drawable.baseline_person_24); //Сюда пихать аватарку пользователя
                if (listRecyclerItem.get(i).getLikes().contains(RetrofitClientInstance.USER_ID)) {
                    itemViewHolder.like.setColorFilter(ContextCompat.getColor(context, R.color.contact_button));
                }
                if (listRecyclerItem.get(i).getDislikes().contains(RetrofitClientInstance.USER_ID)) {
                    itemViewHolder.dislike.setColorFilter(ContextCompat.getColor(context, R.color.contact_button));
                }
                itemViewHolder.likeCount.setText("(" + listRecyclerItem.get(i).getLikes().size() + ")");
                itemViewHolder.dislikeCount.setText("(" + listRecyclerItem.get(i).getDislikes().size() + ")");

                itemViewHolder.like.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ReviewResponse review = listRecyclerItem.get(i);

                            interfaceAPI interfaceApi = RetrofitClientInstance.getInstance();

                            Call<Void> call = interfaceApi.like(review.getProductId(), review.getId(), "Bearer " + RetrofitClientInstance.ACCESS_TOKEN);

                            call.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if (response.isSuccessful()) {
                                        if (listRecyclerItem.get(i).getDislikes().contains(RetrofitClientInstance.USER_ID)) {
                                            Integer dislikesCount = getDislikesCount() - 1;
                                            itemViewHolder.dislikeCount.setText("(" + dislikesCount + ")");
                                            itemViewHolder.dislike.clearColorFilter();
                                            listRecyclerItem.get(i).getDislikes().remove(RetrofitClientInstance.USER_ID);
                                        }
                                        if (listRecyclerItem.get(i).getLikes().contains(RetrofitClientInstance.USER_ID)) {
                                            itemViewHolder.like.clearColorFilter();
                                            listRecyclerItem.get(i).getLikes().remove(RetrofitClientInstance.USER_ID);
                                            Integer likeCountInt = getLikesCount() - 1;
                                            itemViewHolder.likeCount.setText("(" + likeCountInt + ")");
                                        } else {
                                            Integer likeCountInt = getLikesCount() + 1;
                                            listRecyclerItem.get(i).getLikes().add(RetrofitClientInstance.USER_ID);
                                            itemViewHolder.likeCount.setText("(" + likeCountInt + ")");
                                            itemViewHolder.like.setColorFilter(ContextCompat.getColor(context, R.color.contact_button));
                                        }

                                    }
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    System.err.println("fail");
                                }

                                public int getLikesCount() {
                                    String likeCount = itemViewHolder.likeCount.getText().toString();
                                    likeCount = likeCount.substring(likeCount.indexOf("(") + 1, likeCount.indexOf(")"));
                                    return Integer.parseInt(likeCount);
                                }

                                public int getDislikesCount() {
                                    String dislikesCount = itemViewHolder.dislikeCount.getText().toString();
                                    dislikesCount = dislikesCount.substring(dislikesCount.indexOf("(") + 1, dislikesCount.indexOf(")"));
                                    return Integer.parseInt(dislikesCount);
                                }
                            });
                        }
                    });
                    itemViewHolder.dislike.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ReviewResponse review = listRecyclerItem.get(i);

                            interfaceAPI interfaceApi = RetrofitClientInstance.getInstance();

                            Call<Void> call = interfaceApi.dislike(review.getProductId(), review.getId(), "Bearer " + RetrofitClientInstance.ACCESS_TOKEN);

                            call.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if (response.isSuccessful()) {
                                        if (listRecyclerItem.get(i).getLikes().contains(RetrofitClientInstance.USER_ID)) {
                                            Integer likesCount = getLikesCount() - 1;
                                            itemViewHolder.likeCount.setText("(" + likesCount + ")");
                                            itemViewHolder.like.clearColorFilter();
                                            listRecyclerItem.get(i).getLikes().remove(RetrofitClientInstance.USER_ID);
                                        }
                                        if (listRecyclerItem.get(i).getDislikes().contains(RetrofitClientInstance.USER_ID)) {
                                            itemViewHolder.dislike.clearColorFilter();
                                            listRecyclerItem.get(i).getDislikes().remove(RetrofitClientInstance.USER_ID);
                                            Integer disLikeCountInt = getDislikesCount() - 1;
                                            itemViewHolder.dislikeCount.setText("(" + disLikeCountInt + ")");
                                        } else {
                                            Integer dislikeInt = getDislikesCount() + 1;
                                            listRecyclerItem.get(i).getDislikes().add(RetrofitClientInstance.USER_ID);
                                            itemViewHolder.dislikeCount.setText("(" + dislikeInt + ")");
                                            itemViewHolder.dislike.setColorFilter(ContextCompat.getColor(context, R.color.contact_button));
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    System.err.println("fail");
                                }

                                public int getLikesCount() {
                                    String likeCount = itemViewHolder.likeCount.getText().toString();
                                    likeCount = likeCount.substring(likeCount.indexOf("(") + 1, likeCount.indexOf(")"));
                                    return Integer.parseInt(likeCount);
                                }

                                public int getDislikesCount() {
                                    String dislikesCount = itemViewHolder.dislikeCount.getText().toString();
                                    dislikesCount = dislikesCount.substring(dislikesCount.indexOf("(") + 1, dislikesCount.indexOf(")"));
                                    return Integer.parseInt(dislikesCount);
                                }
                            });
                        }
                    });
                }

        }
    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}
