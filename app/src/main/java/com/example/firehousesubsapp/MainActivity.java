package com.example.firehousesubsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.firehousesubsapp.AllActivity.AboutUs;
import com.example.firehousesubsapp.AllActivity.CartActivity;
import com.example.firehousesubsapp.AllActivity.Login;
import com.example.firehousesubsapp.AllActivity.OurFoodActivity;
import com.example.firehousesubsapp.AllActivity.Register;
import com.example.firehousesubsapp.AllActivity.locations;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ImageView logOutIm;

    FragmentManager fManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav= findViewById(R.id.bottomNavigationSpeciality);
        bottomNav.setOnNavigationItemSelectedListener(navLisetner);

        Button btnAboutus = (Button)findViewById(R.id.btnAbout);
        logOutIm = findViewById(R.id.logOutImg);

        btnAboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, AboutUs.class));
            }
        });

        CardView locationCard = (CardView)findViewById(R.id.findLocationCard);

        locationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, locations.class));
            }
        });


        logOutIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.apply();
                Intent i = new Intent(MainActivity.this,Login.class);
                startActivity(i);
            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navLisetner =  new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent i;
            switch (item.getItemId()){
                case R.id.nav_foods:
                    i = new Intent(MainActivity.this, OurFoodActivity.class);


                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                    startActivity(i);
                    break;
                case R.id.nav_cart:
                    i = new Intent(MainActivity.this, CartActivity.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                    startActivity(i);

                    break;


                case R.id.nav_user:
                    i = new Intent(MainActivity.this, Register.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                    startActivity(i);

                    break;
            }
            return true;
        }
    };
}