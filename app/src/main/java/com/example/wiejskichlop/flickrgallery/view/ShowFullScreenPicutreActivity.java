package com.example.wiejskichlop.flickrgallery.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.example.wiejskichlop.flickrgallery.R;

public class ShowFullScreenPicutreActivity extends AppCompatActivity {
    ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen_picture);
        image = findViewById(R.id.imageView1);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.IMAGE_URL_NAME);
        addListenerOnButton(message);
        Log.d("fullscreen",message);

    }

    public void addListenerOnButton(String url) {

        RequestOptions options = new RequestOptions()
                .fitCenter()
                .placeholder(new ColorDrawable(Color.BLACK))
                .error(new ColorDrawable(Color.BLACK))
                .priority(Priority.HIGH);
        Glide.with(getApplicationContext())
                .load(url)
                .apply(options)
                .into(image);

    }


}
