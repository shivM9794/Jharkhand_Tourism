package com.jharkhandtourism.app.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;
import com.jharkhandtourism.app.R;
import com.jharkhandtourism.app.model.SearchRVModel;
import com.jharkhandtourism.app.view.adapter.SearchRVAdapter;
import com.jharkhandtourism.app.view.adapter.SearchResultPagerAdapter;

import java.util.ArrayList;

public class BottomSearchResultFragment extends BottomSheetDialogFragment {

    Context context;
//    RecyclerView idRVSearchResults;
//    SearchRVAdapter searchRVAdapter;
    ArrayList<SearchRVModel> searchRVModalArrayList;
    String placeName;
    TextView tv_place;

    public BottomSearchResultFragment(Context context, ArrayList<SearchRVModel> searchRVModalArrayList, String placeName) {
        this.context = context;
        this.searchRVModalArrayList = searchRVModalArrayList;
        this.placeName = placeName;
    }

    View rootView;

    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_search_result, container, false);

        tv_place = rootView.findViewById(R.id.tv_place);
        tv_place.setText(""+placeName);


        viewPager = rootView.findViewById(R.id.pager);
        viewPager.setAdapter(null);

        SearchResultPagerAdapter myPagerAdapter = new SearchResultPagerAdapter(getChildFragmentManager(), 0, context, searchRVModalArrayList);
        viewPager.setAdapter(myPagerAdapter);

        TabLayout tabLayout = rootView.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

//        idRVSearchResults = rootView.findViewById(R.id.idRVSearchResults);
//        idRVSearchResults.setLayoutManager(new LinearLayoutManager(context));
//        searchRVAdapter = new SearchRVAdapter(searchRVModalArrayList, context);
//        idRVSearchResults.setAdapter(searchRVAdapter);

        return rootView;
    }

//
//    @Override
//
//
//    override fun findScrollingChild(view: View): View? {
//        return if (view is ViewPager) {
//            view.focusedChild?.let { findScrollingChild(it) }
//        } else {
//            super.findScrollingChild(view)
//        }
//    }


}
