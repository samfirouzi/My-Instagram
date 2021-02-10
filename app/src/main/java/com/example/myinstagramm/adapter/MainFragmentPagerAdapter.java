package com.example.myinstagramm.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myinstagramm.view.HomeFragment;
import com.example.myinstagramm.view.LikesFragment;
import com.example.myinstagramm.view.ProfileFragment;
import com.example.myinstagramm.view.SearchFragment;

public class MainFragmentPagerAdapter extends FragmentStateAdapter {


     public MainFragmentPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new SearchFragment();
            case 2:
                return new ProfileFragment();
            case 3:
                return new LikesFragment();

        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
