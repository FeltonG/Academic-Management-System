package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.business.ProfesorService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
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
        Materia materia = new Materia(materiadto.getNombre(),materiadto.getAnio(), materiadto.getCuatrimestre(),materiadto.getProfesorId(),materiadto.getCorrelatividades());
        System.out.println("El id profesor de la materia es: " + materiadto.getProfesorId());
        System.out.println("ID del profesor en Materia: " + materia.getIdprofesor());
        materiaDaoMemoryimp.guardarMateria(materia);
        return materia; // lo retorno
    }

    @Override
    public List<Materia> buscarMateria() {
        List<Materia> lista_de_materias = materiaDaoMemoryimp.buscarMaterias();
        return lista_de_materias;
    }



    @Override
    public Materia buscarmateriaId(long id) {
        Materia materiaId=materiaDaoMemoryimp.buscarMateriaId(id);
        return materiaId;
    }

    @Override
    public Materia modificarMateria(long id, MateriaDto materiaModificada) {

        // Buscar si existe el alumno a través del id
        Materia materiaExistente= materiaDaoMemoryimp.buscarMateriaId(id);

        if (materiaExistente != null) {
            // Actualizar los datos del alumno existente con los nuevos datos del DTO
            System.out.println("Materia modificada correlatividades: "+materiaModificada.getCorrelatividades().toString());
            materiaExistente.setNombre(materiaModificada.getNombre());
            materiaExistente.setAnio(materiaModificada.getAnio());
            materiaExistente.setCuatrimestre(materiaModificada.getCuatrimestre());
            materiaExistente.setIdprofesor(materiaModificada.getProfesorId());
            materiaExistente.setCorrelatividades(materiaModificada.getCorrelatividades());


            // Aquí puedes seguir actualizando otros campos que tengas en AlumnoDto

            // Guardar los cambios
            materiaDaoMemoryimp.modificarMateria(materiaExistente);

            // Retornar el alumno modificado
            return materiaExistente;
        } else {
            // Si no se encuentra el alumno, retornar null o lanzar una excepción
            System.out.println("No se encontró un profesor con el id proporcionado");
            return null;
        }
    }

    @Override
    public Materia borrarmateriaId(long id) {
        // primero salgo a buscarlo.
        Materia materia_Existente = materiaDaoMemoryimp.buscarMateriaId(id);
        if (materia_Existente != null)
        {
            materiaDaoMemoryimp.borrarmateriaporid(id);
        }
        return materia_Existente;
    }


}
