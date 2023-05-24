package com.example.greenhouseautomagion;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PlaceAdapter  extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>{

    private LayoutInflater inflater;

    private Context context;
    private ArrayList<Place> places;

    public  PlaceAdapter(Context context, ArrayList<Place> places)  {
        this.places = places;
        this.context = context;
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder {
        public TextView mNameView;
        public TextView waterVolume;

        public TextView textViewTime;



        public PlaceViewHolder(View itemView) {
            super(itemView);
            mNameView = itemView.findViewById(R.id.textViewName);
            waterVolume = itemView.findViewById(R.id.textViewMilliliters);
            textViewTime = itemView.findViewById(R.id.textViewTime);
        }
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        PlaceViewHolder svh = new PlaceViewHolder(v);
        return svh;
    }
    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        Place currentItem = places.get(position);

        holder.mNameView.setText(currentItem.getPlaceName());
        holder.waterVolume.setText(String.valueOf(currentItem.getWatterVolume()));
        holder.textViewTime.setText(String.valueOf((currentItem.getStartTime())));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("fa",currentItem.getActiveDays().toString());
                Toast.makeText(view.getContext(), "to edit", Toast.LENGTH_SHORT).show();

                Context onClickContext = holder.itemView.getContext();

                Intent intent = new Intent(onClickContext, EditPlase.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // Pass any extra data to the new activity if needed
                intent.putExtra("PlaceName", currentItem.getPlaceName());
                intent.putIntegerArrayListExtra("activeDays", currentItem.getActiveDays());
                intent.putExtra("blinding", currentItem.isBlinding());
                intent.putExtra("ventilation",currentItem.isVentilation());
                intent.putExtra("startTime", currentItem.getStartTime());
                intent.putExtra("watterVolume",currentItem.getWatterVolume());
                intent.putExtra("id",currentItem.getId());

                // Start the new activity
                onClickContext.startActivity(intent);

            }
        });
    }
    @Override
    public int getItemCount() {
        return places.size();
    }



}
