package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.AsignaturaService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.EstadoAsignatura;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.dto.AsignaturaDto;
import ar.edu.utn.frbb.tup.persistence.AsignaturaDaoMemoryImpl;

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
    public Asignatura getAsignatura(int materiaId, long dni) {
        return null;
    }


}
