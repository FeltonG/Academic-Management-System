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

import java.util.List;
import java.util.Random;

@Component
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    // el objetivo de este objeto debajo es usar las herramientas de Dao sin ser por ejemplo
    // un alumno existente
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

    // buscar alumno dni
    public Alumno buscarAlumnoDni(int dni) {
        Alumno alumno = alumnoDaoMemoryImpl.buscarAlumnoporDni(dni);
        return alumno;
    }


    public Alumno modificarAlumno(long id, AlumnoDto alumnoModificado)
    {

        // a traves del id por parametro se busca si ese id pertenece a un alumno existente
        Alumno alumnoExistente = alumnoDaoMemoryImpl.buscarAlumnoporid(id);
        if (alumnoExistente != null)
        {
            alumnoDaoMemoryImpl.borrarAlumnoporid(id);
        }

        // cambiar (uno a uno) los datos del alumnoExistente (existente de dao->csv)
        // los datos son reemplezados por el alumnoModificado
        alumnoExistente.setDni(alumnoModificado.getDni());
        // hacer lo mismo para apellido y nombre
        alumnoExistente.setApellido(alumnoModificado.getApellido());
        alumnoExistente.setNombre(alumnoModificado.getNombre());

        // el problema es el siguiente: necesitamos modificar alumnoExistente usando alumnoDaoMemoryImpl.
        // pero no puedo llamar a modificaralumno desde alumnoExistente
        alumnoDaoMemoryImpl.modificarAlumno(alumnoExistente);
        // Cuidado que debemos de crear un xxxx_DaoMemoryImpl con Autowired con el fin de que pueda usar
        // el metodo modificarAlumno desde un dato que no es en si un alumno real.
        return alumnoExistente;

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