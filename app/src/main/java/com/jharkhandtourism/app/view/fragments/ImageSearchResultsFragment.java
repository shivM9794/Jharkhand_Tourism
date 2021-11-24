package com.jharkhandtourism.app.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jharkhandtourism.app.R;
import com.jharkhandtourism.app.model.SearchRVModel;
import com.jharkhandtourism.app.view.adapter.SearchRVAdapter;

import java.util.ArrayList;

public class ImageSearchResultsFragment extends Fragment{

    Context context;
    RecyclerView recyclerview;
    SearchRVAdapter searchRVAdapter;
    ArrayList<SearchRVModel> searchRVModalArrayList;

    public ImageSearchResultsFragment(){

    }

    public ImageSearchResultsFragment(Context context, ArrayList<SearchRVModel> searchRVModalArrayList){
        this.context = context;
        this.searchRVModalArrayList = searchRVModalArrayList;
    }

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_rv, container, false);

        recyclerview = rootView.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        searchRVAdapter = new SearchRVAdapter(searchRVModalArrayList, context);
        recyclerview.setAdapter(searchRVAdapter);

        return rootView;
    }

}
