package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Alumno;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AlumnoDao {

     public void guardarAlumno(Alumno alumno);

     public List<Alumno> buscarAlumnos();

     public Alumno buscarAlumnoporid(long id);

     public Alumno buscarAlumnoporDni(int Dni);

     public Alumno borrarAlumnoDNI(int Dni);

     public Alumno borrarAlumnoporid(long id);

     public Alumno modificarAlumno(Alumno alumno);

     public int obtenerUltimoId();

}
