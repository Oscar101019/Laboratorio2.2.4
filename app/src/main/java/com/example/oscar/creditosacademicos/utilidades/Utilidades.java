package com.example.oscar.creditosacademicos.utilidades;

/**
 * Created by oscar on 7/03/18.
 */

public class Utilidades {

    public static final String DBNAME="academicos";

    public static String ID_AL="_id";
    public static String TABLA_ALUMNO="alumnos";
    public static String NOMBRE_AL="nombreal";
    public static String NOCTRL_AL="noctrl";
    public static String EMAIL_AL="email";
    public static String CEL_AL="cel";
    public static String CARRERA_AL="carrera";

    public static String ID_AC="_id";
    public static String TABLA_ACTIVIDAD="actividad";
    public static String NOMBRE_ACT="nombreact";
    public static String CREDITOS_ACT="creditos";

    public static String ACAL="acal";
    public static String ACAL_ID_AL="_idAl";
    public static String ACAL_ID_AC="_idAc";
    public static String ACAL_ACTIVIDAD="nombre";
    public static String ACAL_FECHA_INICIO="fechai";
    public static String ACAL_FECHA_FIN="fechaf";
    public static String ACAL_CREDITOS="creditos";



    public static String CREAR_ALUMNO="CREATE TABLE "+TABLA_ALUMNO+"("+
            ID_AL +" INTEGER PRIMARY KEY, "+
            NOMBRE_AL+" TEXT, " +
            NOCTRL_AL+" TEXT , "+
            EMAIL_AL+" TEXT , "+
            CEL_AL+" TEXT , "+
            CARRERA_AL+" TEXT)";

    public static String CREAR_ACTIVIDAD="CREATE TABLE "+TABLA_ACTIVIDAD+"("+
            ID_AC +" INTEGER PRIMARY KEY , "+
            NOMBRE_ACT+" TEXT, "+
            CREDITOS_ACT+" TEXT)";

    public static String CREAR_ACAL="CREATE TABLE "+ACAL+"("+
            ACAL_ID_AL +" INTEGER , "+
            ACAL_ID_AC +" INTEGER , "+
            ACAL_ACTIVIDAD+" TEXT   , "+
            ACAL_FECHA_INICIO+" TEXT , "+
            ACAL_FECHA_FIN+" TEXT , "+
            ACAL_CREDITOS+" INTEGER )";



}
