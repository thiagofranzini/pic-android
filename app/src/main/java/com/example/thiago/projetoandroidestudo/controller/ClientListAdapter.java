package com.example.thiago.projetoandroidestudo.controller;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thiago.projetoandroidestudo.R;

import java.util.List;

import com.example.thiago.projetoandroidestudo.model.entities.Cliente;

/**
 * Created by Thiago on 25/07/2015.
 */
public class ClientListAdapter extends BaseAdapter{

    private Activity context;
    private List<Cliente> listaClientes;

    public ClientListAdapter(Activity context, List<Cliente> listaClientes){
        this.context = context;
        this.listaClientes = listaClientes;
    }

    @Override
    public int getCount() {
        return listaClientes.size();
    }

    @Override
    public Cliente getItem(int position) {
        return listaClientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = context.getLayoutInflater().inflate(R.layout.activity_list_view, parent, false);

        Cliente cliente = getItem(position);

        TextView textViewName = (TextView)view.findViewById(R.id.textViewName);
        textViewName.setText(cliente.getNome());

        TextView textViewAge = (TextView)view.findViewById(R.id.textViewIdade);
        textViewAge.setText(cliente.getIdade().toString());

        return view;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
}
