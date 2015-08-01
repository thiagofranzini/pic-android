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
    private String email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        dest.writeString(this.email == null ? null : this.email);
        dest.writeParcelable(adress, flags);
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
        this.id = in.readInt();
        this.nome = in.readString();
        this.idade = in.readInt();
        this.email = in.readString();
        this.telefone = in.readString();
        this.adress = in.readParcelable(ClientAddress.class.getClassLoader());
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
        if (email != null ? !email.equals(cliente.email) : cliente.email != null) return false;
        return !(adress != null ? !adress.equals(cliente.adress) : cliente.adress != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (idade != null ? idade.hashCode() : 0);
        result = 31 * result + (telefone != null ? telefone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        return result;
    }
}
