package com.example.myinstagramm.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myinstagramm.R;
import com.example.myinstagramm.WebService.Service;
import com.example.myinstagramm.adapter.LikesAdapter;
import com.example.myinstagramm.model.PixabayPosts;
import com.example.myinstagramm.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LikesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_likes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerViewLike=view.findViewById(R.id.recyclerViewLike);
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(gsonConverterFactory)
                .build();
        Service service = retrofit.create(Service.class);
        service.getPostByKeyWord("car").enqueue(new Callback<PixabayPosts>() {
            @Override
            public void onResponse(Call<PixabayPosts> call, Response<PixabayPosts> response) {
                List<Post> postList=response.body().getHits();
                recyclerViewLike.setAdapter(new LikesAdapter(postList,getActivity()));
                recyclerViewLike.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<PixabayPosts> call, Throwable t) {

            }
        });
    }
}
