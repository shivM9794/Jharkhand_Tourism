package com.jharkhandtourism.app.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.jharkhandtourism.app.R;

import java.util.ArrayList;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderViewHolder> {


    ArrayList<Integer> slider;
    Context context;

    public SliderAdapter(ArrayList<Integer> slider, Context context) {
        this.slider = slider;
        this.context = context;
    }

    @NonNull


    @Override
    public SliderAdapter.SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider, parent, false);
        SliderAdapter.SliderViewHolder viewHolder = new SliderAdapter.SliderViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.SliderViewHolder holder, int position) {

        Glide.with(context).load(slider.get(position)).into(holder.slider_img);

    }


    @Override
    public int getCount() {
        return slider.size();
    }

    public class SliderViewHolder extends ViewHolder {

        ImageView slider_img;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);

            slider_img = itemView.findViewById(R.id.slider);
        }
    }
}
