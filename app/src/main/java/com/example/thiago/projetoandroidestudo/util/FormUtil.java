package com.example.thiago.projetoandroidestudo.util;

import android.content.Context;
import android.widget.EditText;

import com.example.thiago.projetoandroidestudo.R;

/**
 * Created by Thiago on 26/07/2015.
 */
public final class FormUtil {

    private FormUtil(){

    }


    public static boolean validarUsuarioLogado(Context context, EditText...editTexts){
        boolean isValido = true;
        for(EditText editText: editTexts){
            String valor = editText.getText()  == null ? null : editText.getText().toString();
            if(validarFormulario(valor)){
                editText.setError(context.getString(R.string.erro));
                isValido = false;
            }
        }
        return isValido;
    }

    public static boolean validarCamposObrigatorios(Context context, EditText...editTexts){
        boolean isValido = true;
        for(EditText edit: editTexts){
            String valor = edit.getText() == null ? null : edit.getText().toString();
            if(validarFormulario(valor)){
                edit.setError(context.getString(R.string.erro));
                isValido = false;
            }
        }
        return isValido;
    }

    private static boolean validarFormulario(String valor) {
        return valor == null || valor.trim().equals("") || valor.equals("");
    }

}
