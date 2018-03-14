package com.example.oscar.creditosacademicos.recycler;
import android.app.Activity;
import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.oscar.creditosacademicos.Add_Actividades;
import com.example.oscar.creditosacademicos.MainActivity;
import com.example.oscar.creditosacademicos.R;
import com.example.oscar.creditosacademicos.View_Details;
import com.example.oscar.creditosacademicos.entidades.Alumno;
import com.example.oscar.creditosacademicos.ConexionSQLiteHelper;

import java.util.ArrayList;

/**
 * Created by oscar on 8/03/18.
 */

public class AdapterAlumno extends RecyclerView.Adapter<AdapterAlumno.ListaViewHolder> {

    Activity context;
    ArrayList<Alumno> listadatos;

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
        //holder.Creditos.setText(con.lisarCreditos("14400925"));
        holder.NoCtrl.setText(item.getNoctrl());
        //holder.Email.setText(item.getEmail());
        //holder.Cel.setText(item.getCel());
        //holder.Carrera.setText(item.getCarrera());

        holder.btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Add_Actividades.class);
                context.startActivity(intent);



            }
        });



        holder.btnView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(view.getContext(), View_Details.class);
                context.startActivity(intent2);





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
            Email=itemView.findViewById(R.id.txtEmail);
            Cel=itemView.findViewById(R.id.txtCel);
            Carrera=itemView.findViewById(R.id.txtCarrera);
            Creditos=itemView.findViewById(R.id.txtCreditos);
            btnAñadir=itemView.findViewById(R.id.imageButtonAñadir);
            btnView=itemView.findViewById(R.id.imageButtonVer);
        }
    }
}
