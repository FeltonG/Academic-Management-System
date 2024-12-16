package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.AsignaturaService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.EstadoAsignatura;
import ar.edu.utn.frbb.tup.model.dto.AsignaturaDto;
import ar.edu.utn.frbb.tup.model.exception.AsignaturaYaExisteException;
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
        return asignaturaExistente;
    }

    @Override
    public Asignatura modificarAsignatura(long id, AsignaturaDto asignaturaDto) {
        // Buscar si existe la asignatura a través del id
        Asignatura asignaturaExistente = asignaturaDaoMemoryImpl.buscarAsignaturaporId(id);

        if (asignaturaExistente != null) {
            // Actualizar los datos de la asignatura existente con los nuevos datos del DTO
            asignaturaExistente.setEstado(asignaturaDto.getEstado());
            asignaturaExistente.setNota(asignaturaDto.getNota());
            asignaturaExistente.setIdmateria(asignaturaDto.getIdmateria());
            asignaturaExistente.setIdalumno(asignaturaDto.getIdalumno());

            // Guardar los cambios
            asignaturaDaoMemoryImpl.modificarAsignatura(asignaturaExistente);

            return asignaturaExistente;
        }

        // Si no se encontró la asignatura, imprimir un mensaje y retornar null
        System.out.println("No se encontró la asignatura con el id proporcionado: " + id);
        return null;
    }

    @Override
    public Asignatura modificarEstadoAsignatura(long idAlumno, long idAsignatura) {
        // Buscar si existe la asignatura a través del id
        Asignatura asignaturaExistente = asignaturaDaoMemoryImpl.buscarAsignaturaporIdAsignaturaIdAlumno(idAsignatura, idAlumno);

        if (asignaturaExistente != null) {
            EstadoAsignatura estadoActual = asignaturaExistente.getEstado();
            int numeroPosicion = estadoActual.ordinal();
            if (numeroPosicion < 1) {
                numeroPosicion++;
            } else if (numeroPosicion == 1 && asignaturaExistente.getNota() >= 4) {
                numeroPosicion++;
            }
            if (asignaturaExistente.getNota() < 4 && numeroPosicion == 1) {
                System.out.println("No cambia a aprobado, porque no aprueba");
            }
            asignaturaExistente.setEstado(EstadoAsignatura.values()[numeroPosicion]); // Cambia el estado
            asignaturaDaoMemoryImpl.modificarAsignatura(asignaturaExistente);
            return asignaturaExistente;
        }

        System.out.println("No se encontró una asignatura para este alumno");
        return null;
    }
}
