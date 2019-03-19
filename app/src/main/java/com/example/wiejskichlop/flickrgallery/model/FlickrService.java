package com.example.wiejskichlop.flickrgallery.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FlickrService {
        @GET("photos_public.gne?format=json")
        Call<List<Image>> listImages();
}
