package com.jharkhandtourism.app.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jharkhandtourism.app.R;
import com.jharkhandtourism.app.model.DestinationsModel;
import com.jharkhandtourism.app.model.TourModel;
import com.jharkhandtourism.app.view.adapter.CityListAdapter;
import com.jharkhandtourism.app.view.adapter.ToursAdapter;
import com.jharkhandtourism.app.view.adapter.ToursAdapter2;
import com.jharkhandtourism.app.view.fragments.BottomCityDetailsFragment;
import com.jharkhandtourism.app.view.interfaces.ItemsInterface;

import java.util.ArrayList;

public class CategoryListActivity extends AppCompatActivity implements ItemsInterface {

    RecyclerView recyclerview;
    TextView main_heading;

    CityListAdapter cityListAdapter;

    String cat = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_listing);

        main_heading = findViewById(R.id.main_heading);
        recyclerview = findViewById(R.id.recyclerview);


        findViewById(R.id.image_back).setOnClickListener(v -> {
            finish();
        });


        cat = getIntent().getStringExtra("cat");
        String type = getIntent().getStringExtra("type");
        main_heading.setText(type);

        if(cat!=null&&cat.equalsIgnoreCase("tours")){
            ArrayList<TourModel> tours = new ArrayList<>();
            tours.add(new TourModel("Jain Spiritual Tour", "Bus Tours", R.drawable.jain_tour, "from ₹ 40,000 per group (up to 2)"));
            tours.add(new TourModel("Ranchi Tour", "Walking Tours", R.drawable.ranchi, "from ₹ 50,000 per group (up to 15)"));
            tours.add(new TourModel("Deoghar Baidyanath Dham", "Full Day Tours", R.drawable.deoghar, "from ₹ 6,500 per Adult"));
            tours.add(new TourModel("Jain Spiritual Tour", "Bus Tours", R.drawable.jain_tour, "from ₹ 40,000 per group (up to 2)"));
            tours.add(new TourModel("Ranchi Tour", "Walking Tours", R.drawable.ranchi, "from ₹ 50,000 per group (up to 15)"));
            tours.add(new TourModel("Deoghar Baidyanath Dham", "Full Day Tours", R.drawable.deoghar, "from ₹ 6,500 per Adult"));
            ToursAdapter2 toursAdapter = new ToursAdapter2(this, tours);
            recyclerview.setLayoutManager(new LinearLayoutManager(this));
            recyclerview.setAdapter(toursAdapter);
            return;
        }

        ArrayList<DestinationsModel> cities = new ArrayList<>();
        recyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        if(cat!=null&&cat.equalsIgnoreCase("religious")){
            cities.add(new DestinationsModel("Ranchi", R.drawable.pahari_mandir_ranchi));
            cities.add(new DestinationsModel("Ranchi", R.drawable.baidyanath_jyotirlinga_temple));
            cities.add(new DestinationsModel("Ranchi", R.drawable.pahari_mandir_ranchi));
            cities.add(new DestinationsModel("Ranchi", R.drawable.baidyanath_jyotirlinga_temple));
            cities.add(new DestinationsModel("Ranchi", R.drawable.pahari_mandir_ranchi));
            cities.add(new DestinationsModel("Ranchi", R.drawable.baidyanath_jyotirlinga_temple));
            cities.add(new DestinationsModel("Ranchi", R.drawable.pahari_mandir_ranchi));
            cities.add(new DestinationsModel("Ranchi", R.drawable.baidyanath_jyotirlinga_temple));
        }else{
            cities.add(new DestinationsModel("Ranchi", R.drawable.jamshedpur));
            cities.add(new DestinationsModel("Ranchi", R.drawable.ranchi));
            cities.add(new DestinationsModel("Ranchi", R.drawable.bokaro));
            cities.add(new DestinationsModel("Ranchi", R.drawable.dhanbad));
            cities.add(new DestinationsModel("Ranchi", R.drawable.jamshedpur));
            cities.add(new DestinationsModel("Ranchi", R.drawable.ranchi));
            cities.add(new DestinationsModel("Ranchi", R.drawable.bokaro));
            cities.add(new DestinationsModel("Ranchi", R.drawable.dhanbad));
        }




        cityListAdapter = new CityListAdapter(this, cities, this);
        recyclerview.setAdapter(cityListAdapter);



    }

    @Override
    public void showDetails(String name) {
        BottomCityDetailsFragment fragment = new BottomCityDetailsFragment(this, name);
        fragment.show(getSupportFragmentManager(), fragment.getTag());
    }
}