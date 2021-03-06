package com.example.wiejskichlop.flickrgallery.adapter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

class CustomInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Response response = chain.proceed(request);
        BufferedSource source = Objects.requireNonNull(response.body()).source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        String responseBodyString = buffer.clone().readString(Charset.forName("UTF-8"));

        MediaType contentType = response.body().contentType();
        response.body().close();

        String trimmedJson = responseBodyString;
        trimmedJson=trimmedJson.substring(trimmedJson.indexOf("["),trimmedJson.lastIndexOf("]")+1);
        ResponseBody body = ResponseBody.create(contentType, trimmedJson );
        return response.newBuilder().body(body).build();
    }
}
