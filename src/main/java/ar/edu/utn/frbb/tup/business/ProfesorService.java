package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;

import java.util.List;

public interface ProfesorService {

    public Profesor borrarProfesorporid(long id);

    List<Profesor> buscarProfesor();

    public Profesor buscaProfesorporid(long id);

    public Profesor buscarProfesorporDni(int dni);



}
