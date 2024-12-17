package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.AsignaturaService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.EstadoAsignatura;
import ar.edu.utn.frbb.tup.model.dto.AsignaturaDto;
import ar.edu.utn.frbb.tup.model.exception.*;
import ar.edu.utn.frbb.tup.persistence.AlumnoDaoMemoryImpl;
import ar.edu.utn.frbb.tup.persistence.AsignaturaDaoMemoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    private AsignaturaDaoMemoryImpl asignaturaDaoMemoryImpl;
    @Autowired
    private AlumnoDaoMemoryImpl alumnoDaoMemoryImpl;
    @Autowired
    private MateriaServiceImpl materiaServiceImpl;

    @Override
    public Asignatura crearAsignatura(AsignaturaDto asignaturaDto) throws AsignaturaYaExisteException {
        // Verificar que los datos del DTO sean válidos
        if (asignaturaDto.getNota() == null) {
            throw new IllegalArgumentException("La nota no puede ser nula");
        }

        if (asignaturaDto.getIdalumno() <= 0) {
            throw new IllegalArgumentException("El ID del alumno es inválido");
        }
        if (asignaturaDto.getNota() < 1) {
            throw new IllegalArgumentException("El ID del alumno es inválido");
        }

        if (asignaturaDto.getIdmateria() <= 0) {
            throw new IllegalArgumentException("El ID de la materia es inválido");
        }

        // Verificar si el alumno y la materia existen
        if (alumnoDaoMemoryImpl.buscarAlumnoporid(asignaturaDto.getIdalumno()) == null) {
            throw new IllegalStateException("El alumno con ID " + asignaturaDto.getIdalumno() + " no existe");
        }

        if (materiaServiceImpl.buscarmateriaId(asignaturaDto.getIdmateria()) == null) {
            throw new IllegalStateException("La materia con ID " + asignaturaDto.getIdmateria() + " no existe");
        }

        // Crear una nueva Asignatura a partir del DTO
        Asignatura asignatura = new Asignatura(
                asignaturaDto.getEstado(),
                asignaturaDto.getNota(),
                asignaturaDto.getIdalumno(),
                asignaturaDto.getIdmateria()
        );

        // Guardar la asignatura en el DAO
        asignaturaDaoMemoryImpl.guardarAsignatura(asignatura);

        return asignatura;
    }

    @Override
    public Asignatura buscarAsignaturaId(long id) {
        Asignatura asignatura= asignaturaDaoMemoryImpl.buscarAsignaturaporId(id);
        if (asignatura == null) {
            throw new IllegalArgumentException("No se encontró el asignatura con ese ID: " + id);
        }
        return asignaturaDaoMemoryImpl.buscarAsignaturaporId(id);
    }


    @Override
    public List<Asignatura> buscarAsignaturas() {
        return asignaturaDaoMemoryImpl.buscarAsignaturas();
    }

    @Override
    public Asignatura borrarAsignaturaporid(long id) {
        Asignatura asignaturaExistente = asignaturaDaoMemoryImpl.borrarAsignaturaporid(id);
        if (asignaturaExistente != null) {
            asignaturaDaoMemoryImpl.borrarAsignaturaporid(id);
        }
        if(asignaturaExistente==null){
            throw new NoseEncontroAsignatura("no se encontro el id de esa asignatura");
        }
        return asignaturaExistente;
    }

    @Override
    public Asignatura modificarAsignatura(long id, AsignaturaDto asignaturaDto) {
        // Buscar si existe la asignatura a través del id
        Asignatura asignaturaExistente = asignaturaDaoMemoryImpl.buscarAsignaturaporId(id);

        if (asignaturaExistente == null) {
            // Lanzar excepción personalizada si no se encuentra la asignatura
            throw new NoseEncontroAsignatura("No se encontró la asignatura con el ID proporcionado: " + id);
        }

        // Actualizar los datos de la asignatura existente con los nuevos datos del DTO
        asignaturaExistente.setEstado(asignaturaDto.getEstado());
        asignaturaExistente.setNota(asignaturaDto.getNota());
        asignaturaExistente.setIdmateria(asignaturaDto.getIdmateria());
        asignaturaExistente.setIdalumno(asignaturaDto.getIdalumno());

        // Guardar los cambios
        asignaturaDaoMemoryImpl.modificarAsignatura(asignaturaExistente);

        return asignaturaExistente;
    }

    @Override
    public Asignatura modificarEstadoAsignatura(long idAlumno, long idAsignatura) throws AsignaturaNoEncontradaException {
        // Buscar la asignatura asociada a este alumno
        Asignatura asignaturaExistente = asignaturaDaoMemoryImpl.buscarAsignaturaporIdAsignaturaIdAlumno(idAsignatura, idAlumno);

        if (asignaturaExistente == null) {
            throw new AsignaturaNoEncontradaException("No se encontró una asignatura para este alumno con id " + idAsignatura + " y alumno id " + idAlumno);
        }

        EstadoAsignatura estadoActual = asignaturaExistente.getEstado();
        int numeroPosicion = estadoActual.ordinal();

        // Incrementar el estado solo si es válido para avanzar a un estado siguiente
        if (numeroPosicion < EstadoAsignatura.values().length - 1) {
            // Si la nota es menor a 4, el estado debe ser NO_CURSADO
            if (asignaturaExistente.getNota() <= 4) {
                asignaturaExistente.setEstado(EstadoAsignatura.NO_CURSADA);
            }
            // Si la nota está entre 4 y 6.99, el estado es Cursado
            else if (asignaturaExistente.getNota() <= 6) {
                asignaturaExistente.setEstado(EstadoAsignatura.CURSADA);
            }
            // Si la nota es 7 o más, el estado es Aprobado
            else if (asignaturaExistente.getNota() >= 7) {
                asignaturaExistente.setEstado(EstadoAsignatura.APROBADA);
            }

            // Guardar la asignatura con su nuevo estado
            asignaturaDaoMemoryImpl.modificarAsignatura(asignaturaExistente);
            return asignaturaExistente;
        } else {
            throw new EstadoInvalidoException("El estado actual no permite avanzar más.");
        }
    }


}
