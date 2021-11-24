package com.jharkhandtourism.app.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jharkhandtourism.app.R;
import com.jharkhandtourism.app.model.DestinationsModel;

import java.util.ArrayList;

public class ReligiousAdapter extends RecyclerView.Adapter<ReligiousAdapter.ViewHolder> {

    Context context;
    ArrayList<DestinationsModel> religious = new ArrayList<>();

    public ReligiousAdapter(Context context, ArrayList<DestinationsModel> religious) {
        this.context = context;
        this.religious = religious;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DestinationsModel item = religious.get(position);

        holder.imageView.setImageResource(item.getImage());
        holder.tv_name.setText(item.getName());

    }

    @Override
    public int getItemCount() {
        return religious.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tv_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
