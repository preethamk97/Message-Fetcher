package com.example.preetham.msg1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    EditText editTextUserName;
    EditText editTextEmail;
    EditText editTextPassword;
    SqliteHelper sqliteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sqliteHelper = new SqliteHelper(this);
    }

    public void register(View view) {
        editTextEmail = (EditText) findViewById(R.id.editText6);
        editTextPassword = (EditText) findViewById(R.id.editText5);
        editTextUserName = (EditText) findViewById(R.id.editText4);
        if (validate()) {
            String UserName = editTextUserName.getText().toString();
            String Email = editTextEmail.getText().toString();
            String Password = editTextPassword.getText().toString();

            //Check in the database is there any user associated with  this email
            if (!sqliteHelper.isEmailExists(Email)) {

                //Email does not exist now add new user to database
                sqliteHelper.addUser(new User(null, UserName, Email, Password));

                Toast.makeText(getApplicationContext(), "User created successfully! Please Login",
                        Toast.LENGTH_LONG).show();

            }else {

                //Email exists with email input provided so show error user already exist

                Toast.makeText(getApplicationContext(), "User already exists with same email ",
                        Toast.LENGTH_LONG).show();
            }


        }
    }
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String UserName = editTextUserName.getText().toString();
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        //Handling validation for UserName field
        if (UserName.isEmpty()) {
            valid = false;

            Toast.makeText(getApplicationContext(), "Please enter valid username!",
                    Toast.LENGTH_LONG).show();
        } else {
            if (UserName.length() > 2) {
                valid = true;

            } else {
                valid = false;

                Toast.makeText(getApplicationContext(), "Username is to short!",
                        Toast.LENGTH_LONG).show();
            }
        }

        //Handling validation for Email field
        if (Email.isEmpty()) {
            valid = false;

            Toast.makeText(getApplicationContext(), "Please enter valid email!",
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

    public void bactologin(View view) {
        Intent intent2=new Intent(this,MainActivity.class);
        startActivity(intent2);
    }
}
