package com.example.ro_fa.myapplication.Remote;

import com.example.ro_fa.myapplication.servicios.LoginService;
import com.example.ro_fa.myapplication.servicios.ProyectosService;
import com.example.ro_fa.myapplication.servicios.UsuariosService;

/**
 * Created by ro_fa on 18/6/2018.
 */

public class ApiUtils {
    public static final String BASE_URL = "http://192.168.1.72:8080/backendrest/webresources/";
    public static final String SESSION_ID = "idusuario";
    public static final String SESSION_NOMBRE = "usernombre";

    public static final String BASE_URL_USUARIOS = "usuarios/";
    public static LoginService getLoginService(){
        return RetrofitClient.getClient(BASE_URL+BASE_URL_USUARIOS).create(LoginService.class);
    }

    public static UsuariosService getUsuariosService(){
        return RetrofitClient.getClient(BASE_URL+BASE_URL_USUARIOS).create(UsuariosService.class);
    }


    public static final String BASE_URL_PROYECTOS = "proyectos/";
    public static ProyectosService getProyectosService(){
        return RetrofitClient.getClient(BASE_URL+BASE_URL_PROYECTOS).create(ProyectosService.class);
    }
}
