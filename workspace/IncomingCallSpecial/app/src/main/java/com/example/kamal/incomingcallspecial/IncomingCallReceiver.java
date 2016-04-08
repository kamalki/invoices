package com.example.kamal.incomingcallspecial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kamal on 17/2/16.
 */
public class IncomingCallReceiver extends BroadcastReceiver {

    public static int state = 0;
    public static long ringing_started = 0;
    public static String phone_number = "";
    public static String current_date = "";

    private String text1;


    @Override
    public void onReceive( final Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (null == bundle)
            return;

        Log.i("IncomingCallReceiver", bundle.toString());
        String state = bundle.getString(TelephonyManager.EXTRA_STATE);
        Log.i("IncomingCallReceiver", "State: " + state);

        if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
            String phone_no = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            IncomingCallReceiver.phone_number = phone_no;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String currentDateandTime = sdf.format(new Date());
            IncomingCallReceiver.current_date = currentDateandTime;

            {
                intent = new Intent(context, DialogActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("phone_number", IncomingCallReceiver.phone_number);
                intent.putExtra("call_date", IncomingCallReceiver.current_date);
                intent.putExtra("call_duration", text1);

            }
            final Intent finalIntent = intent;
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    context.startActivity(finalIntent);
                }
            },2000);

            IncomingCallReceiver.state = 1; // STORE CALL STATE
            IncomingCallReceiver.ringing_started = System.currentTimeMillis(); // NOTE DOWN STARTING TIME

        }else {
            if (IncomingCallReceiver.state == 1) {
                String call_stat = "MISSED";
                if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(state)) {
                    call_stat = "RECIEVED";
                }

                try {
                    int time_taken = (int) (System.currentTimeMillis() - IncomingCallReceiver.ringing_started);
                    FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory() + "Pickup.txt", true);
                    fos.write((call_stat + " " + (time_taken / 1000) + "sec \n\n").getBytes());
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                File Root = Environment.getExternalStorageDirectory();
                File file = new File(Root,"Pickup.txt");
                StringBuilder text = new StringBuilder();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = br.readLine()) != null) {
                        text.append(line);
                        text.append('\n');
                    }
                    br.close();
                    text1 = text.toString();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                // RESET STATES
                IncomingCallReceiver.state = 0;
                IncomingCallReceiver.phone_number = "";
                IncomingCallReceiver.ringing_started = 0;
            }
        }
    }
}

