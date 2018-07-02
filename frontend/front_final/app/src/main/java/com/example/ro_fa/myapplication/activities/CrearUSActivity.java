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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ro_fa.myapplication.R;
import com.example.ro_fa.myapplication.Remote.DatosLogin;
import com.example.ro_fa.myapplication.Remote.RetrofitClient;
import com.example.ro_fa.myapplication.modelos.Proyectos;
import com.example.ro_fa.myapplication.modelos.Sprints;
import com.example.ro_fa.myapplication.modelos.SprintsPK;
import com.example.ro_fa.myapplication.modelos.Us;
import com.example.ro_fa.myapplication.modelos.UsPK;
import com.example.ro_fa.myapplication.modelos.Usuarios;
import com.example.ro_fa.myapplication.servicios.ProyectosService;
import com.example.ro_fa.myapplication.servicios.SprintsService;
import com.example.ro_fa.myapplication.servicios.UsService;
import com.example.ro_fa.myapplication.servicios.UsuariosService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ro_fa.myapplication.Remote.StringUtils.slurp;

public class CrearUSActivity extends AppCompatActivity implements Callback<ResponseBody> {

    private Button CrearUSButton;
    private UsService usService;
    private UsuariosService usuariosService;
    private ProyectosService proyectosService;
    private final Activity mContext = this;

    private TextView tiempoejecucionTxt;
    private TextView descripcionTxt;
    private TextView nombreTxt;
    private TextView horastrabajadasTxt;
    private TextView numerousTxt;
    private Spinner idusuarioSpinner;
    private TextView valortecnicoTxt;
    private TextView valornegocioTxt;

    private Us us;
    private UsPK usPK;
    private Usuarios user;

    private Integer idproyecto;
    private Integer idsprint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_crear_us );
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        usService = (UsService) RetrofitClient.create( UsService.class, "us/" );

        idproyecto = Integer.parseInt(getIntent().getStringExtra("IDPROYECTO")) ;
        idsprint = Integer.parseInt(getIntent().getStringExtra("IDSPRINT")) ;

        tiempoejecucionTxt = (EditText) findViewById( R.id.tiempoejecucion );
        descripcionTxt = (EditText) findViewById( R.id.descripcion );
        nombreTxt = (EditText) findViewById(R.id.nombre);
        horastrabajadasTxt = (EditText) findViewById( R.id.horastrabajadas );
        numerousTxt = (EditText) findViewById( R.id.numerous );
        idusuarioSpinner = (Spinner) findViewById( R.id.idusuario );
        valornegocioTxt = (TextView) findViewById( R.id.valornegocio );
        valortecnicoTxt = (TextView) findViewById( R.id.valortecnico );


        usuariosService = (UsuariosService) RetrofitClient.create(UsuariosService.class, "usuarios/");
        proyectosService = (ProyectosService) RetrofitClient.create(ProyectosService.class, "proyectos/");

        // Listar usuarios
        proyectosService.listarUsuarios(idproyecto).enqueue(new Callback<List<Usuarios>>() {
            @Override
            public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {
                final List<String> nombreClientes = new ArrayList<String>();
                final List<Usuarios> usuarioslst = response.body();
                for (Usuarios usuario : usuarioslst) {
                    nombreClientes.add(usuario.getNombre() + " " + usuario.getApellido());
                }
                //clienteSpinner = (Spinner) findViewById(R.id.cliente);
                idusuarioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        user = usuarioslst.get(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(mContext,
                        android.R.layout.simple_spinner_item, nombreClientes);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                idusuarioSpinner.setAdapter(dataAdapter);
            }

            @Override
            public void onFailure(Call<List<Usuarios>> call, Throwable t) {
                ;
            }
        });

        CrearUSButton = (Button) findViewById(R.id.button_guardar);
        CrearUSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearUS();
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

    private void crearUS() {

        boolean cancel = false;
        View focusView = null;

        Integer tiempoejecucion = Integer.parseInt(tiempoejecucionTxt.getText().toString());
        String descripcion = descripcionTxt.getText().toString();
        String nombre = nombreTxt.getText().toString();
        Integer horastrabajadas = Integer.parseInt( horastrabajadasTxt.getText().toString() );
        Integer numerous = Integer.parseInt( numerousTxt.getText().toString() );
        Integer valornegocio = Integer.parseInt( valornegocioTxt.getText().toString() );
        Integer valortecnico = Integer.parseInt( valortecnicoTxt.getText().toString() );


        if (TextUtils.isEmpty( nombre )) {
            Toast.makeText(CrearUSActivity.this, "Debe establecer un nombre para el US",
                    Toast.LENGTH_SHORT).show();
            focusView = nombreTxt;
            cancel = true;
        } else if(tiempoejecucion == null) {
            Toast.makeText(CrearUSActivity.this, "Debe seleccionar un rol para el usuario del proyecto",
                    Toast.LENGTH_SHORT).show();
            focusView = tiempoejecucionTxt;
            cancel = true;
        } else if(horastrabajadas == null) {
            Toast.makeText( CrearUSActivity.this, "Debe seleccionar un rol para el usuario del proyecto",
                    Toast.LENGTH_SHORT ).show();
            focusView = horastrabajadasTxt;
            cancel = true;
        }else if(numerous == null) {
            Toast.makeText( CrearUSActivity.this, "Debe seleccionar un rol para el usuario del proyecto",
                    Toast.LENGTH_SHORT ).show();
            focusView = numerousTxt;
            cancel = true;
        }else if(valornegocio == null) {
            Toast.makeText( CrearUSActivity.this, "Debe seleccionar un rol para el usuario del proyecto",
                    Toast.LENGTH_SHORT ).show();
            focusView = valornegocioTxt;
            cancel = true;
        }else if(valortecnico == null) {
            Toast.makeText( CrearUSActivity.this, "Debe seleccionar un rol para el usuario del proyecto",
                    Toast.LENGTH_SHORT ).show();
            focusView = valortecnicoTxt;
            cancel = true;
        }else if(TextUtils.isEmpty( descripcion )) {
            Toast.makeText( CrearUSActivity.this, "Debe seleccionar un rol para el usuario del proyecto",
                    Toast.LENGTH_SHORT ).show();
            focusView = descripcionTxt;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            CrearUSButton.setEnabled(true);
            CrearUSButton.setText("Guardar");
        } else {
            usPK = new UsPK( null, idproyecto, idsprint);
            us = new Us( usPK, tiempoejecucion, descripcion, nombre,horastrabajadas,1,numerous,user.getIdusuario(),valortecnico,valornegocio);
            System.out.println( Log.d("objeto", us.toString()));
            Call<ResponseBody> call = usService.crear(us);
            call.enqueue( this );
        }
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if(response.isSuccessful()) {
            showMessageSuccess("Exitoso", "Proyecto creada exitosamente");
            VerSprintsActivity.recargar = true;
        } else {
            ResponseBody body = response.errorBody();
            String json = slurp(body.byteStream(), 1024);
            System.err.println("Error al crear proyecto : " + json);
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
