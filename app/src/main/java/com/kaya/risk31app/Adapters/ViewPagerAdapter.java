package com.kaya.risk31app.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentsList = new ArrayList<>();
    private final List<String> fragmentsListTitles = new ArrayList<>();


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsListTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentsListTitles.get(position);
    }

    public void addFragments(Fragment fragment, String Title) {
        fragmentsList.add(fragment);
        fragmentsListTitles.add(Title);
    }
}
