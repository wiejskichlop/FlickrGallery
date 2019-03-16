package com.example.wiejskichlop.flickrgallery;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
public class ComplexDataAdapter extends RecyclerView.Adapter<ComplexDataAdapter.ViewHolder> {
    private List<Image> images;
    private Context context;

    public ComplexDataAdapter(Context context, List<Image> images) {
        this.context = context;
        this.images = images;

    }

    @Override
    public ComplexDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.big_image_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    /**
     * gets the image url from adapter and passes to Glide API to load the image
     *
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Image image = images.get(i);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(new ColorDrawable(Color.BLACK))
                .error(new ColorDrawable(Color.WHITE))
                .priority(Priority.HIGH);
        Glide.with(context)
                .load(image.getMedia().getM())
                .apply(options)
                .into(viewHolder.img);
        Log.d("ComplexDataAdapter",   image.getMedia().getM());


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
            img = view.findViewById(R.id.bigImageView);
            name = view.findViewById(R.id.fileName);
            info = view.findViewById(R.id.info);

        }
    }
}