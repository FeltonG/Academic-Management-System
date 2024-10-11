package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.dto.AsignaturaDto;

import java.util.List;

public interface AsignaturaService {
    Asignatura crearAsignatura(AsignaturaDto asignatura);

    Asignatura buscarAsignaturaId(long id);

    Asignatura buscarAsignaturaDni(int Dni);

    List<Asignatura> buscarAsignatura();

    Asignatura getAsignatura(int materiaId, long dni);

    Asignatura modificarAsignatura(long id, AsignaturaDto asignatura);

    Asignatura borrarAsignatura(long id);

}
