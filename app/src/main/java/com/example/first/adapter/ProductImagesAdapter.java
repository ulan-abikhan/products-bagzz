package com.example.first.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.first.R;
import com.example.first.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductImagesAdapter extends RecyclerView.Adapter<ProductImagesAdapter.ViewHolder> {

    private final List<String> images;

    private final Context context;

    public ProductImagesAdapter(List<String> images, Context context) {
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductImagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_image_item, parent, false);
        return new ProductImagesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductImagesAdapter.ViewHolder holder, int position) {
        String image = images.get(position);

        Picasso.with(context).
                load(image).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_item_image);
        }
    }
}
