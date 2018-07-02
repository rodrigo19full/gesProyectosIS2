package com.example.ro_fa.myapplication.Remote;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.ro_fa.myapplication.modelos.Usuarios;

/**
 * Created by ro_fa on 30/6/2018.
 */

public class DatosLogin {

    private Context mContext;

    public DatosLogin(Context context) {
        mContext = context;
    }

    public SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public SharedPreferences.Editor getEditor() {
        return getPreferences().edit();
    }

    public void guardarDatosLogin(String idusuario, String nombre) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(ApiUtils.SESSION_ID, idusuario);
        editor.putString( ApiUtils.SESSION_NOMBRE, nombre );
        editor.commit();
    }

    public String getIdUsuario() {
        return getPreferences().getString(ApiUtils.SESSION_ID, "");
    }

    public String getNombreUsuario () { return getPreferences().getString( ApiUtils.SESSION_NOMBRE,"" );}

    public Usuarios getUserLogin() {
        String idusuario = getPreferences().getString(ApiUtils.SESSION_ID, null);
        System.err.println("id : " + idusuario);
        if (idusuario != null) {
            return new Usuarios(Integer.getInteger( idusuario ));
        }
        return null;
    }
}
