package com.example.thiago.projetoandroidestudo.model.dao;

import java.util.List;

import com.example.thiago.projetoandroidestudo.model.entities.Cliente;
import com.example.thiago.projetoandroidestudo.model.entities.Usuario;

/**
 * Created by Thiago on 27/07/2015.
 */
public interface IClienteDataBaseDAO {

    public void save(Cliente cliente);
    public List<Cliente> getAll();
    public void delete(Cliente cliente);
    public boolean verificarSenhaAdm(Usuario usuario);
    public void saveUser(Usuario usuario);
}
