package com.jharkhandtourism.app.view.fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.jharkhandtourism.app.R;
import com.jharkhandtourism.app.model.DestinationsModel;
import com.jharkhandtourism.app.view.adapter.ItemsListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NearByFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, LocationListener {

    private static final String TAG = null;
    ImageView back;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    private GoogleMap googleMap;
    private Geocoder geocoder;
    private Button button;
    LocationManager locationManager;
    TextView tvLocality;
    RecyclerView idHotelsRec;

    RecyclerView rv_natural, rv_religious, rv_hotels, rv_touristAttractions;
    ItemsListAdapter natureAdapter, religiousAdapter, hotelsAdapter, placesAdapter;

    ArrayList<DestinationsModel> naturalSites = new ArrayList<>();
    ArrayList<DestinationsModel> religiousSites = new ArrayList<>();
    ArrayList<DestinationsModel> hotels = new ArrayList<>();
    ArrayList<DestinationsModel> touristSites = new ArrayList<>();

    Context context;
    public NearByFragment(Context context){
        this.context  = context;
    }

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_nearby, container, false);


        tvLocality = rootView.findViewById(R.id.tvLocality);
        rv_natural = rootView.findViewById(R.id.rv_natural);
        rv_religious = rootView.findViewById(R.id.rv_religious);
        rv_hotels = rootView.findViewById(R.id.rv_hotels);
        rv_touristAttractions = rootView.findViewById(R.id.rv_touristAttractions);

        rv_natural.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        rv_religious.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        rv_hotels.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        rv_touristAttractions.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

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


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        fetchLastLocation();

        tvLocality = rootView.findViewById(R.id.tvLocality);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationEnabled();
        getLocation();



        return rootView;
    }

    private void locationEnabled() {

        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!gps_enabled && !network_enabled) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Enable GPS Service")
                    .setMessage("We need your GPS location to show Near Places around you.")
                    .setCancelable(false)
                    .setPositiveButton("Enable", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                }
                            })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }

    private void getLocation() {

        try {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5, 5, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void fetchLastLocation() {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
//                    Toast.makeText(getActivity(), currentLocation.getLatitude()
//                            + "" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_own);
                    supportMapFragment.getMapAsync(NearByFragment.this);

                }
            }
        });
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {

        try {
            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            tvLocality.setText(addresses.get(0).getAddressLine(0));

        } catch (Exception e) {
        }

    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

        Log.d(TAG, "onMapLongClick: " + latLng.toString());

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                .draggable(true)
                .title("Current Location");

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 18);
        googleMap.animateCamera(cameraUpdate);
        googleMap.addMarker(markerOptions);
//        googleMap.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
        googleMap.setOnMapLongClickListener(this);

    }
}
