package com.example.thiago.projetoandroidestudo.model.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.thiago.projetoandroidestudo.model.entities.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thiago on 26/07/2015.
 */
public class ClientContract  {

    public static final String TABLE = "cliente";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String AGE = "age";
    public static final String EMAIL = "email";
    public static final String CREATE_TABLE = " CREATE TABLE ";
    public static final String USERNAME = "usuario";
    public static final String PASSWORD = "senha";


    public static final String[] COLUMNS = {ID, NAME, AGE, EMAIL, PHONE};
    public static final String[] COLUMNS_PERSIST_ADM = {ID, NAME, AGE, EMAIL, PHONE, USERNAME, PASSWORD};
    public static final String[] COLUMNS_ADM = {USERNAME, PASSWORD};
    public static ContentValues contentValues;


    public static String getCreateTableClienteSql(){
        StringBuilder sql = new StringBuilder()
                .append(CREATE_TABLE)
                .append(TABLE).append(" ( ")
                .append(ID + " INTEGER PRIMARY KEY, ")
                .append(NAME + " TEXT, ")
                .append(AGE + " INTEGER, ")
                .append(EMAIL + " TEXT, ")
                .append(PHONE + " TEXT, ")
                .append(USERNAME + " TEXT, ")
                .append(PASSWORD + " TEXT ")
                .append(" ); ");

        return sql.toString();
    }


    public static ContentValues getContentValues(Cliente cliente){
        ContentValues values = new ContentValues();
        values.put(ClientContract.NAME, cliente.getNome());
        values.put(ClientContract.AGE, cliente.getIdade());
        values.put(ClientContract.EMAIL, cliente.getEmail());
        values.put(ClientContract.PHONE, cliente.getTelefone());
        return values;
    }

    public static ContentValues getContentValuesAdm(){
        ContentValues values = new ContentValues();
        values.put(ClientContract.NAME, "Adm");
        values.put(ClientContract.AGE, 1);
        values.put(ClientContract.EMAIL, "teste");
        values.put(ClientContract.PHONE, "1111");
        values.put(ClientContract.USERNAME, "Adm");
        values.put(ClientContract.PASSWORD, "Adm");
        return values;
    }

    private static Cliente bind(Cursor cursor){
        if(!cursor.isBeforeFirst() || cursor.moveToNext()){
            Cliente cliente = new Cliente();
            cliente.setId(cursor.getInt(cursor.getColumnIndex(ClientContract.ID)));
            cliente.setNome(cursor.getString(cursor.getColumnIndex(ClientContract.NAME)));
            cliente.setIdade(cursor.getInt(cursor.getColumnIndex(ClientContract.AGE)));
            cliente.setEmail(cursor.getString(cursor.getColumnIndex(ClientContract.EMAIL)));
            cliente.setTelefone(cursor.getString(cursor.getColumnIndex(ClientContract.PHONE)));
            return cliente;
        }
        return null;
    }

    public static List<Cliente> bindList(Cursor cursor){
        List<Cliente> serviceOrder = new ArrayList<>();
        while(cursor.moveToNext()){
            serviceOrder.add(bind(cursor));
        }
        return serviceOrder;
    }

    public static Cliente bindClientAdm(Cursor cursor){
        List<Cliente> serviceOrder = new ArrayList<>();
        serviceOrder.add(bind(cursor));
        return serviceOrder.get(0);
    }

}
