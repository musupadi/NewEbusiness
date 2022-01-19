package com.ascendant.e_businessprofile.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ascendant.e_businessprofile.R;
import com.bumptech.glide.Glide;

public class FullScreenActivity extends AppCompatActivity {
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        image = findViewById(R.id.ivImage);
        Intent data = getIntent();
        String IMAGE = data.getStringExtra("IMAGE");
        Glide.with(FullScreenActivity.this)
                .load(IMAGE)
                .into(image);
        image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onBackPressed();
                return true;
            }
        });
    }
}