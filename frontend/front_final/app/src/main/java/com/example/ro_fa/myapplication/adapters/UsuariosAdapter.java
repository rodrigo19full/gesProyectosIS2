package com.example.ro_fa.myapplication.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.ro_fa.myapplication.R;
import com.example.ro_fa.myapplication.activities.NavActivity;
import com.example.ro_fa.myapplication.modelos.Usuarios;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by ro_fa on 23/6/2018.
 */

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.ViewHolder> {

    private List<Usuarios> mDataset;
    private Activity mContext;
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView mTextView1;
        public CheckBox cbSelect;

        public ViewHolder(View v) {
            super(v);
            view = v;
            mTextView1 = (TextView) view.findViewById( R.id.nombre);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public UsuariosAdapter(List<Usuarios> myDataset, Activity mContext) {
        this.mDataset = myDataset;
        this.mContext = mContext;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public UsuariosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_usuarios, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Usuarios usuario = mDataset.get(position);
        String nombre_completo = usuario.getNombre() + " " + usuario.getApellido();
        holder.mTextView1.setText(nombre_completo);

        holder.mTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NavActivity.class);
                /*System.err.println("Id hito : " + usuario.getIdusuario());
                intent.putExtra("EXTRA_ID_PROYECTO", proyecto.getIdProyecto().toString());
                intent.putExtra("EXTRA_NOMBRE", proyecto.getNombre());
                intent.putExtra("EXTRA_ESTADO", proyecto.getEstado());
                intent.putExtra("EXTRA_FECHA_CREACION", sdf.format(proyecto.getFechaCreacion()));
                intent.putExtra("EXTRA_DESCRIPCION", proyecto.getDescripcion());
                if (proyecto.getFechaFinalizacion() != null) {
                    intent.putExtra("EXTRA_FECHA_FIN", sdf.format(proyecto.getFechaFinalizacion()));
                }
                if (proyecto.getCategoria() != null) {
                    intent.putExtra("EXTRA_CATEGORIA", proyecto.getCategoria().getNombre());
                }
                if (proyecto.getPropietario() != null) {
                    intent.putExtra("EXTRA_ID_PROPIETARIO", proyecto.getPropietario().getIdUsuario().toString());
                    String propietario = proyecto.getPropietario().getNombre()
                            + " " + proyecto.getPropietario().getApellido();
                    intent.putExtra("EXTRA_NOMBRE_PROPIETARIO", propietario);
                }*/
                mContext.startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
