package com.example.oscar.creditosacademicos.recycler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oscar.creditosacademicos.R;
import com.example.oscar.creditosacademicos.View_Details;
import com.example.oscar.creditosacademicos.entidades.Alumno;
import com.example.oscar.creditosacademicos.ConexionSQLiteHelper;
import com.example.oscar.creditosacademicos.entidades.Item;

import java.util.ArrayList;

/**
 * Created by oscar on 8/03/18.
 */

public class AdapterAlumno extends RecyclerView.Adapter<AdapterAlumno.ListaViewHolder> {

    Activity context;
    ArrayList<Alumno> listadatos;
    private AlertDialog dialog;

    public AdapterAlumno(Activity context, ArrayList<Alumno> listadatos) {
        this.context = context;
        this.listadatos = listadatos;

    }



    @Override
    public ListaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.alumno_layout,null);
        return new ListaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListaViewHolder holder, int position) {
        final Alumno item=listadatos.get(position);

        holder.txtNombre.setText(item.getNombre());
        holder.NoCtrl.setText(item.getNoctrl());
        holder.Carrera.setText(item.getCarrera());

        holder.btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //final int id = item.getId();
                final int idAlumno = item.getId();


                LayoutInflater layoutInflater = context.getLayoutInflater();
                View view1 = layoutInflater.inflate(R.layout.activity_add__actividades,null);

                final Spinner spinnerActividades = (Spinner) view1.findViewById(R.id.spinner);
                final EditText fechaIni = (EditText) view1.findViewById(R.id.txt_fechaIni);
                final EditText fechaFin = (EditText) view1.findViewById(R.id.txt_fechaFin);
                 final ImageButton btnSave = (ImageButton) view1.findViewById(R.id.btnGuardar);
                final ImageButton btnCerrar = (ImageButton) view1.findViewById(R.id.btnCerrar);



                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(view1).setTitle("Edit Records").setNegativeButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });

                final ConexionSQLiteHelper functions = new ConexionSQLiteHelper(context);
                final Item item = new Item();
                spinnerActividades.setAdapter(functions.llenarSpinner(context));

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String fechaIni1 = fechaIni.getText().toString();
                        String fechaFin1 = fechaFin.getText().toString();
                        String spiner = spinnerActividades.getSelectedItem().toString();
                        int cred=spinnerActividades.getSelectedItemPosition()+1;

                        item.setIdAl(idAlumno);
                        item.setIdAct(cred);

                        item.setActividad(spiner);
                        item.setCreditos(cred);
                        item.setFechaIni(fechaIni1);
                        item.setFechaFin(fechaFin1);

                        functions.InsertTabla(item);

                        Toast.makeText(context, spiner + " Agregada", Toast.LENGTH_SHORT).show();
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



        holder.btnView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                final int id = item.getId();

                final ConexionSQLiteHelper functions = new ConexionSQLiteHelper(context);
                final Alumno items = functions.getSingleItemAlumno(id);


                Intent ListSong = new Intent(context.getApplicationContext(), View_Details.class);
                ListSong.putExtra("id", items.getId());
                ListSong.putExtra("nombre", items.getNombre());
                ListSong.putExtra("NumControl", items.getNoctrl());
                ListSong.putExtra("telefono", items.getCel());
                ListSong.putExtra("carrera", items.getCarrera());
                ListSong.putExtra("correo", items.getEmail());

                context.startActivity(ListSong);
            }
        });




    }

    @Override
    public int getItemCount() {
        return listadatos.size();
    }


    public class ListaViewHolder extends RecyclerView.ViewHolder{
        TextView txtNombre,NoCtrl,Email,Cel,Carrera,Creditos;
        ImageButton btnAñadir,btnView;

        public ListaViewHolder(View itemView){
            super(itemView);

            txtNombre=itemView.findViewById(R.id.txtNombre);
            NoCtrl=itemView.findViewById(R.id.txtNoCtrl);
            Carrera=itemView.findViewById(R.id.txtCarrera);
            btnAñadir=itemView.findViewById(R.id.imageButtonAñadir);
            btnView=itemView.findViewById(R.id.imageButtonVer);
        }
    }
}
