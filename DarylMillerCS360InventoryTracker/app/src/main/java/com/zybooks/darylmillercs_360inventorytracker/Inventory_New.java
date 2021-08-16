package com.zybooks.darylmillercs_360inventorytracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Inventory_New extends AppCompatActivity {

    private TextView New_Inventory_Title;
    private EditText New_Inventory_Description;
    private EditText New_Inventory_Quantity;
    private TextView New_Inventory_Error;
    private Inventory_DB Inventory_DB;

    /* Initialize Activity */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_add_inventory);  // Use inventory_add_inventory.xml file

        /* Utilize items in .xml file */
        New_Inventory_Title = findViewById(R.id.New_Inventory_Item_Name);
        New_Inventory_Description = findViewById(R.id.New_Inventory_Description);
        New_Inventory_Quantity = findViewById(R.id.New_Inventory_Quantity);
        New_Inventory_Error = findViewById(R.id.New_Inventory_Error);
        Button create_Item = findViewById(R.id.New_Inventory_Create);
        Inventory_DB = Inventory_DB.getInstance(getApplicationContext());

        /* Method to link add_inventory and event listener */
        create_Item.setOnClickListener(addItemOnClickListener);

    }

    /* onClickListener for adding a new item button */
    private final View.OnClickListener addItemOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {

            /* Valid Addition */
            if(validateInput()) {
                addNewItem();
                Intent intent = new Intent(Inventory_New.this, Inventory_List.class);
                startActivity(intent);

            }

            /* Error adding new Inventory Item */
            else {
                New_Inventory_Error.setVisibility(TextView.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(() -> New_Inventory_Error.setVisibility(TextView.INVISIBLE), 10000);

            }

        }
    };

    /* Add new Inventory Item */
    private void addNewItem() {
        Item newItem = new Item(-1, New_Inventory_Description.getText().toString(), New_Inventory_Title.getText().toString(), Integer.parseInt(New_Inventory_Quantity.getText().toString()));
        Inventory_DB.addItem(newItem);

    }

    /* Validate new Inventory Item */
    private boolean validateInput() {
        String title = New_Inventory_Title.getText().toString();
        String description = New_Inventory_Description.getText().toString();
        boolean validInput = true;
        int count;

        try {
            count = Integer.parseInt(New_Inventory_Quantity.getText().toString());

        }

        catch (NumberFormatException e) {
            return false;

        }

        if(title.isEmpty() || description.isEmpty() || count < 0)
            validInput = false;

        return validInput;

    }

}
