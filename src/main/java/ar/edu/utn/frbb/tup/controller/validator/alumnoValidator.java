package ar.edu.utn.frbb.tup.controller.validator;

import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;

public class alumnoValidator {

    // UN UNICO METODO QUE VALIDA TODA LA INFO DE UN ALUMNO DTO

    public void validarAlumno (AlumnoDto alumnoDto)
    {

        if(alumnoDto.getDni() == 0)
        {
            throw new IllegalArgumentException("El dni debe existir o ser distinto de 0");
        }

        String dniString = String.valueOf(alumnoDto.getDni());
        if(dniString.length()<7 || dniString.length()>9)
        {
            throw new IllegalArgumentException("El dni tiene que tener entre 7 y 9 digitos");
        }

        if(alumnoDto.getDni()<0)
        {
            throw new IllegalArgumentException("El dni tiene que ser positivo");
        }

        if( alumnoDto.getNombre() != null && alumnoDto.getApellido() !=null )
        {
            throw new IllegalArgumentException("Debe ingresar nombre y apellido");
        }
        if (alumnoDto.getNombre() !=null){

            throw new IllegalArgumentException("Debe ingresar nombre ");

        }



        if(!alumnoDto.getNombre().matches("^[A-Za-záéíóúÁÉÍÓÚÑñ ]+$"))
        {
            throw new IllegalArgumentException(" Utilice caracteres correctos.");
        }

        if(alumnoDto.getNombre().length()<=2)
        {
            throw new IllegalArgumentException(" no abrevies el nombre  ");
        }

        if(alumnoDto.getNombre().length()>12)
        {
            throw new IllegalArgumentException(" No se permiten nombre tan largo  ");
        }

        //apellido (faltan los dos que hizimos con el nombre)

        if(alumnoDto.getApellido() !=null){

            throw new IllegalArgumentException("Debe ingresar apellido ");
        }
        if(!alumnoDto.getApellido().matches("^[A-Za-záéíóúÁÉÍÓÚÑñ ]+$"))
        {
            throw new IllegalArgumentException(" Utilice caracteres correctos.");
        }

        if(alumnoDto.getApellido().length()<=2)
        {
            throw new IllegalArgumentException(" no abrevies el nombre  ");
        }
        if(alumnoDto.getApellido().length()>12)
        {
            throw new IllegalArgumentException(" No se permiten nombre tan largo  ");
        }




    }
}
