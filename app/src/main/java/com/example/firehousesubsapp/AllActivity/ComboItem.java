package com.example.firehousesubsapp.AllActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.firehousesubsapp.MainActivity;
import com.example.firehousesubsapp.Pojo.Others;
import com.example.firehousesubsapp.R;
import com.example.firehousesubsapp.SIngletonHandler.OrderSingleton;
import com.example.firehousesubsapp.SIngletonHandler.OthersSingleton;

public class ComboItem extends AppCompatActivity {
    String item1;
    String item2;
    String item3;
    RadioButton r1;
    RadioButton r2;
    RadioButton r3;
    RadioGroup radioGroup;
    RadioButton chkBtn;
    Button btn;
    Others o = new Others();
    OthersSingleton appDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_item);
        Intent i = getIntent();
        Bundle extras = getIntent().getExtras();
        item1 = extras.getString("item1");
        item2 = extras.getString("item2");
        item3 = extras.getString("item3");

        r1 = findViewById(R.id.radio1);
        r2 = findViewById(R.id.radio2);
        r3 = findViewById(R.id.radio3);
        btn = findViewById(R.id.btn);
        radioGroup = findViewById(R.id.radioGrp);
        r1.setChecked(true);
        r1.setText(item1);
        r2.setText(item2);
        r3.setText(item3);

        appDb = OthersSingleton.getInstance(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioBtn = radioGroup.getCheckedRadioButtonId();
                chkBtn = findViewById(radioBtn);
                Log.i("a",chkBtn.getText().toString());
                o.setName(chkBtn.getText().toString());
                appDb.OthersDao().insertOthers(o);
                Toast.makeText(v.getContext(), "Added to Cart",
                        Toast.LENGTH_LONG).show();
                Intent i;
                i = new Intent(ComboItem.this, MainActivity.class);

                startActivity(i);
            }
        });



    }
    public void endThisActivity() {
    }

    public  void checkButton(View v) {
        int radioBtn = radioGroup.getCheckedRadioButtonId();
        chkBtn = findViewById(radioBtn);
        Log.i("a",chkBtn.getText().toString());
    }
}