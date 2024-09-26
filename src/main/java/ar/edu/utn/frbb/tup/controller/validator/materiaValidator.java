package ar.edu.utn.frbb.tup.controller.validator;

import ar.edu.utn.frbb.tup.model.dto.MateriaDto;

public class materiaValidator {

    public void materiaValidator(MateriaDto materiaDto)
    {

        if( materiaDto.getNombre() != null ){
            throw new IllegalArgumentException("debe ingresar materia");
        }

        if (!materiaDto.getNombre().matches("^[A-Za-záéíóúÁÉÍÓÚÑñ ]+$"))
        {
            throw new IllegalArgumentException(" Utilice caracteres correctos.");
        }
        if(materiaDto.getNombre().length()<=2)
        {
            throw new IllegalArgumentException("no abrevies la materia");
        }
        if(materiaDto.getNombre().length()>12)
        {
            throw new IllegalArgumentException(" No se permiten nombre tan largo  ");
        }

        if (materiaDto.getAnio()==0)
        {
            throw new IllegalArgumentException("el anio debe ser mayor a 0");
        }



    }
}
