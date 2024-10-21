package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.AsignaturaDto;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;


import java.util.List;

public interface MateriaService {
    Materia crearMateria(MateriaDto materia);

    Materia buscarmateriaId(long id);



    List<Materia> buscarMateria();

    Materia modificarMateria(long id, MateriaDto materia);

    Materia borrarmateriaId(long id);



}
