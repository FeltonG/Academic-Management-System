package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.business.AsignaturaService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.EstadoAsignatura;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.exception.CorrelatividadesNoAprobadasException;
import ar.edu.utn.frbb.tup.model.exception.EstadoIncorrectoException;
import ar.edu.utn.frbb.tup.persistence.AlumnoDao;
import ar.edu.utn.frbb.tup.persistence.AlumnoDaoMemoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Component
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private  AlumnoDaoMemoryImpl alumnoDaoMemoryImpl;

    @Override
    public Alumno crearAlumno(AlumnoDto alumnodto) {
        // el servicio ademas de crear la informacion y retornarla la almacena en DAO
        // tambien lo que hace es VERIFICAR que todo este BIEN antes de el guardado.
        Alumno alumno = new Alumno(alumnodto.getNombre(), alumnodto.getApellido(),alumnodto.getDni());
        alumnoDaoMemoryImpl.guardarAlumno(alumno);
        return alumno; // lo retorno
    }

    @Override
    public Alumno borraralumnoId(long id) {
        Alumno alumnoExistente = alumnoDaoMemoryImpl.buscarAlumnoporid(id);
        if (alumnoExistente != null)
        {
            alumnoDaoMemoryImpl.borrarAlumnoporid(id);
        }
        return alumnoExistente;
    }


    /*@Override
    public void aprobarAsignatura(int materiaId, int nota, long dni) throws EstadoIncorrectoException, CorrelatividadesNoAprobadasException {

    }*/


    @Override
    public Alumno buscarAlumnoId(long id) {
        Alumno alumno = alumnoDaoMemoryImpl.buscarAlumnoporid(id);
        return alumno;
    }

    @Override
    public Alumno modificarAlumno(long id, AlumnoDto alumnoModificado) {

        // Buscar si existe el alumno a través del id
        Alumno alumnoExistente = alumnoDaoMemoryImpl.buscarAlumnoporid(id);

        if (alumnoExistente != null) {
            // Actualizar los datos del alumno existente con los nuevos datos del DTO
            alumnoExistente.setDni(alumnoModificado.getDni());
            alumnoExistente.setApellido(alumnoModificado.getApellido());
            alumnoExistente.setNombre(alumnoModificado.getNombre());
            // Aquí puedes seguir actualizando otros campos que tengas en AlumnoDto

            // Guardar los cambios
            alumnoDaoMemoryImpl.modificarAlumno(alumnoExistente);

            // Retornar el alumno modificado
            return alumnoExistente;
        } else {
            // Si no se encuentra el alumno, retornar null o lanzar una excepción
            System.out.println("No se encontró un alumno con el id proporcionado");
            return null;
        }
    }

    public List<Alumno> buscarAlumnos()
    {
        List<Alumno> lista_de_alumnos = alumnoDaoMemoryImpl.buscarAlumnos();
        return lista_de_alumnos;
    }


}



/*
    @Override
    public void aprobarAsignatura(int materiaId, int nota, long dni) throws EstadoIncorrectoException, CorrelatividadesNoAprobadasException {
        Asignatura a = asignaturaService.getAsignatura(materiaId, dni);
        for (Materia m:
             a.getMateria().getCorrelatividades()) {
            Asignatura correlativa = asignaturaService.getAsignatura(m.getMateriaId(), dni);
            if (!EstadoAsignatura.APROBADA.equals(correlativa.getEstado())) {
                throw new CorrelatividadesNoAprobadasException("La materia " + m.getNombre() + " debe estar aprobada para aprobar " + a.getNombreAsignatura());
            }
        }
        a.aprobarAsignatura(nota);
        asignaturaService.actualizarAsignatura(a);
        Alumno alumno = alumnoDao.loadAlumno(dni);
        alumno.actualizarAsignatura(a);
        alumnoDao.saveAlumno(alumno);
    }

 */

/*@Override
    public Alumno buscarAlumno(String apellido) {
        return alumnoDao.findAlumno(apellido);
    }*/