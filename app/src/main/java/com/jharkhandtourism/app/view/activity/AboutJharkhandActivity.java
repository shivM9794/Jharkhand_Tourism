package com.jharkhandtourism.app.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jharkhandtourism.app.R;

public class AboutJharkhandActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_jharkhand);

        findViewById(R.id.image_back).setOnClickListener(v -> {
            finish();
        });


        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient(){
            ProgressDialog progressDialog=null;

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                if(progressDialog==null){
                    progressDialog = new ProgressDialog(AboutJharkhandActivity.this);
                    progressDialog.setMessage("");
                    progressDialog.show();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                try{
                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                }catch(Exception e){

                }

            }
        });


        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://en.wikipedia.org/wiki/Jharkhand");


    }
}