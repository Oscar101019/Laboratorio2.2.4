package com.example.oscar.creditosacademicos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;


import com.example.oscar.creditosacademicos.entidades.Actividad;
import com.example.oscar.creditosacademicos.entidades.Alumno;
import com.example.oscar.creditosacademicos.entidades.Item;
import com.example.oscar.creditosacademicos.entidades.Spinner;
import com.example.oscar.creditosacademicos.utilidades.Utilidades;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by oscar on 7/03/18.
 */

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public SQLiteDatabase myDB;

    public ConexionSQLiteHelper(Context context) {
        super(context, Utilidades.DBNAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Utilidades.CREAR_ACTIVIDAD);
        sqLiteDatabase.execSQL(Utilidades.CREAR_ALUMNO);
        sqLiteDatabase.execSQL(Utilidades.CREAR_ACAL);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_ACTIVIDAD);
    }

    public void openDB(){
        myDB=getWritableDatabase();

    }

    public void closeDB() {
        if (myDB != null && myDB.isOpen()) {
            myDB.close();

        }
    }


    public void insertAlumno(Alumno item){

        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Utilidades.ID_AL,item.getId());
        contentValues.put(Utilidades.NOMBRE_AL,item.getNombre());
        contentValues.put(Utilidades.NOCTRL_AL,item.getNoctrl());
        contentValues.put(Utilidades.EMAIL_AL,item.getEmail());
        contentValues.put(Utilidades.CEL_AL,item.getCel());
        contentValues.put(Utilidades.CARRERA_AL,item.getCarrera());

        database.insert(Utilidades.TABLA_ALUMNO,null,contentValues);
    }

    public void insertActividad(Actividad item){

        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Utilidades.ID_AC,item.getId());
        contentValues.put(Utilidades.NOMBRE_ACT,item.getActividad());
        contentValues.put(Utilidades.CREDITOS_ACT,item.getCreditos());

        database.insert(Utilidades.TABLA_ACTIVIDAD,null,contentValues);
    }





    public ArrayList<Alumno> consultaAlumno() {
        ArrayList<Alumno> lista = new ArrayList<Alumno>();
        SQLiteDatabase database = getReadableDatabase();

        String sql = "SELECT * FROM " + Utilidades.TABLA_ALUMNO+ " ORDER BY "+Utilidades.NOCTRL_AL;

        Cursor cursor = database.rawQuery(sql,null);

        if (cursor.moveToFirst()){
            do {
                Alumno item = new Alumno();
                item.setId(cursor.getInt(0));
                item.setNombre(cursor.getString(1));
                item.setNoctrl(cursor.getString(2));
                item.setEmail(cursor.getString(3));
                item.setCel(cursor.getString(4));
                item.setCarrera(cursor.getString(5));

                lista.add(item);

            }while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return lista;
    }

    public ArrayList<Actividad> consultaActividad() {
        ArrayList<Actividad> lista = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        String sql = "SELECT * FROM " + Utilidades.TABLA_ACTIVIDAD+ " ORDER BY "+Utilidades.NOMBRE_ACT;

        Cursor cursor = database.rawQuery(sql,null);

        if (cursor.moveToFirst()){
            do {
                Actividad item = new Actividad();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setActividad(cursor.getString(1));
                item.setCreditos(cursor.getString(2));
                lista.add(item);
            }while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return lista;
    }



    public Alumno getSingleItemAlumno(int id){

        SQLiteDatabase database = getReadableDatabase();

        String sql = "SELECT * FROM " + Utilidades.TABLA_ALUMNO+ " WHERE " + Utilidades.ID_AL +"=?";
        Cursor cursor = database.rawQuery(sql,new String[]{String.valueOf(id)});

        if (cursor != null)
            cursor.moveToNext();

        Alumno item = new Alumno();
        assert cursor != null;
        item.setId(cursor.getInt(0));
        item.setNombre(cursor.getString(1));
        item.setNoctrl(cursor.getString(2));
        item.setEmail(cursor.getString(3));
        item.setCel(cursor.getString(4));
        item.setCarrera(cursor.getString(5));

        cursor.close();
        database.close();
        return item;
    }

    public ArrayList<Item> getAlumnoEspecifico(int id){
        ArrayList<Item> lista= new ArrayList<Item>();
        SQLiteDatabase database = getReadableDatabase();

        String sql = "SELECT * FROM " + Utilidades.ACAL+ " WHERE " + Utilidades.ACAL_ID_AL +"=? ORDER BY "+Utilidades.ACAL_FECHA_INICIO;
        Cursor cursor = database.rawQuery(sql,new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()){
            do {
                Item item=new Item();
                item.setIdAl(cursor.getInt(0));
                item.setIdAct(cursor.getInt(1));
                item.setActividad(cursor.getString(2));
                item.setFechaIni(cursor.getString(3));
                item.setFechaFin(cursor.getString(4));
                item.setCreditos(cursor.getInt(5));

                lista.add(item);
            }while (cursor.moveToNext());
        }



        cursor.close();
        database.close();
        return lista;
    }

    public Actividad getSingleItemActividad(int id){

        SQLiteDatabase database = getReadableDatabase();

        String sql = "SELECT * FROM " + Utilidades.TABLA_ACTIVIDAD+ " WHERE " + Utilidades.ID_AC +"=?";
        Cursor cursor = database.rawQuery(sql,new String[]{String.valueOf(id)});

        if (cursor != null)
            cursor.moveToNext();

        Actividad item = new Actividad();
        item.setId(Integer.parseInt(cursor.getString(0)));
        item.setActividad(cursor.getString(1));
        item.setCreditos(cursor.getString(2));

        cursor.close();
        database.close();
        return item;
    }



    public void UpdateActividad(Actividad item){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Utilidades.NOMBRE_ACT,item.getActividad());
        contentValues.put(Utilidades.CREDITOS_ACT,item.getCreditos());
        database.update(Utilidades.TABLA_ACTIVIDAD,contentValues, Utilidades.ID_AC + "=?",new String[]{String.valueOf(item.getId())});

    }

    public void UpdateAlumno(Alumno item){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Utilidades.NOMBRE_AL,item.getNombre());
        contentValues.put(Utilidades.NOCTRL_AL,item.getNoctrl());
        contentValues.put(Utilidades.CARRERA_AL,item.getCarrera());
        contentValues.put(Utilidades.CEL_AL,item.getCel());
        contentValues.put(Utilidades.EMAIL_AL,item.getEmail());
        database.update(Utilidades.TABLA_ALUMNO,contentValues, Utilidades.ID_AL + "=?",new String[]{String.valueOf(item.getId())});

    }

    public ArrayAdapter<Spinner> llenarSpinner(Context cont){

        ArrayList<Actividad> list = new ArrayList<>();
        ArrayList<Spinner> list2 = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();

        String sql = "SELECT * FROM " + Utilidades.TABLA_ACTIVIDAD +" ORDER BY "+Utilidades.ID_AC+" ASC";

        Cursor cursor = database.rawQuery(sql,null);

        if (cursor.moveToFirst()){
            do {
                Actividad item = new Actividad();
                Spinner item2 =new Spinner();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setActividad(cursor.getString(1));
                list.add(item);

                item2.setId(Integer.parseInt(cursor.getString(0)));
                item2.setNombre(cursor.getString(1));
                list2.add(item2);

            }while (cursor.moveToNext());
        }





        ArrayAdapter<Spinner> adapter =
                new ArrayAdapter<Spinner>(cont, R.layout.spinner_layaout_ejemplo,list2);
        adapter.setDropDownViewResource(R.layout.spinner_layaout_ejemplo);

        cursor.close();
        database.close();

        return adapter;
    }

    public void InsertTabla(Item item){

        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(Utilidades.ACAL_ID_AL,item.getIdAl());
        contentValues.put(Utilidades.ACAL_ID_AC,item.getIdAct());
        contentValues.put(Utilidades.ACAL_CREDITOS,item.getCreditos());
        contentValues.put(Utilidades.ACAL_ACTIVIDAD,item.getActividad());
        contentValues.put(Utilidades.ACAL_FECHA_FIN,item.getFechaFin());
        contentValues.put(Utilidades.ACAL_FECHA_INICIO,item.getFechaIni());

        database.insert(Utilidades.ACAL,null,contentValues);
    }

}
