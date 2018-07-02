package com.example.ro_fa.myapplication.servicios;

import com.example.ro_fa.myapplication.modelos.Notasus;
import com.example.ro_fa.myapplication.modelos.Sprints;
import com.example.ro_fa.myapplication.modelos.SprintsPK;
import com.example.ro_fa.myapplication.modelos.Us;
import com.example.ro_fa.myapplication.modelos.UsPK;

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
 * Created by ro_fa on 2/7/2018.
 */

public interface UsService {

    @POST("crear")
    Call<ResponseBody> crear(@Body Us us);

    @GET("{idus}/notas")
    Call<List<Notasus>> listarNotas(@Path("idus") Integer idus);

    @PUT("editar/{idus}")
    Call<ResponseBody> editar(@Body Us us);

    @DELETE("eliminar/{id}")
    Call<ResponseBody> eliminar(@Path("id") UsPK usPK);
}
