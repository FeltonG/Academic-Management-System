package ar.edu.utn.frbb.tup.controller.validator;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import org.springframework.stereotype.Component;

@Component
public class alumnoValidator {


    public void validarAlumno(AlumnoDto alumnoDto) {
        if (alumnoDto.getDni() == 0) {
            throw new IllegalArgumentException("El DNI debe existir o ser distinto de 0");
        }

        String dniString = String.valueOf(alumnoDto.getDni());
        if (dniString.length() < 7 || dniString.length() > 9) {
            throw new IllegalArgumentException("El DNI tiene que tener entre 7 y 9 dígitos");
        }

        if (alumnoDto.getDni() < 0) {
            throw new IllegalArgumentException("El DNI tiene que ser positivo");
        }

        if (alumnoDto.getNombre() == null || alumnoDto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar un nombre");
        }

        if (alumnoDto.getApellido() == null || alumnoDto.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar un apellido");
        }

        // Formatear nombre y apellido para que empiecen con mayúscula
        alumnoDto.setNombre(formatearTexto(alumnoDto.getNombre()));
        alumnoDto.setApellido(formatearTexto(alumnoDto.getApellido()));

        if (!alumnoDto.getNombre().matches("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ ]+$")) {
            throw new IllegalArgumentException("El nombre debe comenzar con mayúscula y contener solo letras y espacios.");
        }

        if (alumnoDto.getNombre().length() <= 2) {
            throw new IllegalArgumentException("No abrevies el nombre");
        }

        if (alumnoDto.getNombre().length() > 25) {
            throw new IllegalArgumentException("No se permiten nombres tan largos");
        }

        if (!alumnoDto.getApellido().matches("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ ]+$")) {
            throw new IllegalArgumentException("El apellido debe comenzar con mayúscula y contener solo letras y espacios.");
        }

        if (alumnoDto.getApellido().length() <= 2) {
            throw new IllegalArgumentException("No abrevies el apellido");
        }

        if (alumnoDto.getApellido().length() > 50) {
            throw new IllegalArgumentException("No se permiten apellidos tan largos");
        }
    }

    /**
     * Formatea un texto para que cada palabra empiece con mayúscula y el resto sea minúscula.
     *
     * @param texto El texto a formatear
     * @return El texto formateado
     */
    private String formatearTexto(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return "";
        }

        String[] palabras = texto.trim().toLowerCase().split("\\s+");
        StringBuilder textoFormateado = new StringBuilder();

        for (String palabra : palabras) {
            textoFormateado.append(Character.toUpperCase(palabra.charAt(0)))
                    .append(palabra.substring(1))
                    .append(" ");
        }

        return textoFormateado.toString().trim();
    }
}
