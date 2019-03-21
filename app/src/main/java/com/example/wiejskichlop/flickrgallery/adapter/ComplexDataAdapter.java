package com.example.wiejskichlop.flickrgallery.adapter;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.wiejskichlop.flickrgallery.R;
import com.example.wiejskichlop.flickrgallery.model.Image;

import java.util.List;
public class ComplexDataAdapter extends RecyclerView.Adapter<ComplexDataAdapter.ViewHolder> {
    private List<Image> images;
    private Context context;
    View view;

    public ComplexDataAdapter(Context context, List<Image> images) {
        this.context = context;
        this.images = images;


    }

    @Override
    @NonNull
    public ComplexDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

         view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.big_image_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    /**
     * gets the image url from adapter and passes to Glide API to load the image
     *
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(ComplexDataAdapter.ViewHolder viewHolder, int i) {
        Image image = images.get(i);
//        Log.d("ComplexDataAdapter",   image.getMedia().getM());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(new ColorDrawable(Color.WHITE))
                .error(new ColorDrawable(Color.WHITE))
                .priority(Priority.HIGH)
                .override(Target.SIZE_ORIGINAL);
        Glide.with(context)
                .load(image.getMedia().getM())
                .apply(options)
                .into(viewHolder.img);




        viewHolder.name.setText(image.getTitle());
        viewHolder.info.setText(Html.fromHtml(image.getDescription()));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView name;
        TextView info;

        public ViewHolder(View view) {
            super(view);

            img = view.findViewById(R.id.imageView);
            name = view.findViewById(R.id.fileName);
            info = view.findViewById(R.id.info);

        }
    }
}