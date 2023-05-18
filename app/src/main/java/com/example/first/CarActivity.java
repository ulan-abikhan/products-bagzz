package com.example.first;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.first.fragment.FragmentPagerAdapter;
import com.example.first.model.Product;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.squareup.picasso.Picasso;


public class CarActivity extends AppCompatActivity {


    ImageView thumbnail;

    TextView productTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        thumbnail = findViewById(R.id.product_thumbnail);
        productTitle = findViewById(R.id.productTitle);


        Product product = getIntent().getParcelableExtra("product");
        Picasso.with(this).
                load(product.getThumbnail()).into(thumbnail);

        productTitle.setText(product.getTitle());

        ViewPager2 pager = findViewById(R.id.view_pager);
        FragmentStateAdapter pageAdapter = new FragmentPagerAdapter(this, product.getDescription());
        pager.setAdapter(pageAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        TabLayoutMediator tabLayoutMediator= new TabLayoutMediator(tabLayout, pager, (tab, position) ->
                tab.setText(position == 1 ? "Images" : "Description"));
        tabLayoutMediator.attach();


    }
}