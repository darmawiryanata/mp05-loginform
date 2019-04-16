package com.example.latihan5_loginform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {
    Button btnBuku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnBuku = (Button) findViewById(R.id.button);
    }

    public void actBuku(View v) {
        Intent impl = new Intent(MainMenuActivity.this, BookActivity.class);
        startActivity(impl);
    }
}
