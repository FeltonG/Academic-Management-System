package ar.edu.utn.frbb.tup.business;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.dto.ProfesorDto;

import java.util.List;

public interface ProfesorService {

    public Profesor borrarProfesorporid(long id);

    public Profesor buscaProfesorporid(long id);

    List<Profesor> buscarProfesores();

    Profesor crearProfesor(ProfesorDto profesor);

    Profesor modificarProfesor( long id, ProfesorDto profesor);

    public List<Materia> buscarMateriasPorProfesorId(long id);


}
