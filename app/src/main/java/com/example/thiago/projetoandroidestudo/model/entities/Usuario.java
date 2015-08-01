package com.example.thiago.projetoandroidestudo.model.entities;

import com.example.thiago.projetoandroidestudo.model.dao.ClienteDatabaseDAO;

/**
 * Created by Thiago on 01/08/2015.
 */
public class Usuario extends Cliente {

    private String usuario;
    private String senha;


    public Usuario(){
        super();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean realizarLoginAdm(){
        return ClienteDatabaseDAO.getInstance().verificarSenhaAdm(this);
    }


}
