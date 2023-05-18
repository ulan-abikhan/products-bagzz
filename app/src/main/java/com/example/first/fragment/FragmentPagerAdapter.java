package com.example.first.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.first.First2Fragment;

import java.util.List;

public class FragmentPagerAdapter extends FragmentStateAdapter {

    private String description;
    private List<String> images;

    public FragmentPagerAdapter(@NonNull FragmentActivity fragmentActivity, String description, List<String> images) {
        super(fragmentActivity);
        this.description = description;
        this.images = images;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return SecondFragment.newInstance(images);
        }
        return FirstFragment.newInstance(description);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
