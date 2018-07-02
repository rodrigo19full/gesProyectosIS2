package com.example.ro_fa.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ro_fa.myapplication.R;
import com.example.ro_fa.myapplication.Remote.ApiUtils;
import com.example.ro_fa.myapplication.Remote.DatosLogin;
import com.example.ro_fa.myapplication.servicios.LoginService;
import com.example.ro_fa.myapplication.servicios.UsuariosService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsernombre;
    EditText edtUserpass;
    Button btnLogin;
    Button btnCrearCuenta;
    LoginService loginService;
    DatosLogin datosLogin = new DatosLogin( this );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_login);

        edtUsernombre = (EditText) findViewById(R.id.edtUsernombre);
        edtUserpass = (EditText) findViewById(R.id.edtUserpass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCrearCuenta = (Button) findViewById(R.id.btnCrearCuenta);
        loginService = ApiUtils.getLoginService();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernombre = edtUsernombre.getText().toString();
                String userpass = edtUserpass.getText().toString();
                //validate form
                if(validateLogin(usernombre, userpass)){
                    //do login
                    doLogin(usernombre, userpass);
                }
            }
        });

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent f = new Intent(LoginActivity.this, CrearUsuarioActivity.class);
            startActivity(f);
            }
        });

    }

    private boolean validateLogin(String usernombre, String userpass){
        if(usernombre == null || usernombre.trim().length() == 0){
            Toast.makeText(this, "Nombre de usuario requerido", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(userpass == null || userpass.trim().length() == 0){
            Toast.makeText(this, "Password requerido", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void doLogin(final String usernombre, final String userpass){
        Call call = loginService.login(usernombre,userpass);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()){
                    String respuesta = response.body().toString();
                    if(respuesta.equals("false")){

                        Toast.makeText(LoginActivity.this, "El nombre de usuario o contraseña son incrorrectos", Toast.LENGTH_SHORT).show();

                    } else {
                        //login start main activity
                        String id = respuesta;
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        datosLogin.guardarDatosLogin( id,usernombre );
                        intent.putExtra("usernombre", usernombre);
                        intent.putExtra("idusuario", id);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Error! Por favor intente de nuevo!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
