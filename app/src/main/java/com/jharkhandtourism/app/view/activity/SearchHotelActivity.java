package com.jharkhandtourism.app.view.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jharkhandtourism.app.R;
import com.jharkhandtourism.app.model.DestinationsModel;
import com.jharkhandtourism.app.view.adapter.ItemsListAdapter;
import com.jharkhandtourism.app.view.adapter.JKTourismAdapter;

import java.util.ArrayList;
import java.util.Calendar;

public class SearchHotelActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button idBtnCheckIn, idBtnCheckOUT,idBtnSearchHotels;
    RecyclerView idJKTourismRecycler,idTouristRecycler;
    JKTourismAdapter jkTourismAdapter;
    ItemsListAdapter itemsListAdapter;


    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);
        idBtnCheckIn = findViewById(R.id.idBtnCheckIn);
        idBtnCheckOUT = findViewById(R.id.idBtnCheckOUT);
        idJKTourismRecycler = findViewById(R.id.idJKTourismRecycler);
        idTouristRecycler = findViewById(R.id.idTouristRecycler);



        findViewById(R.id.image_back).setOnClickListener(v -> {
            finish();
        });

        findViewById(R.id.idBtnCheckIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
                count = 1;
            }
        });
        idBtnCheckOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
                count = 2;
            }
        });

        idBtnSearchHotels = findViewById(R.id.idBtnSearchHotels);
        idBtnSearchHotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ShowHotelsActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|In2tent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        jkTourism();
        tourist();


    }

    private void tourist() {

        ArrayList<DestinationsModel> tourism = new ArrayList<>();

        tourism.add(new DestinationsModel("Pahari Mandir", R.drawable.pahari_mandir_ranchi));
        tourism.add(new DestinationsModel("Pahari Mandir", R.drawable.heritage_jk));
        tourism.add(new DestinationsModel("Pahari Mandir", R.drawable.city4));
        tourism.add(new DestinationsModel("Pahari Mandir", R.drawable.city5));

        idTouristRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        itemsListAdapter = new ItemsListAdapter(getApplicationContext(), tourism);
        idTouristRecycler.setAdapter(itemsListAdapter);

    }


    private void jkTourism() {

        ArrayList<DestinationsModel> jk_tourism = new ArrayList<>();

        jk_tourism.add(new DestinationsModel("Radisson Blu Hotel", R.drawable.hotels_jk));
        jk_tourism.add(new DestinationsModel("Radisson Blu Hotel", R.drawable.hotels_jk));
        jk_tourism.add(new DestinationsModel("Radisson Blu Hotel", R.drawable.hotels_jk));
        jk_tourism.add(new DestinationsModel("Radisson Blu Hotel", R.drawable.hotels_jk));

        idJKTourismRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        jkTourismAdapter = new JKTourismAdapter(getApplicationContext(), jk_tourism);
        idJKTourismRecycler.setAdapter(jkTourismAdapter);
    }

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

        if (count == 1) {
            String date = "CHECK-IN-DATE " + i + "/" + i1 + "/" + i2;
            idBtnCheckIn.setText(date);
        }

        if (count == 2) {
            String date1 = "CHECK-OUT-DATE " + i + "/" + i1 + "/" + i2;
            idBtnCheckOUT.setText(date1);
        }


    }
}
