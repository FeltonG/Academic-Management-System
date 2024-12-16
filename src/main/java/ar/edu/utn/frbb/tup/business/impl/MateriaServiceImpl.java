package ar.edu.utn.frbb.tup.business.impl;
import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.model.exception.AlumnoYaExisteException;
import ar.edu.utn.frbb.tup.model.exception.MateriaYaExisteException;
import ar.edu.utn.frbb.tup.model.exception.ProfesorNoEncontradoException;
import ar.edu.utn.frbb.tup.persistence.MateriaDaoMemoryImpl;
import ar.edu.utn.frbb.tup.persistence.ProfesorDaoMemoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class MateriaServiceImpl implements MateriaService {
    @Autowired
    private MateriaDaoMemoryImpl materiaDaoMemoryimp;
    @Autowired
    private ProfesorDaoMemoryImpl profesorDaoMem;

    @Override
    public Materia crearMateria(MateriaDto materiadto) throws ProfesorNoEncontradoException, MateriaYaExisteException {
        // Validaciones
        if (materiadto.getNombre() == null || materiadto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la materia no puede estar vacío.");
        }

        if (materiadto.getAnio() <= 0) {
            throw new IllegalArgumentException("El año debe ser un número positivo.");
        }

        if (materiadto.getCuatrimestre() < 1 || materiadto.getCuatrimestre() > 4) {
            throw new IllegalArgumentException("El cuatrimestre debe estar entre 1 y 4.");
        }
        // Validar que la lista de correlatividades no sea nula ni vacía
        if (materiadto.getCorrelatividades() == null || materiadto.getCorrelatividades().isEmpty()) {
            throw new IllegalArgumentException("La materia debe tener al menos una correlatividad.");
        }

        ProfesorDaoMemoryImpl profesorDaoMem = new ProfesorDaoMemoryImpl();
        Profesor profesor = profesorDaoMem.buscarProfesorporid(materiadto.getProfesorId());
        if (profesor == null) {
            throw new ProfesorNoEncontradoException("El id del Profesor no se encuentra en la BASE DE DATOS");
        }

        // Verificar si ya existe una materia con el mismo nombre y cuatrimestre
        List<Materia> todasLasMaterias = materiaDaoMemoryimp.buscarMaterias();
        for (Materia materia : todasLasMaterias) {
            if (materia.getNombre().equalsIgnoreCase(materiadto.getNombre()) &&
                    materia.getCuatrimestre() == materiadto.getCuatrimestre()) {
                throw new MateriaYaExisteException("Ya existe una materia con el mismo nombre y cuatrimestre.");
            }
        }

        // Crear la nueva materia
        Materia materia = new Materia(materiadto.getNombre(), materiadto.getAnio(), materiadto.getCuatrimestre(), materiadto.getProfesorId(), materiadto.getCorrelatividades());

        System.out.println("El id profesor de la materia es: " + materiadto.getProfesorId());
        System.out.println("ID del profesor en Materia: " + materia.getIdprofesor());

        // Guardar la materia en el DAO
        materiaDaoMemoryimp.guardarMateria(materia);

        // Retornar la materia creada
        return materia;
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
