package com.zybooks.darylmillercs_360inventorytracker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Inventory_List extends AppCompatActivity {

    /* Initialize Activity */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_overview); // Utilize inventory_list.xml file

        /* Utilize Add_Inventory, Recycler_View and the Inventory DB to support inventory_overview.xml */
        Button add_inventory = findViewById(R.id.Add_Inventory);

        Inventory_DB inventory_db = Inventory_DB.getInstance(getApplicationContext());

        RecyclerView recycler_view = findViewById(R.id.Recycler_View);

        /* Produce Inventory List */
        Inventory_Recycle myAdapter = new Inventory_Recycle(this, inventory_db.getItems(), inventory_db);
        recycler_view.setAdapter(myAdapter);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        System.out.println(inventory_db.getItems().size());

        /* Method to link add_inventory and event listener */
        add_inventory.setOnClickListener(addItemOnClickListener);

        /* Get or Request Permission to send SMS */
        if(getApplicationContext().checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this, "Granted", Toast.LENGTH_LONG);
        }
        else{
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);  // Pop up asking for permission to receive SMS
        }
    }

    /* Method to override method inherited from super class */
    private final View.OnClickListener addItemOnClickListener = v -> {
        Intent intent = new Intent(Inventory_List.this, Inventory_New.class);
        startActivity(intent);

    };

}