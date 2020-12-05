package com.example.firehousesubsapp.AdapterHandler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firehousesubsapp.AllActivity.CartActivity;
import com.example.firehousesubsapp.MainActivity;
import com.example.firehousesubsapp.Pojo.Order;
import com.example.firehousesubsapp.Pojo.Others;
import com.example.firehousesubsapp.R;
import com.example.firehousesubsapp.SIngletonHandler.OrderSingleton;
import com.example.firehousesubsapp.SIngletonHandler.OthersSingleton;

import java.util.List;

public class OthersAdapter extends RecyclerView.Adapter<OthersAdapter.MyViewHolder> {

    OthersSingleton oSingle;
    private List<Others> mOthers;
    Others o;
    float subTotal;
    float total;


    public OthersAdapter(List<Others> Others){
        mOthers = Others;
    }


    public void test() {

    }
    @NonNull
    @Override
    public OthersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater,parent);

    }

    @Override
    public void onBindViewHolder(@NonNull final OthersAdapter.MyViewHolder holder, final int position) {
        final Others o = mOthers.get(position);
        holder.txtName.setText(o.getName());
        Log.i("as",o.getName());

        final int thisPosition = position;
        Intent intent = new Intent("ItemNumber");
        intent.putExtra("item" , Integer.toString(position));
        LocalBroadcastManager.getInstance(holder.itemView.getContext()).sendBroadcast(intent);
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Others selected = mOthers.get(thisPosition);
                oSingle = OthersSingleton.getInstance(v.getContext());
                oSingle.OthersDao().deleteOthers(o);
                Intent i = new Intent(holder.itemView.getContext(), CartActivity.class);
                Toast.makeText(holder.itemView.getContext(),"Item Removed",Toast.LENGTH_SHORT).show();
                holder.itemView.getContext().startActivity(i);
            }
        });


    }
    public float returnTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public int getItemCount() {
        return mOthers.size();

    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtName;
        Button btnRemove;

        public MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.combo_item,parent,false));
            itemView.setOnClickListener(this);
            txtName = itemView.findViewById(R.id.txtName);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            Others selected = mOthers.get(position);


//            Intent i = new Intent(v.getContext(), OthersDetailsActivity.class);
//            i.putExtra("Others",selected);
//            v.getContext().startActivity(i);

            notifyDataSetChanged();



        }
    }
}
