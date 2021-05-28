package com.example.learnapp_recycleview.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnapp_recycleview.HeroModel;
import com.example.learnapp_recycleview.R;

public class HeroDetailActivity extends AppCompatActivity {
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_NAME = "heroName";
    public static final String EXTRA_SUMMARY = "heroSummary";
    HeroModel heroSelected;

    ImageView ivHeroPhotoDetail;
    TextView tvHeroNameDetail;
    TextView tvHeroSummary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detail);

        ivHeroPhotoDetail = findViewById(R.id.iv_hero_photo_detail);
        tvHeroNameDetail = findViewById(R.id.tv_hero_name_detail);
        tvHeroSummary = findViewById(R.id.tv_hero_summary_detail);

        Intent a = getIntent();
        int heroPhoto = a.getIntExtra(EXTRA_URL, -1);
        String heroName = a.getStringExtra(EXTRA_NAME);
        String heroSummary = a.getStringExtra(EXTRA_SUMMARY);

        ivHeroPhotoDetail.setBackgroundResource(heroPhoto);
        tvHeroNameDetail.setText(heroName);
        tvHeroSummary.setText(heroSummary);
    }
}