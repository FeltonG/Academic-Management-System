package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;

import java.util.List;

public interface ProfesorDao {
    public void guardarProfesor(Profesor profesor);

    public List<Profesor> buscarProfesor();

    public Profesor buscarProfesorporid(long id);

    public Profesor buscarProfesorDni(int Dni);

    public Profesor borrarProfesorporid(long id);

    public Profesor borrarProfesordni(int Dni);

    public Profesor modificarProfesor(Profesor profesor);



}
