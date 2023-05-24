package com.example.greenhouseautomagion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

public class ListAddedPlaces extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context context;
    private ArrayList<Place> placesList = new ArrayList<Place>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideNavigationButtons();

        setContentView(R.layout.activity_list_added_places);

        context = this.getApplicationContext();

//        placesList.add(new Place("gogs", "12:30",123));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//        mAdapter = new PlaceAdapter(context,placesList);
//
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);



        DbHelper db = new DbHelper();
        db.getPlace(new DbHelper.MyCallback() {
            @Override
            public void onRecive(ArrayList<Place> places) {
                Log.d("fa", "in calback");
                mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                mAdapter = new PlaceAdapter(context,places);

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);

            }
        });
    }

    private void hideNavigationButtons() {
        View decorView = getWindow().getDecorView();

        // Скрыть навигационные кнопки и установить флаг IMMERSIVE_STICKY
        int flags = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(flags);
    }
}