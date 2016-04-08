package com.example.kamal.incomingcallspecial;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

        
        new GetContacts().execute();
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
    public void getData() {
        new GetContacts().execute();
        swipeRefreshLayout.setRefreshing(false);
        buckysAdapter.setData(jsonarray);
        buckysAdapter.notifyDataSetChanged();
    }
}
