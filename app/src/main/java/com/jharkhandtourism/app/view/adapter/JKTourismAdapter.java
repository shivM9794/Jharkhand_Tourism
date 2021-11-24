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

import java.util.ArrayList;

public class JKTourismAdapter extends RecyclerView.Adapter<JKTourismAdapter.ViewHolder> {

    Context context;

    ArrayList<DestinationsModel> jkTourism = new ArrayList<>();

    public JKTourismAdapter(Context context, ArrayList<DestinationsModel> jkTourism) {
        this.context = context;
        this.jkTourism = jkTourism;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jk_tour_hotels, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DestinationsModel item = jkTourism.get(position);

    }

    @Override
    public int getItemCount() {
        return jkTourism.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tv_name;
        LinearLayout clickable_ll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tv_name = itemView.findViewById(R.id.tv_name);
            clickable_ll = itemView.findViewById(R.id.clickable_ll);
        }
    }
}
