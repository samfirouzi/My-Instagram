package com.example.myinstagramm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myinstagramm.R;
import com.example.myinstagramm.model.Post;

import java.util.List;

public class LikesAdapter extends RecyclerView.Adapter<LikesAdapter.LikesViewHolder> {
    List<Post> postList;
    Context context;

    public LikesAdapter(List<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    @NonNull
    @Override
    public LikesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_item_likes,parent,false);
        return new LikesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikesViewHolder holder, int position) {
        Glide.with(context).load(postList.get(position).getUserImageURL()).into(holder.imageViewProfileLike);
        holder.textViewUserr.setText(postList.get(position).getUsername());
        holder.textViewActivity.setText("liked your post x");

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    class LikesViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewProfileLike;
        TextView textViewUserr;
        TextView textViewActivity;


        public LikesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProfileLike=itemView.findViewById(R.id.imageViewProfileLike);
            textViewActivity=itemView.findViewById(R.id.textViewActivity);
            textViewUserr=itemView.findViewById(R.id.textViewUserr);
        }
    }
}
