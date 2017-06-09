package com.solvo.hoam.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.solvo.hoam.R;
import com.solvo.hoam.presentation.ui.helper.AdHelper;

public class ImageActivity extends AppCompatActivity {

    public static final String TAG = ImageActivity.class.getSimpleName();

    private static final String EXTRA_IMAGE_URL = "image_url";

    public static Intent buildIntent(Context context, String imageUrl) {
        return new Intent(context, ImageActivity.class)
                .putExtra(EXTRA_IMAGE_URL, imageUrl);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ImageView imageView = (ImageView) findViewById(R.id.ad_image_view);

        Glide.with(this)
                .load(AdHelper.getImageUrl(getIntent().getStringExtra(EXTRA_IMAGE_URL)))
                .crossFade()
                .into(imageView);
    }
}
