package com.jharkhandtourism.app.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jharkhandtourism.app.R;
import com.jharkhandtourism.app.model.DestinationsModel;
import com.jharkhandtourism.app.model.TourModel;
import com.jharkhandtourism.app.view.activity.PlanJouneryModel;
import com.jharkhandtourism.app.view.adapter.FamilyTripAdapter;
import com.jharkhandtourism.app.view.adapter.NaturalAdapter;
import com.jharkhandtourism.app.view.adapter.PlanJouneryAdapter;
import com.jharkhandtourism.app.view.adapter.ReligiousAdapter;
import com.jharkhandtourism.app.view.adapter.ToursAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class PlanFragment extends Fragment {

    RecyclerView recyclerview, idFTRecycler, idReligiousRecycler, idNaturalRecycler, idRecommendedRecycler;
    PlanJouneryAdapter planJouneryAdapter;
    FamilyTripAdapter familyTripAdapter;
    ReligiousAdapter religiousAdapter;
    NaturalAdapter naturalAdapter;
    ToursAdapter toursAdapter;

    ArrayList<PlanJouneryModel> destinations = new ArrayList<>();
    ArrayList<DestinationsModel> destination = new ArrayList<>();
    ArrayList<DestinationsModel> religious = new ArrayList<>();
    ArrayList<DestinationsModel> natural = new ArrayList<>();
    ArrayList<TourModel> tours = new ArrayList<>();

    Context context;

    public PlanFragment(Context context) {
        this.context = context;
    }

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_plan, container, false);

        idRecommendedRecycler = rootView.findViewById(R.id.idRecommendedRecycler);
        idRecommendedRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        tours.add(new TourModel("Jain Spiritual Tour", "Bus Tours", R.drawable.jain_tour, "from ₹ 40,000 per group (up to 2)"));
        tours.add(new TourModel("Ranchi Tour", "Walking Tours", R.drawable.ranchi, "from ₹ 50,000 per group (up to 15)"));
        tours.add(new TourModel("Deoghar Baidyanath Dham", "Full Day Tours", R.drawable.deoghar, "from ₹ 6,500 per Adult"));
        Collections.shuffle(tours);
        toursAdapter = new ToursAdapter(context, tours);
        idRecommendedRecycler.setAdapter(toursAdapter);

        recyclerview = rootView.findViewById(R.id.idPlanRecycler);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        destinations.add(new PlanJouneryModel("Jamshedpur", R.drawable.jamshedpur));
        destinations.add(new PlanJouneryModel("Dhanbad", R.drawable.dhanbad));
        destinations.add(new PlanJouneryModel("Ranchi", R.drawable.ranchi));
        destinations.add(new PlanJouneryModel("Bokaro", R.drawable.bokaro));
        destinations.add(new PlanJouneryModel("Deoghar", R.drawable.deoghar));
        destinations.add(new PlanJouneryModel("Hazaribagh", R.drawable.city1));

        planJouneryAdapter = new PlanJouneryAdapter(getContext(), destinations);
        recyclerview.setAdapter(planJouneryAdapter);

        idFTRecycler = rootView.findViewById(R.id.idFTRecycler);
        idFTRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        destination.add(new DestinationsModel("Jamshedpur", R.drawable.jamshedpur));
        destination.add(new DestinationsModel("Jamshedpur", R.drawable.jamshedpur));
        destination.add(new DestinationsModel("Jamshedpur", R.drawable.jamshedpur));
        destination.add(new DestinationsModel("Jamshedpur", R.drawable.jamshedpur));
        destination.add(new DestinationsModel("Jamshedpur", R.drawable.jamshedpur));
        destination.add(new DestinationsModel("Jamshedpur", R.drawable.jamshedpur));

        familyTripAdapter = new FamilyTripAdapter(getContext(), destination);
        idFTRecycler.setAdapter(familyTripAdapter);

        idReligiousRecycler = rootView.findViewById(R.id.idReligiousRecycler);
        idReligiousRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        religious.add(new DestinationsModel("Jamshedpur", R.drawable.deoghar));
        religious.add(new DestinationsModel("Jamshedpur", R.drawable.religious));
        religious.add(new DestinationsModel("Jamshedpur", R.drawable.religious2));
        religious.add(new DestinationsModel("Jamshedpur", R.drawable.religious3));

        religiousAdapter = new ReligiousAdapter(getContext(), religious);
        idReligiousRecycler.setAdapter(religiousAdapter);

        idNaturalRecycler = rootView.findViewById(R.id.idNaturalRecycler);
        idNaturalRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        natural.add(new DestinationsModel("Jamshedpur", R.drawable.city1));
        natural.add(new DestinationsModel("Jamshedpur", R.drawable.city1));
        natural.add(new DestinationsModel("Jamshedpur", R.drawable.city1));
        natural.add(new DestinationsModel("Jamshedpur", R.drawable.city1));
        natural.add(new DestinationsModel("Jamshedpur", R.drawable.city1));

        naturalAdapter = new NaturalAdapter(getContext(), natural);
        idNaturalRecycler.setAdapter(naturalAdapter);


        return rootView;
    }


}
