package com.example.ro_fa.myapplication.servicios;

import com.example.ro_fa.myapplication.modelos.Roles;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ro_fa on 1/7/2018.
 */

public interface RolesService {

    @GET("listar")
    Call<List<Roles>> listar();

}
