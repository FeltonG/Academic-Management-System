package ar.edu.utn.frbb.tup.controller.validator;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.model.exception.MateriaAbreviadoException;
import ar.edu.utn.frbb.tup.model.exception.MateriaNombreInvalidoException;
import ar.edu.utn.frbb.tup.model.exception.NombreDeLaMateriaException;
import org.springframework.stereotype.Component;

@Component
public class materiaValidator {

    public void materiaValidation(MateriaDto materiaDto) {
        // Validación de que el nombre no sea nulo o vacío
        if (materiaDto.getNombre() == null || materiaDto.getNombre().isEmpty()) {
            throw new MateriaNombreInvalidoException("Debe ingresar el nombre de la materia.");
        }


        // Validación de la longitud del nombre (mínimo 3 caracteres)
        if (materiaDto.getNombre().length() <= 2) {
            throw new MateriaAbreviadoException("No abrevie el nombre de la materia.");
        }

        // Validación de la longitud del nombre (máximo 12 caracteres)
        if (materiaDto.getNombre().length() > 12) {
            throw new NombreDeLaMateriaException("El nombre no puede ser tan largo.");
        }

        // Validación del año
        if (materiaDto.getAnio() <= 0) {
            throw new NombreDeLaMateriaException("El año debe ser mayor a 0.");
        }
    }
}
