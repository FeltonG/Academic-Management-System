package ar.edu.utn.frbb.tup.business.impl;
import ar.edu.utn.frbb.tup.business.CarreraService;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.persistence.CarreraDaoMemoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarreraServiceImpl implements CarreraService {

    @Autowired
    private CarreraDaoMemoryImpl carreraDaoMemoryImpl;

    @Override
    public Carrera crearCarrera(CarreraDto carreraDto)
    {
        if (carreraDto.getNombre() == null || carreraDto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la carrera no puede estar vacío.");
        }
        // Crear una nueva Asignatura a partir del DTO
        Carrera carrera1 = new Carrera(carreraDto.getNombre());

        // Guardar la asignatura en el DAO
        carreraDaoMemoryImpl.guardarCarrera(carrera1);

        // Retornar la asignatura creada
        return carrera1;
    }

    @Override
    public List<Carrera> buscarCarreras() {
        List<Carrera> lista_de_Carreras = carreraDaoMemoryImpl.buscarCarrera();
        return lista_de_Carreras;
    }

    @Override
    public Carrera buscarCarreraId(long id) {
        Carrera carreraid = carreraDaoMemoryImpl.buscarCarreraporId(id);
        return carreraid;
    }

    @Override
    public Carrera modificarCarrera(long id, CarreraDto carrera) {

        // Buscar si existe el alumno a través del id
        Carrera carreraExistente= carreraDaoMemoryImpl.buscarCarreraporId(id);

        if (carreraExistente != null) {
            // Actualizar los datos del alumno existente con los nuevos datos del DTO

            carreraExistente.setNombre(carrera.getNombre());



            // Guardar los cambios
            carreraDaoMemoryImpl.modificarCarrera(carreraExistente);

            // Retornar el alumno modificado
            return carreraExistente;
        } else {
            // Si no se encuentra el alumno, retornar null o lanzar una excepción
            System.out.println("No se encontró un Carrera con el id proporcionado");
            return null;
        }
    }

    @Override
    public Carrera borrarCarreraporid(long id) {
        Carrera carrera_Existente = carreraDaoMemoryImpl.buscarCarreraporId(id);
        if (carrera_Existente != null)
        {
            carreraDaoMemoryImpl.borrarCarreraporid(id);
        }
        return carrera_Existente;
    }
}
