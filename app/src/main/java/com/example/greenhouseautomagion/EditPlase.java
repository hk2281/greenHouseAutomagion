package com.example.greenhouseautomagion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditPlase extends AppCompatActivity {

    private String placeName;
    private ArrayList<Integer> activeDays = new ArrayList<Integer>();
    private String startTime;
    private String id;
    private Boolean ventilation,blinding;
    private Integer watterVolume;

    private TextView placeNameView;
    private TextView timeView;
    private Button save_btn, remove_btn;

    private SeekBar seekBar;
    private Integer seekBarVal = 0;

    private Switch blindsSw,ventilationSw;

    private CheckBox checkBox_M, checkBox_T, checkBox_W,
            checkBox_Th, checkBox_F,checkBox_S,checkBox_St;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideNavigationButtons();
        setContentView(R.layout.activity_edit_plase);

        placeNameView = findViewById(R.id.Editplace_name);
        timeView = findViewById(R.id.Editp_lace_name1);

        checkBox_M = findViewById(R.id.EditcheckBox_M);
        checkBox_T = findViewById(R.id.EditcheckBox_T);
        checkBox_W = findViewById(R.id.EditcheckBox_W);
        checkBox_Th = findViewById(R.id.EditcheckBox_Th);
        checkBox_F = findViewById(R.id.EditcheckBox_F);
        checkBox_S = findViewById(R.id.EditcheckBox_S);
        checkBox_St = findViewById(R.id.EditcheckBox_St);

        seekBar = findViewById(R.id.EditseekBar);
        blindsSw = findViewById(R.id.Editswitch1);
        ventilationSw = findViewById(R.id.Editswitch2);

        save_btn = findViewById(R.id.Edittest_btn);
        remove_btn = findViewById(R.id.remove_btn);





        Intent intent = getIntent();
        if (intent != null) {
            placeName = intent.getStringExtra("PlaceName");
            startTime = intent.getStringExtra("startTime");
            id = intent.getStringExtra("id");
            ventilation = intent.getBooleanExtra("ventilation",false);
            blinding = intent.getBooleanExtra("blinding",false);
            watterVolume = intent.getIntExtra("watterVolume",0);
            activeDays = intent.getIntegerArrayListExtra("activeDays");
            seekBarVal = watterVolume;
            Log.d("fa",checkBox_S.getText().toString());

        }

        placeNameView.setText(placeName);
        timeView.setText(startTime);
        seekBar.setProgress(watterVolume);
        etCheatboxes(activeDays);

        blindsSw.setChecked(blinding);
        ventilationSw.setChecked(ventilation);
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

            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper db = new DbHelper();
                db.updateDoc(id,
                        new Place(placeNameView.getText().toString(),
                                timeView.getText().toString(),
                                countMarkedCheckboxes(),
                                blindsSw.isChecked(),
                                ventilationSw.isChecked(),
                                seekBarVal));
                Toast.makeText(view.getContext(), "saved", Toast.LENGTH_SHORT).show();
                to_main();
            }
        });

        remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper db = new DbHelper();
                db.deleteDoc(id);
                Toast.makeText(view.getContext(), "removed", Toast.LENGTH_SHORT).show();
                to_main();
            }
        });


    }

    private void hideNavigationButtons() {
        View decorView = getWindow().getDecorView();

        // Скрыть навигационные кнопки и установить флаг IMMERSIVE_STICKY
        int flags = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(flags);
    }

    public ArrayList<Integer> countMarkedCheckboxes() {
        ArrayList<Boolean> CheckBList = new ArrayList<Boolean>();
        CheckBList.add(checkBox_M.isChecked());
        CheckBList.add(checkBox_T.isChecked());
        CheckBList.add(checkBox_W.isChecked());
        CheckBList.add(checkBox_Th.isChecked());
        CheckBList.add(checkBox_F.isChecked());
        CheckBList.add(checkBox_S.isChecked());
        CheckBList.add(checkBox_St.isChecked());

        ArrayList<Integer> markedCheckboxes = new ArrayList<>();

        for (int i = 0; i < CheckBList.size(); i++) {
            if (CheckBList.get(i)) {
                markedCheckboxes.add(i);
            }
        }
        Log.d("fa", markedCheckboxes.toString());
        return markedCheckboxes;
    }

    public void to_main(){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }

    private void etCheatboxes(ArrayList<Integer> chekedBoxes){
        for (int position: chekedBoxes) {
            switch (position) {
                case 0:
                    checkBox_M.setChecked(true);
                    break;
                case 1:
                    checkBox_T.setChecked(true);
                    break;
                case 2:
                    checkBox_W.setChecked(true);
                    break;
                case 3:
                    checkBox_Th.setChecked(true);
                    break;
                case 4:
                    checkBox_F.setChecked(true);
                    break;
                case 5:
                    checkBox_S.setChecked(true);
                    break;
                case 6:
                    checkBox_St.setChecked(true);
                    break;
                default:
                    break;
            }
        }
    }
}