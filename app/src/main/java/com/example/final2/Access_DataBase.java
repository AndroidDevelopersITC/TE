package com.example.final2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

class Access_DataBase {
    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static Access_DataBase Instance;

    private Access_DataBase(Context context) {
        this.sqLiteOpenHelper = new My_DataBase(context);


    }

    public static Access_DataBase getInstance(Context context) {
        if (Instance == null)
            Instance = new Access_DataBase(context);

        return Instance;
    }

    public void Open() {
        this.sqLiteDatabase = this.sqLiteOpenHelper.getWritableDatabase();


    }

    public void Close() {
        if (this.sqLiteDatabase != null)
            this.sqLiteDatabase.close();


    }

    public long itmes_count() {
        return DatabaseUtils.queryNumEntries(sqLiteDatabase, My_DataBase.TB_NAME);


    }

    public ArrayList<LibContans> get_items() {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  " + My_DataBase.TB_NAME, null);
        ArrayList<LibContans> arrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
//int id_book, String name_book, int amount_book, int price_book
            do {
                int id = cursor.getInt(cursor.getColumnIndex(My_DataBase.CLN1_ID));
                String name_book = cursor.getString(cursor.getColumnIndex(My_DataBase.CLN2_NAME));
                int price_book = cursor.getInt(cursor.getColumnIndex(My_DataBase.CLN3_PRICE));
                int amount_book = cursor.getInt(cursor.getColumnIndex(My_DataBase.CLN4_AMOUNT));
                arrayList.add(new LibContans(id, name_book, amount_book, price_book));

            } while (cursor.moveToNext());
            cursor.close();


        }

        return arrayList;
    }

    public boolean update_itmes(LibContans items, String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(My_DataBase.CLN4_AMOUNT, items.getAmount_book());
//        contentValues.put(My_DataBase.CLN_NAME, items.getName_book());
        //  contentValues.put(My_DataBase.CLN4_AMOUNT, items.getPrice_book());


        String agrs[] = {name};

        int s = sqLiteDatabase.update(My_DataBase.TB_NAME, contentValues, " name =?", agrs);
        if (s == 0)
            return false;
        return true;


    }

    public LibContans search_items(String nameBook) {
        String a[] = {nameBook};
        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM " + My_DataBase.TB_NAME + " where name  = ? ", a);
        LibContans libContans = null;
        if (cursor.moveToFirst()) {


            do {
                int id = cursor.getInt(cursor.getColumnIndex(My_DataBase.CLN1_ID));

                String name = cursor.getString(cursor.getColumnIndex(My_DataBase.CLN2_NAME));
                int amount = cursor.getInt(cursor.getColumnIndex(My_DataBase.CLN4_AMOUNT));
                int price = cursor.getInt(cursor.getColumnIndex(My_DataBase.CLN3_PRICE));

                libContans = new LibContans(id, name, amount, price);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return libContans;
    }
    public boolean insert_itmes(LibContans items) {
        ContentValues contentValues = new ContentValues();// بنستخدمو عشان نطلع القيم
        contentValues.put(My_DataBase.CLN1_ID, items.getId_book());
        contentValues.put(My_DataBase.CLN2_NAME, items.getName_book());
        contentValues.put(My_DataBase.CLN3_PRICE, items.getPrice_book());
        contentValues.put(My_DataBase.CLN4_AMOUNT, items.getAmount_book());
        long s =  sqLiteDatabase.insert(My_DataBase.TB_NAME, null, contentValues);

        if (s == -1) {
            return false;

        }
        return true;

    }
    public boolean remove_itmes(String name) {

        String agrs[] = {name};

        int s = sqLiteDatabase.delete(My_DataBase.TB_NAME, " name = ?", agrs);
        if (s == 0)
            return false;
        return true;


    }

}
