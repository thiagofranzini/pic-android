package com.example.thiago.projetoandroidestudo.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.thiago.projetoandroidestudo.model.entities.Cliente;
import com.example.thiago.projetoandroidestudo.model.entities.Usuario;
import com.example.thiago.projetoandroidestudo.util.AppUtil;

/**
 * Created by Thiago on 27/07/2015.
 */
public final class ClienteDatabaseDAO implements IClienteDataBaseDAO {


    private static ClienteDatabaseDAO singletonInstance;
    private List<Cliente> listaClientes;

    private ClienteDatabaseDAO(){
        this.listaClientes = new ArrayList<>();
    }

    public static ClienteDatabaseDAO getInstance(){
        if(singletonInstance == null){
            singletonInstance = new ClienteDatabaseDAO();
        }
        return singletonInstance;
    }

    @Override
    public void save(Cliente cliente) {
        DataBaseHelper helper = new DataBaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = ClientContract.getContentValues(cliente);
        try{
            if(cliente.getId() == null){
                db.insert(ClientContract.TABLE, null, values);
            }
            else{
                String where = ClientContract.ID + " = ?";
                String[] args = {cliente.getId().toString()};
                db.update(ClientContract.TABLE, values, where, args);
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            db.close();
            helper.close();
        }
    }

    @Override
    public List<Cliente> getAll() {
        DataBaseHelper helper = new DataBaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(ClientContract.TABLE, ClientContract.COLUMNS, null, null, null, null, ClientContract.NAME);

        List<Cliente> clientes = ClientContract.bindList(cursor);
        db.close();
        helper.close();
        return clientes;
    }

    @Override
    public void delete(Cliente cliente) {
        DataBaseHelper helper = new DataBaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        String where = ClientContract.ID + " = ?";
        String[] args = {cliente.getId().toString()};
        db.delete(ClientContract.TABLE, where, args);
        db.close();

    }

    @Override
    public boolean verificarSenhaAdm(Usuario usuario) {
        DataBaseHelper helper = new DataBaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        String where = ClientContract.USERNAME + " = ? AND "+ ClientContract.PASSWORD + " = ? ";
        String[] args = {usuario.getUsuario(), usuario.getSenha()};

        try {
            return db.query(ClientContract.TABLE, ClientContract.COLUMNS_ADM, where, args, null, null, null, null).moveToFirst();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        finally {
            db.close();
        }
    }

    @Override
    public void saveUser(Usuario usuario) {
        DataBaseHelper helper = new DataBaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = ClientContract.getContentValuesAdm();
        try{
            if(usuario.getId() == null){
                db.insert(ClientContract.TABLE, null, values);
            }
            else{
                String where = ClientContract.ID + " = ?";
                String[] args = {usuario.getId().toString()};
                db.update(ClientContract.TABLE, values, where, args);
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            db.close();
            helper.close();
        }
    }

    /*
    @Override
    public boolean verificarSenhaAdm(Cliente cliente) {
        DataBaseHelper helper = new DataBaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        String where = ClientContract.USERNAME + " = ? AND "+ ClientContract.PASSWORD + " = ? ";
        String[] args = {cliente.getUsuario().toString(), cliente.getSenha()};

        try {
            return db.query(ClientContract.TABLE, ClientContract.COLUMNS_ADM, where, args, null, null, null, null).moveToFirst();
        }
        finally {
            db.close();
        }
    }
    */




}
