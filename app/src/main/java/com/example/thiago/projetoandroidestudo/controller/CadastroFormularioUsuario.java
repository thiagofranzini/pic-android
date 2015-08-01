package com.example.thiago.projetoandroidestudo.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thiago.projetoandroidestudo.R;
import com.example.thiago.projetoandroidestudo.model.dao.ClienteDatabaseDAO;
import com.example.thiago.projetoandroidestudo.model.entities.ClientAddress;
import com.example.thiago.projetoandroidestudo.model.entities.Cliente;
import com.example.thiago.projetoandroidestudo.model.services.CepService;
import com.example.thiago.projetoandroidestudo.util.FormUtil;

/**
 * Created by Thiago on 24/07/2015.
 */
public class CadastroFormularioUsuario  extends AppCompatActivity{

    public static boolean isAlterar = false;
    public static final String CLIENT_PARAM = "CLIENT_PARAM";
    private Cliente cliente;
    private EditText editTextName;
    private EditText editTextIdade;
    private EditText editTextTelefone;
    private EditText editTextEmail;
    private EditText editTextCep;
    private EditText editTextTipoLogradouro;
    private EditText editTextLogradouro;
    private EditText editTextBairro;
    private EditText editTextCidade;
    private EditText editTextEstado;

    private Button buttonFindCep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_user);

        this.editTextName = (EditText) findViewById(R.id.insertName);
        this.editTextIdade = (EditText) findViewById(R.id.insertIdade);
        this.editTextTelefone = (EditText) findViewById(R.id.insertTelefone);
        this.editTextEmail = (EditText) findViewById(R.id.insertEnderedo);

        Bundle extras = getIntent().getExtras();
        bindFields();

        if(extras != null){
            cliente = extras.getParcelable(CLIENT_PARAM);
            if(cliente == null){
                throw new IllegalArgumentException();
            }
            bindForm(cliente);
        }
    }

    private void bindForm(Cliente cliente){
        editTextName.setText(cliente.getNome());
        editTextIdade.setText(String.valueOf(cliente.getIdade()));
        editTextTelefone.setText(cliente.getTelefone());
        editTextEmail.setText(cliente.getEmail());
        editTextCep.setText(cliente.getAdress().getCep());
        editTextTipoLogradouro.setText(cliente.getAdress().getTipoDeLogradouro());
        editTextLogradouro.setText(cliente.getAdress().getLogradouro());
        editTextBairro.setText(cliente.getAdress().getBairro());
        editTextCidade.setText(cliente.getAdress().getCidade());
        editTextEstado.setText(cliente.getAdress().getEstado());
    }

    private void bindFields(){
        editTextName = (EditText)findViewById(R.id.insertName);
        editTextName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_edittext_client, 0);
        editTextName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editTextName.getRight() - editTextName.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        //TODO: Explanation 2:
                        final Intent goToSOContacts = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                        //goToSOContacts.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
                        startActivityForResult(goToSOContacts, 999);
                    }
                }
                return false;
            }
        });
        editTextIdade = (EditText)findViewById(R.id.insertIdade);
        editTextTelefone = (EditText)findViewById(R.id.insertTelefone);
        editTextEmail = (EditText)findViewById(R.id.insertEnderedo);
        editTextCep = (EditText) findViewById(R.id.editTextCep);
        editTextTipoLogradouro = (EditText) findViewById(R.id.editTextTipoLogradouro);
        editTextLogradouro = (EditText) findViewById(R.id.editTextLogradouro);
        editTextBairro = (EditText) findViewById(R.id.editTextBairro);
        editTextCidade = (EditText) findViewById(R.id.editTextCidade);
        editTextEstado = (EditText) findViewById(R.id.editTextEstado);
        bindButtonFindCep();
    }

    private void bindButtonFindCep(){
        buttonFindCep = (Button) findViewById(R.id.buttonFindCep);
        buttonFindCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsyncTask<String, Void, ClientAddress> execute = new GetAddressByCep().execute(editTextCep.getText().toString());

                try {
                    ClientAddress clientAddress = execute.get();
                    editTextCep.setText(clientAddress.getCep());
                    editTextTipoLogradouro.setText(clientAddress.getTipoDeLogradouro());
                    editTextLogradouro.setText(clientAddress.getLogradouro());
                    editTextBairro.setText(clientAddress.getBairro());
                    editTextCidade.setText(clientAddress.getCidade());
                    editTextEstado.setText(clientAddress.getEstado());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999) {

            if (resultCode == Activity.RESULT_OK) {
                Cursor cursor = null;
                try {
                    final Uri contactUri = data.getData();
                    final String[] projection = {
                            ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    };
                    cursor = getContentResolver().query(contactUri, projection, null, null, null);
                    cursor.moveToFirst();

                    editTextName.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME)));
                    editTextTelefone.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                } catch (Exception e) {
                    Log.d("TAG", "Unexpected error");
                }finally{
                    if(cursor != null){
                        cursor.close();
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_persist_user, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        this.getMenuInflater().inflate(R.menu.menu_persist_user, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuPersistir){
            if(FormUtil.validarCamposObrigatorios(CadastroFormularioUsuario.this, this.bindEditText())){
                cliente = bindCliente();
                try {
                    ClienteDatabaseDAO.getInstance().save(cliente);
                    clearEditText();
                    if(isAlterar){
                        Toast.makeText(CadastroFormularioUsuario.this, R.string.successEdit, Toast.LENGTH_SHORT ).show();
                        isAlterar = false;
                    }
                    else{
                        Toast.makeText(CadastroFormularioUsuario.this, R.string.success, Toast.LENGTH_SHORT ).show();
                    }
                }
                catch (Exception e){
                    Toast.makeText(CadastroFormularioUsuario.this, R.string.erroConexao, Toast.LENGTH_SHORT ).show();
                }
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearEditText(){
        this.editTextName.setText("");
        this.editTextIdade.setText("");
        this.editTextTelefone.setText("");
        this.editTextEmail.setText("");
        this.editTextName.requestFocus();
    }


    private EditText[] bindEditText(){
        return new EditText[] {this.editTextName, this.editTextEmail, this.editTextIdade, this.editTextIdade};
    }

    private Cliente bindCliente(){
        if(cliente == null) {
            this.cliente = new Cliente();
        }
        cliente.setNome(editTextName.getText().toString());
        cliente.setEmail(editTextEmail.getText().toString());
        cliente.setIdade(Integer.valueOf(editTextIdade.getText().toString()));
        cliente.setTelefone(editTextTelefone.getText().toString());
        return cliente;
    }

    private class GetAddressByCep extends AsyncTask<String, Void, ClientAddress> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(CadastroFormularioUsuario.this);
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.show();
        }

        @Override
        protected ClientAddress doInBackground(String... params) {
            return CepService.getAddressBy(params[0]);
        }

        @Override
        protected void onPostExecute(ClientAddress clientAddress) {
            progressDialog.dismiss();
        }
    }


}
