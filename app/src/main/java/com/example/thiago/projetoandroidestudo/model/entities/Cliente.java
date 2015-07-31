package com.example.thiago.projetoandroidestudo.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

import com.example.thiago.projetoandroidestudo.model.dao.ClienteDAO;
import com.example.thiago.projetoandroidestudo.model.dao.ClienteDatabaseDAO;

/**
 * Created by Thiago on 25/07/2015.
 */
public class Cliente implements Comparable, Serializable, Parcelable{

    private Integer id;
    private String nome;
    private Integer idade;
    private String telefone;
    private String usuario;
    private String senha;
    private ClientAddress adress;


    public Cliente(){
        super();
    }

    public Cliente(Parcel in){
        super();
        readToParcel(in);
    }



    public ClientAddress getAdress() {
        if(adress == null){
            adress = new ClientAddress();
        }
        return adress;
    }

    public void setAdress(ClientAddress adress) {
        this.adress = adress;
    }

    private String endereco;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }


    @Override
    public int compareTo(Object another) {
        if(!(another instanceof Cliente)){
            return 0;
        }
        Cliente cliente = (Cliente) another;
        return cliente.getId() == this.getId() ? 1 :  -1;
    }

    public void delete(){
        ClienteDatabaseDAO.getInstance().delete(this);
    }

    public void save(){
        ClienteDAO.getInstance().save(this);
    }

    public static List<Cliente> getAll(){
        return ClienteDatabaseDAO.getInstance().getAll();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id == null ? null : this.id);
        dest.writeString(this.nome == null ? null : this.nome);
        dest.writeInt(this.idade == null ? null : this.idade);
        dest.writeString(this.telefone == null ? null : this.telefone);
        dest.writeString(this.endereco == null ? null : this.endereco);
        dest.writeString(this.usuario == null ? null : this.usuario);
        dest.writeString(this.senha == null ? null : this.senha);
        dest.writeParcelable(adress, flags);
    }

    public boolean realizarLoginAdm(){
       return ClienteDatabaseDAO.getInstance().verificarSenhaAdm(this);
    }

    public static final Parcelable.Creator<Cliente> CREATOR = new Parcelable.Creator<Cliente>(){
        public Cliente createFromParcel(Parcel source){
            return new Cliente(source);
        }
        public Cliente[] newArray(int size){
            return new Cliente[size];
        }
    };


    public void readToParcel(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        idade = in.readInt();
        endereco = in.readString();
        telefone = in.readString();
        adress = in.readParcelable(ClientAddress.class.getClassLoader());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        if (id != null ? !id.equals(cliente.id) : cliente.id != null) return false;
        if (nome != null ? !nome.equals(cliente.nome) : cliente.nome != null) return false;
        if (idade != null ? !idade.equals(cliente.idade) : cliente.idade != null) return false;
        if (telefone != null ? !telefone.equals(cliente.telefone) : cliente.telefone != null)
            return false;
        if (usuario != null ? !usuario.equals(cliente.usuario) : cliente.usuario != null)
            return false;
        if (senha != null ? !senha.equals(cliente.senha) : cliente.senha != null) return false;
        if (adress != null ? !adress.equals(cliente.adress) : cliente.adress != null) return false;
        return !(endereco != null ? !endereco.equals(cliente.endereco) : cliente.endereco != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (idade != null ? idade.hashCode() : 0);
        result = 31 * result + (telefone != null ? telefone.hashCode() : 0);
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        result = 31 * result + (senha != null ? senha.hashCode() : 0);
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + (endereco != null ? endereco.hashCode() : 0);
        return result;
    }
}
