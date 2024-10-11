package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.business.ProfesorService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.persistence.MateriaDao;
import ar.edu.utn.frbb.tup.persistence.MateriaDaoMemoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MateriaServiceImpl implements MateriaService {
    @Autowired
    private MateriaDaoMemoryImpl materiaDaoMemoryimp;


    @Override
    public Materia crearMateria(MateriaDto materiadto)
    {
        // el servicio ademas de crear la informacion y retornarla la almacena en DAO
        // tambien lo que hace es VERIFICAR que todo este BIEN antes de el guardado.
        Materia materia = new Materia(materiadto.getNombre(),materiadto.getAnio(), materiadto.getCuatrimestre(),materiadto.getProfesorId());
        materiaDaoMemoryimp.guardarMateria(materia);
        return materia; // lo retorno
    }

    @Override
    public List<Materia> buscarMateria() {
        List<Materia> lista_de_materias = materiaDaoMemoryimp.buscarMateria();
        return lista_de_materias;
    }

    @Override
    public Materia buscarmateriaDni(int Dni) {
        Materia materiaDni=materiaDaoMemoryimp.buscarMateriaDni(Dni);
        return materiaDni;

    }

    @Override
    public Materia buscarmateriaId(long id) {
        Materia materiaId=materiaDaoMemoryimp.buscarMateriaId(id);
        return materiaId;
    }

    @Override
    public Materia modificarMateria(long id, MateriaDto materia) {
        System.out.println("preguntar esta");
        return null;
    }

    @Override
    public Materia borrarMateria(long id) {
        // primero salgo a buscarlo.
        Materia materia_Existente = materiaDaoMemoryimp.buscarMateriaId(id);
        if (materia_Existente != null)
        {
            materiaDaoMemoryimp.borrarmateriaporid(id);
        }
        return materia_Existente;
    }

}
