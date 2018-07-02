package com.example.ro_fa.myapplication.activities;

import android.content.Context;
import android.content.Intent;
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
import com.example.ro_fa.myapplication.modelos.Us;
import com.example.ro_fa.myapplication.servicios.UsuariosService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends NavActivity {

    private CardView misProyectosCardView;
    private LinearLayout sinProyectosContent1;
    private TextView sinProyectosText1;

    private CardView misTareasCardView;
    private LinearLayout sinTareasContent1;
    private TextView sinTareasText1;

    private UsuariosService usuariosService;
    public static boolean recargar = false;
    final DatosLogin datosLogin = new DatosLogin( this );

    @Override
    protected void inint() {
        loadLayout(R.layout.activity_main, "Tablero");

        misProyectosCardView = (CardView) findViewById(R.id.mis_proyectos_card);
        sinProyectosContent1 = (LinearLayout) findViewById(R.id.sin_proyectos_content_1);
        sinProyectosText1 = (TextView) findViewById(R.id.sin_proyectos_text_1);

        misTareasCardView = (CardView) findViewById(R.id.mis_tareas_card);
        sinTareasContent1 = (LinearLayout) findViewById(R.id.sin_tareas_content_1);
        sinTareasText1 = (TextView) findViewById(R.id.sin_tareas_text_1);

        usuariosService = (UsuariosService) RetrofitClient.create(UsuariosService.class, "usuarios/");
        cargarDatos();
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

        System.out.println(Log.d("id",idusuario + " parametro: " + parametro.toString()));


        Call<List<Proyectos>> call = usuariosService.listarMisProyectos( parametro );
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
                        final StableArrayAdapter adapter = new StableArrayAdapter(MainActivity.this,
                                R.layout.list_item, list);

                        misProyectosCardView.setMinimumHeight(proyectos.size() * 200);
                        ListView misProyectosListView = new ListView(MainActivity.this);
                        misProyectosListView.setMinimumHeight(proyectos.size() * 220);
                        misProyectosListView.setAdapter(adapter);
                        misProyectosListView.setDivider(null);
                        misProyectosListView.setPadding(10, 60, 0, 0);
                        misProyectosCardView.addView(misProyectosListView);

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
                    Toast.makeText(MainActivity.this,
                            "Ocurrio un error al procesar la respuesta del Servidor",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Proyectos>> call, Throwable t) {
                System.out.println(Log.d( "response", call.request().toString() + "//////" +call.toString() ));
                sinProyectosText1.setText("Ocurrio un error al recupear los datos");
                Toast.makeText(MainActivity.this,
                        "Ocurrio un error al procesar la respuesta del Servidor",
                        Toast.LENGTH_SHORT).show();
            }
        } );

        Call<List<Us>> call2 = usuariosService.listarTareas( parametro );
        System.out.println(Log.d( "url", String.valueOf( call ) ));
        call2.enqueue( new Callback<List<Us>>() {
            @Override
            public void onResponse(Call<List<Us>> call2, Response<List<Us>> response) {
                if(response.isSuccessful()){

                    final List<Us> tareas = response.body();

                    if (tareas.size() == 0) {

                        sinTareasContent1.setVisibility(View.VISIBLE);

                    } else {
                        System.err.println("Reiniciando pantalla cargando datos");
                        final ArrayList<String> list = new ArrayList<>();
                        for (int i = 0; i < tareas.size(); ++i) {
                            list.add(tareas.get(i).getNumerous() +" " + tareas.get(i).getNombre());
                        }
                        final StableArrayAdapter adapter = new StableArrayAdapter(MainActivity.this,
                                R.layout.list_item, list);

                        misTareasCardView.setMinimumHeight(tareas.size() * 200);
                        ListView misTareasListView = new ListView(MainActivity.this);
                        misTareasListView.setMinimumHeight(tareas.size() * 220);
                        misTareasListView.setAdapter(adapter);
                        misTareasListView.setDivider(null);
                        misTareasListView.setPadding(10, 60, 0, 0);
                        misTareasCardView.addView(misTareasListView);

                        misTareasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Us datosTareas = tareas.get(i);
                                startActivityTarea(datosTareas);
                            }
                        });
                    }

                } else {
                    sinProyectosText1.setText("Ocurrio un error al recupear los datos");
                    System.out.println(Log.d( "response", response.toString() ));
                    Toast.makeText(MainActivity.this,
                            "Ocurrio un error al procesar la respuesta del Servidor",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Us>> call2, Throwable t) {
                System.out.println(Log.d( "response", call2.request().toString() + "//////" +call2.toString() ));
                sinProyectosText1.setText("Ocurrio un error al recupear los datos");
                Toast.makeText(MainActivity.this,
                        "Ocurrio un error al procesar la respuesta del Servidor",
                        Toast.LENGTH_SHORT).show();
            }
        } );
    }

    private void startActivity(Proyectos datosProyecto) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Intent intent = new Intent(MainActivity.this, AccionesProyectoActivity.class);
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

    private void startActivityTarea(Us datosUs) {

        Intent intent = new Intent(MainActivity.this, VerUSActivity.class);
        intent.putExtra("IDPROYECTO", datosUs.getUsPK().getIdproyecto().toString());
        intent.putExtra("IDSPRINT", datosUs.getUsPK().getIdsprint().toString());
        intent.putExtra("IDUS", datosUs.getUsPK().getIdus().toString());
        intent.putExtra("TIEMPOEJECUCION", datosUs.getTiempoejecucion().toString());
        intent.putExtra("DESCRIPCION", datosUs.getDescripcion());
        intent.putExtra("NOMBRE", datosUs.getNombre());
        intent.putExtra("HORASTRABAJADAS", datosUs.getHorastrabajadas().toString());
        intent.putExtra("ESTADO", datosUs.getEstado().toString());
        intent.putExtra("NUMEROUS", datosUs.getNumerous().toString());
        intent.putExtra("IDUSUARIO", datosUs.getIdusuario().toString());
        intent.putExtra("VALORNEGOCIO", datosUs.getValornegocio().toString());
        intent.putExtra("VALORTECNICO", datosUs.getValortecnico().toString());

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
