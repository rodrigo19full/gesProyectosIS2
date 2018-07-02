package com.example.ro_fa.myapplication.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.ro_fa.myapplication.modelos.Notasus;
import com.example.ro_fa.myapplication.modelos.Proyectos;
import com.example.ro_fa.myapplication.modelos.Us;
import com.example.ro_fa.myapplication.modelos.UsPK;
import com.example.ro_fa.myapplication.servicios.SprintsService;
import com.example.ro_fa.myapplication.servicios.UsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ro_fa.myapplication.Remote.StringUtils.slurp;

public class VerUSActivity extends NavActivity {

    private CardView NotaCardView;
    private LinearLayout sinNotaContent1;
    private TextView sinNotaText1;
    private TextView nombretxt;
    private TextView descipciontxt;
    private TextView horastrabajadastxt;
    private TextView tiempoejecuciontxt;
    private TextView numeroustxt;
    private TextView valornegociotxt;
    private TextView valortecnicotxt;
    private Spinner idusuarioSpinner;

    private UsService usService;
    public static boolean recargar = false;
    final DatosLogin datosLogin = new DatosLogin( this );
    private Integer idproyecto;
    private Integer idsprint;
    private Integer idus;

    @Override
    protected void inint() {
        loadLayout( R.layout.activity_ver_us, "Detalles del US" );

        idproyecto = Integer.parseInt( getIntent().getStringExtra( "IDPROYECTO" ) );
        idsprint = Integer.parseInt( getIntent().getStringExtra( "IDSPRINT" ) );
        idus = Integer.parseInt( getIntent().getStringExtra( "IDUS" ) );
        final Integer tiempoejecucion = Integer.parseInt( getIntent().getStringExtra( "TIEMPOEJECUCION" ) );
        final String descipcion = getIntent().getStringExtra( "DESCRIPCION" ) ;
        final String nombre = getIntent().getStringExtra( "NOMBRE" );
        final Integer horastrabajadas = Integer.parseInt(getIntent().getStringExtra( "HORASTRABAJADAS" ));
        final Integer estado = Integer.parseInt( getIntent().getStringExtra( "ESTADO" ) );
        final Integer numerous = Integer.parseInt( getIntent().getStringExtra( "NUMEROUS" ) );
        final Integer idusuario = Integer.parseInt( getIntent().getStringExtra( "IDUSUARIO" ) );
        final Integer valortecnico = Integer.parseInt( getIntent().getStringExtra( "VALORTECNICO" ) );
        final Integer valornegocio = Integer.parseInt( getIntent().getStringExtra( "VALORNEGOCIO" ) );

        NotaCardView = (CardView) findViewById( R.id.nota_card );
        sinNotaContent1 = (LinearLayout) findViewById( R.id.sin_nota_content_1 );
        sinNotaText1 = (TextView) findViewById( R.id.sin_nota_text_1 );

        numeroustxt = (TextView) findViewById( R.id.numerous );
        numeroustxt.setText(numerous.toString());

        nombretxt = (TextView) findViewById( R.id.nombre );
        nombretxt.setText( nombre );

        descipciontxt = (TextView) findViewById( R.id.descripcion);
        descipciontxt.setText( descipcion );

        tiempoejecuciontxt = (TextView) findViewById( R.id.tiempoejecucion );
        tiempoejecuciontxt.setText(tiempoejecucion.toString());

        horastrabajadastxt = (TextView) findViewById( R.id.horastrabajadas );
        horastrabajadastxt.setText(horastrabajadas.toString());

        valornegociotxt = (TextView) findViewById( R.id.valornegocio );
        valornegociotxt.setText(valornegocio.toString());

        valortecnicotxt = (TextView) findViewById( R.id.valortecnico );
        valortecnicotxt.setText(valortecnico.toString());

        idusuarioSpinner = (Spinner) findViewById( R.id.idusuario );

        String usuarioEnSesion = datosLogin.getNombreUsuario();
        System.out.println(Log.d( "usuarioensesion", usuarioEnSesion.toString() ));
        Button eliminarbtn = (Button) findViewById( R.id.eliminar);

        if (usuarioEnSesion.equals( "rodrigo" )) {

            eliminarbtn.setVisibility( View.VISIBLE );
            eliminarbtn.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    UsPK usPK = new UsPK( idus, idproyecto, idsprint );
                    //Us us = new Us( usPK, tiempoejecucion, descripcion, nombre,horastrabajadas,1,numerous,idusuario,valortecnico,valornegocio);
                    //System.out.println( Log.d("objeto", us.toString()));

                    Call<ResponseBody> calleliminiar = usService.eliminar( usPK );
                    calleliminiar.enqueue( new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> calleliminar, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                showMessageSuccess( "Exitoso", "Sprint eliminado exitosamente" );
                                AdministrarProyectosActivity.recargar = true;

                                AccionesProyectoActivity.recargar = true;
                            } else {
                                ResponseBody body = response.errorBody();
                                String json = slurp( body.byteStream(), 1024 );
                                System.err.println( "Error al eliminar sprint : " + json );
                                showMessage( "Error", "Ocurrio un error al realizar la operación" );
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> calleliminar, Throwable t) {
                            showMessage( "Error", "Ocurrio un error al realizar la operación" );
                        }
                    } );
                }
            } );
        } else{
            eliminarbtn.setVisibility( View.GONE );
        }

        Button editartbn = (Button) findViewById( R.id.editar );
        editartbn.setVisibility( View.VISIBLE );
        editartbn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View focusView = null;

                Integer tiempoejecucion = Integer.parseInt(tiempoejecuciontxt.getText().toString());
                String descripcion = descipciontxt.getText().toString();
                String nombre = nombretxt.getText().toString();
                Integer horastrabajadas = Integer.parseInt( horastrabajadastxt.getText().toString() );
                Integer numerous = Integer.parseInt( numeroustxt.getText().toString() );
                Integer valornegocio = Integer.parseInt( valornegociotxt.getText().toString() );
                Integer valortecnico = Integer.parseInt( valortecnicotxt.getText().toString() );

                if (TextUtils.isEmpty( nombre )) {
                    Toast.makeText(VerUSActivity.this, "Debe establecer un nombre para el US",
                            Toast.LENGTH_SHORT).show();
                    focusView = nombretxt;
                } else if(tiempoejecucion == null) {
                    Toast.makeText(VerUSActivity.this, "Debe seleccionar un rol para el usuario del proyecto",
                            Toast.LENGTH_SHORT).show();
                    focusView = tiempoejecuciontxt;
                } else if(horastrabajadas == null) {
                    Toast.makeText( VerUSActivity.this, "Debe seleccionar un rol para el usuario del proyecto",
                            Toast.LENGTH_SHORT ).show();
                    focusView = horastrabajadastxt;
                }else if(numerous == null) {
                    Toast.makeText( VerUSActivity.this, "Debe seleccionar un rol para el usuario del proyecto",
                            Toast.LENGTH_SHORT ).show();
                    focusView = numeroustxt;
                }else if(valornegocio == null) {
                    Toast.makeText( VerUSActivity.this, "Debe seleccionar un rol para el usuario del proyecto",
                            Toast.LENGTH_SHORT ).show();
                    focusView = valornegociotxt;
                }else if(valortecnico == null) {
                    Toast.makeText( VerUSActivity.this, "Debe seleccionar un rol para el usuario del proyecto",
                            Toast.LENGTH_SHORT ).show();
                    focusView = valortecnicotxt;
                }else if(TextUtils.isEmpty( descripcion )) {
                    Toast.makeText( VerUSActivity.this, "Debe seleccionar un rol para el usuario del proyecto",
                            Toast.LENGTH_SHORT ).show();
                    focusView = descipciontxt;
                }

                UsPK usPK = new UsPK( idus, idproyecto, idsprint);
                Us us = new Us( usPK, tiempoejecucion, descripcion, nombre,horastrabajadas,1,numerous,idusuario,valortecnico,valornegocio);
                System.out.println( Log.d("objeto", us.toString()));

                Call<ResponseBody> call2 = usService.editar(us);
                call2.enqueue( new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call2, Response<ResponseBody> response) {
                        if(response.isSuccessful()) {
                            showMessageSuccess("Exitoso", "US modificado exitosamente");
                            VerSprintsActivity.recargar = true;
                        } else {
                            ResponseBody body = response.errorBody();
                            String json = slurp(body.byteStream(), 1024);
                            System.err.println("Error al modificar us : " + json);
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

        Button nuevaNota = (Button) findViewById( R.id.nuevaNota );
        nuevaNota.setVisibility( View.VISIBLE );
        nuevaNota.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( VerUSActivity.this, CrearUSActivity.class );
                i.putExtra( "IDPROYECTO", idproyecto.toString() );
                i.putExtra( "IDSPRINT", idsprint.toString() );
                i.putExtra("IDUS", idus.toString());

                startActivity( i );
            }
        } );
        usService = (UsService) RetrofitClient.create( UsService.class, "us/" );

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

        Call<List<Notasus>> call = usService.listarNotas( idus );
        System.out.println( Log.d( "url", String.valueOf( call ) ) );

        call.enqueue( new Callback<List<Notasus>>() {
            @Override
            public void onResponse(Call<List<Notasus>> call, Response<List<Notasus>> response) {
                if (response.isSuccessful()) {

                    final List<Notasus> notalist = response.body();

                    if (notalist.size() == 0) {

                        sinNotaContent1.setVisibility( View.VISIBLE );

                    } else {
                        System.err.println( "Reiniciando pantalla cargando datos" );
                        final ArrayList<String> list = new ArrayList<>();
                        for (int i = 0; i < notalist.size(); ++i) {
                            list.add( notalist.get( i ).getNota());
                        }
                        final VerUSActivity.StableArrayAdapter adapter = new VerUSActivity.StableArrayAdapter( VerUSActivity.this,
                                R.layout.list_item, list );

                        NotaCardView.setMinimumHeight( notalist.size() * 100 );
                        ListView notaListView = new ListView( VerUSActivity.this );
                        notaListView.setMinimumHeight( notalist.size() * 100 );
                        notaListView.setAdapter( adapter );
                        notaListView.setDivider( null );
                        notaListView.setPadding( 10, 60, 0, 0 );
                        NotaCardView.addView( notaListView);

                        notaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Notasus notasus = notalist.get(i);
                                startActivity( notasus );

                            }
                        });
                    }

                } else {
                    sinNotaText1.setText( "Ocurrio un error al recupear los datos" );
                    System.out.println( Log.d( "response", response.toString() ) );
                    Toast.makeText( VerUSActivity.this,
                            "Ocurrio un error al procesar la respuesta del Servidor",
                            Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<List<Notasus>> call, Throwable t) {
                System.out.println( Log.d( "response", call.request().toString() + "//////" + call.toString() ) );
                sinNotaText1.setText( "Ocurrio un error al recupear los datos" );
                Toast.makeText( VerUSActivity.this,
                        "Ocurrio un error al procesar la respuesta del Servidor",
                        Toast.LENGTH_SHORT ).show();
            }
        } );

    }

    private void startActivity(Notasus notasus) {

        Intent intent = new Intent(VerUSActivity.this, VerUSActivity.class);
        intent.putExtra("IDPROYECTO", notasus.getNotausPK().getIdproyecto().toString());
        intent.putExtra("IDSPRINT", notasus.getNotausPK().getIdsprint().toString());
        intent.putExtra("IDUS", notasus.getNotausPK().getIdus().toString());
        intent.putExtra("IDNOTA", notasus.getNotausPK().getIdnota().toString());
        intent.putExtra("NOTA", notasus.getNota());

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
