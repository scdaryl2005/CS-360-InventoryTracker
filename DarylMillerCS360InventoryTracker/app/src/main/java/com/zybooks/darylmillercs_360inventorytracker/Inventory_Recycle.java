package com.zybooks.darylmillercs_360inventorytracker;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Inventory_Recycle extends RecyclerView.Adapter<Inventory_Recycle.MyViewHolder> {
    private final Context context;

    private final List<Item> Inventory_List;

    private final Inventory_DB Inventory_DB;

    public Inventory_Recycle(Context c, List<Item> items, Inventory_DB db) {
        context = c;

        Inventory_List = items;

        Inventory_DB = db;

    }

    /* onCreateViewHolder for inventory_list.xml */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.inventory_list, parent, false);
        return new MyViewHolder(view);

    }

    /* onBindViewHolder for inventory_list.xml */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemTitle.setText(Inventory_List.get(position).getTitle());
        holder.count.setText(String.valueOf(Inventory_List.get(position).getCount()));

        /* Increase Quantity */
        holder.increase.setOnClickListener(v -> {
            Intent intent = new Intent(context, Inventory_List.class);
            Inventory_DB.increaseQuantity(Inventory_List.get(position).getId(), Inventory_List.get(position).getCount());
            context.startActivity(intent);

        });

        /* Decrease Quantity */
        holder.decrease.setOnClickListener(v -> {
            Intent intent = new Intent(context, Inventory_List.class);
            Inventory_DB.decreaseQuantity(Inventory_List.get(position).getId(), Inventory_List.get(position).getCount());

            if(Inventory_List.get(position).getCount() - 1 == 0) {  // Send SMS if quantity is zero
                if(context.checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) // Check to make sure permissions are enabled to send SMS
                    sendSMS(Inventory_List.get(position).getTitle());

            }

            context.startActivity(intent);

        });

        /* Delete Inventory Item */
        holder.delete.setOnClickListener(v -> {
            Intent intent = new Intent(context, Inventory_List.class);
            Inventory_DB.deleteInventory(Inventory_List.get(position).getId());
            context.startActivity(intent);

        });

    }

    /* getItemCount of inventory_list after recycler_view adjustments */
    @Override
    public int getItemCount() {
        return Inventory_List.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle, count;
        Button increase, decrease, delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.Item);
            count = itemView.findViewById(R.id.Quantity_Number);
            delete = itemView.findViewById(R.id.Delete);
            increase = itemView.findViewById(R.id.Increase);
            decrease = itemView.findViewById(R.id.Decrease);
        }
    }

    /* Send SMS */
    private void sendSMS(String count) {

        String phoneNumber = "15555215554";  // phone number prefix (1555521) and emulator suffix (5554)

        String message = "The quantity of " + count + " has reached zero";  // String message delivered in the SMS

        SmsManager smsManager = SmsManager.getDefault();

        smsManager.sendTextMessage(phoneNumber, null, message, null, null);

    }
}
