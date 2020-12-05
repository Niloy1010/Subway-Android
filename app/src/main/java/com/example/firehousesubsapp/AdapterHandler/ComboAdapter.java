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
import com.example.firehousesubsapp.AllActivity.ComboItem;
import com.example.firehousesubsapp.AllActivity.SpecialSubsActivity;
import com.example.firehousesubsapp.Pojo.Combo;
import com.example.firehousesubsapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ComboAdapter extends FirebaseRecyclerAdapter<Combo, ComboAdapter.ComboHolder> {

    FirebaseRecyclerOptions<Combo> cList;
    int itemNumber;
    boolean isParent;

    public ComboAdapter(FirebaseRecyclerOptions<Combo> options, int foodid,boolean isParent) {
        super(options);
        this.cList = options;
        this.itemNumber = itemNumber;
        this.isParent = isParent;

    }

    public ComboAdapter.ComboHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ComboAdapter.ComboHolder(layoutInflater, parent);
    }


    protected void onBindViewHolder(final ComboAdapter.ComboHolder holder, final int position, final Combo model) {
        StorageReference storRef = FirebaseStorage.getInstance().getReferenceFromUrl(model.getImage());

        holder.txtName.setText(model.getName());
        Glide.with(holder.imgCombo.getContext()).load(storRef).into(holder.imgCombo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {

                                                   Context context = v.getContext();
                                                   Intent i = new Intent(context, ComboItem.class);

                                        if(position==0) {
                                            i.putExtra("item1","Oatmeal Raisin");
                                            i.putExtra("item2","Chocolate chip");
                                            i.putExtra("item3","Lemon Cooler");

                                        }
                                        else if(position ==1) {
                                            i.putExtra("item1","Lays Classic");
                                            i.putExtra("item2","Doritos");
                                            i.putExtra("item3","Cheetos");

                                        }
                                        else{
                                            i.putExtra("item1","Coke");
                                            i.putExtra("item2","Coke Zero");
                                            i.putExtra("item3","Sprite");

                                        }
                                        context.startActivity(i);


                                               }

                                           }
        );
        ;
    }


    class ComboHolder extends RecyclerView.ViewHolder {

        ImageView imgCombo;
        TextView txtName;

        public ComboHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.food_item, parent, false));

            imgCombo = itemView.findViewById(R.id.categoryImg);
            txtName = itemView.findViewById(R.id.categoryTxt);


        }


    }
}
