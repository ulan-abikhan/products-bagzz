package com.example.first;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.first.fragment.FragmentPagerAdapter;
import com.example.first.model.Product;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.squareup.picasso.Picasso;


public class CarActivity extends AppCompatActivity {


    ImageView thumbnail;

    TextView productTitle;

    Button buyNow;

    BottomSheetDialog bottomSheetDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        thumbnail = findViewById(R.id.product_thumbnail);
        productTitle = findViewById(R.id.productTitle);
        buyNow = findViewById(R.id.product_buy_button);

        bottomSheetDialog();
        onBuyButtonClick();


        Product product = getIntent().getParcelableExtra("product");
        Picasso.with(this).
                load(product.getThumbnail()).into(thumbnail);

        productTitle.setText(product.getTitle());

        ViewPager2 pager = findViewById(R.id.view_pager);
        FragmentStateAdapter pageAdapter = new FragmentPagerAdapter(this, product.getDescription(), product.getImages());
        pager.setAdapter(pageAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        TabLayoutMediator tabLayoutMediator= new TabLayoutMediator(tabLayout, pager, (tab, position) ->
                tab.setText(position == 1 ? "Images" : "Description"));
        tabLayoutMediator.attach();


    }

    private void bottomSheetDialog() {
        View view = getLayoutInflater().inflate(R.layout.buy_bottom_sheet_dialog, null);

        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setCancelable(true);
        LayoutInflater layoutInflater = bottomSheetDialog.getLayoutInflater();
        bottomSheetDialog.setContentView(view);

        Button okBottomSheet = view.findViewById(R.id.ok_bottom_sheet);
        okBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
    }

    private void onBuyButtonClick() {
        buyNow.setOnClickListener(v -> bottomSheetDialog.show());
    }

}