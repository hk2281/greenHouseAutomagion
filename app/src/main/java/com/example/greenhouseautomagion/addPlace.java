package com.example.greenhouseautomagion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.type.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class addPlace extends AppCompatActivity {

    private TextView placeNameView;
    private TextView timeView;
    private Button testBtn;

    private SeekBar seekBar;
    private Integer seekBarVal = 0;
    private String watterTime;
    private String placeName;



    private Switch blindsSw,ventilationSw;

    private Boolean blindValue, ventilationValue;

    private CheckBox checkBox_M, checkBox_T, checkBox_W,
            checkBox_Th, checkBox_F,checkBox_S,checkBox_St;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideNavigationButtons();
        setContentView(R.layout.activity_add_place);


        timeView = (EditText) findViewById(R.id.place_name1);
        testBtn = findViewById(R.id.test_btn);
        placeNameView = findViewById(R.id.place_name);
        blindsSw = findViewById(R.id.switch1);
        ventilationSw = findViewById(R.id.switch2);


        ImageView imageView = findViewById(R.id.imageView4); // Замените `my_imageview` на ID вашего ImageView
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
        imageView.startAnimation(animation);


        seekBar = findViewById(R.id.seekBar);
        seekBar.setProgress(0);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBarVal = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                Log.d("fa",String.valueOf(seekBar));
            }
        });


        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeName = placeNameView.getText().toString();
                watterTime = timeView.getText().toString();
                Log.d("fa", placeName);
                Log.d("fa", watterTime);
                blindValue = blindsSw.isChecked();
                ventilationValue = ventilationSw.isChecked();
                Log.d("fa", String.valueOf(ventilationValue));
                Log.d("fa", String.valueOf(blindValue));
                countMarkedCheckboxes();




                sendPlaceObjToDb(new Place(placeName,
                        watterTime,countMarkedCheckboxes(),blindValue,
                        ventilationValue, seekBarVal,Timestamp.now()
                        ));
                Toast.makeText(view.getContext(), "created", Toast.LENGTH_SHORT).show();
                to_main();

            }
        });


    }


        public ArrayList<Integer> countMarkedCheckboxes() {
            ArrayList<Boolean> CheckBList = new ArrayList<Boolean>();
            CheckBList.add(((CheckBox)findViewById(R.id.checkBox_M)).isChecked());
            CheckBList.add(((CheckBox)findViewById(R.id.checkBox_T)).isChecked());
            CheckBList.add(((CheckBox)findViewById(R.id.checkBox_W)).isChecked());
            CheckBList.add(((CheckBox)findViewById(R.id.checkBox_Th)).isChecked());
            CheckBList.add(((CheckBox)findViewById(R.id.checkBox_F)).isChecked());
            CheckBList.add(((CheckBox)findViewById(R.id.checkBox_S)).isChecked());
            CheckBList.add(((CheckBox)findViewById(R.id.checkBox_St)).isChecked());

            ArrayList<Integer> markedCheckboxes = new ArrayList<>();

            for (int i = 0; i < CheckBList.size(); i++) {
                if (CheckBList.get(i)) {
                    markedCheckboxes.add(i);
                }
            }
            Log.d("fa", markedCheckboxes.toString());
            return markedCheckboxes;
        }

        public void sendPlaceObjToDb(Place currentPlace){
            DbHelper dbHelper = new DbHelper();
            dbHelper.postToDb(currentPlace);
        }

    private void hideNavigationButtons() {
        View decorView = getWindow().getDecorView();

        // Скрыть навигационные кнопки и установить флаг IMMERSIVE_STICKY
        int flags = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(flags);
    }

    public void to_main(){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }
}