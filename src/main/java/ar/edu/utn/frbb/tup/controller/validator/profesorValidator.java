package ar.edu.utn.frbb.tup.controller.validator;
import ar.edu.utn.frbb.tup.model.dto.ProfesorDto;
import org.springframework.stereotype.Component;

@Component
public class profesorValidator {

    public void profesorValidation(ProfesorDto profesorDto) {
        // Validación del nombre
        if (profesorDto.getNombre() == null || profesorDto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar un nombre.");
        }

        if (!profesorDto.getNombre().matches("^[a-zA-Z ]+$")) {
            throw new IllegalArgumentException("Utilice solo letras y espacios para el nombre.");
        }

        if (profesorDto.getNombre().length() <= 2) {
            throw new IllegalArgumentException("El nombre no puede ser tan corto.");
        }

        if (profesorDto.getNombre().length() > 50) {
            throw new IllegalArgumentException("El nombre no puede tener más de 50 caracteres.");
        }

        // Validación del apellido
        if (profesorDto.getApellido() == null || profesorDto.getApellido().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar un apellido.");
        }

        if (!profesorDto.getApellido().matches("^[a-zA-Z ]+$")) {
            throw new IllegalArgumentException("Utilice solo letras y espacios para el apellido.");
        }

        if (profesorDto.getApellido().length() <= 2) {
            throw new IllegalArgumentException("El apellido no puede ser tan corto.");
        }

        if (profesorDto.getApellido().length() > 50) {
            throw new IllegalArgumentException("El apellido no puede tener más de 50 caracteres.");
        }

        // Validación del título
        if (profesorDto.getTitulo() == null || profesorDto.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar un título.");
        }

        if (!profesorDto.getTitulo().matches("^[a-zA-Z ]+$")) {
            throw new IllegalArgumentException("Utilice solo letras y espacios para el título.");
        }

        if (profesorDto.getTitulo().length() <= 3) {
            throw new IllegalArgumentException("El título no puede ser tan corto.");
        }

        if (profesorDto.getTitulo().length() > 50) {
            throw new IllegalArgumentException("El título no puede tener más de 50 caracteres.");
        }
    }


}
