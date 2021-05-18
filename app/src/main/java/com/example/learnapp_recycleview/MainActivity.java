package com.example.learnapp_recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private Map<Integer, Fragment> fragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.bn_menu);


        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.nm_home, new HomeFragment());
        fragmentMap.put(R.id.nm_history, new HistoryFragment());
        fragmentMap.put(R.id.nm_bookmark, new BookmarkFragment());
        bottomNav.setOnNavigationItemSelectedListener(this);
        bottomNav.setSelectedItemId(R.id.nm_home);
        //        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, new HomeFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = fragmentMap.get(item.getItemId());
        assert fragment != null;
        switch (item.getItemId()) {
            case R.id.nm_home:
                setActionBarTitle("Home");
                break;
            case R.id.nm_history:
                setActionBarTitle("History");
                break;
            case R.id.nm_bookmark:
                setActionBarTitle("Bookmark");
                break;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_main, fragment)
                .commit();
        return true;
    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}