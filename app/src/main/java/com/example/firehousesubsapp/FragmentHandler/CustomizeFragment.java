package com.example.firehousesubsapp.FragmentHandler;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.firehousesubsapp.Pojo.Order;
import com.example.firehousesubsapp.R;
import com.example.firehousesubsapp.SIngletonHandler.OrderSingleton;

import java.util.ArrayList;
import java.util.List;

public class CustomizeFragment extends Fragment {

    Button btnDone;
    TextView txtCustom;
    Bundle foodBundle;



    private String hasMayo;
    private String hasLettuce;

    private String hasTomato;
    private String hasOnion;
    private String hasMustard;

    public String getSizeSelected() {
        return sizeSelected;
    }

    public void setSizeSelected(String sizeSelected) {
        this.sizeSelected = sizeSelected;
    }

    private String sizeSelected;


    private String hasBBQ;
    private String hasRanch;
    private String hasBacon;
    private String cheese;
    private String description;
    private String name;

    public boolean isWhiteBread() {
        return isWhiteBread;
    }

    public void setWhiteBread(boolean whiteBread) {
        isWhiteBread = whiteBread;
    }

    public boolean isWheatBread() {
        return isWheatBread;
    }

    public void setWheatBread(boolean wheatBread) {
        isWheatBread = wheatBread;
    }

    private boolean isWhiteBread;


    private boolean isWheatBread;
    private String isSmall;
    private String isMedium;
    private String isLarge;

    TextView txtName;
    TextView txtSizeCustom;

    Spinner spinnerMayo;
    Spinner spinnerLettuce;
    Spinner spinnerTomato;
    Spinner spinnerOnion;
    Spinner spinnerMustard;
    Spinner spinnerBBQ;
    Spinner spinnerRanch;
    Spinner spinnerBacon;


    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    private String small;
    private String medium;
    private String large;
    private String price;


    Button btnWhiteBread;
    Button btnWheatBread;
    Button btnSmall;
    Button btnMedium;
    Button btnLarge;
    Button btnCart;
    TextView txtBread;
    OrderSingleton appDb;

    Order order = new Order();
    Order mTempOrder;
    List<Order> orderList = new ArrayList<>();

    public CustomizeFragment(Bundle bundle) {
        this.foodBundle = bundle;


    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customize_layout_fragment,container,false);


        setViews(view);
        handleClicks();
        setItemType();

        txtSizeCustom.setText("Size: M $" + getMedium());

        Log.e("mayo",getHasMayo());
        Log.e("lettuce",getHasLettuce());
        txtName.setText(getName());
        handleSpinner(view);

        appDb = OrderSingleton.getInstance(view.getContext());
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setName(getName());
                order.setMayo(getHasMayo());
                order.setLettuce(getHasLettuce());
                order.setTomato(getHasTomato());
                order.setOnion(getHasOnion());
                order.setMustard(getHasMustard());
                order.setBacon(getHasBacon());
                order.setBBQ(getHasBBQ());
                order.setRanch(getHasRanch());
                order.setWheat(isWheatBread());
                order.setWheat(isWheatBread());
                order.setSmall(getSmall());
                order.setMedium(getMedium());
                order.setLarge(getLarge());
                order.setPrice(getPrice());
                order.setCheese(getCheese());
                order.setQuantity(1);

