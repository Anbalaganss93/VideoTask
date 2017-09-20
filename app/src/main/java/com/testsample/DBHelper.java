package com.testsample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.testsample.dto.DatabaseModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Android on 19-Sep-17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "Regitration";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_EMAIL = "email_address";
    public static final String CONTACTS_COLUMN_PASSWORD = "password";
    private HashMap hp;
    private clearRegister clearRegister;

    public DBHelper(Context context, MainActivity mainActivity) {
        super(context, DATABASE_NAME, null, 1);
        this.clearRegister=mainActivity;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table Regitration" +
                        "(id integer primary key, email_address text,password text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Regitration");
        onCreate(db);
    }

    public boolean insertContact(String email_address, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email_address", email_address);
        contentValues.put("password", password);
        db.insert("Regitration", null, contentValues);
        clearRegister.cleardata();
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Regitration where id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact(Integer id, String email_address, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email_address", email_address);
        contentValues.put("password", password);
        db.update("Regitration", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteContact(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Regitration",
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList<DatabaseModel> getAllCotacts() {
        ArrayList<DatabaseModel> array_list = new ArrayList<DatabaseModel>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Regitration", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            DatabaseModel mdatabaseModel=new DatabaseModel();
            mdatabaseModel.setEmail(res.getString(res.getColumnIndex(CONTACTS_COLUMN_EMAIL)));
            mdatabaseModel.setPassword(res.getString(res.getColumnIndex(CONTACTS_COLUMN_PASSWORD)));
            array_list.add(mdatabaseModel);
            res.moveToNext();
        }
        return array_list;
    }

    public interface clearRegister{
        void cleardata();
    }
}