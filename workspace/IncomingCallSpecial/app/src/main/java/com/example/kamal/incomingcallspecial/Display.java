package com.example.kamal.incomingcallspecial;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Display extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Bundle b = getIntent().getExtras();
//        String bufferstring = (String) b.getString("bufferstring");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
//        TextView buffer = (TextView) findViewById(R.id.buffer);
//        buffer.setText(bufferstring);

        SqliteHelper db = new SqliteHelper(this);
        final Cursor res = db.selectRecords();

//        String[] columns = new String[] {"UserId","Phone_number","Call_date","Remark"};
//        int [] widgets = new int[] {R.id.userid, R.id.phone_number,R.id.call_date, R.id.userRemark};
//        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, res, columns, widgets, 0);
//        ListView listView = (ListView)findViewById(R.id.list);
//        listView.setAdapter(cursorAdapter);

        if(res.getCount() == 0) {
            showMessage("Error","No Customer Details Found.");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Id :"+res.getString(0)+"\n");
            buffer.append("Phone_number :"+res.getString(1)+"\n");
            buffer.append("Call_date :"+res.getString(2)+"\n");
            buffer.append("Remark :" + res.getString(3) + "\n\n");
        }
        ListView listview = (ListView)findViewById(R.id.list);
        String[] values = new String[] {buffer.toString()};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Display.this, android.R.layout.simple_list_item_1, android.R.id.text1,values);
        listview.setAdapter(adapter);
    }
    private void showMessage(String title, String Message) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(Message);
            builder.show();
    }
}