                order.setWhite(isWhiteBread());
                order.setSizeSelected(getSizeSelected());
                createNewOrder(order);
                Toast.makeText(getActivity(), "Added to Cart",
                        Toast.LENGTH_LONG).show();
                btnCart.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnCart.setTextColor(Color.parseColor("#000000"));
                getActivity().finish();

            }
        });


        return  view;


    }



    public void setViews(View view) {
        txtName = view.findViewById(R.id.txtName);
        btnWhiteBread = view.findViewById(R.id.btnWhiteBread);
        btnWheatBread = view.findViewById(R.id.btnWheatBread);
        btnSmall = view.findViewById(R.id.btnSmallCustom);
        btnMedium = view.findViewById(R.id.btnMediumCustom);
        btnLarge = view.findViewById(R.id.btnLargeCustom);
        btnCart = view.findViewById(R.id.btnAddToCartCustom);
        txtSizeCustom = view.findViewById(R.id.txtSizeCustom);
        txtBread = view.findViewById(R.id.txtBread);
        txtBread.setText("Bread" + " (White)");
        setColorWhite();
        setColorMedium();
        setSizeSelected("Medium");


    }

    void setColorSmall() {

        btnSmall.setBackgroundColor(Color.parseColor("#FFFFFF"));
        btnSmall.setTextColor(Color.parseColor("#000000"));

        btnMedium.setBackgroundColor(Color.parseColor("#d71921"));
        btnMedium.setTextColor(Color.parseColor("#FFFFFF"));

        btnLarge.setBackgroundColor(Color.parseColor("#d71921"));
        btnLarge.setTextColor(Color.parseColor("#FFFFFF"));

    }
    void setColorMedium() {

        btnSmall.setBackgroundColor(Color.parseColor("#d71921"));
        btnSmall.setTextColor(Color.parseColor("#FFFFFF"));

        btnMedium.setBackgroundColor(Color.parseColor("#FFFFFF"));
        btnMedium.setTextColor(Color.parseColor("#000000"));

        btnLarge.setBackgroundColor(Color.parseColor("#d71921"));
        btnLarge.setTextColor(Color.parseColor("#FFFFFF"));
    }
    void setColorLarge() {

        btnSmall.setBackgroundColor(Color.parseColor("#d71921"));
        btnSmall.setTextColor(Color.parseColor("#FFFFFF"));

        btnMedium.setBackgroundColor(Color.parseColor("#d71921"));
        btnMedium.setTextColor(Color.parseColor("#FFFFFF"));

        btnLarge.setBackgroundColor(Color.parseColor("#FFFFFF"));
        btnLarge.setTextColor(Color.parseColor("#000000"));
    }




    public void handleClicks() {
        btnWhiteBread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWhiteBread(true);
                setWheatBread(false);
                txtBread.setText("Bread" + " (White)");
                setColorWhite();
            }
        });
        btnWheatBread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setWhiteBread(false);
                setWheatBread(true);
                txtBread.setText("Bread" + "(Wheat)");
                setColorWheat();
            }
        });
        btnSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPrice(getSmall());
                setColorSmall();
                setSizeSelected("Small");
                txtSizeCustom.setText("Size: S $" + getSmall());
            }
        });
        btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPrice(getMedium());
                setColorMedium();
                setSizeSelected("Medium");
                txtSizeCustom.setText("Size: M $" + getMedium());

            }
        });
        btnLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPrice(getLarge());
                setColorLarge();
                setSizeSelected("Large");
                txtSizeCustom.setText("Size: L $" + getLarge());

            }
        });



    }

    void setColorWhite(){
        btnWhiteBread.setBackgroundColor(Color.parseColor("#FFFFFF"));
        btnWhiteBread.setTextColor(Color.parseColor("#000000"));
        btnWheatBread.setBackgroundColor(Color.parseColor("#d71921"));
        btnWheatBread.setTextColor(Color.parseColor("#FFFFFF"));
    }
    void setColorWheat(){

        btnWhiteBread.setBackgroundColor(Color.parseColor("#d71921"));
        btnWhiteBread.setTextColor(Color.parseColor("#FFFFFF"));
        btnWheatBread.setBackgroundColor(Color.parseColor("#FFFFFF"));
        btnWheatBread.setTextColor(Color.parseColor("#000000"));

    }
    public void createNewOrder(Order o){
        mTempOrder = o;
        appDb.orderDao().insertOrder(o);
        orderList.add(o);

    }
    public void handleSpinner(View view) {
        List<String> list = new ArrayList<String>();
        list.add("Yes");
        list.add("No");
        list.add("Extra");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(),R.layout.support_simple_spinner_dropdown_item,list);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerMayo(view,arrayAdapter);
        spinnerLettuce(view,arrayAdapter);
        spinnerTomato(view,arrayAdapter);
        spinnerOnion(view,arrayAdapter);
        spinnerMustard(view,arrayAdapter);
        spinnerBBQ(view,arrayAdapter);
        spinnerRanch(view,arrayAdapter);
        spinnerBacon(view,arrayAdapter);

    }

    public void spinnerMayo(View view, ArrayAdapter<String> adapter) {
        ArrayAdapter<String> arrayAdapter = adapter;
        spinnerMayo = view.findViewById(R.id.spnMayo);


        Log.e("mayo",getHasMayo());
        Log.e("lettuce",getHasLettuce());
        spinnerMayo.setAdapter(arrayAdapter);
        if(getHasMayo().equals("Yes")) {
            spinnerMayo.setSelection(0);
        }
        else{

            spinnerMayo.setSelection(1);
        }
        spinnerMayo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0) {
                    setHasMayo("Yes");
                }
                else if(position ==1 ){

                    setHasMayo("No");
                }
                else{

                    setHasMayo("Extra");
                }
                spinnerMayo.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void spinnerLettuce(View view, ArrayAdapter<String> adapter) {
        ArrayAdapter<String> arrayAdapter = adapter;
        spinnerLettuce = view.findViewById(R.id.hasLettuce);
        spinnerLettuce.setAdapter(arrayAdapter);
        if(getHasLettuce().equals("Yes")) {
            spinnerLettuce.setSelection(0);
        }
        else{

            spinnerLettuce.setSelection(1);
        }
        spinnerLettuce.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0) {
                    setHasLettuce("Yes");
                }
                else if(position ==1 ){

                    setHasLettuce("No");
                }
                else{

                    setHasLettuce("Extra");
                }
                spinnerLettuce.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void spinnerTomato(View view, ArrayAdapter<String> adapter) {
        ArrayAdapter<String> arrayAdapter = adapter;
        spinnerTomato = view.findViewById(R.id.hasTomato);
        spinnerTomato.setAdapter(arrayAdapter);
        if(getHasTomato().equals("Yes")) {
            spinnerTomato.setSelection(0);
        }
        else{

            spinnerTomato.setSelection(1);
        }
        spinnerTomato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    setHasTomato("Yes");
                }
                else if(position ==1 ){

                    setHasTomato("No");
                }
                else{

                    setHasTomato("Extra");
                }
                spinnerTomato.setSelection(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void spinnerOnion(View view, ArrayAdapter<String> adapter) {
        ArrayAdapter<String> arrayAdapter = adapter;
        spinnerOnion = view.findViewById(R.id.hasOnion);
        spinnerOnion.setAdapter(arrayAdapter);
        if(getHasOnion().equals("Yes")) {
            spinnerOnion.setSelection(0);
        }
        else{

            spinnerOnion.setSelection(1);
        }
        spinnerOnion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    setHasOnion("Yes");
                }
                else if(position ==1 ){

                    setHasOnion("No");
                }
                else{

                    setHasOnion("Extra");
                }
                spinnerOnion.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void spinnerMustard(View view, ArrayAdapter<String> adapter) {
        ArrayAdapter<String> arrayAdapter = adapter;
        spinnerMustard = view.findViewById(R.id.hasMustard);
        spinnerMustard.setAdapter(arrayAdapter);
        if(getHasMustard().equals("Yes")) {
            spinnerMustard.setSelection(0);
        }
        else{

            spinnerMustard.setSelection(1);
        }
        spinnerMustard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    setHasMustard("Yes");
                }
                else if(position ==1 ){

                    setHasMustard("No");
                }
                else{

                    setHasMustard("Extra");
                }
                spinnerMustard.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void spinnerBBQ(View view, ArrayAdapter<String> adapter) {
        ArrayAdapter<String> arrayAdapter = adapter;
        spinnerBBQ = view.findViewById(R.id.hasBBQ);
        spinnerBBQ.setAdapter(arrayAdapter);
        if(getHasBBQ().equals("Yes")) {
            spinnerBBQ.setSelection(0);
        }
        else{

            spinnerBBQ.setSelection(1);
        }
        spinnerBBQ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    setHasBBQ("Yes");
                }
                else if(position ==1 ){

                    setHasBBQ("No");
                }
                else{

                    setHasBBQ("Extra");
                }
                spinnerBBQ.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void spinnerRanch(View view, ArrayAdapter<String> adapter) {
        ArrayAdapter<String> arrayAdapter = adapter;
        spinnerRanch = view.findViewById(R.id.hasRanch);
        spinnerRanch.setAdapter(arrayAdapter);
        if(getHasRanch().equals("Yes")) {
            spinnerRanch.setSelection(0);
        }
        else{

            spinnerRanch.setSelection(1);
        }
        spinnerRanch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    setHasRanch("Yes");
                }
                else if(position ==1 ){

                    setHasRanch("No");
                }
                else{

                    setHasRanch("Extra");
                }
                spinnerRanch.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void spinnerBacon(View view, ArrayAdapter<String> adapter) {
        ArrayAdapter<String> arrayAdapter = adapter;
        spinnerBacon = view.findViewById(R.id.hasBacon);
        spinnerBacon.setAdapter(arrayAdapter);
        if(getHasBacon().equals("Yes")) {
            spinnerBacon.setSelection(0);
        }
        else{

            spinnerBacon.setSelection(1);
        }
        spinnerBacon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    setHasBacon("Yes");
                }
                else if(position ==1 ){

                    setHasBacon("No");
                }
                else{

                    setHasBacon("Extra");
                }
                spinnerBacon.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }









    public void setItemType() {

        setName(foodBundle.getString("name"));
        setHasMayo(foodBundle.getString("mayo"));
        setHasLettuce(foodBundle.getString("lettuce"));
        setHasTomato(foodBundle.getString("tomato"));
        setHasOnion(foodBundle.getString("onion"));
        setHasMustard(foodBundle.getString("mustard"));
        setHasBBQ(foodBundle.getString("bbq"));
        setHasRanch(foodBundle.getString("ranch"));
        setHasBacon(foodBundle.getString("bacon"));
        setCheese(foodBundle.getString("cheese"));
        setSmall(foodBundle.getString("smallPrice"));
        setMedium(foodBundle.getString("mediumPrice"));
        setLarge(foodBundle.getString("largePrice"));

        setSizeSelected("Medium");
        setPrice(getMedium());
        setWhiteBread(true);
    }









    public String getHasMayo() {
        return hasMayo;
    }

    public void setHasMayo(String hasMayo) {
        this.hasMayo = hasMayo;
    }

    public String getHasLettuce() {
        return hasLettuce;
    }

    public void setHasLettuce(String hasLettuce) {
        this.hasLettuce = hasLettuce;
    }

    public String getHasTomato() {
        return hasTomato;
    }

    public void setHasTomato(String hasTomato) {
        this.hasTomato = hasTomato;
    }

    public String getHasOnion() {
        return hasOnion;
    }

    public void setHasOnion(String hasOnion) {
        this.hasOnion = hasOnion;
    }

    public String getHasMustard() {
        return hasMustard;
    }

    public void setHasMustard(String hasMustard) {
        this.hasMustard = hasMustard;
    }

    public String getHasBBQ() {
        return hasBBQ;
    }

    public void setHasBBQ(String hasBBQ) {
        this.hasBBQ = hasBBQ;
    }

    public String getHasRanch() {
        return hasRanch;
    }

    public void setHasRanch(String hasRanch) {
        this.hasRanch = hasRanch;
    }

    public String getHasBacon() {
        return hasBacon;
    }

    public void setHasBacon(String hasBacon) {
        this.hasBacon = hasBacon;
    }

    public String getCheese() {
        return cheese;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getIsSmall() {
        return isSmall;
    }

    public void setIsSmall(String isSmall) {
        this.isSmall = isSmall;
    }

    public String getIsMedium() {
        return isMedium;
    }

    public void setIsMedium(String isMedium) {
        this.isMedium = isMedium;
    }

    public String getIsLarge() {
        return isLarge;
    }

    public void setIsLarge(String isLarge) {
        this.isLarge = isLarge;
    }


}
