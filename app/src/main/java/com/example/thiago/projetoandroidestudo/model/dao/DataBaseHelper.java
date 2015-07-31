package com.example.thiago.projetoandroidestudo.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Thiago on 26/07/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper{

    public static final String BANCO_DADOS = "MY_DATABASE";
    public static final int VERSION = 1;

    public DataBaseHelper(Context context){
        super(context, DataBaseHelper.BANCO_DADOS, null, DataBaseHelper.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ClientContract.getCreateTableClienteSql());
        db.insert(ClientContract.TABLE, null, ClientContract.getContentValuesAdm());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
