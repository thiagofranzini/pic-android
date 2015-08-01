package com.example.thiago.projetoandroidestudo.model.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.thiago.projetoandroidestudo.model.entities.Cliente;

/**
 * Created by Thiago on 25/07/2015.
 */
public class ClienteDAO implements IClienteDAO {


    private static ClienteDAO singletonInstance;
    private List<Cliente> clienteList;

    private ClienteDAO(){
        this.clienteList = new ArrayList<>();
    }

    public static ClienteDAO getInstance(){
        if(singletonInstance == null){
            return singletonInstance = new ClienteDAO();
        }
        return singletonInstance;
    }

    @Override
    public void save(Cliente cliente) {
        this.clienteList.add(this.popularObjetoCliente(cliente));
    }

    private Cliente popularObjetoCliente(Cliente cliente){
        Cliente client = new Cliente();
        client.setId(cliente.getId());
        client.setNome(cliente.getNome());
        client.setIdade(cliente.getIdade());
        client.setTelefone(cliente.getTelefone());
        client.setEmail(cliente.getEmail());
        return client;
    }

    @Override
    public List<Cliente> getAll() {
        return this.clienteList;
    }

    @Override
    public void delete(Cliente cliente) {
        this.clienteList.remove(cliente);
    }
}
