package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.ProfesorService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.model.dto.ProfesorDto;
import ar.edu.utn.frbb.tup.persistence.MateriaDao;
import ar.edu.utn.frbb.tup.persistence.MateriaDaoMemoryImpl;
import ar.edu.utn.frbb.tup.persistence.ProfesorDao;
import ar.edu.utn.frbb.tup.persistence.ProfesorDaoMemoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorServiceImpl implements ProfesorService {
    @Autowired
    private ProfesorDaoMemoryImpl profesorDaoMemoryimpl;

    public Profesor crearProfesor(ProfesorDto profesordto)
    {
        // el servicio ademas de crear la informacion y retornarla la almacena en DAO
        // tambien lo que hace es VERIFICAR que todo este BIEN antes de el guardado.
        Profesor profesor = new Profesor(profesordto.getNombre(),profesordto.getApellido(), profesordto.getTitulo());
        profesorDaoMemoryimpl.guardarProfesor(profesor);
        return profesor; // lo retorno
    }

    public Profesor borrarprofesor(long id) {
        // primero salgo a buscarlo.
        Profesor profesorexistente = profesorDaoMemoryimpl.buscarProfesorporid(id);
        if (profesorexistente != null)
        {
           profesorDaoMemoryimpl.borrarProfesorporid(id);
        }
       return profesorexistente;
    }

    @Override
    public Profesor buscarProfesor(long id) {
        return null;
    }

    @Override
    public Profesor borrarProfesorporid(long id) {

        Profesor profesorExistente = profesorDaoMemoryimpl.buscarProfesorporid(id);

        if (profesorExistente != null) {
            profesorDaoMemoryimpl.borrarProfesorporid(id);
            return profesorExistente; // Retornar el profesor eliminado.
        }

        // Si el profesor no existe, puedes lanzar una excepción o retornar null.
        return null;
    }
}
