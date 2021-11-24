package com.jharkhandtourism.app.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jharkhandtourism.app.R;
import com.jharkhandtourism.app.model.SearchRVModel;

import java.util.ArrayList;

public class SearchRVAdapter extends RecyclerView.Adapter<SearchRVAdapter.ViewHolder> {

    ArrayList<SearchRVModel> searchRVModals;
    Context context;

    public SearchRVAdapter(ArrayList<SearchRVModel> searchRVModals, Context context) {
        this.searchRVModals = searchRVModals;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchRVModel searchRVModal = searchRVModals.get(position);
        holder.titleTV.setText(searchRVModal.getTitle());
        holder.descTV.setText(searchRVModal.getSnippet());
        holder.snippetTV.setText(searchRVModal.getLink());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(searchRVModal.getLink()));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchRVModals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTV, descTV, snippetTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.idTVTitle);
            descTV = itemView.findViewById(R.id.idTVDescription);
            snippetTV = itemView.findViewById(R.id.idTVSnippet);
        }
    }
}
