package com.example.ro_fa.myapplication.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ro_fa.myapplication.R;
import com.example.ro_fa.myapplication.Remote.DatosLogin;
import com.example.ro_fa.myapplication.Remote.RetrofitClient;
import com.example.ro_fa.myapplication.modelos.Proyectos;
import com.example.ro_fa.myapplication.modelos.Roles;
import com.example.ro_fa.myapplication.modelos.Usuarios;
import com.example.ro_fa.myapplication.modelos.Usuariosproyectos;
import com.example.ro_fa.myapplication.modelos.UsuariosproyectosPK;
import com.example.ro_fa.myapplication.servicios.ProyectosService;
import com.example.ro_fa.myapplication.servicios.RolesService;
import com.example.ro_fa.myapplication.servicios.UsuariosProyectosService;
import com.example.ro_fa.myapplication.servicios.UsuariosService;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ro_fa.myapplication.Remote.StringUtils.slurp;

public class AsignarUsuarioActivity extends AppCompatActivity implements Callback<ResponseBody>{

    private Button AsignarUsuarioButton;
    private RolesService rolesService;
    private UsuariosService usuarioService;
    private final Activity mContext = this;
    final DatosLogin datosLogin = new DatosLogin(this);
    private UsuariosProyectosService usuariosProyectosService;



    private Spinner usuarioSpinner;
    private Spinner rolSpinner;

    private Usuarios usuario;
    private Roles rol;
    private Proyectos proyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_asignar_usuario );
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        usuarioSpinner = (Spinner) findViewById(R.id.usuario);
        rolSpinner = (Spinner) findViewById( R.id.rol );

        usuarioService = (UsuariosService) RetrofitClient.create(UsuariosService.class, "usuarios/");
        rolesService = (RolesService) RetrofitClient.create( RolesService.class, "roles/" );
        usuariosProyectosService = (UsuariosProyectosService) RetrofitClient.create( UsuariosProyectosService.class, "usuariosproyectos/" );

        Integer idproyecto = Integer.parseInt(getIntent().getStringExtra("IDPROYECTO")) ;
        //String fechainiciotxt = getIntent().getStringExtra( "FECHAINICIO" );
        Integer anho = Integer.parseInt(getIntent().getStringExtra( "ANHO" ));
        String descripcion = getIntent().getStringExtra( "DESCRIPCION" );
        //String fechafintxt = getIntent().getStringExtra( "fechafin" );
        Integer codproyecto = Integer.parseInt(getIntent().getStringExtra( "CODPROYECTO" ));
        String nombre = getIntent().getStringExtra( "NOMBRE" );
        Date fechainicio = new Date();
        Date fechafin = new Date();

        proyecto = new Proyectos(idproyecto, fechainicio, anho, descripcion, fechafin, codproyecto, nombre );

        // Listar usuarios
        usuarioService.listar().enqueue(new Callback<List<Usuarios>>() {
            @Override
            public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {
                final List<String> nombreUsuarios = new ArrayList<String>();
                final List<Usuarios> usuarios = response.body();
                for (Usuarios usuario : usuarios) {
                    nombreUsuarios.add(usuario.getNombre() + " " + usuario.getApellido());
                }

                usuarioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        usuario = usuarios.get(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(mContext,
                        android.R.layout.simple_spinner_item, nombreUsuarios);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                usuarioSpinner.setAdapter(dataAdapter);
            }

            @Override
            public void onFailure(Call<List<Usuarios>> call, Throwable t) {
                ;
            }
        });

        // Listar roles
        rolesService.listar().enqueue(new Callback<List<Roles>>() {
            @Override
            public void onResponse(Call<List<Roles>> call, Response<List<Roles>> response) {
                final List<String> nombreRoles = new ArrayList<String>();
                final List<Roles> roles = response.body();
                for (Roles rol : roles) {
                    nombreRoles.add(rol.getRol());
                }

                rolSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        rol = roles.get(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(mContext,
                        android.R.layout.simple_spinner_item, nombreRoles);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                rolSpinner.setAdapter(dataAdapter);
            }

            @Override
            public void onFailure(Call<List<Roles>> call, Throwable t) {
                ;
            }
        });

        AsignarUsuarioButton = (Button) findViewById(R.id.button_guardar);
        AsignarUsuarioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asignarUsuario();
            }
        });

        Button cancelarCuentaButton = (Button) findViewById(R.id.button_cancelar);
        cancelarCuentaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccionesProyectoActivity.recargar = false;
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AccionesProyectoActivity.recargar = false;
        super.onBackPressed();
    }

    private void asignarUsuario() {

        boolean cancel = false;
        View focusView = null;

        if (usuario == null) {
            Toast.makeText(AsignarUsuarioActivity.this, "Campo requerido",
                    Toast.LENGTH_SHORT).show();
            focusView = usuarioSpinner;
            cancel = true;
        } else if(rol == null) {
            Toast.makeText(AsignarUsuarioActivity.this, "Campo requerido",
                    Toast.LENGTH_SHORT).show();
            focusView = rolSpinner;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            AsignarUsuarioButton.setEnabled(true);
            AsignarUsuarioButton.setText("Guardar");
        } else {
            UsuariosproyectosPK usuariosproyectosPK = new UsuariosproyectosPK( proyecto.getIdproyecto(),usuario.getIdusuario() );
            Usuariosproyectos nuevo = new Usuariosproyectos( usuariosproyectosPK, rol);
            System.out.println( Log.d("objeto", nuevo.toString()));
            Call<ResponseBody> call = usuariosProyectosService.crear(nuevo);
            call.enqueue( this );
        }
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


    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if(response.isSuccessful()) {
            showMessageSuccess("Exitoso", "Usuario asignado exitosamente");
            AccionesProyectoActivity.recargar=true;
        } else {
            ResponseBody body = response.errorBody();
            String json = slurp(body.byteStream(), 1024);
            System.err.println("Error al asignar usuario : " + json);
            showMessage("Error", "Ocurrio un error al realizar la operación");
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        showMessage("Error", "Ocurrio un error al realizar la operación");
    }
}
