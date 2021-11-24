package com.jharkhandtourism.app.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jharkhandtourism.app.R;
import com.jharkhandtourism.app.model.HotelModel;
import com.jharkhandtourism.app.view.activity.PaymentActivity;

import java.util.List;
import java.util.Locale;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private final List<HotelModel> hotelModels;
    Context context;

    public HotelAdapter(List<HotelModel> hotelModels, Context context) {
        this.hotelModels = hotelModels;
        this.context = context;

    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_hotel, parent, false);
        return new HotelAdapter.HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {

        holder.setHotel(hotelModels.get(position));
//        holder.imageMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String uri = String.format(Locale.ENGLISH, "geo:0,0?q=");
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//                context.startActivity(intent);
//            }
//
//        });
        holder.idBtnSnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, PaymentActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return hotelModels.size();
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder {

        TextView textName, textCategory, textPrice,idBtnSnap;
        ImageView imagePoster, imageMap;
        RatingBar ratingBar;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textName);
            textCategory = itemView.findViewById(R.id.textCategory);
            textPrice = itemView.findViewById(R.id.textPrice);
            imagePoster = itemView.findViewById(R.id.imagePoster);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageMap = itemView.findViewById(R.id.imageMap);
            idBtnSnap = itemView.findViewById(R.id.idBtnSnap);
        }

        void setHotel(HotelModel hotel) {
            imagePoster.setImageResource(hotel.poster);
            textName.setText(hotel.name_hotel);
            textCategory.setText(hotel.category);
            textPrice.setText(hotel.price);
            ratingBar.setRating(hotel.rating);

        }
    }
}
