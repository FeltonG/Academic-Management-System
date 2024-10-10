package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Materia;

import java.util.List;


public interface MateriaDao {
    Materia save(Materia materia);
    public List<Materia> buscarMateria();
    public Materia buscarMateriaId(long id);
    public Materia buscarMateriaDni(int Dni);
    public void guardarMateria(Materia materia);
}
