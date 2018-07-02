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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ro_fa.myapplication.R;
import com.example.ro_fa.myapplication.Remote.DatosLogin;
import com.example.ro_fa.myapplication.Remote.RetrofitClient;
import com.example.ro_fa.myapplication.modelos.Proyectos;
import com.example.ro_fa.myapplication.modelos.Roles;
import com.example.ro_fa.myapplication.modelos.Sprints;
import com.example.ro_fa.myapplication.modelos.SprintsPK;
import com.example.ro_fa.myapplication.modelos.Usuarios;
import com.example.ro_fa.myapplication.modelos.Usuariosproyectos;
import com.example.ro_fa.myapplication.modelos.UsuariosproyectosPK;
import com.example.ro_fa.myapplication.servicios.ProyectosService;
import com.example.ro_fa.myapplication.servicios.RolesService;
import com.example.ro_fa.myapplication.servicios.SprintsService;
import com.example.ro_fa.myapplication.servicios.UsuariosProyectosService;
import com.example.ro_fa.myapplication.servicios.UsuariosService;

import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ro_fa.myapplication.Remote.StringUtils.slurp;

public class CrearSprintActivity extends AppCompatActivity implements Callback<ResponseBody> {

    private Button CrearSprintButton;
    private final Activity mContext = this;
    final DatosLogin datosLogin = new DatosLogin(this);
    private ProyectosService proyectosService;
    private SprintsService sprintsService;
    Integer idSp;

    private TextView nombreTxt;
    private TextView duracionTxt;

    private Proyectos proyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_crear_sprint );
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        proyectosService = (ProyectosService) RetrofitClient.create( ProyectosService.class, "proyectos/" );
        sprintsService = (SprintsService) RetrofitClient.create( SprintsService.class, "sprints/" );

        Integer idproyecto = Integer.parseInt(getIntent().getStringExtra("IDPROYECTO")) ;
        Integer anho = Integer.parseInt(getIntent().getStringExtra( "ANHO" ));
        String descripcion = getIntent().getStringExtra( "DESCRIPCION" );
        Integer codproyecto = Integer.parseInt(getIntent().getStringExtra( "CODPROYECTO" ));
        String nombre = getIntent().getStringExtra( "NOMBRE" );
        Date fechainicio = new Date();
        Date fechafin = new Date();

        proyecto = new Proyectos(idproyecto, fechainicio, anho, descripcion, fechafin, codproyecto, nombre );

        nombreTxt = (EditText) findViewById(R.id.txtNombre);
        duracionTxt = (EditText) findViewById( R.id.txtduracion );

        CrearSprintButton = (Button) findViewById(R.id.button_guardar);
        CrearSprintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearSprint();
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

    private void crearSprint() {

        boolean cancel = false;
        View focusView = null;

        String nombresp = nombreTxt.getText().toString();
        Integer duracionsp = Integer.parseInt(duracionTxt.getText().toString());
        Integer estadosp = 1;

        if (TextUtils.isEmpty( nombresp )) {
            Toast.makeText(CrearSprintActivity.this, "Debe seleccionar un usuario para el proyecto",
                    Toast.LENGTH_SHORT).show();
            focusView = nombreTxt;
            cancel = true;
        } else if(duracionsp == null) {
            Toast.makeText(CrearSprintActivity.this, "Debe seleccionar un rol para el usuario del proyecto",
                    Toast.LENGTH_SHORT).show();
            focusView = duracionTxt;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            CrearSprintButton.setEnabled(true);
            CrearSprintButton.setText("Guardar");
        } else {
            SprintsPK sprintsPK = new SprintsPK( idSp, proyecto.getIdproyecto());
            Sprints sprint = new Sprints( sprintsPK, duracionsp, estadosp, nombresp);
            System.out.println( Log.d("objeto", sprint.toString()));
            Call<ResponseBody> call = sprintsService.crear(sprint);
            call.enqueue( this );
        }
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if(response.isSuccessful()) {
            showMessageSuccess("Exitoso", "Sprint creado exitosamente");
        } else {
            ResponseBody body = response.errorBody();
            String json = slurp(body.byteStream(), 1024);
            System.err.println("Error al crear sprint : " + json);
            showMessage("Error", "Ocurrio un error al realizar la operación");
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        showMessage("Error", "Ocurrio un error al realizar la operación");
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
