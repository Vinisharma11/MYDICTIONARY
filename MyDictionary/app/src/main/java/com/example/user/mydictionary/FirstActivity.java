package com.example.user.mydictionary;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    EditText searchET;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);

        searchBtn = findViewById(R.id.searchBtn);
        searchET = findViewById(R.id.word);

        searchBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(TextUtils.isEmpty(searchET.getText().toString())){
            searchET.setError("Required");
            return;
        }

        String word = searchET.getText().toString().trim();

        startActivity(new Intent(FirstActivity.this, SecondActivity.class).putExtra("word",word));

    }
}
