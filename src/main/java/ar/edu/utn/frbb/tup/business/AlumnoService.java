package ar.edu.utn.frbb.tup.business;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.exception.AlumnoNoEncontradoException;
import ar.edu.utn.frbb.tup.model.exception.AlumnoYaExisteException;

import java.util.List;

public interface AlumnoService {
    // void aprobarAsignatura(int materiaId, int nota, int dni) throws EstadoIncorrectoException, CorrelatividadesNoAprobadasException;

    Alumno crearAlumno(AlumnoDto alumno) throws AlumnoYaExisteException;

    Alumno borraralumnoId(long id) throws AlumnoNoEncontradoException;


    Alumno buscarAlumnoId(long id) throws AlumnoNoEncontradoException;



    Alumno modificarAlumno(long id, AlumnoDto alumno) throws AlumnoNoEncontradoException;

    List<Alumno> buscarAlumnos();


}
