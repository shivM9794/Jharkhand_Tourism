package com.jharkhandtourism.app.view.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.jharkhandtourism.app.model.SearchRVModel;
import com.jharkhandtourism.app.view.fragments.HotelListFragment;
import com.jharkhandtourism.app.view.fragments.ImageSearchResultsFragment;

import java.util.ArrayList;

public class SearchResultPagerAdapter extends FragmentPagerAdapter {

    Context context;
    ArrayList<SearchRVModel> searchRVModalArrayList;
    private SparseArray<Fragment> registeredFragments = new SparseArray<>();

    public SearchResultPagerAdapter(@NonNull FragmentManager fm, int behavior, Context context, ArrayList<SearchRVModel> searchRVModalArrayList) {
        super(fm, behavior);
        this.context = context;
        this.searchRVModalArrayList = searchRVModalArrayList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return new ImageSearchResultsFragment(context, searchRVModalArrayList);

            case 1:
                return new HotelListFragment(context);
        }
        return new ImageSearchResultsFragment(context, searchRVModalArrayList);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){

            case 0:
                return "About";

            case 1:
                return "Hotels Near By";

        }
        return "Title";
    }



    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

}
