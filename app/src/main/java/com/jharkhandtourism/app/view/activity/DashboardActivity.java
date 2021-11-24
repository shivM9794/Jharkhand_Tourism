package com.jharkhandtourism.app.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.jharkhandtourism.app.R;
import com.jharkhandtourism.app.view.fragments.HomeFragment;
import com.jharkhandtourism.app.view.fragments.NavDrawerFragment;
import com.jharkhandtourism.app.view.fragments.NearByFragment;
import com.jharkhandtourism.app.view.fragments.PlanFragment;
import com.jharkhandtourism.app.view.fragments.ProfileFragment;
import com.jharkhandtourism.app.view.interfaces.DrawerInterface;

import java.util.Objects;

public class DashboardActivity extends AppCompatActivity implements NavDrawerFragment.NavigationDrawerCallbacks, DrawerInterface {

    DrawerLayout drawerLayout;
    TabLayout tabLayout;
    String[] navLabels;

    TextView main_heading;
    ImageView main_logo;

    ProfileFragment profileFragment;
    PlanFragment planFragment;
    HomeFragment homeFragment;
    NearByFragment nearByFragment;

    String currentTitle="";
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        navLabels = new String[]{
                "Home",
                "Scan",
                "Plan",
                "Near By",
                "Profile",
                "AR",
                "Language Translator"
//                "Alerts"
        };

        searchView = findViewById(R.id.searchView);
        searchll = findViewById(R.id.search_ll);
        main_logo = findViewById(R.id.main_logo);
        main_heading = findViewById(R.id.main_heading);
        drawerLayout = findViewById(R.id.drawer_layout);
        tabLayout = findViewById(R.id.tablayout);


        fragmentManager = getSupportFragmentManager();

