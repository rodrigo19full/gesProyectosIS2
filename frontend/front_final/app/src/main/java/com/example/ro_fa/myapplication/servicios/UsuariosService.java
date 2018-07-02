package com.example.ro_fa.myapplication.servicios;

import com.example.ro_fa.myapplication.modelos.Proyectos;
import com.example.ro_fa.myapplication.modelos.Us;
import com.example.ro_fa.myapplication.modelos.Usuarios;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by ro_fa on 18/6/2018.
 */

public interface UsuariosService {

    @GET("listar")
    Call<List<Usuarios>> listar ();

    @GET("listar/{idusuario}")
    Call<Usuarios> listarUsuario (@Path("idusuario") Integer idusuario);

    @POST("crear")
    Call<ResponseBody> create(@Body Usuarios user);

    @GET("{idusuario}/proyectos")
    Call<List<Proyectos>> listarMisProyectos(@Path("idusuario") Integer idusuario);

    @GET("{idusuario}/tareas")
    Call<List<Us>> listarTareas(@Path("idusuario") Integer idusuario);

    @DELETE("eliminar/{idusuario}")
    Call<ResponseBody> eliminar(@Path("idusuario") Integer idusuario);

    @PUT("editar/{id}")
    Call<ResponseBody> editar(@Path( "id" ) Integer id, @Body Usuarios usuario);

}
