package com.example.greenhouseautomagion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText login, pass;
    private Button login_btn;

    private String valid_login = "root";
    private String  valid_pass = "toor";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        login_btn = findViewById(R.id.login_btn);
        login = findViewById(R.id.editTextTextEmailAddress);
        pass = findViewById(R.id.editTextTextPassword);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enter_login = login.getText().toString();
                String enter_pass = pass.getText().toString();
//                Log.d("pass",enter_pass);
                Log.d("pass",enter_login);
                if (valid_login.equals(enter_login) && valid_pass.equals(enter_pass)){
                    to_main();
                }else {
                    Toast.makeText(view.getContext(), "invalid data", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    public void to_main(){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }
}