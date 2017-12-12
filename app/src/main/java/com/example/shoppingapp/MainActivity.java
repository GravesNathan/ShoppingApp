package com.example.shoppingapp;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.TestLooperManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    EditText userEditText = (EditText) findViewById(R.id.userEditText);
//    EditText passEditText = (EditText) findViewById(R.id.passEditText);
//    TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
//    private Button loginBtn = (Button) findViewById(R.id.loginBtn);
//    private Button createBtn = (Button) findViewById(R.id.createBtn);
    private EditText userEditText;
    private EditText passEditText;
    private TextView resultTextView;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEditText = (EditText) findViewById(R.id.userEditText);
                passEditText = (EditText) findViewById(R.id.passEditText);
                resultTextView = (TextView) findViewById(R.id.resultTextView);
                String username = userEditText.getText().toString();
                String password = passEditText.getText().toString();
                if (username.equals("o") && password.equals("o")) {
                    result = "successful login";
                } else {
                    result = "username " + username + " or password " + password + " is incorrect";
                }
                Intent startIntent = new Intent(getApplicationContext(), LoginResults.class);
                startIntent.putExtra("com.example.shoppingapp.loginResult", result);//pass info in key, value pairs.  Keys are usually packages.SomeName
                startActivity(startIntent);
                //easiest way to change an int result to a text is to concat it with an empty string.  Sample comment below.
                resultTextView.setText(result);
            }
        });

        //Sample URL connection
        Button createBtn = (Button) findViewById(R.id.createBtn);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new JSONTask.execute("http://localhost:8080/ShoppingServer/");
                new JSONTask().execute("https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesDemoItem.txt");
            }
        });
    }

    //Need an AsyncTask to request URLs.  We just named it JSONTask following tutorial https://youtu.be/_7r_vdwmW0o?list=PLgjt1h_kabFebUn1anGYZ6_Tfkw64Ww12
    public class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            StringBuffer buffer = null;
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            //String username = userEditText.getText().toString();
            //String password = passEditText.getText().toString();

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            resultTextView = (TextView) findViewById(R.id.resultTextView);
            resultTextView.setText(result);
        }
    }
}