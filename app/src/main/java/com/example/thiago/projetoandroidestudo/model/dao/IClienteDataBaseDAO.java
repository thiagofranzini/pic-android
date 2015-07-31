package com.example.thiago.projetoandroidestudo.model.dao;

import java.util.List;

import com.example.thiago.projetoandroidestudo.model.entities.Cliente;

/**
 * Created by Thiago on 27/07/2015.
 */
public interface IClienteDataBaseDAO {

    public void save(Cliente cliente);
    public List<Cliente> getAll();
    public void delete(Cliente cliente);
    public boolean verificarSenhaAdm(Cliente cliente);
}
