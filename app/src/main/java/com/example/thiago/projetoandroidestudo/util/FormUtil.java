package com.example.thiago.projetoandroidestudo.util;

import android.content.Context;
import android.widget.EditText;

import com.example.thiago.projetoandroidestudo.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Thiago on 26/07/2015.
 */
public final class FormUtil {

    private static final String PHONE_PATTERN = "\\([0-9]{2,3}\\)[9|\\s][0-9]{4}\\-[0-9]{4}";
    private static final String AGE_PATTERN = "[0-9][2]";

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
            }else if(edit.getId() == (R.id.insertTelefone)){
                boolean telefoneFormatado = edit.getText().toString().matches(PHONE_PATTERN);
                if(!telefoneFormatado){
                    edit.setError(context.getString(R.string.formatAgeError));
                    isValido = false;
                }
            }else if(edit.getId() == (R.id.insertIdade)){
                boolean idadeFormatada = edit.getText().toString().matches(PHONE_PATTERN);
                if(!idadeFormatada){
                    edit.setError(context.getString(R.string.formatPhoneError));
                    isValido = false;
                }
            }
        }
        return isValido;
    }

    private static boolean validarFormulario(String valor) {
        return valor == null || valor.trim().equals("") || valor.equals("");
    }

}
