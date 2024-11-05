package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.dto.AsignaturaDto;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;

import java.util.List;

public interface CarreraService {
    Carrera crearCarrera(CarreraDto carrera);

    Carrera buscarCarreraId(long id);

    List<Carrera> buscarCarreras();

    Carrera modificarCarrera(long id, CarreraDto carrera);

    Carrera borrarCarreraporid(long id);
}
