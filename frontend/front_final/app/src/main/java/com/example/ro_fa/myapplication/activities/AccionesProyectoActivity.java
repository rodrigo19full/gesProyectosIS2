package com.example.ro_fa.myapplication.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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
import com.example.ro_fa.myapplication.modelos.Proyectos;
import com.example.ro_fa.myapplication.modelos.Sprints;
import com.example.ro_fa.myapplication.modelos.SprintsPK;
import com.example.ro_fa.myapplication.modelos.Usuarios;
import com.example.ro_fa.myapplication.servicios.ProyectosService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ro_fa.myapplication.Remote.StringUtils.slurp;

public class AccionesProyectoActivity extends NavActivity {

    private CardView usuariosCardView;
    private LinearLayout sinUsuariosContent1;
    private TextView sinUsuariosText1;

    private CardView sprintsCardView;
    private LinearLayout sinSprintsContent1;
    private TextView sinSprintsText1;

    private EditText nombreTxt;
    private EditText codproyectoTxt;
    private EditText descripcionTxt;
    private EditText anhoTxt;
    private EditText fechafinTxt;
    private EditText fechainicioTxt;
    private Date fechainicioDate;
    private Date fechafinDate;


    private ProyectosService proyectosService;
    public static boolean recargar = false;
    final DatosLogin datosLogin = new DatosLogin( this );
    private Integer idproyecto;


