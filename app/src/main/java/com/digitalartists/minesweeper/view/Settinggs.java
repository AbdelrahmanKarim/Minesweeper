package com.digitalartists.minesweeper.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.digitalartists.minesweeper.R;

public class Settinggs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settinggs);
        getSupportActionBar().hide();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void homset(View view) {
        Intent nt =new Intent(Settinggs.this , MainActivity.class);
        startActivity(nt);
    }

    public void ldrset(View view) {
        Intent ny = new Intent(Settinggs.this ,Leaderboard.class);
        startActivity(ny);
    }

    public void noads(View view) {
        Intent nu = new Intent(Settinggs.this,Noads.class);
        startActivity(nu);
    }

    public void stat(View view) {
        Intent ni = new Intent(Settinggs.this ,Statistics.class);
        startActivity(ni);
    }

    public void htpl(View view) {
        Intent ni = new Intent(Settinggs.this ,Howtoplay.class);
        startActivity(ni);
    }
    public void nggg(View view) {
        Intent ni = new Intent(Settinggs.this ,Registrationn.class);
        startActivity(ni);
    }

}