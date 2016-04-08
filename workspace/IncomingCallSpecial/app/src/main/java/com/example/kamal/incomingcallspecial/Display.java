package com.example.kamal.incomingcallspecial;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Display extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String phone_number = "phone_number";
    public static final String remark = "remark";
    private String phone_no;
    private String currentDateandTime;
    private String remarki;


    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Bundle b = getIntent().getExtras();
//        phone_no = (String) b.getString("phone_number");
//        currentDateandTime = (String) b.getString("call_date");
//        remarki = (String) b.getString("remark");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        SqliteHelper db = new SqliteHelper(this);
        final Cursor cursor = db.selectRecords();
        String[] columns = new String[] {"Phone_number","Remark"};

        int [] widgets = new int[] {R.id.phone_number, R.id.userRemark};

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, columns, widgets, 0);
        listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(cursorAdapter);


//        ListView listview = (ListView)findViewById(R.id.list);
//        String[] values = new String[] {"UserId", "Phone_number","Remark"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,values);
//        listview.setAdapter(adapter);


//        TextView phone = (TextView) findViewById(R.id.etPhone);
//        TextView remark1 = (TextView) findViewById(R.id.etRemark);
//        SharedPreferences sharedpreferences = getSharedPreferences(PREFS_NAME, 0);
//
//        if (sharedpreferences.contains(phone_number)) {
//            phone.setText(sharedpreferences.getString(phone_number, ""));
//        }
//        if (sharedpreferences.contains(remark)) {
//            remark1.setText(sharedpreferences.getString(remark, ""));
//        }
    }
}