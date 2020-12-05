package com.example.firehousesubsapp.FragmentHandler;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.firehousesubsapp.Pojo.Order;
import com.example.firehousesubsapp.R;
import com.example.firehousesubsapp.SIngletonHandler.OrderSingleton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class FoodLayoutFragment extends Fragment {
    Bundle getBundle;
    Bundle finalBundle;


    private String hasMayo;
    private String hasLettuce;

    private String hasTomato;
    private String hasOnion;
    private String hasMustard;
    private String hasBBQ;
    private String hasRanch;
    private String hasBacon;
    private String cheese;
    private String description;
    private String name;
    private String small;

    public String getSizeSelected() {
        return sizeSelected;
    }

    public void setSizeSelected(String sizeSelected) {
        this.sizeSelected = sizeSelected;
    }

    private String sizeSelected;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

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

    private String medium;
    private String large;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String price;


    private String image;



    private boolean isWhiteBread;


    private boolean isWheatBread;
    private String isSmall;

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

    private String isMedium;
    private String isLarge;

    TextView txtDescription;
    TextView txtFoodName;
    TextView txtBread;
    TextView txtSize;

    Button btnCustomize;

    Button btnWhiteBread;
    Button btnWheatBread;
    Button btnSmall;
    Button btnMedium;
    Button btnLarge;
    Button btnAddToCart;
    Order order = new Order();
    Order mTempOrder;
    List<Order> orderList;

    OrderSingleton appDb;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.food_layout_fragment,container,false);
        orderList = new ArrayList<>() ;

        appDb = OrderSingleton.getInstance(view.getContext());
        setViews(view);
        handleClicks();
        handleClickPrice();
        getBundle = getArguments();
        setItemStrings(getBundle);
        setLayoutId(view);


        setLayoutValues();

        btnCustomize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCustomize.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnCustomize.setTextColor(Color.parseColor("#000000"));
                Fragment frag = new CustomizeFragment(getBundle);
                FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragCustomizeHolder,frag);
                fr.addToBackStack(null);
                fr.commit();
            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
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
                order.setSizeSelected(getSizeSelected());
                order.setWhite(isWhiteBread());

                createNewOrder(order);
                Toast.makeText(getActivity(), "Added to Cart",
                        Toast.LENGTH_LONG).show();
                btnAddToCart.setBackgroundColor(Color.parseColor("#FFFFFF"));
                btnAddToCart.setTextColor(Color.parseColor("#000000"));
                getActivity().finish();


            }
        });




        return view;
    }



    public List<Order> getOrderList() {
        orderList = appDb.orderDao().getOrderList();
        return orderList;
    }
    public void createNewOrder(Order o){
        mTempOrder = o;
        appDb.orderDao().insertOrder(o);
        orderList.add(o);

    }

    public void setViews(View view) {
        btnWhiteBread = view.findViewById(R.id.btnWhiteBread);
        btnWheatBread = view.findViewById(R.id.btnWheatBread);
        btnSmall = view.findViewById(R.id.btnSmallCustom);
        btnMedium = view.findViewById(R.id.btnMediumCustom);
        btnLarge = view.findViewById(R.id.btnLargeCustom);
        txtSize = view.findViewById(R.id.txtSizeCustom);
        txtBread = view.findViewById(R.id.txtBread);
        txtBread.setText("Bread" + " (White)");
        setColorWhite();
        setColorMedium();

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
                txtSize.setText("Size: S $" + getSmall());
            }
        });
        btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPrice(getMedium());
                setColorMedium();
                setSizeSelected("Medium");
                txtSize.setText("Size: M $" + getMedium());


            }
        });
        btnLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPrice(getLarge());
                setColorLarge();
                setSizeSelected("Large");
                txtSize.setText("Size: L $" + getLarge());
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

    public void handleClickPrice() {

    }


    public void setLayoutId(View view) {
        View view1 = view;
        ImageView foodImg;
        foodImg = view.findViewById(R.id.foodImg);


        StorageReference storRef = FirebaseStorage.getInstance().getReferenceFromUrl(getImage());

        txtDescription = view.findViewById(R.id.txtDescription);
        txtFoodName = view.findViewById(R.id.txtFoodName);
        btnCustomize = view.findViewById(R.id.btnCustomize);
        btnAddToCart = view.findViewById(R.id.btnAddToCartCustom);

        Glide.with(foodImg.getContext()).load(storRef).into(foodImg);


    }
    public void setLayoutValues() {

        txtFoodName.setText(getName());
        txtDescription.setText(getDescription());
        txtSize.setText("Size : $" + getMedium());
    }

    public void setItemStrings(Bundle bundle) {
        finalBundle = bundle;
        setHasMayo(finalBundle.getString("mayo"));
        setHasLettuce(finalBundle.getString("lettuce"));
        setHasTomato(finalBundle.getString("tomato"));
        setHasOnion(finalBundle.getString("onion"));
        setHasMustard(finalBundle.getString("mustard"));
        setHasBBQ(finalBundle.getString("bbq"));
        setHasRanch(finalBundle.getString("ranch"));
        setHasBacon(finalBundle.getString("bacon"));
        setCheese(finalBundle.getString("cheese"));
        setName(finalBundle.getString("name"));
        setDescription(finalBundle.getString("description"));
        setImage(finalBundle.getString("image"));
        setSmall(finalBundle.getString("smallPrice"));
        setMedium(finalBundle.getString("mediumPrice"));
        setLarge(finalBundle.getString("largePrice"));


        setSizeSelected("Medium");
        setPrice(getMedium());
        setWhiteBread(true);


    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Bundle getGetBundle() {
        return getBundle;
    }

    public void setGetBundle(Bundle getBundle) {
        this.getBundle = getBundle;
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




}
