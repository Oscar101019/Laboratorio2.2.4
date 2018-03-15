package com.example.oscar.creditosacademicos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oscar.creditosacademicos.entidades.Alumno;
import com.example.oscar.creditosacademicos.recycler.AdapterAlumno;

import java.util.ArrayList;

public class View_Details extends AppCompatActivity {

    RecyclerView recycler;
    ArrayList<Alumno> ListaAlumnos = new ArrayList<Alumno>();
    AdapterAlumno adapterAlumno;
    TextView txtFechaInicio,txtFechaFin;
    ImageButton ImgButtonEditar,ImgButtonCan;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__details);

        final TextView nombre= (TextView)findViewById(R.id.txtNombre);
        final TextView numControl= (TextView)findViewById(R.id.txtNumControl);
        final TextView cel= (TextView)findViewById(R.id.txtCel);
        final TextView correo= (TextView)findViewById(R.id.txtCorreo);
        final TextView carrera= (TextView)findViewById(R.id.txtCarrera);
        ImgButtonEditar = (ImageButton)findViewById(R.id.btnEditar);
        ImgButtonCan = (ImageButton)findViewById(R.id.btnCerrar);

        final Bundle datos = this.getIntent().getExtras();
        //listarDatosAlumno(datos.getInt("id"));
        nombre.setText(""+datos.getString("nombre"));
        numControl.setText(""+datos.getString("NumControl"));
        cel.setText(""+datos.getString("telefono"));
        carrera.setText(""+datos.getString("carrera"));
        correo.setText(""+datos.getString("correo"));
        //ac.setText("Total Creditos: "+creditos(datos.getInt("nc")));


        ImgButtonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id = datos.getInt("id");


                LayoutInflater layoutInflater = View_Details.this.getLayoutInflater();
                View view1 = layoutInflater.inflate(R.layout.editar_alumnos,null);

                final EditText nombreAlumno = (EditText) view1.findViewById(R.id.txt_nombre);
                final EditText  numeroControl = (EditText) view1.findViewById(R.id.txt_numero_ctrl);
                final EditText telefono = (EditText) view1.findViewById(R.id.txt_numero_celular);
                final EditText carreraE = (EditText) view1.findViewById(R.id.txt_carrera);
                final EditText correoE = (EditText) view1.findViewById(R.id.txt_correo);
                final ImageButton btnGuardar = (ImageButton) view1.findViewById(R.id.btnGuardar);
                final ImageButton btnCerrar = (ImageButton) view1.findViewById(R.id.btnCerrar);

                AlertDialog.Builder builder = new AlertDialog.Builder(View_Details.this);
                builder.setView(view1).setTitle("Editar Alumno").setNegativeButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });

                final ConexionSQLiteHelper functions = new ConexionSQLiteHelper(View_Details.this);
                final Alumno _items = functions.getSingleItemAlumno(id);

                nombreAlumno.setText(_items.getNombre());
                numeroControl.setText(_items.getNoctrl());
                telefono.setText(_items.getCel());
                carreraE.setText(_items.getCarrera());
                correoE.setText(_items.getEmail());

                btnGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String nombres = nombreAlumno.getText().toString();
                        String Control = numeroControl.getText().toString();
                        String celular = telefono.getText().toString();
                        String carr = carreraE.getText().toString();
                        String corr = correoE.getText().toString();

                        _items.setNombre(nombres);
                        _items.setNoctrl(Control);
                        _items.setCel(celular);
                        _items.setCarrera(carr);
                        _items.setEmail(corr);

                        functions.UpdateAlumno(_items);

                        Toast.makeText(View_Details.this, nombres + " actualizado", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                        nombre.setText(nombres);
                        numControl.setText(Control);
                        cel.setText(celular);
                        carrera.setText(carr);
                        correo.setText(corr);

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


        ImgButtonCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (View_Details.this, MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    private void listarDatosAlumno( int idAl) {
        recycler = (RecyclerView) findViewById(R.id.recycler);
        adapterAlumno= new AdapterAlumno(this,ListaAlumnos);

        LinearLayoutManager layoutManager = new LinearLayoutManager(View_Details.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapterAlumno);

        ListaAlumnos.clear();

        ConexionSQLiteHelper functions = new ConexionSQLiteHelper(View_Details.this);

        ArrayList<Alumno> data = functions.getAlumnoEspecifico(idAl);

        int cre=0;
        if (data.size()>0){
            for (int i = 0; i<data.size(); i++){

                int id=data.get(i).getId();
                String nombreAlumno = data.get(i).getNombre();
                String noControl= data.get(i).getNoctrl();
                String email= data.get(i).getEmail();
                String cel=data.get(i).getCel();
                String carrera=data.get(i).getCarrera();

                Alumno item = new Alumno();

                item.setId(id);
                item.setNombre(nombreAlumno);
                item.setNoctrl(noControl);
                item.setEmail(email);
                item.setCel(cel);
                item.setCarrera(carrera);
                ListaAlumnos.add(item);

            }adapterAlumno.notifyDataSetChanged();

        }else {
            Toast.makeText(View_Details.this, "No se encontraron registros", Toast.LENGTH_SHORT).show();
        }
    }

}
