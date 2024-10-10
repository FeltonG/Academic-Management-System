package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.dto.AsignaturaDto;

public interface AsignaturaService {
    Asignatura getAsignatura(int materiaId, long dni);
    Asignatura buscarAsignaturaDni(int Dni);
    Asignatura crearAsignatura(AsignaturaDto asignatura);
    Asignatura buscarAsignaturaId(long id);

}
