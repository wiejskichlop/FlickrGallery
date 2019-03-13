package com.example.wiejskichlop.flickrgallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        imageView = (ImageView) findViewById(R.id.imageView);
        imageView = (ImageView) findViewById(R.id.bigImageView);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List images = prepareData();
//        DataAdapter dataAdapter = new DataAdapter(getApplicationContext(), images);
//        recyclerView.setAdapter(dataAdapter);
        ComplexDataAdapter dataAdapter = new ComplexDataAdapter(getApplicationContext(), images);
        recyclerView.setAdapter(dataAdapter);
    }

    private List prepareData() {

        Controller controller = new Controller(this);
        controller.start();
        return getData(controller);
    }

    protected List getData(Controller controller) {
        Log.d("MainActivity", "List count: " + controller.images.size());



        List<Image> imageList =  controller.images;


        Log.d("MainActivity", "List count: " + imageList.size());
//        DataAdapter dataAdapter = new DataAdapter(getApplicationContext(), imageList);
        ComplexDataAdapter dataAdapter = new ComplexDataAdapter(getApplicationContext(), imageList);

        recyclerView.setAdapter(dataAdapter);
        return imageList;
    }


}