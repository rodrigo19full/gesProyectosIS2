package com.example.ro_fa.myapplication.servicios;

/**
 * Created by ro_fa on 18/6/2018.
 */

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LoginService {

    @GET("login/{usernombre}/{userpass}")
    Call <String> login(@Path("usernombre") String usernombre, @Path("userpass") String userpass);
}
