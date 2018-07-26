package com.example.preetham.msg1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSION_RE_SMS_RECEIVE = 10;
    SqliteHelper sqliteHelper;
    EditText mail;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},MY_PERMISSION_RE_SMS_RECEIVE);
        sqliteHelper = new SqliteHelper(this);

    }


    public void Sign(View view){
         mail=findViewById(R.id.editText);
         password=findViewById(R.id.editText2);




        if (validate()) {

            //Get values from EditText fields
            String Email = mail.getText().toString();
            String Password = password.getText().toString();

            //Authenticate user
            User currentUser = sqliteHelper.Authenticate(new User(null, null, Email, Password));

            //Check Authentication is successful or not
            if (currentUser != null) {

                Toast.makeText(getApplicationContext(), "Successfully Logged in!!",
                        Toast.LENGTH_LONG).show();
                //User Logged in Successfully Launch You home screen activity
                        Intent intent=new Intent(this,ContactList.class);
                        startActivity(intent);
                        finish();
            } else {

                //User Logged in Failed
                Toast.makeText(getApplicationContext(), "Failed to log in , please try again",
                        Toast.LENGTH_LONG).show();

            }
        }

    }
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String Email = mail.getText().toString();
        String Password = password.getText().toString();

        //Handling validation for Email field
        if (Email.isEmpty()) {
            valid = false;

            Toast.makeText(getApplicationContext(), "Please enter valid email!!",
                    Toast.LENGTH_LONG).show();
        } else {
            valid = true;

        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;

            Toast.makeText(getApplicationContext(), "Please enter valid password!",
                    Toast.LENGTH_LONG).show();
        } else {
            if (Password.length() > 5) {
                valid = true;

            } else {
                valid = false;

                Toast.makeText(getApplicationContext(), "Password is to short!",
                        Toast.LENGTH_LONG).show();
            }
        }

        return valid;
    }

    public void signup(View view) {

        Intent intent1=new Intent(this,signup.class);
        startActivity(intent1);
    }
}

