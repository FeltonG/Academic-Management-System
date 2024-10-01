package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Alumno;

public interface AlumnoDao {

     public void guardarAlumno(Alumno alumno);
     public Alumno buscarAlumnoporid(long id);
     public Alumno borrarAlumnoporid(long id);
     public Alumno borrarAlumnoDNI(int Dni);
     public Alumno buscarAlumnoporDni(int Dni);

}
