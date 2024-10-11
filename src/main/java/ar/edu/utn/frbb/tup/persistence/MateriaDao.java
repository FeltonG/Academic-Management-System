package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Materia;

import java.util.List;


public interface MateriaDao {
    public void guardarMateria(Materia materia);

    public List<Materia> buscarMateria();

    public Materia buscarMateriaId(long id);

    public Materia buscarMateriaDni(int Dni);

    public Materia borrarmateriaDni(int Dni);

    public Materia borrarmateriaporid(long id);

    public Materia modificarMateria(Materia materia);



}
