package com.example.myinstagramm.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myinstagramm.R;
import com.example.myinstagramm.WebService.Service;
import com.example.myinstagramm.adapter.HomeAdapter;
import com.example.myinstagramm.model.PixabayPosts;
import com.example.myinstagramm.model.Post;

import java.security.GeneralSecurityException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {

    RecyclerView recyclerViewSearch;
    TextView textViewState;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText editTextSearch = view.findViewById(R.id.editTextSearchPost);
        textViewState=view.findViewById(R.id.textViewSate);
        recyclerViewSearch = view.findViewById(R.id.recyclerViewSearch);
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String searchKeyWord=editTextSearch.getText().toString();
                    searchInPixabay(searchKeyWord);
                    hideSoftKeyboard(getActivity());
                    textViewState.setVisibility(View.INVISIBLE);
                    recyclerViewSearch.setVisibility(View.INVISIBLE);
                    return true;

                }
                return false;
            }
        });
    }

    public void searchInPixabay(String keyWord) {
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(gsonConverterFactory).build();
        Service service = retrofit.create(Service.class);
        service.getPostByKeyWord(keyWord).enqueue(new Callback<PixabayPosts>() {
            @Override
            public void onResponse(Call<PixabayPosts> call, Response<PixabayPosts> response) {
                recyclerViewSearch.setVisibility(View.VISIBLE);
                assert response.body() != null;
                List<Post> postList = response.body().getHits();
                if (postList.isEmpty()){
                    textViewState.setVisibility(View.VISIBLE);

                }
                HomeAdapter homeAdapter = new HomeAdapter(postList, getActivity());
                recyclerViewSearch.setAdapter(homeAdapter);
                recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getActivity()));

            }

            @Override
            public void onFailure(Call<PixabayPosts> call, Throwable t) {

            }
        });
    }
    // make hide the Keyboard after click on Search button
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

}
