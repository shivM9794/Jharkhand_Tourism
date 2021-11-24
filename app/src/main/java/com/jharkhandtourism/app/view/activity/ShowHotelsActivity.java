package com.jharkhandtourism.app.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.jharkhandtourism.app.R;
import com.jharkhandtourism.app.model.DestinationsModel;
import com.jharkhandtourism.app.model.HotelModel;
import com.jharkhandtourism.app.view.adapter.ExploreWanderlustAdapter;
import com.jharkhandtourism.app.view.adapter.HotelAdapter;
import com.jharkhandtourism.app.view.adapter.RecommendedAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShowHotelsActivity extends AppCompatActivity {

    RecyclerView idRecommendedRecycler,idExploreRecycler;
    RecommendedAdapter recommendedAdapter;
    ExploreWanderlustAdapter wanderlustAdapter;
    ImageView image_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hotels);
        idRecommendedRecycler = findViewById(R.id.idRecommendedRecycler);
        idExploreRecycler = findViewById(R.id.idExploreRecycler);


        image_back = findViewById(R.id.image_back);
        image_back.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SearchHotelActivity.class);
            startActivity(intent);
        });

        setupHotelViewPager();
        recommendedHotels();
        exploreWanderlust();
    }



    private void exploreWanderlust() {

        ArrayList<DestinationsModel> exploreWanderlust = new ArrayList<>();

        exploreWanderlust.add(new DestinationsModel("Chill at beach", R.drawable.beaches));
        exploreWanderlust.add(new DestinationsModel("Discover heritage site", R.drawable.heritage_jk));
        exploreWanderlust.add(new DestinationsModel("Travel for work", R.drawable.work));
        exploreWanderlust.add(new DestinationsModel("Chill at beach", R.drawable.beaches));

        idExploreRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        wanderlustAdapter = new ExploreWanderlustAdapter(getApplicationContext(),exploreWanderlust);
        idExploreRecycler.setAdapter(wanderlustAdapter);
    }

    private void recommendedHotels() {

        ArrayList<DestinationsModel> hotel = new ArrayList<>();
        hotel.add(new DestinationsModel("Ranchi", R.drawable.city1));
        hotel.add(new DestinationsModel("Ranchi", R.drawable.city1));
        hotel.add(new DestinationsModel("Ranchi", R.drawable.city1));
        hotel.add(new DestinationsModel("Ranchi", R.drawable.city1));


        idRecommendedRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recommendedAdapter = new RecommendedAdapter(getApplicationContext(), hotel);
        idRecommendedRecycler.setAdapter(recommendedAdapter);
    }

    private void setupHotelViewPager() {

        ViewPager2 hotelsViewPager = findViewById(R.id.hotelsViewPager);
        hotelsViewPager.setClipToPadding(false);
        hotelsViewPager.setClipChildren(false);
        hotelsViewPager.setOffscreenPageLimit(5);
        hotelsViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });
        hotelsViewPager.setPageTransformer(compositePageTransformer);
        hotelsViewPager.setAdapter(new HotelAdapter(getHotelModel(),this));
    }

    private List<HotelModel> getHotelModel() {
        List<HotelModel> hotelModels = new ArrayList<>();

        HotelModel hm = new HotelModel();
        hm.poster = R.drawable.hotels;
        hm.name_hotel = "Radisson Blu Hotel";
        hm.category = "Near Ranchi International Airport";
        hm.price = "₹ 4,500";
        hm.rating = 4.5f;
        hotelModels.add(hm);

        HotelModel hm1 = new HotelModel();
        hm1.poster = R.drawable.hotels;
        hm1.name_hotel = "Radisson Blu Hotel";
        hm1.category = "Near Ranchi International Airport";
        hm1.price = "₹ 4,500";
        hm1.rating = 4.5f;
        hotelModels.add(hm1);

        HotelModel hm2 = new HotelModel();
        hm2.poster = R.drawable.hotels;
        hm2.name_hotel = "Radisson Blu Hotel";
        hm2.category = "Near Ranchi International Airport";
        hm2.price = "₹ 4,500";
        hm2.rating = 4.5f;
        hotelModels.add(hm2);

        return hotelModels;
    }

}