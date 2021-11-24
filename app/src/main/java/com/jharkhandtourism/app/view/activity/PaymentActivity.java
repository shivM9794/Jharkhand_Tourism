package com.jharkhandtourism.app.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jharkhandtourism.app.R;
import com.jharkhandtourism.app.model.DestinationsModel;
import com.jharkhandtourism.app.view.adapter.Refer_EarnAdapter;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    RecyclerView idReferRecycler;
    Refer_EarnAdapter refer_earnAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        findViewById(R.id.image_back).setOnClickListener(v -> {
            finish();
        });

        idReferRecycler = findViewById(R.id.idReferRecycler);
        refer();
    }

    private void refer() {

        ArrayList<DestinationsModel> refer = new ArrayList<>();

        refer.add(new DestinationsModel("Refer & Earn", R.drawable.refer_earn));
        refer.add(new DestinationsModel("Refer & Earn", R.drawable.refer_earn));
        refer.add(new DestinationsModel("Refer & Earn", R.drawable.refer_earn));

        idReferRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        refer_earnAdapter = new Refer_EarnAdapter(getApplicationContext(), refer);
        idReferRecycler.setAdapter(refer_earnAdapter);
    }
}