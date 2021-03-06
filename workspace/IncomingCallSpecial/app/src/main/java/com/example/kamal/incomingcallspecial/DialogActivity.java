package com.example.kamal.incomingcallspecial;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

public class DialogActivity extends AppCompatActivity {

    private String phone_number;
    private String currentDateandTime;
    public String text;
    private ProgressDialog pDialog;
    private String text1;
    private TextView call_pickup;



    SqliteHelper myDb;
    private String result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle b = getIntent().getExtras();
        phone_number = (String) b.getString("phone_number");
        currentDateandTime = (String) b.getString("call_date");
        text1 = (String) b.getString("call_duration");

        try {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.setFinishOnTouchOutside(false);
            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_dialog);

            if (CheckInternet()) {

                new GetContacts().execute();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showMessage("Last Remark", phone_number + "\n" + text);
                    }
                }, 1500);

            } else {

                myDb = new SqliteHelper(this);
                Cursor res = myDb.particularRecords(phone_number);
                if (res.getCount() == 0) {
                    showMessage("Alert", "No Remark Found of mobile number: " + phone_number);
                    return;
                }
                if (res != null && res.getCount() > 0) {
                    res.moveToLast();
                    result = (res.getString(1) + "\n" + res.getString(3));
                }
                showMessage("Last Remark", result);
            }

        }  catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setPositiveButton("CUSTOMER CALL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent in = new Intent(DialogActivity.this, RemarkActivity.class);
                in.putExtra("phone_number", phone_number);
                in.putExtra("call_date", currentDateandTime);
                startActivity(in);
            }
        });
        builder.setNegativeButton("PRIVATE CALL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (CheckInternet()){
                    try {
                        makeHTTPPOSTRequest();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (HttpException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            DialogActivity.this.finish();
//                    this.setFinishOnTouchOutside(false);
                            System.exit(0);
                        }
                    },500);

                }else {
                    myDb = new SqliteHelper(DialogActivity.this);

                    myDb.insertLead(phone_number,currentDateandTime,"It was personal call.");

                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            DialogActivity.this.finish();
//                    this.setFinishOnTouchOutside(false);
                            System.exit(0);
                        }
                    }, 500);
                }
            }
        });

        AlertDialog a=builder.create();
        a.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams star = a.getWindow().getAttributes();
        star.gravity = Gravity.TOP | Gravity.CENTER;
        a.show();

        Button buttonbackground = a.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonbackground.setTextColor(Color.RED);

        Button buttonbackground1 = a.getButton(DialogInterface.BUTTON_POSITIVE);
        buttonbackground1.setTextColor(Color.BLUE);
    }

    public class GetContacts extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(DialogActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected String doInBackground(Void... params) {
            ServiceHandler sh = new ServiceHandler();
            String jsonStr = null;
            try {
                jsonStr = sh.makeServiceCall(("http://kamal.stag.webnish.com/users/"+ phone_number +".json"), ServiceHandler.GET);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {

                    JSONObject jsonObject = new JSONObject(jsonStr);
                    text=jsonObject.getString("remark");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
            return text;

        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
        }
    }

    private boolean CheckInternet() {
        ConnectivityManager connec = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo mobile = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isConnected()) {
            return true;
        } else if (mobile.isConnected()) {
            return true;
        }
        return false;
    }


    private void makeHTTPPOSTRequest() throws URISyntaxException, IOException, HttpException {
            RequestParams params = new RequestParams();
            AsyncHttpClient client = new AsyncHttpClient();
            params.put("phone_number", phone_number);
            params.put("call_date", currentDateandTime);
            params.put("remark", "It was personal call.");

            RequestHandle post = client.post("http://kamal.stag.webnish.com/users.json", params, new JsonHttpResponseHandler() {
                public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {

                }
            });
    }
}
