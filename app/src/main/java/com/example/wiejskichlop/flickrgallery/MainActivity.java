package com.example.wiejskichlop.flickrgallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        ArrayList imageUrlList = prepareData();
        DataAdapter dataAdapter = new DataAdapter(getApplicationContext(), imageUrlList);
        recyclerView.setAdapter(dataAdapter);
    }

    private ArrayList prepareData() {

// here you should give your image URLs and that can be a link from the Internet
        String imageUrls[] = {
                "url1",
                "url2",
                "url3",
                "url4"};

        ArrayList imageUrlList = new ArrayList<>();
        for (int i = 0; i < imageUrls.length; i++) {
            ImageUrl imageUrl = new ImageUrl();
            imageUrl.setImageUrl(imageUrls[i]);
            imageUrlList.add(imageUrl);
        }
        Controller controller = new Controller();
        controller.start();
        Log.d("MainActivity", "List count: " + imageUrlList.size());
        return imageUrlList;
    }
}