    @Override
    protected void inint() {
        loadLayout(R.layout.activity_acciones_proyecto, "Detalles del Proyecto");
        idproyecto = Integer.parseInt( getIntent().getStringExtra("IDPROYECTO") ) ;
        final String nombre = getIntent().getStringExtra( "NOMBRE" );
        final String codproyecto = getIntent().getStringExtra( "CODPROYECTO" );
        final String anho = getIntent().getStringExtra( "ANHO" );
        final String descripcion = getIntent().getStringExtra( "DESCRIPCION" );
        final String fechainicio = getIntent().getStringExtra( "FECHAINICIO" );
        final String fechafin = getIntent().getStringExtra( "FECHAFIN" );


        nombreTxt = (EditText) findViewById(R.id.nombre);
        nombreTxt.setText( nombre );

        codproyectoTxt = (EditText) findViewById( R.id.codproyecto );
        codproyectoTxt.setText( codproyecto );

        descripcionTxt = (EditText) findViewById(R.id.descripcion);
        descripcionTxt.setText( descripcion );

        anhoTxt = (EditText) findViewById( R.id.anho );
        anhoTxt.setText( anho );

        fechafinTxt = (EditText) findViewById( R.id.fechafin );
        fechafinTxt.setText( fechafin );

        fechainicioTxt = (EditText) findViewById( R.id.fechainicio );
        fechainicioTxt.setText( fechainicio );


        String usuarioEnSesion = datosLogin.getNombreUsuario();
        System.out.println(Log.d( "usuarioensesion", usuarioEnSesion.toString() ));
        Button eliminar = (Button) findViewById( R.id.eliminar);

        if (usuarioEnSesion.equals( "rodrigo" )){
            eliminar.setVisibility( View.VISIBLE );
            eliminar.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Call<ResponseBody> calleliminiar = proyectosService.eliminar( idproyecto );
                    calleliminiar.enqueue( new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> calleliminar, Response<ResponseBody> response) {
                            if(response.isSuccessful()) {
                                showMessageSuccess("Exitoso", "Proyecto eliminado exitosamente");
                                AdministrarProyectosActivity.recargar = true;

                            } else {
                                ResponseBody body = response.errorBody();
                                String json = slurp(body.byteStream(), 1024);
                                System.err.println("Error al eliminar proyecto : " + json);
                                showMessage("Error", "Ocurrio un error al realizar la operación");
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> calleliminar, Throwable t) {
                            showMessage("Error", "Ocurrio un error al realizar la operación");
                        }
                    } );
                }
            } );
        } else {
            eliminar.setVisibility( View.GONE );
        }



        Button editartbn = (Button) findViewById( R.id.editar );
        editartbn.setVisibility( View.VISIBLE );
        editartbn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View focusView = null;

                String nombre = nombreTxt.getText().toString();
                Integer codproyecto = Integer.parseInt( codproyectoTxt.getText().toString() ) ;
                String descripcion = descripcionTxt.getText().toString();
                Integer anho = Integer.parseInt( anhoTxt.getText().toString() );
                String inicio = fechainicioTxt.getText().toString();
                String fin = fechafinTxt.getText().toString();

                if (TextUtils.isEmpty(nombre)) {
                    nombreTxt.setError(getString(R.string.error_campo_requerido));
                    focusView = nombreTxt;
                } else if (codproyecto == null) {
                    codproyectoTxt.setError(getString(R.string.error_campo_requerido));
                    focusView = codproyectoTxt;
                } else if (TextUtils.isEmpty(descripcion)) {
                    descripcionTxt.setError(getString(R.string.error_campo_requerido));
                    focusView = descripcionTxt;
                } else if (anho == null) {
                    anhoTxt.setError(getString(R.string.error_campo_requerido));
                    focusView = anhoTxt;
                } else if (TextUtils.isEmpty(inicio)) {
                    fechainicioTxt.setError(getString(R.string.error_campo_requerido));
                    focusView = fechainicioTxt;
                } else if (TextUtils.isEmpty(fin)) {
                    fechafinTxt.setError(getString(R.string.error_campo_requerido));
                    focusView = fechafinTxt;
                }

                Proyectos proyecto = new Proyectos( idproyecto, fechainicioDate, anho, descripcion, fechafinDate, codproyecto, nombre);
                System.out.println( Log.d("objeto", proyecto.toString()));
                Call<ResponseBody> call2 = proyectosService.editar(proyecto.getIdproyecto(),proyecto);
                call2.enqueue( new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call2, Response<ResponseBody> response) {
                        if(response.isSuccessful()) {
                            showMessageSuccess("Exitoso", "Proyecto modificado exitosamente");
                            AccionesProyectoActivity.recargar = true;
                            AdministrarProyectosActivity.recargar = true;
                        } else {
                            ResponseBody body = response.errorBody();
                            String json = slurp(body.byteStream(), 1024);
                            System.err.println("Error al modificar proyecto : " + json);
                            showMessage("Error", "Ocurrio un error al realizar la operación");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call2, Throwable t) {
                        showMessage("Error", "Ocurrio un error al realizar la operación");
                    }
                } );
            }
        } );

        Button nuevoUsuario = (Button) findViewById(R.id.nuevoUsuario);
        if (usuarioEnSesion.equals( "rodrigo" )){

            nuevoUsuario.setVisibility( View.VISIBLE);
            nuevoUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(AccionesProyectoActivity.this, AsignarUsuarioActivity.class);
                    i.putExtra( "IDPROYECTO", idproyecto.toString() );
                    i.putExtra( "NOMBRE", nombre );
                    i.putExtra("CODPROYECTO", codproyecto);
                    i.putExtra( "ANHO",anho );
                    i.putExtra( "DESCRIPCION", descripcion );
                    i.putExtra( "FECHAINICIO", fechainicio );
                    i.putExtra( "FECHAFIN", fechafin );

                    startActivity(i);
                }
            });
        } else {
            nuevoUsuario.setVisibility( View.GONE );
        }



        Button nuevoSprint = (Button) findViewById( R.id.nuevoSprint );
        nuevoSprint.setVisibility( View.VISIBLE );
        nuevoSprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccionesProyectoActivity.this, CrearSprintActivity.class);
                i.putExtra( "IDPROYECTO", idproyecto.toString() );
                i.putExtra( "NOMBRE", nombre );
                i.putExtra("CODPROYECTO", codproyecto);
                i.putExtra( "ANHO",anho );
                i.putExtra( "DESCRIPCION", descripcion );
                i.putExtra( "FECHAINICIO", fechainicio );
                i.putExtra( "FECHAFIN", fechafin );
                startActivity(i);
            }
        });


        usuariosCardView = (CardView) findViewById(R.id.usuarios_card);
        sinUsuariosContent1 = (LinearLayout) findViewById(R.id.sin_usuarios_content_1);
        sinUsuariosText1 = (TextView) findViewById(R.id.sin_usuarios_text_1);


        sprintsCardView = (CardView) findViewById(R.id.sprints_card);
        sinSprintsContent1 = (LinearLayout) findViewById(R.id.sin_sprints_content_1);
        sinSprintsText1 = (TextView) findViewById(R.id.sin_sprints_text_1);

        proyectosService = (ProyectosService) RetrofitClient.create(ProyectosService.class, "proyectos/");

        cargarDatos();
    }

    protected void onRestart() {
        super.onRestart();
        if (recargar) {
            System.err.println("Reiniciando pantalla del Tablero");
            cargarDatos();
        }
    }

    private void cargarDatos() {

        Call<List<Usuarios>> call = proyectosService.listarUsuarios(idproyecto);
        System.out.println( Log.d( "url", String.valueOf( call ) ));

        call.enqueue( new Callback<List<Usuarios>>() {
            @Override
            public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {
                if(response.isSuccessful()){

                    final List<Usuarios> usuarios = response.body();

                    if (usuarios.size() == 0) {

                        sinUsuariosContent1.setVisibility(View.VISIBLE);

                    } else {
                        System.err.println("Reiniciando pantalla cargando datos");
                        ArrayList<String> list = new ArrayList<>();
                        list.clear();
                        for (int i = 0; i < usuarios.size(); ++i) {
                            list.add(usuarios.get(i).getNombre() + " " + usuarios.get(i).getApellido());
                        }
                        final AccionesProyectoActivity.StableArrayAdapter adapter = new AccionesProyectoActivity.StableArrayAdapter(AccionesProyectoActivity.this,
                                R.layout.list_item, list);

                        usuariosCardView.setMinimumHeight(usuarios.size() * 100);
                        ListView usuariosListView = new ListView(AccionesProyectoActivity.this);
                        usuariosListView.setMinimumHeight(usuarios.size() * 100);
                        usuariosListView.setAdapter(adapter);
                        usuariosListView.setDivider(null);
                        usuariosListView.setPadding(10, 80, 0, 0);
                        usuariosCardView.addView(usuariosListView);

                        /*usuariosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Usuarios datosUsuario = usuarios.get(i);

                            }
                        });*/
                    }

                } else {
                    sinUsuariosText1.setText("Ocurrio un error al recupear los datos");
                    System.out.println(Log.d( "response", response.toString() ));
                    Toast.makeText(AccionesProyectoActivity.this,
                            "Ocurrio un error al procesar la respuesta del Servidor",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Usuarios>> call, Throwable t) {
                System.out.println(Log.d( "response", call.request().toString() + "//////" +call.toString() ));
                sinUsuariosText1.setText("Ocurrio un error al recupear los datos");
                Toast.makeText(AccionesProyectoActivity.this,
                        "Ocurrio un error al procesar la respuesta del Servidor",
                        Toast.LENGTH_SHORT).show();
            }
        } );


        Call<List<Sprints>> call2 = proyectosService.listarSprints(idproyecto);
        System.out.println( Log.d( "url", String.valueOf( call2 ) ));

        call2.enqueue( new Callback<List<Sprints>>() {
            @Override
            public void onResponse(Call<List<Sprints>> call2, Response<List<Sprints>> response) {
                if(response.isSuccessful()){

                    final List<Sprints> sprints = response.body();

                    if (sprints.size() == 0) {

                        sinSprintsContent1.setVisibility(View.VISIBLE);

                    } else {
                        System.err.println("Reiniciando pantalla cargando datos");
                        ArrayList<String> list = new ArrayList<>();
                        for (int i = 0; i < sprints.size(); ++i) {
                            list.add(sprints.get(i).getNombre());
                        }
                        final AccionesProyectoActivity.StableArrayAdapter adapter = new AccionesProyectoActivity.StableArrayAdapter(AccionesProyectoActivity.this,
                                R.layout.list_item, list);

                        sprintsCardView.setMinimumHeight(sprints.size() * 100);
                        ListView sprintsListView = new ListView(AccionesProyectoActivity.this);
                        sprintsListView.setMinimumHeight(sprints.size() * 100);
                        sprintsListView.setAdapter(adapter);
                        sprintsListView.setDivider(null);
                        sprintsListView.setPadding(10, 80, 0, 0);
                        sprintsCardView.addView(sprintsListView);

                        sprintsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Sprints datosSprint = sprints.get(i);
                                startActivity(datosSprint);
                            }
                        });
                    }

                } else {
                    sinSprintsText1.setText("Ocurrio un error al recupear los datos");
                    System.out.println(Log.d( "response", response.toString() ));
                    Toast.makeText(AccionesProyectoActivity.this,
                            "Ocurrio un error al procesar la respuesta del Servidor",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Sprints>> call2, Throwable t) {
                System.out.println(Log.d( "response", call2.request().toString() + "//////" +call2.toString() ));
                sinUsuariosText1.setText("Ocurrio un error al recupear los datos");
                Toast.makeText(AccionesProyectoActivity.this,
                        "Ocurrio un error al procesar la respuesta del Servidor",
                        Toast.LENGTH_SHORT).show();
            }
        } );

    }

    private void startActivity(Sprints datosSprint) {

        Intent intent = new Intent(AccionesProyectoActivity.this, VerSprintsActivity.class);
        intent.putExtra("IDPROYECTO", datosSprint.getSprintsPK().getIdproyecto().toString());
        intent.putExtra("IDSPRINT", datosSprint.getSprintsPK().getIdsprint().toString());
        intent.putExtra("DURACION", datosSprint.getDuracion().toString());
        intent.putExtra("NOMBRE", datosSprint.getNombre());
        intent.putExtra("ESTADO", datosSprint.getEstado().toString());

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
