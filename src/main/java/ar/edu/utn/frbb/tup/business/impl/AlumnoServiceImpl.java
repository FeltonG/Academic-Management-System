package ar.edu.utn.frbb.tup.business.impl;
import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.exception.AlumnoNoEncontradoException;
import ar.edu.utn.frbb.tup.model.exception.AlumnoYaExisteException;
import ar.edu.utn.frbb.tup.persistence.AlumnoDaoMemoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Component
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoDaoMemoryImpl alumnoDaoMemoryImpl;

    @Override
    public Alumno crearAlumno(AlumnoDto alumnodto) throws AlumnoYaExisteException {
        Alumno alumnoExistente = alumnoDaoMemoryImpl.buscarAlumnopordni(alumnodto.getDni());
        if (alumnoExistente != null &&
                alumnoExistente.getNombre().equals(alumnodto.getNombre()) &&
                alumnoExistente.getApellido().equals(alumnodto.getApellido())) {
            throw new AlumnoYaExisteException("Este alumno ya existe, no puede crear un alumno con el mismo nombre, apellido y DNI");
        }
        Alumno alumno = new Alumno(alumnodto.getNombre(), alumnodto.getApellido(), alumnodto.getDni());
        alumnoDaoMemoryImpl.guardarAlumno(alumno);
        return alumno;
    }

    @Override
    public Alumno borraralumnoId(long id) throws AlumnoNoEncontradoException {
        Alumno alumnoExistente = alumnoDaoMemoryImpl.buscarAlumnoporid(id);
        if (alumnoExistente == null) {
            throw new AlumnoNoEncontradoException("No se encontró un alumno con el ID proporcionado: " + id);
        }
        alumnoDaoMemoryImpl.borrarAlumnoporid(id);
        return alumnoExistente;
    }

    @Override
    public Alumno buscarAlumnoId(long idAlumno) throws AlumnoNoEncontradoException {
        Alumno alumno = alumnoDaoMemoryImpl.buscarAlumnoporid(idAlumno);
        if (alumno == null) {
            throw new AlumnoNoEncontradoException("No se encontró el alumno con ID: " + idAlumno);
        }
        return alumno;
    }

    @Override
    public Alumno modificarAlumno(long id, AlumnoDto alumnoModificado) throws AlumnoNoEncontradoException {
        Alumno alumnoExistente = alumnoDaoMemoryImpl.buscarAlumnoporid(id);
        if (alumnoExistente == null) {
            throw new AlumnoNoEncontradoException("No se encontró un alumno con el ID proporcionado: " + id);
        }

        // Actualizar los datos del alumno existente con los nuevos datos del DTO
        alumnoExistente.setDni(alumnoModificado.getDni());
        alumnoExistente.setApellido(alumnoModificado.getApellido());
        alumnoExistente.setNombre(alumnoModificado.getNombre());

        alumnoDaoMemoryImpl.modificarAlumno(alumnoExistente);
        return alumnoExistente;
    }

    @Override
    public List<Alumno> buscarAlumnos() {
        return alumnoDaoMemoryImpl.buscarAlumnos();
    }
}




