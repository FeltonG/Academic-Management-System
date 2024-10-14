package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.EstadoAsignatura;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("alumno")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;


    @PostMapping
    public Alumno crearAlumno(@RequestBody AlumnoDto alumnoDto) {
        return alumnoService.crearAlumno(alumnoDto);
    }
    // Método para buscar un alumno por su ID
    @GetMapping("/{idAlumno}")
    public Alumno buscarAlumnoId(@PathVariable("idAlumno") Integer idAlumno) {
        return alumnoService.buscarAlumnoId(idAlumno);
    }
    // Modificar un alumno existente
    @PutMapping("/{idAlumno}")
    public Alumno modificarAlumno(@PathVariable("idAlumno") Integer idAlumno, @RequestBody AlumnoDto alumnoDto) {
        return alumnoService.modificarAlumno(idAlumno, alumnoDto);
    }

    // Eliminar un alumno
    @DeleteMapping("/{idAlumno}")
    public Alumno eliminarAlumno(@PathVariable("idAlumno") Integer idAlumno) {
        return alumnoService.borraralumnoId(idAlumno);
    }


}
