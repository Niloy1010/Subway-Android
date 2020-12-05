package com.example.firehousesubsapp.AdapterHandler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firehousesubsapp.AllActivity.ComboActivity;
import com.example.firehousesubsapp.AllActivity.FoodItemActivity;
import com.example.firehousesubsapp.Pojo.Category;
import com.example.firehousesubsapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class CategoryAdapter extends FirebaseRecyclerAdapter<Category, CategoryAdapter.CategoryHolder> {

    FirebaseRecyclerOptions<Category> cList;
    int itemNumber;
    boolean isParent;

    public CategoryAdapter(FirebaseRecyclerOptions<Category> options, int foodid,boolean isParent) {
        super(options);
        this.cList = options;
        this.itemNumber = itemNumber;
        this.isParent = isParent;

    }

    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new CategoryHolder(layoutInflater, parent);
    }


    protected void onBindViewHolder(final CategoryHolder holder, final int position, Category model) {
        StorageReference storRef = FirebaseStorage.getInstance().getReferenceFromUrl(model.getImage());

        holder.txtName.setText(model.getName());
        Glide.with(holder.imgCategory.getContext()).load(storRef).into(holder.imgCategory);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                           public void onClick(View v) {

                           Context context = v.getContext();


                           String food_id = getRef(position).getKey();

                           if(isParent) {
                               if(food_id.toString().equals("1")) {

                                   Intent foodItemIntent = new Intent(context, FoodItemActivity.class);
                                   foodItemIntent.putExtra("food_id", food_id);
                                   context.startActivity(foodItemIntent);
                               }
                               else{

                                   Intent foodItemIntent = new Intent(context, ComboActivity.class);
                                   foodItemIntent.putExtra("food_id", food_id);
                                   context.startActivity(foodItemIntent);
                               }

                           }
                      }

                  }
        );
        ;
    }


    class CategoryHolder extends RecyclerView.ViewHolder {

        ImageView imgCategory;
        TextView txtName;

        public CategoryHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.food_item, parent, false));

            imgCategory = itemView.findViewById(R.id.categoryImg);
            txtName = itemView.findViewById(R.id.categoryTxt);


        }


    }
}
