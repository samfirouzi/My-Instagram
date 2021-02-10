package com.example.myinstagramm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myinstagramm.R;
import com.example.myinstagramm.model.Post;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfilePostViewHolder> {
    List<Post> postList;
    Context context;
    public ProfileAdapter(List<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }



    @NonNull
    @Override
    public ProfilePostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_list_profile,parent,false);
        return new ProfilePostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilePostViewHolder holder, int position) {
        Post post=postList.get(position);
        Glide.with(context).load(post.getWebformatURL())
                .placeholder(R.color.colorGray)
                .into(holder.imageViewProfilePost);

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class ProfilePostViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewProfilePost;

        public ProfilePostViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProfilePost=itemView.findViewById(R.id.imageViewProfilePost);
        }
    }
}
