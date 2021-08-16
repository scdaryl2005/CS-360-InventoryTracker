package com.zybooks.darylmillercs_360inventorytracker;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/* Execute MainActivity */
public class MainActivity extends AppCompatActivity {

    private Inventory_DB Inventory_DB;
    private TextView Authentication_Failed;
    private EditText Username;
    private EditText Password;

    /* Initialize instance */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Use the activity_main.xml file

        Inventory_DB = Inventory_DB.getInstance(getApplicationContext());  // Get the database

        /* Utilize all elements of the xml file */
        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        Button register_New_User = findViewById(R.id.Register_New_User);
        Button login = findViewById(R.id.Login);
        Authentication_Failed = findViewById(R.id.Authentication_Failed);

        /* Utilize the event listener for the Register and Login buttons */
        register_New_User.setOnClickListener(registerButtonOnClickListener);
        login.setOnClickListener(loginButtonOnClickListener);

        /* Get permissions to send SMS */
        getApplicationContext().checkSelfPermission(Manifest.permission.SEND_SMS);

    }

    /* OnClickListener for Register button */
    private final View.OnClickListener registerButtonOnClickListener = v -> {
        Intent intent = new Intent(MainActivity.this, Inventory_Register_Users.class);
        startActivity(intent);

    };

    /* OnClickListener for Login button */
    private final View.OnClickListener loginButtonOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {

            /* Successful login and go to Inventory_List screen */
            if(verifyLogin()) {
                Intent intent = new Intent(MainActivity.this, Inventory_List.class);
                startActivity(intent);

            }

            /* Show error message for authentication failed */
            else {
                Authentication_Failed.setVisibility(TextView.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(() -> Authentication_Failed.setVisibility(TextView.INVISIBLE), 10000);

            }

        }

    };

    /* Authenticate User */
    private boolean verifyLogin() {
        Inventory_Users tempUser = new Inventory_Users(Username.getText().toString(), Password.getText().toString());
        Inventory_Users dbUser = Inventory_DB.getUser(tempUser);

        if(dbUser != null) {
            return tempUser.getPassword().equals(dbUser.getPassword());

        }

        else

            return false;

    }

}