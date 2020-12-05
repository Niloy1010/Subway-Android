package com.example.firehousesubsapp.AllActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firehousesubsapp.AdapterHandler.ComboAdapter;
import com.example.firehousesubsapp.MainActivity;
import com.example.firehousesubsapp.Pojo.Combo;
import com.example.firehousesubsapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ComboActivity extends AppCompatActivity {

    private String receiverFoodItemId;
    private String foodItemName;
    private int itemNumber;


    RecyclerView recyclerView;
    ComboAdapter adapter;
    TextView txtFullName;
    private Query query;
    private boolean isParent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item);

        receiverFoodItemId = getIntent().getExtras().get("food_id").toString();
        Toast.makeText(this,receiverFoodItemId,Toast.LENGTH_SHORT).show();
        itemNumber = Integer.parseInt(receiverFoodItemId);
        if(itemNumber==1){
            foodItemName = "HotSpecial";
        }
        else{

            foodItemName = "Combo";
        }

        BottomNavigationView bottomNav= findViewById(R.id.bottomNavigationSpeciality);
        bottomNav.setOnNavigationItemSelectedListener(navListenerFood);
        bottomNav.setSelectedItemId(R.id.nav_foods);
        query = FirebaseDatabase.getInstance().getReference().child(foodItemName);

        recyclerView = findViewById(R.id.recycler_food_item);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Combo> options = new FirebaseRecyclerOptions.Builder<Combo>().setQuery(query, Combo.class).build();


        adapter = new ComboAdapter(options,itemNumber,isParent);

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
                    i = new Intent(ComboActivity.this, MainActivity.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                    startActivity(i);
                    break;
                case R.id.nav_cart:
                    i = new Intent(ComboActivity.this, CartActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                    startActivity(i);
                    break;

                case R.id.nav_user:
                    i = new Intent(ComboActivity.this, Register.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                    startActivity(i);

                    break;
            }
            return true;
        }
    };
}
