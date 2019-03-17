package com.example.wiejskichlop.flickrgallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    List<Image> imageList;
    RecyclerView.Adapter dataAdapter;
    boolean simpleView=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        imageView = (ImageView) findViewById(R.id.imageView);
        imageView = (ImageView) findViewById(R.id.bigImageView);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List images = prepareData();
//        DataAdapter dataAdapter = new DataAdapter(getApplicationContext(), images);
//        recyclerView.setAdapter(dataAdapter);
         dataAdapter = new ComplexDataAdapter(getApplicationContext(), images);
        recyclerView.setAdapter(dataAdapter);
        AppCompatActivity mainActivity=this;
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(mainActivity, ShowFullScreenPicutreActivity.class);
                        String message = position+"";
                        intent.putExtra(EXTRA_MESSAGE, message);
                        startActivity(intent);
                        Log.d("click",""+position);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

    }

    private List prepareData() {

        Controller controller = new Controller(this);
        controller.start();
        return getData(controller);
    }

    protected List getData(Controller controller) {
        if(controller.images.size()==0)
            return new ArrayList();
        Log.d("MainActivity", "List count: " + controller.images.size());



        imageList =  controller.images;


        Log.d("MainActivity", "List count: " + imageList.size());
       dataAdapter = new ComplexDataAdapter(getApplicationContext(), imageList);

        recyclerView.setAdapter(dataAdapter);
        return imageList;
    }
    public void changeView(View view) {
        if (!simpleView) {
            dataAdapter = new DataAdapter(getApplicationContext(), imageList);
            gridLayoutManager.setSpanCount(2);
        }
        else {
            dataAdapter = new ComplexDataAdapter(getApplicationContext(), imageList);
            gridLayoutManager.setSpanCount(1);

        }

        simpleView=!simpleView;
        recyclerView.setAdapter(dataAdapter);

    }
}