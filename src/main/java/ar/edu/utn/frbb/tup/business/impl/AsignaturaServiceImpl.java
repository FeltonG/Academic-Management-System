package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.AsignaturaService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.EstadoAsignatura;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.dto.AsignaturaDto;
import ar.edu.utn.frbb.tup.persistence.AsignaturaDaoMemoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    private AsignaturaDaoMemoryImpl asignaturaDaoMemoryImpl;

    @Override
    public Asignatura crearAsignatura(AsignaturaDto asignaturadto) {
        // Verificar que los datos del DTO sean válidos
        if (asignaturadto.getNota() == null) { // Si 'Nota' es un objeto que puede ser null
            throw new IllegalArgumentException("La nota no puede ser nula");
        }

        // Verificar si 'idalumno' y 'idmateria' son 0 o valores inválidos
        if (asignaturadto.getIdalumno() == 0) {
            throw new IllegalArgumentException("El ID del alumno es inválido");
        }

        if (asignaturadto.getIdmateria() == 0) {
            throw new IllegalArgumentException("El ID de la materia es inválido");
        }


        // Crear una nueva Asignatura a partir del DTO
        Asignatura asignatura1 = new Asignatura(asignaturadto.getEstado(),asignaturadto.getNota(), asignaturadto.getIdalumno(), asignaturadto.getIdmateria());

        // Guardar la asignatura en el DAO
        asignaturaDaoMemoryImpl.guardarAsignatura(asignatura1);

        // Retornar la asignatura creada
        return asignatura1;
    }


    @Override
    public Asignatura buscarAsignaturaId(long id) {
        Asignatura asignatura=asignaturaDaoMemoryImpl.buscarAsignaturaporId(id);
        return asignatura;
    }

    public List<Asignatura> buscarAsignaturas()
    {
        List<Asignatura> lista_de_asignaturas = asignaturaDaoMemoryImpl.buscarAsignaturas();
        return lista_de_asignaturas;
    }


    @Override
    public Asignatura borrarAsignaturaporid(long id) {
        // primero salgo a buscarlo.
        Asignatura asignatura_Existente = asignaturaDaoMemoryImpl.borrarAsignaturaporid(id);
        if (asignatura_Existente != null)
        {
            asignaturaDaoMemoryImpl.borrarAsignaturaporid(id);
        }
        return asignatura_Existente;
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

            // Retornar la asignatura modificada
            return asignaturaExistente;
        }

        // Si no se encontró la asignatura, imprimir un mensaje y retornar null
        System.out.println("No se encontró la asignatura con el id proporcionado: " + id);
        return null;
    }


    @Override
    public Asignatura modificarEstadoAsignatura(long idAlumno, long idAsignatura)
    {
        // Buscar si existe la asignatura a través del id
        Asignatura asignaturaExistente = asignaturaDaoMemoryImpl.buscarAsignaturaporIdAsignaturaIdAlumno(idAsignatura, idAlumno);


        if (asignaturaExistente != null) {
            // Actualizar los datos de la asignatura existente con los nuevos datos del DTO
            EstadoAsignatura estadoActual = asignaturaExistente.getEstado();
            int numeroPosicion = estadoActual.ordinal();
            if (numeroPosicion < 1) {
                numeroPosicion++;
            } else if (numeroPosicion == 1 && asignaturaExistente.getNota() >= 4)
            {
                numeroPosicion++;
            }
            if(asignaturaExistente.getNota()<4 && numeroPosicion == 1)
            {
                System.out.println("No cambia a aprobado, porque no aprueba");
            }
            asignaturaExistente.setEstado(EstadoAsignatura.values()[numeroPosicion]); // quiero el ordinal de numeroPosicion
            asignaturaDaoMemoryImpl.modificarAsignatura(asignaturaExistente);
            return asignaturaExistente;
        }

            // Guardar los cambios
        System.out.println("No se encontro una asignatura para este alumno");
        return null;
    }

}
