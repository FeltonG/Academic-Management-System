package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;

public interface ProfesorDao {
    public void guardarProfesor(Profesor profesor);
    public Profesor borrarProfesorporid(long id);
    public Profesor buscarProfesorporid(long id);
}
