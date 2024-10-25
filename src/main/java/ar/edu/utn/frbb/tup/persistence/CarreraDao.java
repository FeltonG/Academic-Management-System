package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Carrera;

import java.util.List;

public interface CarreraDao {

    public void guardarCarrera(Carrera carrera);

    public List<Carrera> buscarCarrera();

    public Asignatura buscarCarreraporId(long id);

    public Asignatura borrarCarreraporid(long id);

    public Asignatura modificarCarrera(Carrera carrera);

}
