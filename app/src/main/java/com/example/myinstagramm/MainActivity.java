package com.example.myinstagramm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myinstagramm.R;
import com.example.myinstagramm.view.HomeFragment;
import com.example.myinstagramm.view.LikesFragment;
import com.example.myinstagramm.view.ProfileFragment;
import com.example.myinstagramm.view.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
        }
        
        private BottomNavigationView.OnNavigationItemSelectedListener navListener=
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment=null;
                        switch (item.getItemId()){
                            case R.id.nav_home:
                                selectedFragment=new HomeFragment();
                                break;
                                case R.id.nav_account:
                                selectedFragment=new ProfileFragment();
                                break;
                                case R.id.nav_search:
                                selectedFragment=new SearchFragment();
                                break;
                                case R.id.nav_like:
                                selectedFragment=new LikesFragment();
                                break;
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();
                      return true;
                        }
                };
    }
