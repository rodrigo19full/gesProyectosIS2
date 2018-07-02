package com.example.ro_fa.myapplication.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ro_fa.myapplication.R;
import com.example.ro_fa.myapplication.Remote.DatosLogin;
import com.example.ro_fa.myapplication.Remote.RetrofitClient;
import com.example.ro_fa.myapplication.modelos.Sprints;
import com.example.ro_fa.myapplication.modelos.SprintsPK;
import com.example.ro_fa.myapplication.modelos.Us;
import com.example.ro_fa.myapplication.modelos.Usuarios;
import com.example.ro_fa.myapplication.servicios.ProyectosService;
import com.example.ro_fa.myapplication.servicios.SprintsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ro_fa.myapplication.Remote.StringUtils.slurp;

public class VerSprintsActivity extends NavActivity {

    private CardView USCardView;
    private LinearLayout sinUSContent1;
    private TextView sinUSText1;
    private TextView nombretxt;
    private TextView duraciontxt;

    private SprintsService sprintsService;
    public static boolean recargar = false;
    //private final Activity mContext = this;
    final DatosLogin datosLogin = new DatosLogin( this );
    private Integer idproyecto;
    private Integer idsprint;

    @Override
    protected void inint() {
        loadLayout( R.layout.activity_ver_sprints, "Detalles del Sprint" );
        idproyecto = Integer.parseInt( getIntent().getStringExtra( "IDPROYECTO" ) );
        idsprint = Integer.parseInt( getIntent().getStringExtra( "IDSPRINT" ) );
        final String nombre = getIntent().getStringExtra( "NOMBRE" );
        final Integer duracion = Integer.parseInt( getIntent().getStringExtra( "DURACION" ) );
        final Integer estado = Integer.parseInt( getIntent().getStringExtra( "ESTADO" ) );

        Button nuevoUS = (Button) findViewById( R.id.nuevoUS );
        nuevoUS.setVisibility( View.VISIBLE );
        nuevoUS.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( VerSprintsActivity.this, CrearUSActivity.class );
                i.putExtra( "IDPROYECTO", idproyecto.toString() );
                i.putExtra( "IDSPRINT", idsprint.toString() );
                i.putExtra( "NOMBRE", nombre );
                i.putExtra( "DURACION", duracion.toString() );
                i.putExtra( "ESTADO", estado.toString() );

                startActivity( i );
            }
        } );

        String usuarioEnSesion = datosLogin.getNombreUsuario();
        System.out.println(Log.d( "usuarioensesion", usuarioEnSesion.toString() ));
        Button eliminar = (Button) findViewById( R.id.eliminar);

        if (usuarioEnSesion.equals( "rodrigo" )){
            eliminar.setVisibility( View.VISIBLE );
            eliminar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SprintsPK sprintsPK = new SprintsPK( idsprint, idproyecto);
                Sprints sprint = new Sprints( sprintsPK, duracion, 1, nombre);

                System.out.println(Log.d( "objeto ", sprintsPK.toString() ));

                Call<ResponseBody> calleliminiar = sprintsService.eliminar( sprint.getSprintsPK() );
                calleliminiar.enqueue( new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> calleliminar, Response<ResponseBody> response) {
                        if(response.isSuccessful()) {
                            showMessageSuccess("Exitoso", "Sprint eliminado exitosamente");

                            AccionesProyectoActivity.recargar = true;
                        } else {
                            ResponseBody body = response.errorBody();
                            String json = slurp(body.byteStream(), 1024);
                            System.err.println("Error al eliminar sprint : " + json);
                            showMessage("Error", "Ocurrio un error al realizar la operación");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> calleliminar, Throwable t) {
                        showMessage("Error", "Ocurrio un error al realizar la operación");
                    }
                } );
            }
        } );} else{
            eliminar.setVisibility( View.GONE );}

        Button nuevoSprint = (Button) findViewById( R.id.editar );
        nuevoSprint.setVisibility( View.VISIBLE );
        nuevoSprint.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombreED = nombretxt.getText().toString();
                Integer duracionED = Integer.parseInt(duraciontxt.getText().toString());

                SprintsPK sprintsPK = new SprintsPK( idsprint, idproyecto);
                Sprints sprint = new Sprints( sprintsPK, duracionED, 1, nombreED);
                System.out.println( Log.d("objeto", sprint.toString()));
                Call<ResponseBody> call2 = sprintsService.editar(sprint);
                call2.enqueue( new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()) {
                            showMessageSuccess("Exitoso", "Sprint modificado exitosamente");

                            AccionesProyectoActivity.recargar = true;
                        } else {
                            ResponseBody body = response.errorBody();
                            String json = slurp(body.byteStream(), 1024);
                            System.err.println("Error al modificar sprint : " + json);
                            showMessage("Error", "Ocurrio un error al realizar la operación");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        showMessage("Error", "Ocurrio un error al realizar la operación");
                    }
                } );
            }
        } );


        USCardView = (CardView) findViewById( R.id.us_card );
        sinUSContent1 = (LinearLayout) findViewById( R.id.sin_us_content_1 );
        sinUSText1 = (TextView) findViewById( R.id.sin_us_text_1 );
        nombretxt = (TextView) findViewById( R.id.nombre );
        nombretxt.setText( nombre );
        duraciontxt = (TextView) findViewById( R.id.duracion );
        duraciontxt.setText( duracion.toString() );

        sprintsService = (SprintsService) RetrofitClient.create( SprintsService.class, "sprints/" );

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

        Call<List<Us>> call = sprintsService.listaUS( idsprint );
        System.out.println( Log.d( "url", String.valueOf( call ) ) );

        call.enqueue( new Callback<List<Us>>() {
            @Override
            public void onResponse(Call<List<Us>> call, Response<List<Us>> response) {
                if (response.isSuccessful()) {

                    final List<Us> uslist = response.body();

                    if (uslist.size() == 0) {

                        sinUSContent1.setVisibility( View.VISIBLE );

                    } else {
                        System.err.println( "Reiniciando pantalla cargando datos" );
                        final ArrayList<String> list = new ArrayList<>();
                        for (int i = 0; i < uslist.size(); ++i) {
                            list.add( uslist.get( i ).getNumerous() + " " + uslist.get( i ).getNombre() );
                        }
                        final VerSprintsActivity.StableArrayAdapter adapter = new VerSprintsActivity.StableArrayAdapter( VerSprintsActivity.this,
                                R.layout.list_item, list );

                        USCardView.setMinimumHeight( uslist.size() * 100 );
                        ListView usListView = new ListView( VerSprintsActivity.this );
                        usListView.setMinimumHeight( uslist.size() * 100 );
                        usListView.setAdapter( adapter );
                        usListView.setDivider( null );
                        usListView.setPadding( 10, 60, 0, 0 );
                        USCardView.addView( usListView );

                        usListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Us datosUs = uslist.get(i);
                                startActivity( datosUs );

                            }
                        });
                    }

                } else {
                    sinUSText1.setText( "Ocurrio un error al recupear los datos" );
                    System.out.println( Log.d( "response", response.toString() ) );
                    Toast.makeText( VerSprintsActivity.this,
                            "Ocurrio un error al procesar la respuesta del Servidor",
                            Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<List<Us>> call, Throwable t) {
                System.out.println( Log.d( "response", call.request().toString() + "//////" + call.toString() ) );
                sinUSText1.setText( "Ocurrio un error al recupear los datos" );
                Toast.makeText( VerSprintsActivity.this,
                        "Ocurrio un error al procesar la respuesta del Servidor",
                        Toast.LENGTH_SHORT ).show();
            }
        } );

    }

    private void startActivity(Us datosUs) {

        Intent intent = new Intent(VerSprintsActivity.this, VerUSActivity.class);
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
