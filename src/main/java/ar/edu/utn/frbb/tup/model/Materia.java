package ar.edu.utn.frbb.tup.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Materia {

    private static long contador=0;

    private long id;
    private String nombre;
    private int anio;
    private int cuatrimestre;
    private long idprofesor;
    private List<Long> correlatividades;


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(int cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }


    public Materia(){}


    public Materia(String nombre, int anio, int cuatrimestre, long idprofesor) {

        incrementarId();
        this.id=contador;
        this.anio = anio;
        this.cuatrimestre = cuatrimestre;
        this.nombre = nombre;
        this.idprofesor = idprofesor;
        correlatividades = new ArrayList<>();
    }


    public String getNombre() {
        return nombre;
    }
    public void setMateriaId(int materiaId) {
        this.id = materiaId;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, anio, cuatrimestre, idprofesor, correlatividades);

    }

    private void incrementarId()
    {
        this.contador++;

    }
}
