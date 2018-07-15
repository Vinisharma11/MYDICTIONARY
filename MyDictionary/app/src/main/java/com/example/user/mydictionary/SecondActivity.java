package com.example.user.mydictionary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<WordModel> words = null;
    private MyDatabase sqliteDatabase;
    private SQLiteDatabase db;

    TextView text;
    ImageButton next, prev;

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        text = findViewById(R.id.defTextView);

        next = findViewById(R.id.next);
        prev = findViewById(R.id.prev);

        prev.setOnClickListener(this);
        next.setOnClickListener(this);

        words = new ArrayList<>();

        sqliteDatabase = new MyDatabase(this);
        db = sqliteDatabase.getWritableDatabase();

        Intent intent = getIntent();

        if (intent.getStringExtra("word") != null) {

            String word = intent.getStringExtra("word");

            String SEARCH_QUERY = " SELECT * FROM wordlist WHERE WORD LIKE '%" + word + "%'; ";

            Cursor cursor = db.rawQuery(SEARCH_QUERY, null);

            if (cursor.getCount() > 0) {

                while (cursor.moveToNext()) {

                    String id = cursor.getString(cursor.getColumnIndex("ID"));
                    String wrd = cursor.getString(cursor.getColumnIndex("WORD"));
                    String wordDef = cursor.getString(cursor.getColumnIndex("DEF"));

                    WordModel wordModel = new WordModel(id, wrd, wordDef);

                    words.add(wordModel);

                }

                text.setText(words.get(0).getWordDef());
                i = 0;

            } else {

                text.setText("No match found");
                next.setEnabled(false);
                prev.setEnabled(false);

            }

            cursor.close();

        }





    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.prev:
                if(i != 0){
                    i--;
                    text.setText(words.get(i).getWordDef());
                }
                return;

            case R.id.next:
                if(i != words.size()-1){
                    i++;
                    text.setText(words.get(i).getWordDef());
                }

        }

    }
}
