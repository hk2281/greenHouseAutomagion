package com.example.greenhouseautomagion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {

    private Button add_btn;
    private Button show_scope_bth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_screen);

//        //Remove title bar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//
////Remove notification bar
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
////set content view AFTER ABOVE sequence (to avoid crash)
//        setContentView(R.layout.activity_main_screen);
        add_btn= findViewById(R.id.button);
        show_scope_bth = findViewById(R.id.myScope_btn);


        show_scope_bth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_tasks();
            }
        });
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_new_plase();
            }
        });
    }

    public void add_new_plase() {
        Intent intent = new Intent(this, addPlace.class);
        startActivity(intent);
    }
    public void show_tasks(){
        Intent intent = new Intent(this, ListAddedPlaces.class);
        startActivity(intent);
    }
}
