package com.jharkhandtourism.app.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.jharkhandtourism.app.R;

public class ProfileFragment extends Fragment{

   Context context;
    public ProfileFragment(Context context){
        this.context  = context;
    }

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_profile, container, false);


        return rootView;
    }


}
