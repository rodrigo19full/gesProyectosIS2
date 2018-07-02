package com.example.ro_fa.myapplication.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ro_fa.myapplication.R;
import com.example.ro_fa.myapplication.Remote.DatosLogin;
import com.example.ro_fa.myapplication.Remote.RetrofitClient;
import com.example.ro_fa.myapplication.servicios.ProyectosService;

import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatosProyectoActivity extends AppCompatActivity {

    private ProyectosService proyectosService;
    private String idproyecto;
    final DatosLogin datosLogin = new DatosLogin(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_proyecto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        proyectosService = (ProyectosService) RetrofitClient.create(ProyectosService.class, "proyectos/");
        idproyecto = getIntent().getStringExtra("IDPROYECTO");
        final String nombre = getIntent().getStringExtra("NOMBRE");
        final String fechainicio = getIntent().getStringExtra("FECHAINICIO");
        final String anho = getIntent().getStringExtra("ANHO");
        final String descripcion = getIntent().getStringExtra("DESCRIPCION");
        final String fechaFin = getIntent().getStringExtra("FECHAFIN");
        final String codproyecto = getIntent().getStringExtra("CODPROYECTO");
        final String cliente = getIntent().getStringExtra("CLIENTE");

        TextView nombreTxt = (TextView) findViewById(R.id.nombre);
        nombreTxt.setText(nombre);

        TextView codProyectoTxt = (TextView) findViewById(R.id.codproyecto);
        codProyectoTxt.setText(codproyecto);

        TextView descripcionTxt = (TextView) findViewById(R.id.descripcion);
        descripcionTxt.setText(descripcion);

        TextView fechainicioTxt = (TextView) findViewById(R.id.fechainicio);
        fechainicioTxt.setText(fechainicio);

        TextView anhoTxt = (TextView) findViewById(R.id.anho);
        anhoTxt.setText(anho);

        TextView fechaFinTxt = (TextView) findViewById(R.id.fechaFin);
        fechaFinTxt.setText(fechaFin);


        Button editarBtn = (Button) findViewById(R.id.button_editar);
        editarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DatosProyectoActivity.this, EditarProyectoActivity.class);
                intent.putExtra("IDPROYECTO", idproyecto);
                intent.putExtra("NOMBRE", nombre);
                intent.putExtra("DESCRIPCION", descripcion);
                intent.putExtra( "ANHO", anho  );
                intent.putExtra( "FECHAINICIO", fechainicio );
                intent.putExtra("FECHAFIN", fechaFin);
                intent.putExtra( "CODPROYECTO", codproyecto );
                intent.putExtra("CLIENTE", cliente);
                startActivity(intent);
            }
        });

        Button eliminarBtn = (Button) findViewById(R.id.button_eliminar);
        eliminarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessageSuccess("Confirmar", "Esta seguro que desea eliminar el Proyecto ?");
            }
        });

    }

    private void showMessageSuccess(String titulo, String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mensaje).setTitle(titulo);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Integer parametro = Integer.parseInt(idproyecto);
                Call<ResponseBody> call = proyectosService.eliminar(parametro);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            MainActivity.recargar = true;
                            showMessageSuccess("Exitoso", "Proyecto eliminado exitosamente");
                        } else {
                            showMessage("Error", "Ocurrio un error al realizar la operación");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        showMessage("Error", "Ocurrio un error al realizar la operación");
                    }
                });
                System.err.println("Error editar proyecto, eliminar proyecto");
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.err.println("Error editar proyecto, NO eliminar proyecto");
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showMessage(String titulo, String mensaje) {
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
}
