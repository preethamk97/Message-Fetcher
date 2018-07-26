package com.example.preetham.msg1;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Send_msg extends AppCompatActivity {
    //   private static final int MY_PERMISSION_RE_SMS_RECEIVE = 10;

    String phonenum, message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_send_msg);
        Bundle b = getIntent().getExtras();
        phonenum = b.getString("phone");
        // int  phoneNumber=Integer.parseInt(phonenumber);
        Button startBtn = (Button) findViewById(R.id.button4);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText et = findViewById(R.id.editText3);
                message = et.getText().toString();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

                //Get the SmsManager instance and call the sendTextMessage method to send message
                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(phonenum, null, message, pi,null);

                Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                        Toast.LENGTH_LONG).show();
            }
        });

    }


}


