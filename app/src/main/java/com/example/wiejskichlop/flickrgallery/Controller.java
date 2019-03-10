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

public class Controller implements Callback<List<Item>> {

    static final String BASE_URL = "https://api.flickr.com/services/feeds/";
    List<String> imageLinks=new ArrayList<>();
    MainActivity mainActivity;

    public Controller(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
    }

    public void start() {
        Log.d("Retrofit","started");

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new CustomInterceptor()) // This is used to add ApplicationInterceptor.
//                .addNetworkInterceptor(new CustomInterceptor()) //This is used to add NetworkInterceptor.
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        FlickrService flickrService = retrofit.create(FlickrService.class);

        Call<List<Item>> call = flickrService.listImages();
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
        Log.d("Retrofit","responsing");

        if(response.isSuccessful()) {

            List<Item> changesList = response.body();
                for(Item i:changesList)
                   imageLinks.add(new String(i.getMedia().getM()));
                mainActivity.getData(this);

        } else {

            Log.d("Retrofit","not successful");
            Log.d("Retrofit",response.toString());

        }

    }

    @Override
    public void onFailure(Call<List<Item>> call, Throwable t) {
        Log.d("Retrofit", "Call failed ");

        t.printStackTrace();
    }
}
