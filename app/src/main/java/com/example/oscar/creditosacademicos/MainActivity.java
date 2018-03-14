package com.example.oscar.creditosacademicos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.oscar.creditosacademicos.entidades.Actividad;
import com.example.oscar.creditosacademicos.entidades.Alumno;
import com.example.oscar.creditosacademicos.recycler.AdapterActividad;
import com.example.oscar.creditosacademicos.recycler.AdapterAlumno;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    ConexionSQLiteHelper con;

    ArrayList<Actividad> ListaActividad = new ArrayList<Actividad>();
    AdapterActividad adapterActividad;
    RecyclerView recycler;

    ArrayList<Alumno> ListaAlumnos = new ArrayList<Alumno>();
    AdapterAlumno adapterAlumno;

    private AlertDialog dialog;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navegacion_alumno:
                    listarDatosAlumno();
                    return true;
                case R.id.navegacion_actividad:
                    listarDatosActividad();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        con=new ConexionSQLiteHelper(MainActivity.this);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        recycler=(RecyclerView) findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);

        /*
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] letra = {"A","B","C","D","E"};



        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, letra));


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });
        */


        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (navigation.getSelectedItemId() == R.id.navegacion_alumno) {

                    LayoutInflater inflater = getLayoutInflater();
                    View mView1 = inflater.inflate(R.layout.activity_add_alumno, null);

                    final EditText txt_Nombre = (EditText) mView1.findViewById(R.id.txt_nombre);
                    final EditText txt_Numero_Ctrl = (EditText) mView1.findViewById(R.id.txt_numero_ctrl);
                    final EditText txt_Carrera = (EditText) mView1.findViewById(R.id.txt_carrera);
                    final EditText txt_Numero_Celular = (EditText) mView1.findViewById(R.id.txt_numero_celular);
                    final EditText txt_Correo = (EditText) mView1.findViewById(R.id.txt_correo);
                    final ImageButton btnGuardar = (ImageButton) mView1.findViewById(R.id.btnGuardar);
                    final ImageButton btnCerrar = (ImageButton) mView1.findViewById(R.id.btnCerrar);

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setView(mView1).setTitle("Añadir Nuevo Alumno")
                            .setNegativeButton("", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialog.dismiss();
                                }
                            });





                    btnGuardar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            String nombre = txt_Nombre.getText().toString();
                            String numero_cotrol = txt_Numero_Ctrl.getText().toString();
                            String carrera = txt_Carrera.getText().toString();
                            String celular = txt_Numero_Celular.getText().toString();
                            String correo = txt_Correo.getText().toString();

                            if (nombre.equals("") && numero_cotrol.equals("") && carrera.equals("")) {
                                Snackbar.make(view, "Campos Incompletos", Snackbar.LENGTH_SHORT).show();

                            } else {
                                registrarAlumno(nombre, numero_cotrol, carrera, celular, correo);
                                dialog.dismiss();
                                Snackbar.make(view, "Guardando", Snackbar.LENGTH_SHORT).show();
                                listarDatosAlumno();
                            }


                        }
                    });

                    btnCerrar.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    dialog = builder.create();
                    dialog.show();
                }else {

                    LayoutInflater inflater = getLayoutInflater();
                    View mView1 = inflater.inflate(R.layout.activity_add_actividad, null);

                    final EditText txt_Id = (EditText) mView1.findViewById(R.id.txt_idActividad);
                    final EditText txt_NombreAct = (EditText) mView1.findViewById(R.id.txt_nombreActividad);
                    final EditText txt_Creditos = (EditText) mView1.findViewById(R.id.txt_creditos);

                    final ImageButton btnGuardar = (ImageButton) mView1.findViewById(R.id.btnGuardar);
                    final ImageButton btnCerrar = (ImageButton) mView1.findViewById(R.id.btnCerrar);

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setView(mView1).setTitle("Añadir Nueva Actividad")
                            .setNegativeButton("", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialog.dismiss();
                                }
                            });

                    btnGuardar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            int id_act = Integer.parseInt(txt_Id.getText().toString());
                            String nombreAct = txt_NombreAct.getText().toString();
                            String creditos = txt_Creditos.getText().toString();

                            if (nombreAct.equals("") && creditos.equals("") && id_act==0 ) {
                                Snackbar.make(view, "Campos Incompletos", Snackbar.LENGTH_SHORT).show();

                            } else {
                                registrarActividad(id_act,nombreAct, creditos);
                                dialog.dismiss();
                                Snackbar.make(view, "Guardando", Snackbar.LENGTH_SHORT).show();
                                listarDatosActividad();
                            }


                        }
                    });

                    btnCerrar.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    dialog = builder.create();
                    dialog.show();
                }
            }

        });





    }


    @Override
    protected void onStart() {
        super.onStart();
        con.openDB();
        listarDatosAlumno();
    }

    @Override
    protected void onStop() {
        super.onStop();
        con.closeDB();
    }


    private void listarDatosAlumno() {
        recycler = (RecyclerView) findViewById(R.id.recycler);
        adapterAlumno= new AdapterAlumno(this,ListaAlumnos);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapterAlumno);

        ListaAlumnos.clear();

        ConexionSQLiteHelper functions = new ConexionSQLiteHelper(MainActivity.this);

        ArrayList<Alumno> data = functions.consultaAlumno();

        if (data.size()>0){
            for (int i = 0; i<data.size(); i++){
                String nombreAlumno = data.get(i).getNombre();
                String noControl= data.get(i).getNoctrl();
                String email= data.get(i).getEmail();
                String cel=data.get(i).getCel();
                String carrera=data.get(i).getCarrera() ;

                Alumno item = new Alumno();

                item.setNombre(nombreAlumno);
                item.setNoctrl(noControl);
                item.setEmail(email);
                item.setCel(cel);
                item.setCarrera(carrera);
                ListaAlumnos.add(item);

            }adapterAlumno.notifyDataSetChanged();

        }else {
            Toast.makeText(MainActivity.this, "No se encontraron registros", Toast.LENGTH_SHORT).show();
        }
    }

    public void listarDatosActividad() {
        recycler = (RecyclerView) findViewById(R.id.recycler);
        adapterActividad = new AdapterActividad(this,ListaActividad);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapterActividad);

        ListaActividad.clear();

        ConexionSQLiteHelper functions = new ConexionSQLiteHelper(MainActivity.this);

        ArrayList<Actividad> data = functions.consultaActividad();

        if (data.size()>0){
            for (int i = 0; i<data.size(); i++){
                int id=data.get(i).getId();
                String nombreActividad = data.get(i).getActividad();
                String creditos = data.get(i).getCreditos();

                Actividad item = new Actividad();

                item.setId(id);
                item.setActividad(nombreActividad);
                item.setCreditos(creditos);
                ListaActividad.add(item);

            }adapterActividad.notifyDataSetChanged();

        }else {
            Toast.makeText(MainActivity.this, "No se encontraron registros", Toast.LENGTH_SHORT).show();
        }
    }


    public void registrarActividad(int id_Act,String nombreAct, String creditos) {
        ConexionSQLiteHelper functions = new ConexionSQLiteHelper(MainActivity.this);
        Actividad item = new Actividad();

        item.setId(id_Act);
        item.setActividad(nombreAct);
        item.setCreditos(creditos);
        functions.insertActividad(item);
        Toast.makeText(MainActivity.this, "Saved...", Toast.LENGTH_SHORT).show();
    }

    public void registrarAlumno(String nombre, String numControl, String carrera, String numeroTelefono,String correo) {
        ConexionSQLiteHelper functions = new ConexionSQLiteHelper(MainActivity.this);
        Alumno item = new Alumno();

        item.setId(Integer.parseInt(numControl));
        item.setNombre(nombre);
        item.setNoctrl(numControl);
        item.setEmail(correo);
        item.setCel(numeroTelefono);
        item.setCarrera(carrera);
        functions.insertAlumno(item);
        Toast.makeText(MainActivity.this, "Saved...", Toast.LENGTH_SHORT).show();
    }





}
