package com.example.thiago.projetoandroidestudo.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thiago.projetoandroidestudo.R;

import java.util.List;

import com.example.thiago.projetoandroidestudo.model.dao.ClienteDatabaseDAO;
import com.example.thiago.projetoandroidestudo.model.entities.Cliente;
import com.melnykov.fab.FloatingActionButton;

import org.apache.http.protocol.HTTP;

/**
 * Created by Thiago on 23/07/2015.
 */
public class ConsultaUsuarioCadastrado extends AppCompatActivity {

    private ListView listViewClientes;
    private Cliente cliente;
    private FloatingActionButton fAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_base);
        this.bindClientList();
        this.bindFab();
    }

    private void bindFab(){
        this.fAdd = (FloatingActionButton)findViewById(R.id.fabAdd);
        this.fAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPersistActivity = new Intent(ConsultaUsuarioCadastrado.this, CadastroFormularioUsuario.class);
                startActivity(goToPersistActivity);
            }
        });
    }

    private void bindClientList() {
        List<Cliente> listaClientes = Cliente.getAll();
        ClientListAdapter adapter = new ClientListAdapter(ConsultaUsuarioCadastrado.this, listaClientes);
        listViewClientes = (ListView) findViewById(R.id.listViewCliente);
        listViewClientes.setAdapter(adapter);
        listViewClientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                cliente = (Cliente) parent.getItemAtPosition(position);
                return false;
            }
        });

        listViewClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente client = (Cliente) parent.getItemAtPosition(position);
                // Best Practices: http://stackoverflow.com/questions/4275678/how-to-make-phone-call-using-intent-in-android
                final Intent goToSOPhoneCall = new Intent(Intent.ACTION_DIAL /* or Intent.ACTION_DIAL (no manifest permission needed) */);
                goToSOPhoneCall.setData(Uri.parse("tel:" + client.getTelefone()));
                startActivity(goToSOPhoneCall);
            }
        });

        registerForContextMenu(listViewClientes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_insert_user, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        this.getMenuInflater().inflate(R.menu.menu_client_list_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menuPersistir) {
            // Create the text message with a string
            final Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Seu texto aqui...");
            sendIntent.setType(HTTP.PLAIN_TEXT_TYPE);

            // Create intent to show the chooser dialog
            final Intent chooser = Intent.createChooser(sendIntent, "Titulo Chooser");

            // Verify the original intent will resolve to at least one activity
            if (sendIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(chooser);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshClienteList() {
        ClientListAdapter adapter = (ClientListAdapter) listViewClientes.getAdapter();
        adapter.setListaClientes(Cliente.getAll());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menuDelete) {
            new AlertDialog.Builder(ConsultaUsuarioCadastrado.this)
                    .setMessage(R.string.confirmProgress)
                    .setTitle(R.string.confirm)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cliente.delete();
                            refreshClienteList();
                            Toast.makeText(ConsultaUsuarioCadastrado.this, R.string.success, Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).create().show();
        }
        else if(item.getItemId() == R.id.menuEdit){
            Intent goToPersistActivity = new Intent(ConsultaUsuarioCadastrado.this, CadastroFormularioUsuario.class);
            goToPersistActivity.putExtra(CadastroFormularioUsuario.CLIENT_PARAM, (Parcelable)this.cliente);
            startActivity(goToPersistActivity);
        }

        return super.onContextItemSelected(item);
    }
}
