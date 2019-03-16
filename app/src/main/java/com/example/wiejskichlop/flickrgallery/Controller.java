package com.example.wiejskichlop.flickrgallery;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<List<Image>> {

    static final String BASE_URL = "https://api.flickr.com/services/feeds/";
    List<Image> images =new ArrayList<>();
    MainActivity mainActivity;

    public Controller(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
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
    public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
        Log.d("Retrofit","responsing");

        if(response.isSuccessful()) {

            List<Image> responseImages = response.body();
                for(Image i:responseImages)
                   images.add(i);
                mainActivity.getData(this);
            Log.d("Retrofit","done");


        } else {

            Log.d("Retrofit","not successful");
            Log.d("Retrofit",response.toString());

        }

    }

    @Override
    public void onFailure(Call<List<Image>> call, Throwable t) {
        Log.d("Retrofit", "Call failed ");

        t.printStackTrace();
    }
}
