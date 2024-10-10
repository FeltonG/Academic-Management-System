package ar.edu.utn.frbb.tup.controller.validator;

import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.dto.ProfesorDto;

public class profesorValidator {

    public void validarprofesor (ProfesorDto profesorDto){


        // Validación del DNI

        // Validación del nombre
        if (profesorDto.getNombre() == null || profesorDto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar un nombre");
        }

        if (!profesorDto.getNombre().matches("^[A-Za-záéíóúÁÉÍÓÚÑñ ]+$")) {
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

        if (!profesorDto.getApellido().matches("^[A-Za-záéíóúÁÉÍÓÚÑñ ]+$")) {
            throw new IllegalArgumentException("Utilice caracteres correctos para el apellido");
        }

        if (profesorDto.getApellido().length() <= 2) {
            throw new IllegalArgumentException("El apellido no puede ser tan corto");
        }

        if (profesorDto.getApellido().length() > 12) {
            throw new IllegalArgumentException("El apellido no puede ser tan largo");
        }
        if(profesorDto.getTitulo().length()>12){
            throw new IllegalArgumentException("el titulo no puede ser tan largo");

        }
        if(profesorDto.getTitulo().length()>3){
            throw new IllegalArgumentException("el titulo no puede ser tan corto");

        }
        if (profesorDto.getTitulo() == null ) {
            throw new IllegalArgumentException("Debe ingresar un apellido");
        }
        if (!profesorDto.getTitulo().matches("^[A-Za-záéíóúÁÉÍÓÚÑñ ]+$")) {
            throw new IllegalArgumentException("Utilice caracteres correctos para el titulo");
        }


    }

}
