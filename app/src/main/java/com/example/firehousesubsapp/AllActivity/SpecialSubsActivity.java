package com.example.firehousesubsapp.AllActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.firehousesubsapp.FragmentHandler.FoodLayoutFragment;
import com.example.firehousesubsapp.MainActivity;
import com.example.firehousesubsapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SpecialSubsActivity extends AppCompatActivity {

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
    private String smallPrice;

    public String getSmallPrice() {
        return smallPrice;
    }

    public void setSmallPrice(String smallPrice) {
        this.smallPrice = smallPrice;
    }

    public String getMediumPrice() {
        return mediumPrice;
    }

    public void setMediumPrice(String mediumPrice) {
        this.mediumPrice = mediumPrice;
    }

    public String getLargePrice() {
        return largePrice;
    }

    public void setLargePrice(String largePrice) {
        this.largePrice = largePrice;
    }

    private String mediumPrice;
    private String largePrice;



    private String image;

    TextView txtDescription;
    TextView txtFoodName;
    TextView txtBread;
    TextView txtSize;

    Button btnCustomize;


    private Bundle foodItem;
    Bundle foodFragmentBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_subs);
        BottomNavigationView bottomNav= findViewById(R.id.bottomNavigationSpeciality);
        bottomNav.setOnNavigationItemSelectedListener(navListenerFood);
        bottomNav.setSelectedItemId(R.id.nav_foods);

        setIntent();

        foodFragmentBundle = createBundle();
        createFragment(foodFragmentBundle);


    }
    public void createFragment(Bundle bundle) {


        FragmentManager fManager = getSupportFragmentManager();
        Fragment frag  = fManager.findFragmentById(R.id.fragCustomizeHolder);

        if(frag == null) {

            Bundle fragmentBundle = bundle;
            frag = new FoodLayoutFragment();
            frag.setArguments(fragmentBundle);
            fManager.beginTransaction().add(R.id.fragCustomizeHolder,frag).commit();

        }


    }



    public Bundle createBundle() {
        foodItem = new Bundle();
        foodItem.putString("description",getDescription());
        foodItem.putString("mayo",getHasMayo());
        foodItem.putString("lettuce",getHasLettuce());
        foodItem.putString("tomato",getHasTomato());
        foodItem.putString("onion",getHasOnion());
        foodItem.putString("mustard",getHasMustard());
        foodItem.putString("bbq",getHasBBQ());
        foodItem.putString("bacon",getHasBacon());
        foodItem.putString("ranch",getHasRanch());
        foodItem.putString("cheese",getCheese());
        foodItem.putString("name",getName());
        foodItem.putString("image",getImage());
        foodItem.putString("smallPrice",getSmallPrice());
        foodItem.putString("mediumPrice",getMediumPrice());
        foodItem.putString("largePrice",getLargePrice());
        return foodItem;
    }


    public  void setIntent() {


        setDescription(getIntent().getExtras().get("description").toString());
        setHasMayo(getIntent().getExtras().get("mayo").toString());
        setHasLettuce( getIntent().getExtras().get("lettuce").toString());
        setHasTomato(getIntent().getExtras().get("tomato").toString());
        setHasOnion( getIntent().getExtras().get("onion").toString());
        setHasMustard(getIntent().getExtras().get("mustard").toString());
        setHasBBQ(getIntent().getExtras().get("bbq").toString());
        setHasBacon(getIntent().getExtras().get("bacon").toString());
        setHasRanch(getIntent().getExtras().get("ranch").toString());
        setCheese(getIntent().getExtras().get("cheese").toString());
        setName(getIntent().getExtras().get("name").toString());
        setImage(getIntent().getExtras().get("image").toString());
        setSmallPrice(getIntent().getExtras().get("smallPrice").toString());
        setMediumPrice(getIntent().getExtras().get("mediumPrice").toString());
        setLargePrice(getIntent().getExtras().get("largePrice").toString());
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListenerFood =  new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent i;
            switch (item.getItemId()){
                case R.id.nav_cart:
                    i = new Intent(SpecialSubsActivity.this, CartActivity.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                    startActivity(i);

                    break;


                case R.id.nav_user:
                    i = new Intent(SpecialSubsActivity.this, Register.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                    startActivity(i);

                    break;
            }
            return true;
        }
    };





    public String getHasMayo() {
        return hasMayo;
    }

    public void setHasMayo(String hasMayo) {
        this.hasMayo = hasMayo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Bundle getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(Bundle foodItem) {
        this.foodItem = foodItem;
    }


}