        fragmentManager.registerFragmentLifecycleCallbacks(new FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentViewCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull View v, @Nullable Bundle savedInstanceState) {
                super.onFragmentViewCreated(fm, f, v, savedInstanceState);
                setTitle();
            }
        }, false);

        setUpTabs(tabLayout);

        findViewById(R.id.drawer_icon).setOnClickListener(v -> {
            openDrawer();
        });

        findViewById(R.id.search_ll).setOnClickListener(v -> {
        });

        findViewById(R.id.search_icon).setOnClickListener(v -> {
            search();
        });


    }
    boolean isSearchVisible=false;
    SearchView searchView;
    LinearLayout searchll;

    public void hideSearch() {
        isSearchVisible=false;
        searchll.setVisibility(View.GONE);
        searchll.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_out_left));
    }

    public void search() {
        isSearchVisible=true;

        searchll.setVisibility(View.VISIBLE);
        searchll.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_in_up_fade_in));

        searchView.setIconified(false);
        searchView.requestFocus();
        searchView.requestFocusFromTouch();
        searchView.setQuery("", false);

    }

    private void setTitle(){
        if(currentTitle==null||currentTitle.isEmpty()){
            main_logo.setVisibility(View.VISIBLE);
            main_heading.setVisibility(View.INVISIBLE);
        }else{
            main_logo.setVisibility(View.INVISIBLE);
            main_heading.setVisibility(View.VISIBLE);
            main_heading.setText(currentTitle);
        }
    }

    private void setUpTabs(TabLayout tabLayout) {
        int[] iconIds = new int[6];
        iconIds[0] = R.drawable.ic_home;
        iconIds[1] = R.drawable.ic_scanner;
        iconIds[2] = R.drawable.ic_roadmap;
        iconIds[3] = R.drawable.ic_address;
        iconIds[4] = R.drawable.ic_profile;
        iconIds[5] = R.drawable.ic_image;

//        tabLayout.addTab(tabLayout.newTab().setIcon(iconIds[0]));
//        tabLayout.addTab(tabLayout.newTab().setIcon(iconIds[1]));
//        tabLayout.addTab(tabLayout.newTab().setIcon(iconIds[2]));
//        tabLayout.addTab(tabLayout.newTab().setIcon(iconIds[3]));
//        tabLayout.addTab(tabLayout.newTab().setIcon(iconIds[4]));


        for(int i=0;i<=5;i++){

            LinearLayout tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.nav_tab, null);

            TextView tab_label = (TextView) tab.findViewById(R.id.nav_label);
            ImageView tab_icon = (ImageView) tab.findViewById(R.id.nav_icon);



//            if(i == 0) {
//                tab_label.setText(navLabels[i]);
//                tab_label.setTextColor(getResources().getColor(R.color.tab_heading));
//                tab_icon.setImageResource(iconIds_selected[i]);
//            } else {
            tab_label.setText(navLabels[i]);

            tab_icon.setImageResource(iconIds[i]);

            if(i==0){
                tab_label.setTextColor(getColor(R.color.color_primary_on_primary));
                tab_icon.setColorFilter(getColor(R.color.color_primary_on_primary));
            }else {
                tab_label.setTextColor(getColor(R.color.bottom_bar_text));
                tab_icon.setColorFilter(getColor(R.color.bottom_bar_text));
            }
//            }

            tabLayout.addTab(tabLayout.newTab().setCustomView(tab));
//            tabLayout.getTabAt(i).setCustomView(tab);

            Log.e(">>>>>> ", ""+i);

        }

        tabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {

                        switch (tab.getPosition()){
                            case 1:
                                scanImage(tabLayout);
                                break;
                            case 2:
                                plan(tabLayout);
                                break;
                            case 3:
                                nearBy(tabLayout);
                                break;
                            case 4:
                                profile(tabLayout);
                                break;
                            case 5:
                                ar(tabLayout);
                                break;
                            case 6:
                                languageTranslator(tabLayout);
                                break;
                            default:
                                home(tabLayout);
                                break;
                        }

                        View tabView = tab.getCustomView();

                        TextView tab_label = (TextView) tabView.findViewById(R.id.nav_label);
                        ImageView tab_icon = (ImageView) tabView.findViewById(R.id.nav_icon);

                        tab_label.setTextColor(getColor(R.color.color_primary_on_primary));
                        tab_icon.setColorFilter(getColor(R.color.color_primary_on_primary));
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        View tabView = tab.getCustomView();

                        TextView tab_label = (TextView) tabView.findViewById(R.id.nav_label);
                        ImageView tab_icon = (ImageView) tabView.findViewById(R.id.nav_icon);

                        tab_label.setTextColor(getColor(R.color.bottom_bar_text));
                        tab_icon.setColorFilter(getColor(R.color.bottom_bar_text));
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                }
        );


        animateDrawer();

        home(tabLayout);

    }


    @Override
    protected void onResume() {
        super.onResume();
        Objects.requireNonNull(tabLayout.getTabAt(0)).select();
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(findViewById(R.id.navigation_drawer))){
            closeDrawer();
            return;
        }

        if(isSearchVisible){
            hideSearch();
            return;
        }

        super.onBackPressed();

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }

    @Override
    public void home(View view) {
        currentTitle ="";
//        if(homeFragment==null)
            homeFragment = new HomeFragment(DashboardActivity.this);

        fragmentManager.beginTransaction().replace(R.id.content, homeFragment).commit();

        Objects.requireNonNull(tabLayout.getTabAt(0)).select();
        closeDrawer();



    }

    @Override
    public void scanImage(View view) {
        currentTitle="";
        startActivity(new Intent(this, ImageScanningActivity.class));
//        Objects.requireNonNull(tabLayout.getTabAt(1)).select();
        closeDrawer();

    }

    @Override
    public void plan(View view) {
        currentTitle = "Plan Your Trip";
//        if(planFragment==null)
            planFragment = new PlanFragment(DashboardActivity.this);
//        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, planFragment).commit();
        Objects.requireNonNull(tabLayout.getTabAt(2)).select();
        closeDrawer();
    }

    @Override
    public void nearBy(View view) {
        currentTitle = "Near By";
//        if(nearByFragment==null)
            nearByFragment = new NearByFragment(DashboardActivity.this);
//        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, nearByFragment).commit();
        Objects.requireNonNull(tabLayout.getTabAt(3)).select();
        closeDrawer();
    }

    @Override
    public void profile(View view) {
        currentTitle = "Profile";
//        if(profileFragment==null)
            profileFragment = new ProfileFragment(DashboardActivity.this);
        fragmentManager.beginTransaction().replace(R.id.content, profileFragment).commit();
        Objects.requireNonNull(tabLayout.getTabAt(4)).select();
        closeDrawer();
    }

    @Override
    public void ar(View view) {
        currentTitle = "AR";
        startActivity(new Intent(this, ARActivity.class));
        closeDrawer();

    }

    @Override
    public void languageTranslator(View view) {
        currentTitle = "Language Translator";
        startActivity(new Intent(this, LanguagrTranslatorActivity.class));
        closeDrawer();

    }

    @Override
    public void logOut(View view) {
        Toast.makeText(this, "logOut", Toast.LENGTH_SHORT).show();
    }



    private void closeDrawer(){
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void openDrawer(){
        drawerLayout.openDrawer(GravityCompat.START);
    }


    final float END_SCALE = 0.8f;

    private void animateDrawer() {

        LinearLayout contentView = findViewById(R.id.content_ll);

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }


}