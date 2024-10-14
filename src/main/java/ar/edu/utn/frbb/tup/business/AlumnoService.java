package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.exception.CorrelatividadesNoAprobadasException;
import ar.edu.utn.frbb.tup.model.exception.EstadoIncorrectoException;

import java.util.List;

public interface AlumnoService {
    // void aprobarAsignatura(int materiaId, int nota, int dni) throws EstadoIncorrectoException, CorrelatividadesNoAprobadasException;

    Alumno crearAlumno(AlumnoDto alumno);

    Alumno borraralumnoId(long id);

    Alumno borraralumnoDni(int dni);


    Alumno buscarAlumnoId(long id);

    Alumno buscarAlumnoDni(int dni);

    Alumno modificarAlumno(long id, AlumnoDto alumno);

    List<Alumno> buscarAlumnos();


}
