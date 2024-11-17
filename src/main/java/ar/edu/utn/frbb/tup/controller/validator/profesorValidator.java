package ar.edu.utn.frbb.tup.controller.validator;
import ar.edu.utn.frbb.tup.model.dto.ProfesorDto;
import org.springframework.stereotype.Component;

@Component
public class profesorValidator {

    public void profesorValidation(ProfesorDto profesorDto) {
        // Validación del nombre
        if (profesorDto.getNombre() == null || profesorDto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar un nombre");
        }

        if (!profesorDto.getNombre().matches("^[\\p{L} ]+$")) {
            throw new IllegalArgumentException("Utilice caracteres correctos para el nombre");
        }

        if (profesorDto.getNombre().length() <= 2) {
            throw new IllegalArgumentException("El nombre no puede ser tan corto");
        }

        if (profesorDto.getNombre().length() > 12) {
            throw new IllegalArgumentException("El nombre no puede ser tan largo");
        }

        // Validación del apellido
        if (profesorDto.getApellido() == null || profesorDto.getApellido().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar un apellido");
        }

        if (!profesorDto.getApellido().matches("^[\\p{L} ]+$")) {
            throw new IllegalArgumentException("Utilice caracteres correctos para el apellido");
        }

        if (profesorDto.getApellido().length() <= 2) {
            throw new IllegalArgumentException("El apellido no puede ser tan corto");
        }

        if (profesorDto.getApellido().length() > 12) {
            throw new IllegalArgumentException("El apellido no puede ser tan largo");
        }

        // Validación del título
        if (profesorDto.getTitulo() == null || profesorDto.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar un título");
        }

        if (!profesorDto.getTitulo().matches("^[\\p{L} ]+$")) {
            throw new IllegalArgumentException("Utilice caracteres correctos para el título");
        }

        if (profesorDto.getTitulo().length() <= 3) {
            throw new IllegalArgumentException("El título no puede ser tan corto");
        }

        if (profesorDto.getTitulo().length() > 12) {
            throw new IllegalArgumentException("El título no puede ser tan largo");
        }
    }


}
