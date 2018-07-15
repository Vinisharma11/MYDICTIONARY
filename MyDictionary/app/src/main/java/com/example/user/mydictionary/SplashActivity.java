package com.example.user.mydictionary;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private static int time_splash = 4000;

    private MyDatabase sqliteDatabase;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ProgressBar progressBar= findViewById(R.id.progressBar);

        sqliteDatabase = new MyDatabase(this);
        db = sqliteDatabase.getWritableDatabase();

        checkForUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent i1= new Intent(SplashActivity.this, FirstActivity.class);
                startActivity(i1);
                finish();
            }
        },time_splash);


    }

    private void checkForUser() {

        SharedPreferences sp = getSharedPreferences("details",MODE_PRIVATE);

        boolean isNewUser = sp.getBoolean("new",true);

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("new",false);
        editor.apply();

        if(isNewUser){
            populateDatabase();
        }


    }

    private void populateDatabase() {

        List<WordModel> wordList = new ArrayList<>();

        //read from csv file

        try{

            InputStreamReader inputStreamReader = new InputStreamReader(getApplication().getAssets().open("dictionary.csv"));
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = null;

            while((line = reader.readLine()) != null){

                String[] data = line.split(",");

                WordModel word = new WordModel();
                word.setId(data[0]);
                word.setWord(data[1]);
                word.setWordDef(data[2]);

                wordList.add(word);
            }

            inputStreamReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //insert to databse
        addData(wordList);

    }

    private void addData(List<WordModel> wordList) {

        for(WordModel word : wordList){

            ContentValues cv = new ContentValues();
            cv.put("ID",word.getId());
            cv.put("WORD",word.getWord());
            cv.put("DEF",word.getWordDef());

            db.insert("wordlist",null,cv);

        }

    }


}
