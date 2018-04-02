package com.example.ro_fa.gesproyectos.activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ro_fa.gesproyectos.R;
import com.example.ro_fa.gesproyectos.interfaces.UsuariosService;
import com.example.ro_fa.gesproyectos.models.Usuarios;
import com.example.ro_fa.gesproyectos.parceables.UsuarioParceable;
import com.example.ro_fa.gesproyectos.util.GsonDateAdapterFactory;
import com.example.ro_fa.gesproyectos.util.NetworkUtil;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient googleApiClient;

    private SignInButton signInButton;

    public static final int SIGN_IN_CODE = 777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
            .enableAutoManage(this,this)
            .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
            .build();

        signInButton = (SignInButton) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN_CODE);
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==SIGN_IN_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            goMainScreen();
            //validarUsuario(account.getEmail());
        } else {
            Toast.makeText(this,"No se puede iniciar Sesión",Toast.LENGTH_SHORT).show();
        }
    }

    private void goMainScreen() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * En este metodo verificamos si el usuario existe en la BD
     */
    private void validarUsuario(final String email) {

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(NetworkUtil.URL)
                .addConverterFactory(GsonConverterFactory.create(GsonDateAdapterFactory.gson()))
                .build();
        UsuariosService service = retrofit.create(UsuariosService.class);
        Call<Usuarios> usuariosCall = service.getUsuarios(email);
        usuariosCall.enqueue(new Callback<Usuarios>() {
            @SuppressLint("StringFormatInvalid")
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                if (response.isSuccessful()) { //si la peticion fue realizada
                    Usuarios usuario = response.body(); //recupera el JSON en el objeto usuario

                    if (usuario != null) { //comprueba si no es nulo el usuario
                        if (email.equals(usuario.getEmail())) {
                            UsuarioParceable usuarioParceable = new UsuarioParceable(usuario);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("usuario", usuarioParceable);
                            startActivity(intent);
                        }
                        //goMainScreen();
                    } else { //si el objeto usuario recuperado es null
                        Snackbar.make((LinearLayout) findViewById(R.id.signInButton), getString(R.string.mensaje_usuario_no_registrado_en_la_bd_fmt, email), Snackbar.LENGTH_LONG).show();
                    }
                } else { //si hay error en la peticion
                    Toast.makeText(getApplicationContext(), response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            } // fin del metodo interno onResponse()

            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.mensaje_error_conexion_de_red, Toast.LENGTH_SHORT).show();
            }
        });
    }// fin del metodo validarUsuario()
}
