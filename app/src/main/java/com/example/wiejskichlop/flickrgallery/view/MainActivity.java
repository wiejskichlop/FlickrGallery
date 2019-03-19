package com.example.wiejskichlop.flickrgallery.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.wiejskichlop.flickrgallery.adapter.ComplexDataAdapter;
import com.example.wiejskichlop.flickrgallery.adapter.FlickrApiController;
import com.example.wiejskichlop.flickrgallery.adapter.DataAdapter;
import com.example.wiejskichlop.flickrgallery.model.Image;
import com.example.wiejskichlop.flickrgallery.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    List<Image> imageList;
    RecyclerView.Adapter dataAdapter;
    static String IMAGE_URL_NAME="ImageUrl";
    private boolean simpleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.bigImageView);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        dataAdapter = new ComplexDataAdapter(getApplicationContext(), prepareData());
        recyclerView.setAdapter(dataAdapter);

        AppCompatActivity mainActivity=this;
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(mainActivity, ShowFullScreenPicutreActivity.class);
                        String message = imageList.get(position).getMedia().getM();
                        intent.putExtra(IMAGE_URL_NAME, message);
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );

    }

    private List prepareData() {

        FlickrApiController flickrApiController = new FlickrApiController(this);
        flickrApiController.start();
        return getData(flickrApiController);
    }

    public List getData(FlickrApiController flickrApiController) {
        if(flickrApiController.getImages().size()==0)
            return new ArrayList();
        imageList =  flickrApiController.getImages();
        Log.d("MainActivity", "List count: " + imageList.size());

        return imageList;
    }

    public void changeView(View view) {
        gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount()%2+1);
        if (!simpleView)
            dataAdapter = new DataAdapter(getApplicationContext(), imageList);
        else
            dataAdapter = new ComplexDataAdapter(getApplicationContext(), imageList);
        recyclerView.setAdapter(dataAdapter);

    }
}