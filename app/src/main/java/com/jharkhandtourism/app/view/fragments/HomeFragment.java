package com.jharkhandtourism.app.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jharkhandtourism.app.R;
import com.jharkhandtourism.app.model.DestinationsModel;
import com.jharkhandtourism.app.view.activity.AboutJharkhandActivity;
import com.jharkhandtourism.app.view.activity.DashboardActivity;
import com.jharkhandtourism.app.view.adapter.DestinationListAdapter;
import com.jharkhandtourism.app.view.adapter.HotelListAdapter;
import com.jharkhandtourism.app.view.adapter.SliderAdapter;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class HomeFragment extends Fragment{

    NestedScrollView scroll;
    SliderView sliderView;

    RecyclerView recyclerview;
    DestinationListAdapter adapter;

    ArrayList<DestinationsModel> destinations = new ArrayList<>();

    Context context;

    public HomeFragment(Context context){
        this.context  = context;
    }

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        scroll = rootView.findViewById(R.id.scroll);
        sliderView = rootView.findViewById(R.id.slider_image);
        recyclerview = rootView.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));

        destinations.add(new DestinationsModel("Top Destinations", R.drawable.tagore_hill, "top_destinations"));
        destinations.add(new DestinationsModel("Popular Cities", R.drawable.jamshedpur, "cities"));
        destinations.add(new DestinationsModel("Religious Places", R.drawable.pahari_mandir_ranchi, "religious"));
        destinations.add(new DestinationsModel("Spiritual Places", R.drawable.city5, "spiritual"));
        destinations.add(new DestinationsModel("Natural Sites", R.drawable.city2, "natural"));
        destinations.add(new DestinationsModel("Popular Tours", R.drawable.city6, "tours"));

        adapter = new DestinationListAdapter(getContext(), destinations);
        recyclerview.setAdapter(adapter);

        sliderImage();

        new Handler().postDelayed((Runnable) () -> {
            scroll.scrollTo(0, 0);
        },500);


        rootView.findViewById(R.id.clickable_ll).setOnClickListener(v -> {
            context.startActivity(new Intent(context, AboutJharkhandActivity.class));
        });

        return rootView;
    }


    private void sliderImage() {

        ArrayList<Integer> slider = new ArrayList<>();
        slider.add(R.drawable.jharkhand_tourism_banner);
        slider.add(R.drawable.jharkhand_tourism_banner);
        slider.add(R.drawable.jharkhand_tourism_banner);
        slider.add(R.drawable.jharkhand_tourism_banner);
        slider.add(R.drawable.jharkhand_tourism_banner);
        SliderAdapter sliderAdapter = new SliderAdapter(slider,getContext());
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
    }

}
