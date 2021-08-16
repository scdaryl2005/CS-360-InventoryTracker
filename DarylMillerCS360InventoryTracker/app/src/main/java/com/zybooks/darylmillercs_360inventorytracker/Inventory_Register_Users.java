package com.zybooks.darylmillercs_360inventorytracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/* Initialize Inventory_Register_Users */
public class Inventory_Register_Users extends AppCompatActivity {

    private Inventory_DB Inventory_DB;
    private EditText Username;
    private EditText Password;
    private EditText Confirm;
    private Button Login;
    private TextView Error;

    /* onCreate method for create_an_account.xml */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_an_account);

        /* Get Inventory_DB */
        Inventory_DB = Inventory_DB.getInstance(getApplicationContext());

        /* Utilize all elements in create_an_account.xml */
        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        Confirm = findViewById(R.id.Confirm);
        Login = findViewById(R.id.Login);
        Error = findViewById(R.id.Error);

        /* addTextChangedListener to ensure "Confirm Password" has been updated */
        Confirm.addTextChangedListener(confirmPasswordTextChangeListener);

        /* Event listener for "Register" button */
        Login.setOnClickListener(registerOnClickListener);

    }

    /* TextWatcher to ensure Password and Confirm Password match */
    private final TextWatcher confirmPasswordTextChangeListener = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String passwordTemp = Password.getText().toString();
            String confirmPasswordTemp = Confirm.getText().toString();

            if(!confirmPasswordTemp.equals(passwordTemp)) {
                System.out.println("NOT EQUAL");
                Login.setEnabled(false);

            }

            else {
                System.out.println("EQUAL");
                Login.setEnabled(true);

            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }

    };

    /* onCLickListener to register new user */
    private final View.OnClickListener registerOnClickListener = new View.OnClickListener() {
        public void onClick(View v)
        {
            /* Register new user */
            if(!userExists()) {
                createNewUser();
                Intent intent = new Intent(Inventory_Register_Users.this, MainActivity.class);
                startActivity(intent);

            }

            /* Display error message that user is already in the DB */
            else {
                Error.setVisibility(TextView.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(() -> Error.setVisibility(TextView.INVISIBLE), 10000);

            }

        }

    };

    /* Create New User */
    private void createNewUser() {
        Inventory_Users newUser = new Inventory_Users(Username.getText().toString(), Password.getText().toString());
        Inventory_DB.addUser(newUser);

    }

    /* User exists in Inventory_Users */
    private boolean userExists() {
        Inventory_Users tempUser = new Inventory_Users(Username.getText().toString(), Password.getText().toString());
        return Inventory_DB.getUser(tempUser) != null;

    }

}