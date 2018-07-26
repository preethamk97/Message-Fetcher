package com.example.preetham.msg1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Establish_connection_trust extends AppCompatActivity {
    public static String name,phone_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establish_connection_trust);
        Bundle b=getIntent().getExtras();

        name=b.getString("name").toString();

        phone_number=b.get("phone_no").toString();
        TextView name_textview=(TextView)findViewById(R.id.textView);
        TextView phone_textview=(TextView)findViewById(R.id.textView3);
        name_textview.setText(name);
        phone_textview.setText(phone_number);

    }

    public void Confirm(View view) {

        Intent intent=new Intent(this,Send_msg.class);
        intent.putExtra("name",name);
        intent.putExtra("phone",phone_number);
        startActivity(intent);
    }

    public void read(View view) {
        Intent intent=new Intent(this,Read_sms.class);
        startActivity(intent);
    }
}

