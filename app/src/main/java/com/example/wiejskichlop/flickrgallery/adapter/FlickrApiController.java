package com.example.wiejskichlop.flickrgallery.adapter;

import android.util.Log;

import com.example.wiejskichlop.flickrgallery.model.FlickrService;
import com.example.wiejskichlop.flickrgallery.model.Image;
import com.example.wiejskichlop.flickrgallery.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.internal.annotations.EverythingIsNonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlickrApiController implements Callback<List<Image>> {

    private static final String BASE_URL = "https://api.flickr.com/services/feeds/";
    private List<Image> images =new ArrayList<>();
    private MainActivity mainActivity;

    public FlickrApiController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public List<Image> getImages() {
        return images;
    }
    public void start() {
        Log.d("Retrofit","started");

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new CustomInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        FlickrService flickrService = retrofit.create(FlickrService.class);

        Call<List<Image>> call = flickrService.listImages();
        call.enqueue(this);

    }

    @Override
    @EverythingIsNonNull
    public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
        Log.d("Retrofit","onResponse");

        if(response.isSuccessful()) {

            List<Image> responseImages = Objects.requireNonNull(response.body());
                   images.addAll(responseImages);
                mainActivity.getData(this);
            Log.d("Retrofit","Done loading the images");


        } else {

            Log.d("Retrofit","Loading images not successful");
            Log.d("Retrofit",response.toString());

        }

    }

    @Override
    @EverythingIsNonNull
    public void onFailure(Call<List<Image>> call, Throwable t) {
        Log.d("Retrofit", "Call failed ");

        t.printStackTrace();
    }
}
