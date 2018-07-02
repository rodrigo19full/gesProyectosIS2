package com.example.ro_fa.myapplication.servicios;

import com.example.ro_fa.myapplication.modelos.Proyectos;
import com.example.ro_fa.myapplication.modelos.Sprints;
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

public interface ProyectosService {

    @GET ("listar")
    Call<List<Proyectos>> listar();

    @DELETE ("eliminar/{idproyecto}")
    Call<ResponseBody> eliminar(@Path("idproyecto") Integer parametro);

    @POST ("crear")
    Call<ResponseBody> crear(@Body Proyectos proyecto);

    @GET("{idproyecto}/usuarios")
    Call<List<Usuarios>> listarUsuarios(@Path("idproyecto") Integer idproyecto);

    @GET("{idproyecto}/sprints")
    Call<List<Sprints>> listarSprints(@Path("idproyecto") Integer idproyecto);

    @PUT("editar/{id}")
    Call<ResponseBody> editar(@Path( "id" ) Integer id, @Body Proyectos proyecto);


}
