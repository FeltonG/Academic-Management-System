package ar.edu.utn.frbb.tup.model;

import ar.edu.utn.frbb.tup.persistence.MateriaDaoMemoryImpl;
import ar.edu.utn.frbb.tup.persistence.ProfesorDaoMemoryImpl;

import java.util.List;
import java.util.Random;

public class Profesor {



    private long id;
    private String nombre;
    private String apellido;
    private String titulo;


    public Profesor(String nombre, String apellido, String titulo) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.titulo = titulo;
        this.id=incrementarId();
    }

    public Profesor(long id, String nombre, String apellido, String titulo) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.titulo = titulo;
        this.id=id;
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
    public long getId(){
        return id;
    }

    private int incrementarId()
    {
        ProfesorDaoMemoryImpl admi = new ProfesorDaoMemoryImpl();
        int ultimoId = admi.obtenerUltimoId();
        ultimoId++;
        return ultimoId;

    }

}
