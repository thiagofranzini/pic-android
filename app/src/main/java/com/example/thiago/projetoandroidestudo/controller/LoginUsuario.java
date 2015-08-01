package com.example.thiago.projetoandroidestudo.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thiago.projetoandroidestudo.R;
import com.example.thiago.projetoandroidestudo.model.entities.Cliente;
import com.example.thiago.projetoandroidestudo.model.entities.Usuario;
import com.example.thiago.projetoandroidestudo.util.FormUtil;

/**
 * Created by Thiago on 23/07/2015.
 */
public class LoginUsuario extends AppCompatActivity {

    private Button button;
    private EditText editTextName;
    private EditText editTextIdade;
    private EditText editTextTelefone;
    private EditText editTextEndereco;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Cliente cliente;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);
        editTextUsername = (EditText) findViewById(R.id.nomeUsuario);
        editTextPassword = (EditText) findViewById(R.id.senhaUsuario);

        this.submeterFormularioLogin();
    }

    private void submeterFormularioLogin() {
        this.button = (Button) findViewById(R.id.submitLoginButton);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FormUtil.validarUsuarioLogado(LoginUsuario.this, editTextUsername, editTextPassword)) {
                    Usuario usuario  = bindUsuario();
                    if(usuario.realizarLoginAdm()){
                        Intent goToListView = new Intent(LoginUsuario.this, ConsultaUsuarioCadastrado.class);
                        startActivity(goToListView);
                    }
                    else{
                        Toast.makeText(LoginUsuario.this, R.string.errorLogin, Toast.LENGTH_SHORT ).show();
                    }
                }
            }
        });
    }

    private Usuario bindUsuario(){
        if(this.usuario == null) {
            this.usuario = new Usuario();
        }
        usuario.setUsuario(editTextUsername.getText().toString());
        usuario.setSenha(editTextPassword.getText().toString());
        return usuario;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
