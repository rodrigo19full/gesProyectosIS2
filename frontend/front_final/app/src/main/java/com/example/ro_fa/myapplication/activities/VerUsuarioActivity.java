package com.example.ro_fa.myapplication.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ro_fa.myapplication.R;
import com.example.ro_fa.myapplication.Remote.DatosLogin;
import com.example.ro_fa.myapplication.Remote.RetrofitClient;
import com.example.ro_fa.myapplication.modelos.Notasus;
import com.example.ro_fa.myapplication.modelos.Us;
import com.example.ro_fa.myapplication.modelos.UsPK;
import com.example.ro_fa.myapplication.modelos.Usuarios;
import com.example.ro_fa.myapplication.servicios.UsService;
import com.example.ro_fa.myapplication.servicios.UsuariosService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ro_fa.myapplication.Remote.StringUtils.slurp;

public class VerUsuarioActivity extends NavActivity {

    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtCedula;
    private EditText txtEmail;
    private EditText txtTelefono;
    private EditText txtDireccion;
    private EditText txtUsernombre;
    private EditText txtUserpass;

    private UsuariosService usuariosService;
    public static boolean recargar = false;
    final DatosLogin datosLogin = new DatosLogin( this );
    private Integer idusuario;

    @Override
    protected void inint() {
        loadLayout( R.layout.activity_ver_usuario, "Detalles del Usuario" );

        idusuario = Integer.parseInt( getIntent().getStringExtra( "IDUSUARIO" ) );
        final Integer cedula = Integer.parseInt( getIntent().getStringExtra( "CEDULA" ) );
        final String apellido = getIntent().getStringExtra( "APELLIDO" ) ;
        final String nombre = getIntent().getStringExtra( "NOMBRE" );
        final String fechanacimiento = getIntent().getStringExtra( "FECHANACIMIENTO" );
        final String direccion = getIntent().getStringExtra( "DIRECCION" );
        final String telefono = getIntent().getStringExtra( "TELEFONO" );
        final String email = getIntent().getStringExtra( "EMAIL" );
        final String usernombre = getIntent().getStringExtra( "USERNOMBRE" );
        final String userpass = getIntent().getStringExtra( "USERPASS" );
        final String fechacreacion = getIntent().getStringExtra( "FECHACREACION" );
        final Integer estado = Integer.parseInt( getIntent().getStringExtra( "ESTADO" ) );

        txtCedula = (EditText) findViewById( R.id.cedula);
        txtCedula.setText(cedula.toString());

        txtNombre = (EditText) findViewById( R.id.nombre );
        txtNombre.setText( nombre );

        txtApellido = (EditText) findViewById( R.id.apellido);
        txtApellido.setText( apellido );

        txtEmail = (EditText) findViewById( R.id.email);
        txtEmail.setText(email.toString());

        txtDireccion = (EditText) findViewById( R.id.direccion);
        txtDireccion.setText(direccion.toString());

        txtTelefono = (EditText) findViewById( R.id.telefono);
        txtTelefono.setText(telefono.toString());

        txtUsernombre = (EditText) findViewById( R.id.usernombre);
        txtUsernombre.setText(usernombre.toString());

        txtUserpass = (EditText) findViewById( R.id.userpass);
        txtUserpass.setText(userpass.toString());

        usuariosService = (UsuariosService) RetrofitClient.create( UsuariosService.class, "usuarios/" );

        String usuarioEnSesion = datosLogin.getNombreUsuario();
        String usuariaidsesion = datosLogin.getIdUsuario();
        System.out.println( Log.d( "usuarioensesion", usuarioEnSesion.toString() ));
        Button eliminarbtn = (Button) findViewById( R.id.eliminar);

        if (usuarioEnSesion.equals( "rodrigo" )) {

            eliminarbtn.setVisibility( View.VISIBLE );
            eliminarbtn.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Call<ResponseBody> calleliminiar = usuariosService.eliminar( idusuario);
                    calleliminiar.enqueue( new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> calleliminar, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                showMessageSuccess( "Exitoso", "Usuario eliminado exitosamente" );
                                ListarUsuariosActivity.recargar = true;
                            } else {
                                ResponseBody body = response.errorBody();
                                String json = slurp( body.byteStream(), 1024 );
                                System.err.println( "Error al eliminar usuario : " + json );
                                showMessage( "Error", "Ocurrio un error al realizar la operación" );
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> calleliminar, Throwable t) {
                            showMessage( "Error", "Ocurrio un error al realizar la operación" );
                        }
                    } );
                }
            } );
        } else{
            eliminarbtn.setVisibility( View.GONE );
        }

        Button editartbn = (Button) findViewById( R.id.editar );
        if (usuarioEnSesion.equals( "rodrigo" ) || usuariaidsesion.equals( idusuario.toString() )) {
            editartbn.setVisibility( View.VISIBLE );
            editartbn.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    View focusView = null;

                    String nombre = txtNombre.getText().toString();
                    String apellido = txtApellido.getText().toString();
                    Integer cedula = Integer.valueOf( txtCedula.getText().toString() );
                    String direccion = txtDireccion.getText().toString();
                    String telefono = txtTelefono.getText().toString();
                    String email = txtEmail.getText().toString();
                    String usernombre = txtUsernombre.getText().toString();
                    String userpass = txtUserpass.getText().toString();
                    Integer estado = 1;

                    if (TextUtils.isEmpty( nombre )) {
                        txtNombre.setError( getString( R.string.error_campo_requerido ) );
                        focusView = txtNombre;
                    } else if (TextUtils.isEmpty( apellido )) {
                        txtApellido.setError( getString( R.string.error_campo_requerido ) );
                        focusView = txtApellido;
                    } else if (Integer.toString( cedula ).isEmpty()) {
                        txtCedula.setError( getString( R.string.error_campo_requerido ) );
                        focusView = txtCedula;
                    } else if (TextUtils.isEmpty( direccion )) {
                        txtDireccion.setError( getString( R.string.error_campo_requerido ) );
                        focusView = txtDireccion;
                    } else if (TextUtils.isEmpty( telefono )) {
                        txtTelefono.setError( getString( R.string.error_campo_requerido ) );
                        focusView = txtTelefono;
                    } else if (TextUtils.isEmpty( email )) {
                        txtEmail.setError( getString( R.string.error_campo_requerido ) );
                        focusView = txtEmail;
                    } else if (!isEmailValido( email )) {
                        txtEmail.setError( getString( R.string.error_email_invalido ) );
                        focusView = txtEmail;
                    } else if (TextUtils.isEmpty( usernombre )) {
                        txtUsernombre.setError( getString( R.string.error_campo_requerido ) );
                        focusView = txtUsernombre;
                    } else if (TextUtils.isEmpty( userpass )) {
                        txtUserpass.setError( getString( R.string.error_campo_requerido ) );
                        focusView = txtUserpass;
                    }


                    Usuarios user = new Usuarios( idusuario, cedula, nombre, apellido, direccion, telefono, email, usernombre, userpass, estado );
                    System.out.println( Log.d( "usuario", user.toString() ) );
                    Call<ResponseBody> call2 = usuariosService.editar( idusuario, user );

                    call2.enqueue( new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call2, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                showMessageSuccess( "Exitoso", "Usuario modificado exitosamente" );
                                ListarUsuariosActivity.recargar = true;
                            } else {
                                ResponseBody body = response.errorBody();
                                String json = slurp( body.byteStream(), 1024 );
                                System.err.println( "Error al modificar usuario : " + json );
                                showMessage( "Error", "Ocurrio un error al realizar la operación" );
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call2, Throwable t) {
                            showMessage( "Error", "Ocurrio un error al realizar la operación" );
                        }
                    } );
                }
            } );
        }
        else {
            editartbn.setVisibility( View.GONE );
        }
    }

    protected void onRestart() {
        super.onRestart();
        if (recargar) {
            System.err.println("Reiniciando pantalla del Tablero");
            cargarDatos();
        }
    }

    private void cargarDatos() {
;
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

    private boolean isEmailValido(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String userpass) {
        return userpass.length() > 4;
    }

}
