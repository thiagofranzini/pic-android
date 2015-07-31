package com.example.thiago.projetoandroidestudo.model.dao;

import java.util.List;

import com.example.thiago.projetoandroidestudo.model.entities.Cliente;

/**
 * Created by Thiago on 25/07/2015.
 */
public interface IClienteDAO {

    public void save(Cliente cliente);
    public List<Cliente> getAll();
    public void delete(Cliente cliente);
}
