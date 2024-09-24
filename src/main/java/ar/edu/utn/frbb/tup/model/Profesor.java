package ar.edu.utn.frbb.tup.model;

import java.util.List;
import java.util.Random;

public class Profesor {

    private static long contador=0;

    private long id;
    private String nombre;
    private String apellido;
    private String titulo;


    public Profesor(String nombre, String apellido, String titulom) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.titulo = titulo;
        this.id=contador;
    }

    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getTitulo() {
        return titulo;
    }

    private void incrementarId()
    {
        this.contador++;

    }

}
