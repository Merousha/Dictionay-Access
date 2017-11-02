package com.example.vanil_singh.dictionaryaccess;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.dictResults_ListView);
        TextView textView = (TextView)findViewById(R.id.textView);

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(UserDictionary.Words. CONTENT_URI ,
                null , null , null , null );

        try {
            textView.setText( "UserDictionary content = " + cursor.getCount() +
                    " custom words \n " );
            int wordIndex = cursor.getColumnIndex(UserDictionary.Words. WORD );
            String[] List = {UserDictionary.Words.WORD,};
            int [] to = { android.R.id. text1 };

            while (cursor.moveToNext()) {
                String currentWord = cursor.getString(wordIndex);
                adapter = new SimpleCursorAdapter( this ,
                        android.R.layout. simple_list_item_1 , null , List, to, 0 );
            }

        } finally {
            cursor.close();
        }
    }
}
