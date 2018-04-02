package com.example.ro_fa.gesproyectos.interfaces;

import com.example.ro_fa.gesproyectos.models.Usuarios;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuariosService {

    @POST("entitys.usuarios/consultar-por-email/{email}")
    Call<Usuarios> getUsuarios(@Path("email") String email);
}
