package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.AsignaturaService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.EstadoAsignatura;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.dto.AsignaturaDto;
import ar.edu.utn.frbb.tup.persistence.AsignaturaDaoMemoryImpl;

import java.util.List;

public class AsignaturaServiceImpl implements AsignaturaService {

    private AsignaturaDaoMemoryImpl asignaturaDaoMemoryImpl;

    @Override
    public Asignatura crearAsignatura(AsignaturaDto asignaturadto) {
        // el servicio ademas de crear la informacion y retornarla la almacena en DAO
        // tambien lo que hace es VERIFICAR que todo este BIEN antes de el guardado.
        Asignatura asignatura = new Asignatura(asignaturadto.getNota(),asignaturadto.getIdalumno(), asignaturadto.getNota());
        asignaturaDaoMemoryImpl.guardarAsignatura(asignatura);
        return asignatura; // lo retorno
    }

    @Override
    public Asignatura buscarAsignaturaDni(int Dni) {
        Asignatura asignatura = asignaturaDaoMemoryImpl.buscarAsignaturaporDni(Dni);
        return asignatura;
    }

    @Override
    public Asignatura buscarAsignaturaId(long id) {
        Asignatura asignatura=asignaturaDaoMemoryImpl.buscarAsignaturaporId(id);
        return asignatura;
    }

    public List<Asignatura> buscarAsignatura()
    {
        List<Asignatura> lista_de_asignaturas = asignaturaDaoMemoryImpl.buscarAsignatura();
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
            asignaturaExistente.setNota(asignaturaDto.getNota());
            asignaturaExistente.setIdalumno(asignaturaDto.getIdalumno());
            asignaturaExistente.setIdmateria(asignaturaDto.getIdmateria()); // Corregido el error de sintaxis

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
    public Asignatura borrarAsignaturaporDni(int dni) {
        // primero salgo a buscarlo.
        Asignatura asignatura_Existente = asignaturaDaoMemoryImpl.borrarAsignaturaDni(dni);
        if (asignatura_Existente != null)
        {
            asignaturaDaoMemoryImpl.borrarAsignaturaDni(dni);
        }
        return asignatura_Existente;
    }
}
