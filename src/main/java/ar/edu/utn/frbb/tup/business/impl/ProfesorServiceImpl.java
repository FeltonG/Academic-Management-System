package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.ProfesorService;
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

    private ProfesorDaoMemoryImpl profesorDaoMemoryimpl;

    public Profesor crearProfesor(ProfesorDto profesordto)
    {
        // el servicio ademas de crear la informacion y retornarla la almacena en DAO
        // tambien lo que hace es VERIFICAR que todo este BIEN antes de el guardado.
        Profesor profesor = new Profesor(profesordto.getNombre(),profesordto.getApellido(), profesordto.getTitulo());
        profesorDaoMemoryimpl.guardarProfesor(profesor);
        return profesor; // lo retorno
    }

    @Override
    public Profesor buscarProfesor(long id) {
        return null;
    }
}
