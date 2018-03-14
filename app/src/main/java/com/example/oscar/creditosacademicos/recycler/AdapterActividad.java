package com.example.oscar.creditosacademicos.recycler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oscar.creditosacademicos.ConexionSQLiteHelper;
import com.example.oscar.creditosacademicos.MainActivity;
import com.example.oscar.creditosacademicos.R;
import com.example.oscar.creditosacademicos.entidades.Actividad;

import java.util.ArrayList;

/**
 * Created by oscar on 8/03/18.
 */

public class AdapterActividad extends RecyclerView.Adapter<AdapterActividad.ListaViewHolder> {

    Activity context;
    ArrayList<Actividad> listadatos;
    private AlertDialog dialog;

    public AdapterActividad(Activity context, ArrayList<Actividad> listadatos) {
        this.context = context;
        this.listadatos = listadatos;
    }

    @Override
    public ListaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.actividad_layout,null);
        return new ListaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListaViewHolder holder, int position) {
        final Actividad item=listadatos.get(position);

        holder.txtActividad.setText(item.getActividad());
        holder.txtCreditos.setText(item.getCreditos());

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id = item.getId();

                LayoutInflater layoutInflater = context.getLayoutInflater();
                View view1 = layoutInflater.inflate(R.layout.ediar_actividad,null);

                final EditText nombreActividad = (EditText) view1.findViewById(R.id.txt_nombreActividad);
                final EditText creditos = (EditText) view1.findViewById(R.id.txt_creditos);
                final ImageButton btnGuardar = (ImageButton) view1.findViewById(R.id.btnGuardar);
                final ImageButton btnCerrar = (ImageButton) view1.findViewById(R.id.btnCerrar);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(view1).setTitle("Modificar Actividades")
                        .setNegativeButton("", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog.dismiss();
                            }
                        });

                final ConexionSQLiteHelper functions = new ConexionSQLiteHelper(context);
                final Actividad _items = functions.getSingleItemActividad(id);

                nombreActividad.setText(_items.getActividad());
                creditos.setText(_items.getCreditos());

                btnGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nombre = nombreActividad.getText().toString();
                        String creaditos = creditos.getText().toString();

                        _items.setActividad(nombre);
                        _items.setCreditos(creaditos);

                        functions.UpdateActividad(_items);

                        Toast.makeText(context, nombre + " actualizado.", Toast.LENGTH_SHORT).show();
                        ((MainActivity)context).listarDatosActividad();
                        dialog.dismiss();
                    }
                });


                btnCerrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listadatos.size();
    }

    public class ListaViewHolder extends RecyclerView.ViewHolder {
        TextView txtActividad,txtCreditos;
        ImageButton btnEditar;

        public ListaViewHolder(View itemView) {
            super(itemView);

            txtActividad=itemView.findViewById(R.id.txtActividad);
            txtCreditos=itemView.findViewById(R.id.txtCreditos);
            btnEditar=itemView.findViewById(R.id.BtnEditarActividad);
        }
    }

}
