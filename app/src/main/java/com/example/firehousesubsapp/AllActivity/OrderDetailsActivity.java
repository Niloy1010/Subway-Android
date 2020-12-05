package com.example.firehousesubsapp.AllActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.firehousesubsapp.Pojo.Order;
import com.example.firehousesubsapp.R;
import com.example.firehousesubsapp.SIngletonHandler.OrderSingleton;

import java.io.Serializable;

public class OrderDetailsActivity extends AppCompatActivity implements Serializable {

    ElegantNumberButton btnNumber;
    TextView hasMayo;
    TextView hasLettuce;
    TextView hasTomato;
    TextView hasOnion;
    TextView hasMustard;
    TextView hasBBQ;
    TextView hasRanch;
    TextView hasBacon;
    TextView cheese;
    TextView txtName;


    Button rmvCart;
    Button updtCart;
    OrderSingleton oSingle;
    Order o;
    int quantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        getViews();



        Intent i = getIntent();
        o = (Order) i.getSerializableExtra("order");

        setViews(o);



        btnNumber = findViewById(R.id.incrementButton);
        btnNumber.setNumber("1");
        btnNumber.setRange(1,10);
        btnNumber.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                if(newValue<1) {
                    quantity = 1;
                }
                else {
                    quantity = newValue;
                }
            }
        });
        rmvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oSingle = OrderSingleton.getInstance(v.getContext());
                oSingle.orderDao().deleteOrder(o);
                changeActivityDelete();

            }
        });

        updtCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oSingle = OrderSingleton.getInstance(v.getContext());
                o.setQuantity(quantity);

                oSingle.orderDao().updateOrder(o);
                changeActivity();


            }
        });


    }

    public void changeActivity() {

        Intent i = new Intent(this, CartActivity.class);
        Toast.makeText(this,"Item Updated",Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
    public void changeActivityDelete() {

        Intent i = new Intent(this, CartActivity.class);
        Toast.makeText(this,"Item Removed",Toast.LENGTH_SHORT).show();
        startActivity(i);
    }


    public void getViews() {

        hasMayo = findViewById(R.id.hasMayo);
        hasLettuce = findViewById(R.id.hasLettuce);
        hasTomato = findViewById(R.id.hasTomato);
        hasOnion = findViewById(R.id.hasOnion);
        hasMustard = findViewById(R.id.hasMustard);
        hasBacon = findViewById(R.id.hasBacon);
        hasBBQ = findViewById(R.id.hasBBQ);
        hasRanch = findViewById(R.id.hasRanch);
        cheese = findViewById(R.id.txtDetails);
        rmvCart = findViewById(R.id.rmvCart);
        updtCart = findViewById(R.id.updtCart);
        txtName = findViewById(R.id.txtName);
    }

    public void setViews(Order o) {

        hasMayo.setText(o.getMayo());
        hasLettuce.setText(o.getLettuce());
        hasTomato.setText(o.getTomato());
        hasOnion.setText(o.getOnion());
        hasMustard.setText(o.getMustard());
        hasBBQ.setText(o.getBBQ());
        hasRanch.setText(o.getRanch());
        hasBacon.setText(o.getBacon());
        cheese.setText(o.getCheese());
        txtName.setText(o.getName());

    }

}