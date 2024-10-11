package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;

import java.util.List;


public interface AsignaturaDao {

    public void guardarAsignatura(Asignatura asignatura);

    public List<Asignatura> buscarAsignatura();

    public Asignatura buscarAsignaturaporId(long id);

    public Asignatura buscarAsignaturaporDni(int Dni);

    public Asignatura borrarAsignaturaDni(int Dni);

    public Asignatura borrarAsignaturaporid(long id);

    public Asignatura modificarAsignatura(Asignatura asignatura);







}
