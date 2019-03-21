package com.example.wiejskichlop.flickrgallery.adapter;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.example.wiejskichlop.flickrgallery.R;
import com.example.wiejskichlop.flickrgallery.model.Image;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder2> {
    private List<Image> images;
    private Context context;


    public DataAdapter(Context context, List<Image> images) {
        this.context = context;
        this.images = images;

    }

    @Override
    @NonNull
    public ViewHolder2 onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_layout, viewGroup, false);
        return new ViewHolder2(view);
    }

    /**
     * gets the image url from adapter and passes to Glide API to load the image
     *
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(ViewHolder2 viewHolder, int i) {
        Image image = images.get(i);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(new ColorDrawable(Color.BLACK))
                .error(new ColorDrawable(Color.WHITE))
                .priority(Priority.HIGH);
        Glide.with(context)
                .load(image.getMedia().getM())
                .apply(options)
                .into(viewHolder.img);    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {

        ImageView img;

        public ViewHolder2(View view) {
            super(view);

            img = view.findViewById(R.id.imageView);
        }
    }
}