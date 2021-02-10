package com.example.myinstagramm.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myinstagramm.R;
import com.example.myinstagramm.WebService.Service;
import com.example.myinstagramm.adapter.HomeAdapter;
import com.example.myinstagramm.adapter.ProfileAdapter;
import com.example.myinstagramm.model.PixabayPosts;
import com.example.myinstagramm.model.Post;
import com.qintong.library.InsLoadingView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {
    RecyclerView recyclerViewProfilePost;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InsLoadingView imageViewUser=view.findViewById(R.id.imageViewUser);
        recyclerViewProfilePost=view.findViewById(R.id.recyclerVIewProfile);
        Glide.with(getContext()).load("https://images.unsplash.com/photo-1520410006060-eb5c4710b802?ixid=MXwxMjA3fDB8MHxzZWFyY2h8NTV8fHByb2ZpbGV8ZW58MHx8MHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60")
                .circleCrop()
                .into(imageViewUser);
        imageViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageViewUser.getStatus()==InsLoadingView.Status.LOADING){
                    imageViewUser.setStatus(InsLoadingView.Status.UNCLICKED);
                }else {
                    imageViewUser.setStatus(InsLoadingView.Status.LOADING);
                }
            }
        });
      getPhotos();
    }
    void getPhotos(){
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(gsonConverterFactory).build();
        Service service = retrofit.create(Service.class);
        service.getAllPost().enqueue(new Callback<PixabayPosts>() {
            @Override
            public void onResponse(Call<PixabayPosts> call, Response<PixabayPosts> response) {
                List<Post> postList = response.body().getHits();
                recyclerViewProfilePost.setAdapter(new ProfileAdapter(postList,getActivity()));
                GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
                recyclerViewProfilePost.setLayoutManager(gridLayoutManager);

            }

            @Override
            public void onFailure(Call<PixabayPosts> call, Throwable t) {

            }
        });

    }
}
