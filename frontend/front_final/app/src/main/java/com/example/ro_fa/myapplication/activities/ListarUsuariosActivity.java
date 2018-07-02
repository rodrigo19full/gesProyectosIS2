package com.example.ro_fa.myapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ro_fa.myapplication.R;
import com.example.ro_fa.myapplication.Remote.DatosLogin;
import com.example.ro_fa.myapplication.Remote.RetrofitClient;
import com.example.ro_fa.myapplication.adapters.UsuariosAdapter;
import com.example.ro_fa.myapplication.modelos.Proyectos;
import com.example.ro_fa.myapplication.modelos.Usuarios;
import com.example.ro_fa.myapplication.servicios.ProyectosService;
import com.example.ro_fa.myapplication.servicios.UsuariosService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarUsuariosActivity extends NavActivity{

    private CardView usuariosCardView;
    private LinearLayout sinUsuariosContent1;
    private TextView sinUsuariosText1;

    private UsuariosService usuariosService;
    public static boolean recargar = false;
    final DatosLogin datosLogin = new DatosLogin( this );

    @Override
    protected void inint() {
        loadLayout(R.layout.activity_listar_usuarios, "Administrar Usuarios");


        String usuarioEnSesion = datosLogin.getNombreUsuario();
        System.out.println(Log.d( "usuarioensesion", usuarioEnSesion.toString() ));
        if (usuarioEnSesion.equals( "rodrigo" )){
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setVisibility( View.VISIBLE);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(ListarUsuariosActivity.this, CrearUsuarioActivity.class);
                    startActivity(i);
                }
            });

            usuariosCardView = (CardView) findViewById(R.id.usuarios_card);
            sinUsuariosContent1 = (LinearLayout) findViewById(R.id.sin_usuarios_content_1);
            sinUsuariosText1 = (TextView) findViewById(R.id.sin_usuarios_text_1);
            usuariosService = (UsuariosService) RetrofitClient.create(UsuariosService.class, "usuarios/");
            cargarDatos();
        } else {
            Toast.makeText(ListarUsuariosActivity.this,
                    "Usted no tiene permisos para administrar los usuarios",
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

        Call<List<Usuarios>> call = usuariosService.listar();
        System.out.println(Log.d( "url", String.valueOf( call ) ));
        call.enqueue( new Callback<List<Usuarios>>() {
            @Override
            public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {
                if(response.isSuccessful()){

                    final List<Usuarios> usuarios = response.body();

                    if (usuarios.size() == 0) {

                        sinUsuariosContent1.setVisibility(View.VISIBLE);

                    } else {
                        System.err.println("Reiniciando pantalla cargando datos");
                        final ArrayList<String> list = new ArrayList<>();
                        for (int i = 0; i < usuarios.size(); ++i) {
                            list.add(usuarios.get(i).getNombre() + " " + usuarios.get(i).getApellido());
                        }
                        final ListarUsuariosActivity.StableArrayAdapter adapter = new ListarUsuariosActivity.StableArrayAdapter(ListarUsuariosActivity.this,
                                R.layout.list_item, list);

                        usuariosCardView.setMinimumHeight(usuarios.size() * 200);
                        ListView usuariosListView = new ListView(ListarUsuariosActivity.this);
                        usuariosListView.setMinimumHeight(usuarios.size() * 220);
                        usuariosListView.setAdapter(adapter);
                        usuariosListView.setDivider(null);
                        usuariosListView.setPadding(10, 60, 0, 0);
                        usuariosCardView.addView(usuariosListView);

                        usuariosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Usuarios datosUsuario = usuarios.get(i);
                                startActivity(datosUsuario);
                            }
                        });
                    }

                } else {
                    sinUsuariosText1.setText("Ocurrio un error al recupear los datos");
                    System.out.println(Log.d( "response", response.toString() ));
                    Toast.makeText(ListarUsuariosActivity.this,
                            "Ocurrio un error al procesar la respuesta del Servidor",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Usuarios>> call, Throwable t) {
                System.out.println(Log.d( "response", call.request().toString() + "//////" +call.toString() ));
                sinUsuariosText1.setText("Ocurrio un error al recupear los datos");
                Toast.makeText(ListarUsuariosActivity.this,
                        "Ocurrio un error al procesar la respuesta del Servidor",
                        Toast.LENGTH_SHORT).show();
            }
        } );

    }

    private void startActivity(Usuarios datosUsuario) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Intent intent = new Intent(ListarUsuariosActivity.this, VerUsuarioActivity.class);
        intent.putExtra("IDUSUARIO", datosUsuario.getIdusuario().toString());
        intent.putExtra("CEDULA", datosUsuario.getCedula().toString());
        intent.putExtra("NOMBRE", datosUsuario.getNombre());
        intent.putExtra("APELLIDO", datosUsuario.getApellido());
        intent.putExtra("DIRECCION", datosUsuario.getDireccion());
        intent.putExtra("FECHANACIMIENTO", sdf.format(datosUsuario.getFechanacimiento()));
        intent.putExtra("FECHACREACION", sdf.format(datosUsuario.getFechacreacion()));
        intent.putExtra("EMAIL", datosUsuario.getEmail());
        intent.putExtra("USERNOMBRE", datosUsuario.getUsernombre());
        intent.putExtra("USERPASS", datosUsuario.getUserpass());
        intent.putExtra("TELEFONO", datosUsuario.getTelefono());
        intent.putExtra("ESTADO", datosUsuario.getEstado().toString());

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
