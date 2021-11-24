package com.jharkhandtourism.app.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jharkhandtourism.app.R;
import com.jharkhandtourism.app.model.DestinationsModel;
import com.jharkhandtourism.app.model.TourModel;

import java.util.ArrayList;

public class ToursAdapter extends RecyclerView.Adapter<ToursAdapter.ViewHolder> {


    Context context;

    ArrayList<TourModel> tours = new ArrayList<>();

    public ToursAdapter(Context context, ArrayList<TourModel> tours) {
        this.context = context;
        this.tours=tours;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TourModel item = tours.get(position);

        holder.imageView.setImageResource(item.getImage());
        holder.tv_name.setText(item.getName());
        holder.tv_category.setText(item.getCategory());
        holder.tv_price.setText(item.getPrice());

    }

    @Override
    public int getItemCount() {
        return tours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tv_name;
        TextView tv_category;
        TextView tv_price;
        LinearLayout clickable_ll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_category = itemView.findViewById(R.id.tv_category);
            tv_price = itemView.findViewById(R.id.tv_price);
            clickable_ll = itemView.findViewById(R.id.clickable_ll);

        }
    }
}
