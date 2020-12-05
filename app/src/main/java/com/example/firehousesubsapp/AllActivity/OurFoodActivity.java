package com.example.firehousesubsapp.AllActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.firehousesubsapp.MainActivity;
import com.example.firehousesubsapp.Pojo.Category;
import com.example.firehousesubsapp.AdapterHandler.CategoryAdapter;
import com.example.firehousesubsapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class OurFoodActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    RecyclerView recyclerView;
    CategoryAdapter adapter;
    TextView txtFullName;
    private Query query;
    private int itemNumber;
    private boolean isParent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_food);



        BottomNavigationView bottomNav= findViewById(R.id.bottomNavigationSpeciality);
        bottomNav.setOnNavigationItemSelectedListener(navListenerFood);
        bottomNav.setSelectedItemId(R.id.nav_foods);
        query = FirebaseDatabase.getInstance().getReference().child("Category");

        recyclerView = findViewById(R.id.recycler_food_item);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Category> options = new FirebaseRecyclerOptions.Builder<Category>().setQuery(query, Category.class).build();

        itemNumber = 0;
        isParent = true;
        adapter = new CategoryAdapter(options,itemNumber,isParent);

        recyclerView.setAdapter(adapter);


        //Load Images




    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListenerFood =  new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent i;


            switch (item.getItemId()){

                case R.id.nav_home:
                    i = new Intent(OurFoodActivity.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                    startActivity(i);
                    break;
                case R.id.nav_cart:
                    i = new Intent(OurFoodActivity.this, CartActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                    startActivity(i);
                    break;

                case R.id.nav_user:
                    i = new Intent(OurFoodActivity.this, Register.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                    startActivity(i);

                    break;
            }
            return true;
        }
    };


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}