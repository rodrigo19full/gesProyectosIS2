package com.example.ro_fa.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ro_fa.myapplication.R;
import com.example.ro_fa.myapplication.Remote.RetrofitClient;
import com.example.ro_fa.myapplication.servicios.ProyectosService;

public class VerProyectosActivity extends AppCompatActivity {

    private ProyectosService proyectosService;
    private String idproyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_ver_proyectos );

        proyectosService = (ProyectosService) RetrofitClient.create(ProyectosService.class, "/proyectos");
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

        TextView descripcionTxt = (TextView) findViewById(R.id.descripcion);
        descripcionTxt.setText(descripcion);

        TextView fechainicioTxt = (TextView) findViewById(R.id.fechainicio);
        fechainicioTxt.setText(fechainicio);

        TextView anhoTxt = (TextView) findViewById(R.id.anho);
        anhoTxt.setText(anho);

        TextView fechaFinTxt = (TextView) findViewById(R.id.fechaFin);
        fechaFinTxt.setText(fechaFin);

        TextView clienteTxt = (TextView) findViewById(R.id.cliente);
        clienteTxt.setText(cliente);

        TextView codproyectoTxt = (TextView) findViewById(R.id.codproyecto);
        codproyectoTxt.setText(codproyecto);

        Button editarBtn = (Button) findViewById(R.id.button_editar);
        editarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerProyectosActivity.this, MainActivity.class);
                intent.putExtra("EXTRA_ID_PROYECTO", idproyecto);
                intent.putExtra("EXTRA_ID_PROPIETARIO", cliente);
                startActivity(intent);
            }
        });
    }
}
