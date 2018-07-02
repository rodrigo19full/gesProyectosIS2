package com.example.ro_fa.myapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ro_fa.myapplication.R;
import com.example.ro_fa.myapplication.Remote.DatosLogin;
import com.example.ro_fa.myapplication.Remote.RetrofitClient;
import com.example.ro_fa.myapplication.modelos.Proyectos;
import com.example.ro_fa.myapplication.servicios.ProyectosService;
import com.example.ro_fa.myapplication.servicios.UsuariosService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdministrarProyectosActivity extends NavActivity {

    private CardView proyectosCardView;
    private LinearLayout sinProyectosContent1;
    private TextView sinProyectosText1;

    private ProyectosService proyectosService;
    public static boolean recargar = false;
    final DatosLogin datosLogin = new DatosLogin( this );

    @Override
    protected void inint() {
        loadLayout(R.layout.activity_main, "Administrar Proyectos");


        String usuarioEnSesion = datosLogin.getNombreUsuario();
        System.out.println(Log.d( "usuarioensesion", usuarioEnSesion.toString() ));
        if (usuarioEnSesion.equals( "rodrigo" )){
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setVisibility( View.VISIBLE);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(AdministrarProyectosActivity.this, CrearProyectoActivity.class);
                    startActivity(i);
                }
            });

            proyectosCardView = (CardView) findViewById(R.id.mis_proyectos_card);
            sinProyectosContent1 = (LinearLayout) findViewById(R.id.sin_proyectos_content_1);
            sinProyectosText1 = (TextView) findViewById(R.id.sin_proyectos_text_1);
            proyectosService = (ProyectosService) RetrofitClient.create(ProyectosService.class, "proyectos/");
            cargarDatos();
        } else {
            Toast.makeText(AdministrarProyectosActivity.this,
                    "Usted no tiene permisos para administrar los proyectos",
                    Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (recargar) {
            System.err.println("Reiniciando pantalla del Tablero");
            cargarDatos();
        }
    }

    private void cargarDatos() {
        String idusuario = datosLogin.getIdUsuario();

        Integer parametro = Integer.parseInt( idusuario );

        System.out.println( Log.d("id",idusuario + " parametro: " + parametro.toString()));


        Call<List<Proyectos>> call = proyectosService.listar();
        System.out.println(Log.d( "url", String.valueOf( call ) ));
        call.enqueue( new Callback<List<Proyectos>>() {
            @Override
            public void onResponse(Call<List<Proyectos>> call, Response<List<Proyectos>> response) {
                if(response.isSuccessful()){

                    final List<Proyectos> proyectos = response.body();

                    if (proyectos.size() == 0) {

                        sinProyectosContent1.setVisibility(View.VISIBLE);

                    } else {
                        System.err.println("Reiniciando pantalla cargando datos");
                        final ArrayList<String> list = new ArrayList<>();
                        for (int i = 0; i < proyectos.size(); ++i) {
                            list.add(proyectos.get(i).getNombre());
                        }
                        final AdministrarProyectosActivity.StableArrayAdapter adapter = new AdministrarProyectosActivity.StableArrayAdapter(AdministrarProyectosActivity.this,
                                R.layout.list_item, list);

                        proyectosCardView.setMinimumHeight(proyectos.size() * 200);
                        ListView misProyectosListView = new ListView(AdministrarProyectosActivity.this);
                        misProyectosListView.setMinimumHeight(proyectos.size() * 220);
                        misProyectosListView.setAdapter(adapter);
                        misProyectosListView.setDivider(null);
                        misProyectosListView.setPadding(10, 60, 0, 0);
                        proyectosCardView.addView(misProyectosListView);

                        misProyectosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Proyectos datosProyecto = proyectos.get(i);
                                startActivity(datosProyecto);
                            }
                        });
                    }

                } else {
                    sinProyectosText1.setText("Ocurrio un error al recupear los datos");
                    System.out.println(Log.d( "response", response.toString() ));
                    Toast.makeText(AdministrarProyectosActivity.this,
                            "Ocurrio un error al procesar la respuesta del Servidor",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Proyectos>> call, Throwable t) {
                System.out.println(Log.d( "response", call.request().toString() + "//////" +call.toString() ));
                sinProyectosText1.setText("Ocurrio un error al recupear los datos");
                Toast.makeText(AdministrarProyectosActivity.this,
                        "Ocurrio un error al procesar la respuesta del Servidor",
                        Toast.LENGTH_SHORT).show();
            }
        } );

    }

    private void startActivity(Proyectos datosProyecto) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Intent intent = new Intent(AdministrarProyectosActivity.this, AccionesProyectoActivity.class);
        intent.putExtra("IDPROYECTO", datosProyecto.getIdproyecto().toString());
        intent.putExtra("NOMBRE", datosProyecto.getNombre());
        intent.putExtra("CODPROYECTO", datosProyecto.getCodproyecto().toString());
        intent.putExtra("DESCRIPCION", datosProyecto.getDescripcion());
        intent.putExtra("FECHAINICIO", sdf.format(datosProyecto.getFechainicio()));
        intent.putExtra("FECHAFIN", sdf.format(datosProyecto.getFechafin()));
        intent.putExtra("ANHO", datosProyecto.getAnho().toString());
        //intent.putExtra("CLIENTE", datosProyecto.getCliente().getIdusuario().toString());

        startActivity(intent);

    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
