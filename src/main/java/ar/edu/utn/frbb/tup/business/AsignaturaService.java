package ar.edu.utn.frbb.tup.business;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.dto.AsignaturaDto;
import java.util.List;

public interface AsignaturaService {
    Asignatura crearAsignatura(AsignaturaDto asignatura);

    Asignatura buscarAsignaturaId(long id);

    List<Asignatura> buscarAsignaturas();

    Asignatura modificarAsignatura(long id, AsignaturaDto asignatura);

    Asignatura borrarAsignaturaporid(long id);

    Asignatura modificarEstadoAsignatura(long idAlumno, long idAsignatura);
}
