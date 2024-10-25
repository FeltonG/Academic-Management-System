package ar.edu.utn.frbb.tup.model;

import ar.edu.utn.frbb.tup.persistence.AsignaturaDaoMemoryImpl;

import java.util.List;

public class Carrera {

    private String nombre;
    private long id;

    public Carrera(String nombre) {
        this.id = incrementarId();
        this.nombre = nombre;
    }

    public Carrera(String nombre, long id) {
        this.nombre = nombre;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }

    private int incrementarId()
    {
        AsignaturaDaoMemoryImpl admi = new AsignaturaDaoMemoryImpl();
        int ultimoId = admi.obtenerUltimoId();
        ultimoId++;
        return ultimoId;

    }


}
