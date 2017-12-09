package com.example.shoppingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addBtn = (Button) findViewById(R.id.returnBtn);
        //The button name does not have to be the same as the button being reference but it helps to be uniform.
        //in the findViewById R stands for resources.  The code lets us search our resources for an element by it's ID.
        //We use the (Button) to cast the result if the findViewById as a button.

        //Now we'll create an onClick listener.  This will listen for any time the button specified is clicked to perform the action we request.
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//This is the onClick Event.  All of our code has to go in here for the click.
                //Here we create java objects for both the EditText fields using the EditText object type.  As before these java objects do not need to
                //be of the same name as the android ID, but it helps to be consistent.
                EditText userEditText = (EditText) findViewById(R.id.userEditText);
                EditText passEditText = (EditText) findViewById(R.id.passEditText);
                TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
                //As the above are actually text strings we now need to parse it to get the integer for calculations.
                String username = userEditText.getText().toString();
                String password = passEditText.getText().toString();
                String result;
                if (username.equals("o") && password.equals("o")){
                    result = "successful login";
                } else {
                    result = "username " + username + " or password " + password +" is incorrect";
                }
                Intent startIntent = new Intent(getApplicationContext(), LoginResults.class);
                startIntent.putExtra("com.example.shoppingapp.loginResult", result);//pass info in key, value pairs.  Keys are usually packages.SomeName
                startActivity(startIntent);
                //easiest way to change an int result to a text is to concat it with an empty string.  Sample comment below.
                //resultTextView.setText(result);
            }
        });
    }
}