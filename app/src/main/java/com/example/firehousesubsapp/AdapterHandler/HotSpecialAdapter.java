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
import com.example.firehousesubsapp.AllActivity.SpecialSubsActivity;
import com.example.firehousesubsapp.Pojo.HotSpecial;
import com.example.firehousesubsapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class HotSpecialAdapter extends FirebaseRecyclerAdapter<HotSpecial, HotSpecialAdapter.HotSpecialHolder> {

    FirebaseRecyclerOptions<HotSpecial> cList;
    int itemNumber;
    boolean isParent;

    public HotSpecialAdapter(FirebaseRecyclerOptions<HotSpecial> options, int foodid,boolean isParent) {
        super(options);
        this.cList = options;
        this.itemNumber = itemNumber;
        this.isParent = isParent;

    }

    public HotSpecialAdapter.HotSpecialHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new HotSpecialAdapter.HotSpecialHolder(layoutInflater, parent);
    }


    protected void onBindViewHolder(final HotSpecialAdapter.HotSpecialHolder holder, final int position, final HotSpecial model) {
        StorageReference storRef = FirebaseStorage.getInstance().getReferenceFromUrl(model.getImage());

        holder.txtName.setText(model.getName());
        Glide.with(holder.imgHotSpecial.getContext()).load(storRef).into(holder.imgHotSpecial);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {

                                                   Context context = v.getContext();
                                                   String food_id = getRef(position).getKey();
                                                   Intent foodItemIntent = new Intent(context, SpecialSubsActivity.class);
                                                   foodItemIntent.putExtra("food_id", food_id);
                                                   foodItemIntent.putExtra("name", model.getName());
                                                   foodItemIntent.putExtra("mayo",model.getHasMayo());
                                                   foodItemIntent.putExtra("lettuce",model.getHasLettuce());
                                                   foodItemIntent.putExtra("tomato",model.getHasTomato());
                                                   foodItemIntent.putExtra("onion",model.getHasOnion());
                                                   foodItemIntent.putExtra("mustard",model.getHasMustard());
                                                   foodItemIntent.putExtra("bbq",model.getHasBBQ());
                                                   foodItemIntent.putExtra("ranch",model.getHasRanch());
                                                   foodItemIntent.putExtra("bacon",model.getHasBacon());
                                                   foodItemIntent.putExtra("cheese",model.getCheese());
                                                   foodItemIntent.putExtra("description",model.getDescription());
                                                   foodItemIntent.putExtra("image",model.getImage());
                                                   foodItemIntent.putExtra("smallPrice",model.getSmallPrice());
                                                   foodItemIntent.putExtra("mediumPrice", model.getMediumPrice());
                                                   foodItemIntent.putExtra("largePrice", model.getLargePrice());
                                                   context.startActivity(foodItemIntent);

                                               }

                                           }
        );
        ;
    }


    class HotSpecialHolder extends RecyclerView.ViewHolder {

        ImageView imgHotSpecial;
        TextView txtName;

        public HotSpecialHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.food_item, parent, false));

            imgHotSpecial = itemView.findViewById(R.id.categoryImg);
            txtName = itemView.findViewById(R.id.categoryTxt);


        }


    } 
}
