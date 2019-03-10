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

        Controller controller = new Controller(this);
        controller.start();
        return getData(controller);
    }

    protected ArrayList getData(Controller controller) {
        Log.d("MainActivity", "List count: " + controller.imageLinks.size());

        String imageUrls[] = new String[controller.imageLinks.size()];
        controller.imageLinks.toArray(imageUrls);

        ArrayList imageUrlList = new ArrayList<>();
        for (int i = 0; i < imageUrls.length; i++) {
            ImageUrl imageUrl = new ImageUrl();
            imageUrl.setImageUrl(imageUrls[i]);
            imageUrlList.add(imageUrl);
        }

        Log.d("MainActivity", "List count: " + imageUrlList.size());
        DataAdapter dataAdapter = new DataAdapter(getApplicationContext(), imageUrlList);
        recyclerView.setAdapter(dataAdapter);
        return imageUrlList;
    }


}