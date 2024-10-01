package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Materia;


public interface MateriaDao {
    Materia save(Materia materia);

    public void guardarMateria(Materia materia);
}
