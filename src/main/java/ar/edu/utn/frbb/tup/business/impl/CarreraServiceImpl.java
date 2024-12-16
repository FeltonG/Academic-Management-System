package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.CarreraService;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.model.exception.CarreraYaExisteEstaException;
import ar.edu.utn.frbb.tup.persistence.CarreraDaoMemoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraServiceImpl implements CarreraService {

    @Autowired
    private CarreraDaoMemoryImpl carreraDaoMemoryImpl;

    @Override
    public Carrera crearCarrera(CarreraDto carreraDto) throws CarreraYaExisteEstaException {
        // Validaciones
        if (carreraDto.getNombre() == null || carreraDto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la carrera no puede estar vacío.");
        }

        // Verificar si ya existe una carrera con el mismo nombre
        Carrera carreraExistente = carreraDaoMemoryImpl.buscarCarrerasPorNombre(carreraDto.getNombre());
        if (carreraExistente != null) {
            throw new CarreraYaExisteEstaException("Ya existe una carrera con el nombre especificado.");
        }

        // Crear la nueva carrera
        Carrera carrera = new Carrera(carreraDto.getNombre());

        // Guardar la carrera en el DAO
        carreraDaoMemoryImpl.guardarCarrera(carrera);

        // Retornar la carrera creada
        return carrera;
    }

    @Override
    public List<Carrera> buscarCarreras() {
        return carreraDaoMemoryImpl.buscarCarrera();
    }

    @Override
    public Carrera buscarCarreraId(long id) {
        return carreraDaoMemoryImpl.buscarCarreraporId(id);
    }

    @Override
    public Carrera modificarCarrera(long id, CarreraDto carreraDto) {
        // Buscar si existe la carrera a través del id
        Carrera carreraExistente = carreraDaoMemoryImpl.buscarCarreraporId(id);

        if (carreraExistente != null) {
            // Actualizar los datos de la carrera existente con los nuevos datos del DTO
            carreraExistente.setNombre(carreraDto.getNombre());

            // Guardar los cambios
            carreraDaoMemoryImpl.modificarCarrera(carreraExistente);

            // Retornar la carrera modificada
            return carreraExistente;
        } else {
            // Si no se encuentra la carrera, retornar null o lanzar una excepción
            System.out.println("No se encontró una carrera con el id proporcionado");
            return null;
        }
    }

    @Override
    public Carrera borrarCarreraporid(long id) {
        Carrera carreraExistente = carreraDaoMemoryImpl.buscarCarreraporId(id);
        if (carreraExistente != null) {
            carreraDaoMemoryImpl.borrarCarreraporid(id);
        }
        return carreraExistente;
    }

    @Override
    public Carrera buscarCarreraPornombre(String nombre) {
        // Obtener todas las carreras
        List<Carrera> carreras = carreraDaoMemoryImpl.buscarCarrera();

        // Buscar la carrera por nombre
        for (Carrera carrera : carreras) {
            if (carrera.getNombre().equalsIgnoreCase(nombre)) { // Compara ignorando mayúsculas y minúsculas
                return carrera;
            }
        }

        // Si no se encuentra la carrera, retorna null o puedes lanzar una excepción adecuada
        return null;
    }
}
