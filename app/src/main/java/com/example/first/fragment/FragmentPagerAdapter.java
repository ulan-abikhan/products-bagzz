package com.example.first.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.first.First2Fragment;

public class FragmentPagerAdapter extends FragmentStateAdapter {

    private String description;

    public FragmentPagerAdapter(@NonNull FragmentActivity fragmentActivity, String description) {
        super(fragmentActivity);
        this.description = description;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return new SecondFragment();
        }
        return FirstFragment.newInstance(description);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
