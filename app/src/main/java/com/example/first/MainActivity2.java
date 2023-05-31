package com.example.first;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.first.adapter.ProductAdapter;
import com.example.first.model.Category;
import com.example.first.model.Product;
import com.example.first.response.ProductsResponse;
import com.example.first.retrofit.RetrofitInstance;
import com.example.first.service.DummyJsonService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity2 extends AppCompatActivity {


    TextView categoryText;

    ImageView categoryImage;
    RecyclerView recyclerView;



    @SuppressLint({"UseCompatLoadingForDrawables", "SourceLockedOrientationActivity"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        categoryText = findViewById(R.id.category_of_products);

        categoryImage = findViewById(R.id.category_image);


        Category category = getIntent().getParcelableExtra("category");

        if (category != null) {
            categoryText.setText(category.getName());
            getProductsByCategoryName(category.getName());

            Picasso.with(this).
                    load(category.getImageUrl()).into(categoryImage);
        }

        recyclerView = findViewById(R.id.product_list);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

//    private void getProduct(int id) {
//
//        DummyJsonService dummyJsonService = RetrofitInstance.getService();
//        Call<Product> call = dummyJsonService.getProduct(id);
//        call.enqueue(new Callback<Product>() {
//            @Override
//            public void onResponse(Call<Product> call, Response<Product> response) {
//                Product product = response.body();
//                if (product != null) {
//                    textView.setText(product.toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Product> call, Throwable t) {
//
//            }
//        });
//
//    }

    private void getProductsByCategoryName(String category) {

        DummyJsonService dummyJsonService = RetrofitInstance.getService();

        Call<ProductsResponse> call = dummyJsonService.getProducts(category);
        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                ProductsResponse productsResponse = response.body();
                if (productsResponse != null) {
                    ProductAdapter productAdapter = new ProductAdapter(productsResponse.getProducts(), MainActivity2.this);
                    recyclerView.setAdapter(productAdapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity2.this, 2, RecyclerView.VERTICAL, false));
                    recyclerView.setHasFixedSize(true);
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                Log.d("MainActivity2", "Unexpected error");
                //
                /// ximer ximer
            }
        });
    }

    private void cart() {

    }

}