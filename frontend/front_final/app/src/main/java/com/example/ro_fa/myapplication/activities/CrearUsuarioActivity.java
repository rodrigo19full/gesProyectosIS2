package com.example.ro_fa.myapplication.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.ro_fa.myapplication.R;
import com.example.ro_fa.myapplication.Remote.ApiUtils;
import com.example.ro_fa.myapplication.Remote.RetrofitClient;
import com.example.ro_fa.myapplication.modelos.Usuarios;
import com.example.ro_fa.myapplication.servicios.UsuariosService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ro_fa on 21/6/2018.
 */

public class CrearUsuarioActivity extends AppCompatActivity  {

    private Button btnConfirmar;
    private Button btnCancelar;
    private UsuariosService usuariosService;

    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtCedula;
    private EditText txtEmail;
    private EditText txtTelefono;
    private EditText txtDireccion;
    private EditText txtUsernombre;
    private EditText txtUserpass;
    private EditText txtUserpass2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_crear_usuario );

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtCedula = (EditText) findViewById(R.id.txtCedula);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtUsernombre = (EditText) findViewById(R.id.txtUsernombre);
        txtUserpass = (EditText) findViewById(R.id.txtUserpass);
        txtUserpass2= (EditText) findViewById(R.id.txtUserpass2);


        usuariosService = (UsuariosService) RetrofitClient.create( UsuariosService.class, "usuarios/" );

        btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearUsuario();
            }
        });

        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void crearUsuario() {

        btnConfirmar.setEnabled(false);
        btnConfirmar.setText("Procesando");
        boolean cancel = false;
        View focusView = null;

        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();
        Integer cedula = Integer.valueOf(txtCedula.getText().toString());
        String direccion = txtDireccion.getText().toString();
        String telefono = txtTelefono.getText().toString();
        String email = txtEmail.getText().toString();
        String usernombre = txtUsernombre.getText().toString();
        String userpass = txtUserpass.getText().toString();
        String userpass2 = txtUserpass2.getText().toString();
        Integer estado = 1;


        if (TextUtils.isEmpty(nombre)) {
            txtNombre.setError(getString(R.string.error_campo_requerido));
            focusView = txtNombre;
            cancel = true;
        } else if (TextUtils.isEmpty(apellido)) {
            txtApellido.setError(getString(R.string.error_campo_requerido));
            focusView = txtApellido;
            cancel = true;
        } else if (Integer.toString(cedula).isEmpty()) {
            txtCedula.setError(getString(R.string.error_campo_requerido));
            focusView = txtCedula;
            cancel = true;
        } else if (TextUtils.isEmpty(direccion)) {
            txtDireccion.setError(getString(R.string.error_campo_requerido));
            focusView = txtDireccion;
            cancel = true;
        } else if (TextUtils.isEmpty(telefono)) {
            txtTelefono.setError(getString(R.string.error_campo_requerido));
            focusView = txtTelefono;
            cancel = true;
        } else if (TextUtils.isEmpty(email)) {
            txtEmail.setError(getString(R.string.error_campo_requerido));
            focusView = txtEmail;
            cancel = true;
        } else if (!isEmailValido(email)) {
            txtEmail.setError(getString(R.string.error_email_invalido));
            focusView = txtEmail;
            cancel = true;
        } else if (TextUtils.isEmpty(usernombre)) {
            txtUsernombre.setError(getString(R.string.error_campo_requerido));
            focusView = txtUsernombre;
            cancel = true;
        } else if (TextUtils.isEmpty(userpass)) {
            txtUserpass.setError(getString(R.string.error_campo_requerido));
            focusView = txtUserpass;
            cancel = true;
        } else if (TextUtils.isEmpty(userpass2)) {
            txtUserpass2.setError(getString(R.string.error_campo_requerido));
            focusView = txtUserpass2;
            cancel = true;
        } else if (!userpass.equals(userpass2)) {
            txtUserpass2.setError(getString(R.string.error_password_no_coincide));
            focusView = txtUserpass2;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            btnConfirmar.setEnabled(true);
            btnConfirmar.setText(R.string.action_registrar_usuario);
        } else {
            Usuarios user = new Usuarios(cedula, nombre, apellido, direccion, telefono, email, usernombre, userpass, estado);
            Call<ResponseBody> call = usuariosService.create(user);
            System.out.println(Log.d("usuario",user.toString()+call.toString()));
            call.enqueue( new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        btnConfirmar.setEnabled(true);
                        btnConfirmar.setText(R.string.action_registrar_usuario);
                        System.out.println(Log.d("exito","entro aca"));
                        showMessageSuccess("Exitoso", "Usuario creado exitosamente");
                        ListarUsuariosActivity.recargar = true;
                    }
                    else {
                        btnConfirmar.setEnabled(true);
                        btnConfirmar.setText(R.string.action_registrar_usuario);
                        System.out.println( Log.d("error", response.message().toString()));
                        showMessage("Error", "Ocurrio un error al realizar la operación");
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    btnConfirmar.setEnabled(true);
                    btnConfirmar.setText(R.string.action_registrar_usuario);
                    showMessage("Error", "Ocurrio un error al realizar la operacion");
                }
            } );
        }

    }

    private boolean isEmailValido(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String userpass) {
        return userpass.length() > 4;
    }

    private void showMessageSuccess(String titulo, String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mensaje).setTitle(titulo);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showMessage(String titulo, String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mensaje).setTitle(titulo);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
