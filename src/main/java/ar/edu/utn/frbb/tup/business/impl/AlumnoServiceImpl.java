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
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AlumnoServiceImpl implements AlumnoService {

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
    public Alumno borrarAlumno(long id) {
        // primero salgo a buscarlo.
        Alumno alumnoExistente = alumnoDaoMemoryImpl.buscarAlumno(id);
        if (alumnoExistente != null)
        {
            alumnoDaoMemoryImpl.borrarAlumno(alumnoExistente);
        }
    }

    @Override
    public void aprobarAsignatura(int materiaId, int nota, long dni) throws EstadoIncorrectoException, CorrelatividadesNoAprobadasException {

    }
    @Override
    public Alumno buscarAlumno(String apellidoAlumno) {
        return null;
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