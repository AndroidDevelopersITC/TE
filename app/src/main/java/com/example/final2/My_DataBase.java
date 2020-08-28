package com.example.final2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

class My_DataBase extends SQLiteAssetHelper {
    public static final String DB_NAME = "mydb.db";
    public static final String TB_NAME = "tb";
    public static final String CLN1_ID = "ID";
    public static final String CLN2_NAME = "name";
    public static final String CLN3_PRICE = "price";
    public static final String CLN4_AMOUNT = "amount";
    public static final int DB_VERSION = 1;

    public My_DataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
}
