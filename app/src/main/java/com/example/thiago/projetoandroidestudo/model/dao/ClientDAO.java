package com.example.thiago.projetoandroidestudo.model.dao;

import java.util.List;

import com.example.thiago.projetoandroidestudo.model.entities.Cliente;

/**
 * Created by Thiago on 26/07/2015.
 */
public class ClientDAO implements IClienteDAO {

    private static ClientDAO singletonInstance;

    private ClientDAO(){
        super();
    }

    public static ClientDAO getInstance(){
        if(singletonInstance == null){
            singletonInstance = new ClientDAO();
        }
        return singletonInstance;
    }

    @Override
    public void save(Cliente cliente) {

    }

    @Override
    public List<Cliente> getAll() {
        return null;
    }

    @Override
    public void delete(Cliente cliente) {

    }
}
