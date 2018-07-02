package com.example.ro_fa.myapplication.servicios;

import com.example.ro_fa.myapplication.modelos.Usuariosproyectos;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ro_fa on 1/7/2018.
 */

public interface UsuariosProyectosService {

    @POST("crear")
    Call<ResponseBody> crear(@Body Usuariosproyectos nuevo);
}
