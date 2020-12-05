package com.example.firehousesubsapp.AdapterHandler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firehousesubsapp.AllActivity.OrderDetailsActivity;
import com.example.firehousesubsapp.Pojo.Order;
import com.example.firehousesubsapp.R;
import com.example.firehousesubsapp.SIngletonHandler.OrderSingleton;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    OrderSingleton oSingle;
    private List<Order> mOrder;
    Order o;
    float subTotal;
    float total;


    public OrderAdapter(List<Order> order){
        mOrder = order;
    }


    public void test() {

    }
    @NonNull
    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(layoutInflater,parent);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.MyViewHolder holder, int position) {
        Order o = mOrder.get(position);

        float fullPrice = Float.parseFloat(o.getPrice()) * o.getQuantity();

        subTotal = subTotal + fullPrice;
        setTotal(subTotal);

        Log.e("Subtotal",Float.toString(subTotal));
        Log.e("total",Float.toString(total));
        holder.txtName.setText(o.getName());

        String s = String.format("%.2f", fullPrice);
        holder.txtPrice.setText("$"+s);
        holder.txtQnty.setText("Qty: "+ Integer.toString(o.getQuantity()));
        Context actContext = holder.itemView.getContext();

        Intent intent = new Intent("message_subject_intent");
        intent.putExtra("name" , Float.toString(returnTotal()));
        LocalBroadcastManager.getInstance(holder.itemView.getContext()).sendBroadcast(intent);


        if(o.isWhite()) {

            holder.txtCheese.setText(o.getSizeSelected() + " Sub on" + " White bread");
        }
        else{

            holder.txtCheese.setText(o.getSizeSelected() + " Sub on"  + " Wheat bread");
        }


    }
    public float returnTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public int getItemCount() {
        return mOrder.size();

    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtQnty;
        TextView txtName;
        TextView txtCheese;
        TextView txtPrice;
        ImageView imgPic;

        public MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.cart_item,parent,false));
            itemView.setOnClickListener(this);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtCheese = itemView.findViewById(R.id.txtDetails);
            txtQnty = itemView.findViewById(R.id.txtQnty);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            Order selected = mOrder.get(position);


            Intent i = new Intent(v.getContext(), OrderDetailsActivity.class);
            i.putExtra("order",selected);
            v.getContext().startActivity(i);

            notifyDataSetChanged();



        }
    }
}
