package com.example.oscar.creditosacademicos.recycler;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oscar.creditosacademicos.R;
import com.example.oscar.creditosacademicos.entidades.Item;

import java.util.ArrayList;

/**
 * Created by lenovo on 07/03/2018.
 * Define RecyclerView.Adapter y RecyclerView.ViewHolder
 * Establece listener para los componentes
 */

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.MyViewHolder> {

    private Activity activity;
    private ArrayList<Item> list;
    private AlertDialog dialog;

    public AdapterItem(Activity activity, ArrayList<Item> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Referencia para almacenar el contexto
        Context context;
        context = parent.getContext();

        // Referencia para el inflater
        LayoutInflater nuevo;
        nuevo = LayoutInflater.from(context);

        View itemView = nuevo.inflate(R.layout.detalles_atividad,parent,false);


        return new MyViewHolder(itemView);
    }

    /*
    Vincula los datos con los datos con los elementos holder
     Establece listeners para los "botones" (que no son botones)

     */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final Item item = list.get(position);

        holder.nombreAct.setText(""+item.getActividad());
        holder.fechaIni.setText(""+item.getFechaIni());
        holder.fechaFin.setText(""+item.getFechaFin());
        holder.creditos.setText(""+item.getCreditos());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView fechaIni,fechaFin,nombreAct,creditos;


        public MyViewHolder(View itemView) {
            super(itemView);

            nombreAct = itemView.findViewById(R.id.txtActividad);
            fechaIni=itemView.findViewById(R.id.txtFechaIni);
            fechaFin=itemView.findViewById(R.id.txtFechaFin);
            creditos=itemView.findViewById(R.id.txtCreditos);


        }
    }
}
