package com.example.ro_fa.myapplication.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ro_fa.myapplication.R;
import com.example.ro_fa.myapplication.Remote.DatosLogin;
import com.example.ro_fa.myapplication.Remote.RetrofitClient;
import com.example.ro_fa.myapplication.modelos.Proyectos;
import com.example.ro_fa.myapplication.modelos.Usuarios;
import com.example.ro_fa.myapplication.servicios.ProyectosService;
import com.example.ro_fa.myapplication.servicios.UsuariosService;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ro_fa.myapplication.Remote.StringUtils.slurp;

/**
 * Created by ro_fa on 29/6/2018.
 */

public class CrearProyectoActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener, Callback<ResponseBody> {

    private Button cearProyectoButton;
    private ProyectosService proyectosService;
    private UsuariosService usuarioService;
    private final Activity mContext = this;
    final DatosLogin datosLogin = new DatosLogin(this);


    private EditText nombreTxt;
    private EditText codproyectoTxt;
    private EditText descripcionTxt;
    private EditText anhoTxt;
    private EditText fechafinTxt;
    private EditText fechainicioTxt;

    private Date fechainicio;
    private Date fechafin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_crear_proyecto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        nombreTxt = (EditText) findViewById(R.id.nombre);
        codproyectoTxt = (EditText) findViewById( R.id.codproyecto );
        descripcionTxt = (EditText) findViewById(R.id.descripcion);
        anhoTxt = (EditText) findViewById( R.id.anho );
        fechafinTxt = (EditText) findViewById( R.id.fechafin );
        fechainicioTxt = (EditText) findViewById( R.id.fechainicio );


        proyectosService = (ProyectosService) RetrofitClient.create(ProyectosService.class, "proyectos/");

        cearProyectoButton = (Button) findViewById(R.id.button_guardar);
        cearProyectoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearProyecto();
            }
        });

        Button cancelarCuentaButton = (Button) findViewById(R.id.button_cancelar);
        cancelarCuentaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.recargar = false;
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        MainActivity.recargar = false;
        super.onBackPressed();
    }

    public void datePicker(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        fechafin = cal.getTime();
        ((TextView) findViewById( R.id.fechafin )).setText( dateFormat.format( cal.getTime() ) );
        Calendar cal2 = new GregorianCalendar(year, month, day);
        fechainicio = cal2.getTime();
        ((TextView) findViewById( R.id.fechainicio )).setText( dateFormat.format( cal.getTime() ) );
    }

    private void crearProyecto() {

        boolean cancel = false;
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
            cancel = true;
        } else if (codproyecto == null) {
            codproyectoTxt.setError(getString(R.string.error_campo_requerido));
            focusView = codproyectoTxt;
            cancel = true;
        } else if (TextUtils.isEmpty(descripcion)) {
            descripcionTxt.setError(getString(R.string.error_campo_requerido));
            focusView = descripcionTxt;
            cancel = true;
        } else if (anho == null) {
            anhoTxt.setError(getString(R.string.error_campo_requerido));
            focusView = anhoTxt;
            cancel = true;
        } else if (TextUtils.isEmpty(inicio)) {
            fechainicioTxt.setError(getString(R.string.error_campo_requerido));
            focusView = fechainicioTxt;
            cancel = true;
        } else if (TextUtils.isEmpty(fin)) {
            fechafinTxt.setError(getString(R.string.error_campo_requerido));
            focusView = fechafinTxt;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            cearProyectoButton.setEnabled(true);
            cearProyectoButton.setText("Guardar");
        } else {
            Proyectos proyecto = new Proyectos( fechainicio, anho, descripcion, fechafin, codproyecto, nombre);
            System.out.println( Log.d("objeto", proyecto.toString()));
            Call<ResponseBody> call = proyectosService.crear(proyecto);
            call.enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.isSuccessful()) {
            AdministrarProyectosActivity.recargar = true;
            showMessageSuccess("Exitoso", "Proyecto creado exitosamente");
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

    public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(), year, month, day);
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
