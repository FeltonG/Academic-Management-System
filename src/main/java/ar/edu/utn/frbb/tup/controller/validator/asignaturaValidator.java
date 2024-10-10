package ar.edu.utn.frbb.tup.controller.validator;

import ar.edu.utn.frbb.tup.model.dto.AsignaturaDto;

public class asignaturaValidator {
    public void validarasignatura(AsignaturaDto asignaturaDto){

            // Validación del estado
            if (asignaturaDto.getEstado() == null) {
                throw new IllegalArgumentException("El estado de la asignatura no puede ser nulo");
            }

            // Validación de la nota
            if (asignaturaDto.getNota() == null) {
                throw new IllegalArgumentException("La nota no puede ser nula");
            }

            if (asignaturaDto.getNota() < 0 || asignaturaDto.getNota() > 10) {
                throw new IllegalArgumentException("La nota debe estar entre 0 y 10");
            }

            // Validación del ID de la materia
            if (asignaturaDto.getIdmateria() <= 0) {
                throw new IllegalArgumentException("El ID de la materia debe ser un número positivo");
            }

            // Validación del ID del alumno
            if (asignaturaDto.getIdalumno() <= 0) {
                throw new IllegalArgumentException("El ID del alumno debe ser un número positivo");
            }
        }


    }

