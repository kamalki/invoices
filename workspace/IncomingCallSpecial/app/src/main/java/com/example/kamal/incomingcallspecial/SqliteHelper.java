package com.example.kamal.incomingcallspecial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kamal on 25/3/16.
 */
public class SqliteHelper extends SQLiteOpenHelper {
    private static final String LOGCAT = null;

    public SqliteHelper(Context applicationcontext) {
        super(applicationcontext, "webnish.db", null, 1);
        Log.d(LOGCAT, "Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        query = "CREATE TABLE Users ( UserId INTEGER PRIMARY KEY AUTOINCREMENT, Phone_number TEXT, Call_date TEXT, Remark TEXT)";
        db.execSQL(query);
        Log.d(LOGCAT, "Users Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query;
        query = "DROP TABLE IF EXISTS Users";
        db.execSQL(query);
        onCreate(db);
    }

    public boolean insertLead( String phone_number,String currentDateandTime ,String remark) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Phone_number", phone_number);
        values.put("Call_date", currentDateandTime);
        values.put("Remark", remark);
        long result = db.insert("Users", null, values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor selectRecords() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + "Users", null);
        return res;
    }

    public Cursor particularRecords(String phone_number) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM Users where Phone_number='"+phone_number+"'";
        Cursor res = db.rawQuery(selectQuery, null);
        return res;
    }

}
