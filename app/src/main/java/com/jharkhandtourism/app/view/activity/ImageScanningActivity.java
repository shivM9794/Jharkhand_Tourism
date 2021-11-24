package com.jharkhandtourism.app.view.activity;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.mlkit.vision.common.InputImage;
//import com.google.mlkit.vision.label.ImageLabeler;
//import com.google.mlkit.vision.label.ImageLabel;
//import com.google.mlkit.vision.label.ImageLabeling;
//import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;
import com.jharkhandtourism.app.R;
import com.jharkhandtourism.app.model.SearchRVModel;
import com.jharkhandtourism.app.view.adapter.SearchRVAdapter;
import com.jharkhandtourism.app.view.fragments.BottomSearchResultFragment;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImageScanningActivity extends AppCompatActivity {

    ImageView captureIV;
    Button snapBtn,getSearchResultsBtn;

    ArrayList<SearchRVModel> searchRVModalArrayList;
    int REQUEST_CODE = 1;
    ProgressBar loadingPB;
    Bitmap imageBitmap;
    String title,link,displayedLink,snippet;

    int images[] = new int[]{
            R.drawable.baidyanath_jyotirlinga_temple,
            R.drawable.betla_national_park,
            R.drawable.pahari_mandir_ranchi
            };

    String places[] = new String[]{
            "Baidyanath Jyotirlinga Temple",
            "Betla National Park",
            "Pahari Mandir, Ranchi"
    };

    Random random = new Random();
    int currentSelection = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_scanning);
        captureIV = findViewById(R.id.image);
        snapBtn = findViewById(R.id.idBtnSnap);
        getSearchResultsBtn = findViewById(R.id.idBtnResults);
        loadingPB = findViewById(R.id.idPBLoading);
//        resultsRv = findViewById(R.id.idRVSearchResults);
        searchRVModalArrayList = new ArrayList<>();
//        searchRVAdapter = new SearchRVAdapter(searchRVModalArrayList, this);
//        resultsRv.setLayoutManager(new LinearLayoutManager(ImageScanningActivity.this));
//        resultsRv.setAdapter(searchRVAdapter);

        snapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchRVModalArrayList.clear();
//                searchRVAdapter.notifyDataSetChanged();
                takePictureIntent();

            }
        });

        getSearchResultsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchRVModalArrayList.clear();
//                searchRVAdapter.notifyDataSetChanged();
                loadingPB.setVisibility(View.VISIBLE);
                getResults();

            }
        });
    }

    private void getResults(){
        getSearchResults("searchQuery");


//        InputImage image = InputImage.fromBitmap(imageBitmap, 0);
//        ImageLabeler labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS);
//
//        labeler.process(image).addOnSuccessListener(new OnSuccessListener<List<ImageLabel>>() {
//            @Override
//            public void onSuccess(List<ImageLabel> firebaseVisionImageLabels) {
//                if(firebaseVisionImageLabels!=null && !firebaseVisionImageLabels.isEmpty()) {
//                    for(ImageLabel imageLabel: firebaseVisionImageLabels){
//                        Log.e("ImageLabels", ">>>>>  "+imageLabel.getText());
//                    }
//                    String searchQuery = firebaseVisionImageLabels.get(0).getText();
//                    getSearchResults(searchQuery);
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(ImageScanningActivity.this, "Failed to detect image...", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE && resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
//            captureIV.setImageBitmap(imageBitmap);

            //
            currentSelection = random.nextInt(3);
            captureIV.setImageResource(images[currentSelection]);

            searchRVModalArrayList.clear();
//            searchRVAdapter.notifyDataSetChanged();
            loadingPB.setVisibility(View.VISIBLE);
            getResults();

        }
    }

    private void getSearchResults(String searchQuery){

        searchQuery = places[currentSelection];

        String url = "https://serpapi.com/search.json?q=" + searchQuery + "&location=Delhi,India&hl=en&gl=us&google_domain=google.com&api_key=3fd6acf9cc75dbbe79c3d38e4925213e119fe00c61fd95a126f325131eab1af6";
        RequestQueue queue = Volley.newRequestQueue(ImageScanningActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loadingPB.setVisibility(View.GONE);
                try {

                    JSONArray organicArray = response.getJSONArray("organic_results");
                    for (int i= 0;i<organicArray.length();i++){
                        JSONObject organicObj = organicArray.getJSONObject(i);
                        if (organicObj.has("title")){
                            title = organicObj.getString("title");
                        }
                        if (organicObj.has("link")){
                            link = organicObj.getString("link");
                        }
                        if (organicObj.has("displayed_link")){
                            displayedLink = organicObj.getString("displayed_link");
                        }
                        if (organicObj.has("snippet")){
                            snippet = organicObj.getString("snippet");
                        }
                        searchRVModalArrayList.add(new SearchRVModel(title,link,displayedLink,snippet));
                    }

                    BottomSearchResultFragment fragment = new BottomSearchResultFragment(ImageScanningActivity.this, searchRVModalArrayList, places[currentSelection]);
                    fragment.show(getSupportFragmentManager(), fragment.getTag());

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ImageScanningActivity.this, "Failed to get Search results...", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);

    }

//    private void takePictureIntent() {
//
//        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (i.resolveActivity(getPackageManager())!=null){
//            startActivityForResult(i,REQUEST_CODE);
//
//        }
//    }

    private void takePictureIntent() {

        if(checkPermission()) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(i, REQUEST_CODE);

            }
        }
    }

    private static final int PERMISSION_REQ_CODE = 1004;
    private String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private boolean checkPermission() {
        boolean granted = true;
        for (String per : PERMISSIONS) {
            if (!permissionGranted(per)) {
                granted = false;
                break;
            }
        }

        if (granted) {
        } else {
            requestPermissions();
        }

        return granted;
    }

    private boolean permissionGranted(String permission) {
        return ContextCompat.checkSelfPermission(
                this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQ_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQ_CODE) {
            boolean granted = true;
            for (int result : grantResults) {
                granted = (result == PackageManager.PERMISSION_GRANTED);
                if (!granted) break;
            }

            if (granted) {
            } else {
                toastNeedPermissions();
            }
        }
    }

    private void toastNeedPermissions() {
        Toast.makeText(this, "Some features may not work, if permission is denied!", Toast.LENGTH_SHORT).show();
    }
}