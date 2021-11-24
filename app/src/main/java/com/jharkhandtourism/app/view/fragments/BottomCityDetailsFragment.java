package com.jharkhandtourism.app.view.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;
import com.jharkhandtourism.app.R;
import com.jharkhandtourism.app.model.DestinationsModel;
import com.jharkhandtourism.app.model.SearchRVModel;
import com.jharkhandtourism.app.view.adapter.ItemsListAdapter;
import com.jharkhandtourism.app.view.adapter.SearchResultPagerAdapter;

import java.util.ArrayList;
import java.util.Locale;

public class BottomCityDetailsFragment extends BottomSheetDialogFragment {

    Context context;
    String placeName;
    TextView tv_place;

    RecyclerView rv_natural, rv_religious, rv_hotels, rv_touristAttractions;
    ItemsListAdapter natureAdapter, religiousAdapter, hotelsAdapter, placesAdapter;

    ArrayList<DestinationsModel> naturalSites = new ArrayList<>();
    ArrayList<DestinationsModel> religiousSites = new ArrayList<>();
    ArrayList<DestinationsModel> hotels = new ArrayList<>();
    ArrayList<DestinationsModel> touristSites = new ArrayList<>();

    public BottomCityDetailsFragment(Context context, String placeName) {
        this.context = context;
        this.placeName = placeName;
    }

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_city_detail, container, false);

        rv_natural = rootView.findViewById(R.id.rv_natural);
        rv_religious = rootView.findViewById(R.id.rv_religious);
        rv_hotels = rootView.findViewById(R.id.rv_hotels);
        rv_touristAttractions = rootView.findViewById(R.id.rv_touristAttractions);

        rv_natural.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        rv_religious.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        rv_hotels.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        rv_touristAttractions.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        tv_place = rootView.findViewById(R.id.tv_place);
        tv_place.setText(""+placeName);


        naturalSites.add(new DestinationsModel("Dassam Falls", R.drawable.city1));
        naturalSites.add(new DestinationsModel("Dassam Falls", R.drawable.city1));
        naturalSites.add(new DestinationsModel("Dassam Falls", R.drawable.city1));
        naturalSites.add(new DestinationsModel("Dassam Falls", R.drawable.city1));
        naturalSites.add(new DestinationsModel("Dassam Falls", R.drawable.city1));
        naturalSites.add(new DestinationsModel("Dassam Falls", R.drawable.city1));
        natureAdapter = new ItemsListAdapter(context, naturalSites);

        religiousSites.add(new DestinationsModel("Pahari Mandir", R.drawable.pahari_mandir_ranchi));
        religiousSites.add(new DestinationsModel("Pahari Mandir", R.drawable.pahari_mandir_ranchi));
        religiousSites.add(new DestinationsModel("Pahari Mandir", R.drawable.pahari_mandir_ranchi));
        religiousSites.add(new DestinationsModel("Pahari Mandir", R.drawable.pahari_mandir_ranchi));
        religiousSites.add(new DestinationsModel("Pahari Mandir", R.drawable.pahari_mandir_ranchi));
        religiousSites.add(new DestinationsModel("Pahari Mandir", R.drawable.pahari_mandir_ranchi));
        religiousSites.add(new DestinationsModel("Pahari Mandir", R.drawable.pahari_mandir_ranchi));
        religiousAdapter = new ItemsListAdapter(context, religiousSites);

        touristSites.add(new DestinationsModel("Tagore Hill", R.drawable.tagore_hill));
        touristSites.add(new DestinationsModel("Tagore Hill", R.drawable.tagore_hill));
        touristSites.add(new DestinationsModel("Tagore Hill", R.drawable.tagore_hill));
        touristSites.add(new DestinationsModel("Tagore Hill", R.drawable.tagore_hill));
        touristSites.add(new DestinationsModel("Tagore Hill", R.drawable.tagore_hill));
        touristSites.add(new DestinationsModel("Tagore Hill", R.drawable.tagore_hill));
        touristSites.add(new DestinationsModel("Tagore Hill", R.drawable.tagore_hill));
        placesAdapter = new ItemsListAdapter(context, touristSites);

        hotels.add(new DestinationsModel("Radisson Blu Hotel", R.drawable.hotels));
        hotels.add(new DestinationsModel("Radisson Blu Hotel", R.drawable.hotels));
        hotels.add(new DestinationsModel("Radisson Blu Hotel", R.drawable.hotels));
        hotels.add(new DestinationsModel("Radisson Blu Hotel", R.drawable.hotels));
        hotels.add(new DestinationsModel("Radisson Blu Hotel", R.drawable.hotels));
        hotels.add(new DestinationsModel("Radisson Blu Hotel", R.drawable.hotels));
        hotels.add(new DestinationsModel("Radisson Blu Hotel", R.drawable.hotels));
        hotelsAdapter = new ItemsListAdapter(context, hotels);

        rv_hotels.setAdapter(hotelsAdapter);
        rv_touristAttractions.setAdapter(placesAdapter);
        rv_natural.setAdapter(natureAdapter);
        rv_religious.setAdapter(religiousAdapter);

//        idRVSearchResults = rootView.findViewById(R.id.idRVSearchResults);
//        idRVSearchResults.setLayoutManager(new LinearLayoutManager(context));
//        searchRVAdapter = new SearchRVAdapter(searchRVModalArrayList, context);
//        idRVSearchResults.setAdapter(searchRVAdapter);


        rootView.findViewById(R.id.imageMap).setOnClickListener(v -> {
            openMap(placeName);
        });


        return rootView;
    }

    private void openMap(String placeName) {
        String uri = String.format(Locale.ENGLISH, "geo:0,0?q="+placeName);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        context.startActivity(intent);
    }

    @NonNull
    @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(dialogInterface -> {
            BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
            setupFullHeight(bottomSheetDialog);
        });
        return  dialog;
    }


    private void setupFullHeight(BottomSheetDialog bottomSheetDialog) {
        FrameLayout bottomSheet = bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();

//        int windowHeight = getWindowHeight()-150;
//        if (layoutParams != null) {
//            layoutParams.height = windowHeight;
//        }
        bottomSheet.setLayoutParams(layoutParams);
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

}
