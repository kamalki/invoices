package com.example.kamal.incomingcallspecial;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    CodeLearnAdapter buckysAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private static String url = "http://kamal.stag.webnish.com/users.json";

    JSONArray jsonarray;
    private ProgressDialog pDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(swipe_refresh_layout);
//        swipeRefreshLayout.setOnRefreshListener(this);

        if (CheckInternet()){
            new GetContacts().execute();
        }else{

            SqliteHelper db = new SqliteHelper(this);
            final Cursor res = db.selectRecords();
            if(res.getCount() == 0) {
                showMessage("Alert!","No Customer Details Found In local database.");
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()){
//                buffer.append(""+res.getString(0)+"\n");
                buffer.append(""+res.getString(1)+"\n");
                buffer.append(""+res.getString(2)+"\n");
                buffer.append("" + res.getString(3) + "\n_______________________________________\n");
            }
            ListView listview = (ListView)findViewById(R.id.list123);
            String[] values = new String[] {buffer.toString()};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1,values);
            listview.setAdapter(adapter);
        }
    }
    private void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    public void onRefresh() {
        getData();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = null;
            try {
                jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {

                    jsonarray = new JSONArray(jsonStr);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            buckysAdapter = new CodeLearnAdapter(MainActivity.this, new JSONArray());
            ListView buckysListView = (ListView) findViewById(R.id.listView111);
            buckysListView.setAdapter(buckysAdapter);
            getData();
        }
        public void getData() {
            buckysAdapter.setData(jsonarray);
            buckysAdapter.notifyDataSetChanged();
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

    public void getData() {
        new GetContacts().execute();
        swipeRefreshLayout.setRefreshing(false);
        buckysAdapter.setData(jsonarray);
        buckysAdapter.notifyDataSetChanged();
    }
}
