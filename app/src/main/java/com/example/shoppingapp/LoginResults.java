package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by NathanG on 12/8/2017.
 */

public class LoginResults extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_results);
        //create object refering to text view area.
        TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
        //grab Extra from other activity if the specific key was an extra passed to this activity.
        if (getIntent().hasExtra("com.example.shoppingapp.loginResult")) {
            String result = getIntent().getExtras().getString("com.example.shoppingapp.loginResult");
            resultTextView.setText(result);
        }
        //Return button setup
        Button returnBtn = (Button) findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
