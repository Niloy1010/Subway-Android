package com.example.firehousesubsapp.AllActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firehousesubsapp.AdapterHandler.OrderAdapter;
import com.example.firehousesubsapp.AdapterHandler.OthersAdapter;
import com.example.firehousesubsapp.MainActivity;
import com.example.firehousesubsapp.Pojo.Order;
import com.example.firehousesubsapp.Pojo.Others;
import com.example.firehousesubsapp.R;
import com.example.firehousesubsapp.SIngletonHandler.OrderSingleton;
import com.example.firehousesubsapp.SIngletonHandler.OthersSingleton;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements Serializable {

    private RecyclerView recyclerView;
    private  RecyclerView recyclerView2;
    private  OrderAdapter adapter;
    private OthersAdapter adapter2;
    private List<Order> orderList  = new ArrayList<>();;
    private List<Others> othersList  = new ArrayList<>();;
    private List<Order> workingOrderList  = new ArrayList<>();;
    private List<Others> workingOthersList = new ArrayList<>();
    OrderSingleton appDb;
    OthersSingleton othersDB;
    Order o;
    Others os;
    TextView txtSubTotal;
    TextView txtTax;
    TextView txtTotal;
    float subTotal;
    float taxes;
    float total;
    Button btnPlaceOrder;
    LinearLayout priceHolder;
    String test;
    int comboItemNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        BottomNavigationView bottomNav= findViewById(R.id.bottomNavigationSpeciality);
        bottomNav.setOnNavigationItemSelectedListener(navListenerFood);
        bottomNav.setSelectedItemId(R.id.nav_cart);
        txtSubTotal = findViewById(R.id.txtSubTotal);
        txtTax = findViewById(R.id.txtTax);
        txtTotal = findViewById(R.id.txtTotal);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        priceHolder = findViewById(R.id.priceHolder);

        priceHolder.setVisibility(View.INVISIBLE);


        appDb = OrderSingleton.getInstance(this);
        othersDB = OthersSingleton.getInstance(this);

        Intent i = getIntent();
        workingOrderList = getOrderList();
        workingOthersList = getOthersList();
        o = (Order) i.getSerializableExtra("order");
        recyclerView = findViewById(R.id.recyclerCart);

        recyclerView2 = findViewById(R.id.recyclerCombo);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        workingOrderList = getOrderList();
        workingOthersList = getOthersList();
        if(adapter!=null) {
            adapter.notifyDataSetChanged();
        }
        adapter = new OrderAdapter(getOrderList());
        recyclerView.setAdapter(adapter);

        comboItemNo = workingOthersList.size();
        adapter2 = new OthersAdapter(getOthersList());
        recyclerView2.setAdapter(adapter2);
         broadCast();
//        BroadcastReceiver mMessageReceiver2 = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//                priceHolder.setVisibility(View.VISIBLE);
//                String itemNo= intent.getStringExtra("item");
//
//                comboItemNo = Float.parseFloat(itemNo);
//                Log.i("aaaaaaaaaaaaaaaaaaaaa",itemNo);
//                subTotal = subTotal+(comboItemNo+1)+2;
//                taxes = (subTotal*15)/100;
//                total = subTotal + taxes;
//                txtSubTotal.setText( String.format("%.2f",subTotal)) ;
//                txtTax.setText( String.format("%.2f",taxes)) ;
//                txtTotal.setText( String.format("%.2f",total)) ;
//            }
//
//        };
//
//        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver2,new IntentFilter("ItemNumber"));
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Order has been placed",Toast.LENGTH_LONG).show();
                appDb.orderDao().deleteAllOrder();
                Intent i = new Intent(v.getContext(),MainActivity.class);
                startActivity(i);

                finish();
            }
        });

    }


public void broadCast() {
    BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            priceHolder.setVisibility(View.VISIBLE);
            String name= intent.getStringExtra("name");
            Log.e("name",name);
            test = name;
            subTotal = comboItemNo*2+ Float.parseFloat(name);
            taxes = (subTotal*15)/100;
            total = subTotal + taxes;

            txtSubTotal.setText( String.format("%.2f",subTotal)) ;
            txtTax.setText( String.format("%.2f",taxes)) ;
            txtTotal.setText( String.format("%.2f",total)) ;
        }

    };
    LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,new IntentFilter("message_subject_intent"));
}


    public List<Order> getOrderList() {
        orderList = appDb.orderDao().getOrderList();
        return orderList;
    }

    public  List<Others> getOthersList() {
        othersList = othersDB.OthersDao().getOthersList();
        return othersList;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListenerFood =  new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent i;


            switch (item.getItemId()){

                case R.id.nav_home:
                    i = new Intent(CartActivity.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                    startActivity(i);
                    finish();

                    break;
                case R.id.nav_foods:
                    i = new Intent(CartActivity.this, OurFoodActivity.class);


                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                    startActivity(i);
                    break;
                case R.id.nav_user:
                    i = new Intent(CartActivity.this, Register.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                    startActivity(i);

                    break;
            }
            return true;
        }
    };
}