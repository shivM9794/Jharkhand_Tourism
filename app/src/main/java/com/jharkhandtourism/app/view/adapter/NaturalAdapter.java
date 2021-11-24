package com.jharkhandtourism.app.view.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.jharkhandtourism.app.view.activity.SearchHotelActivity;

import java.util.ArrayList;

public class NaturalAdapter extends RecyclerView.Adapter<NaturalAdapter.ViewHolder> {

    Context context;

    ArrayList<DestinationsModel> natural = new ArrayList<>();

    public NaturalAdapter(Context context, ArrayList<DestinationsModel> natural) {
        this.context = context;
        this.natural = natural;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DestinationsModel item = natural.get(position);

        holder.imageView.setImageResource(item.getImage());
        holder.tv_name.setText(item.getName());

        holder.clickable_ll.setOnClickListener(view -> {
            Intent intent = new Intent(context, SearchHotelActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return natural.size();
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
