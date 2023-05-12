package com.example.first.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.first.MainActivity2;
import com.example.first.R;
import com.example.first.model.Category;
import com.example.first.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private final List<Product> products;

    private final Context context;

    public ProductAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_item, parent, false);
        return new ProductAdapter.ViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);
        final boolean[] isLiked = {false};

        holder.productName.setText(product.getTitle());
        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, MainActivity2.class);
//            intent.putExtra("category", category.getName());
//            context.startActivity(intent);
        });

        holder.likeImage.setOnClickListener(v -> {


            if (isLiked[0]) {
                holder.likeImage.setImageDrawable(context.getDrawable(R.drawable.like_active));
            }
            else holder.likeImage.setImageDrawable(context.getDrawable(R.drawable.like));
            isLiked[0] = !isLiked[0];
        });
        Picasso.with(context).
                load(product.getThumbnail()).into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView productImage;
        final TextView productName;
        final ImageView likeImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            likeImage = itemView.findViewById(R.id.like);
        }
    }
}
