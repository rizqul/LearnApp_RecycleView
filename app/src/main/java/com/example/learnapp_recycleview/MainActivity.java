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
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListHeroAdapter.OnItemClickListener {
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_NAME = "heroName";
    public static final String EXTRA_SUMMARY = "heroSummary";

    private RecyclerView recyclerViewHero;
    private List<HeroModel> models = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.bn_menu);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, new HomeFragment());

        recyclerViewHero = findViewById(R.id.rv_hero);
        recyclerViewHero.setHasFixedSize(true);
        models.addAll(HeroDatabase.getListData());
        recyclerViewHero.setLayoutManager(new LinearLayoutManager(this));
        ListHeroAdapter adapter = new ListHeroAdapter(models);
        recyclerViewHero.setAdapter(adapter);
        adapter.setOnItemClickListener(MainActivity.this);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.nm_home:
                    selectedFragment = new HomeFragment();
                    break;

                case R.id.nm_history:
                    selectedFragment = new HistoryFragment();
                    break;

                case R.id.nm_bookmark:
                    selectedFragment = new BookmarkFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.rv_hero, selectedFragment).commit();
            return true;
        }
    };

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(MainActivity.this, HeroDetailActivity.class);
        HeroModel clickedItem = models.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedItem.getPhoto());
        detailIntent.putExtra(EXTRA_NAME, clickedItem.getName());
        detailIntent.putExtra(EXTRA_SUMMARY, clickedItem.getSummary());

        startActivity(detailIntent);
    }
}