package ar.edu.utn.frbb.tup.controller.validator;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import org.springframework.stereotype.Component;

@Component
public class carreraValidator {
    public void carreraValidation(CarreraDto carreraDto) {

        // Validación del nombre
        if (carreraDto.getNombre() == null || carreraDto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar un nombre para la carrera");
        }

        if (!carreraDto.getNombre().matches("^[a-z ]+$")) {
            throw new IllegalArgumentException("Utilice solo letras sin tildes y en minúsculas para el nombre de la carrera");
        }
        if (carreraDto.getNombre().length() <= 2) {
            throw new IllegalArgumentException("El nombre de la carrera no puede ser tan corto");
        }
        if (carreraDto.getNombre().length() > 12) {
            throw new IllegalArgumentException("El nombre de la carrera no puede ser tan largo");
        }
    }
}
