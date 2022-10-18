package com.practice.on.textencryption;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private ArrayList<Fragment> arrayListFragment = new ArrayList<>();
    private ArrayList<String> arrayListTitle = new ArrayList<>();
    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return arrayListFragment.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayListFragment.size();
    }

    // Add Fragment
    public void addFragment(Fragment fragment, String title){
        arrayListFragment.add(fragment);
        arrayListTitle.add(title);
    }

    // Return fragment title
    public String getFragmentTitle(int position){
        return arrayListTitle.get(position);
    }
}
