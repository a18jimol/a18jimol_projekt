package com.example.a18jimol_projekt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class InfoDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        Intent intent = getIntent();
        String mountain1 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String mountain2 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);

        TextView headerView = findViewById(R.id.textView15);
        TextView headerView2 = findViewById(R.id.textView22);
        headerView.setText(mountain1);
        headerView2.setText(mountain2);
    }
}
