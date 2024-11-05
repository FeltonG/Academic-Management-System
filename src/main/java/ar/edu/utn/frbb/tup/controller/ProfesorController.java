package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.AsignaturaService;
import ar.edu.utn.frbb.tup.business.ProfesorService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.dto.ProfesorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    /*public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }*/

    // Crear un nuevo profesor
    @PostMapping
    public ResponseEntity<Profesor> crearProfesor(@RequestBody ProfesorDto profesorDto) {
        Profesor nuevoProfesor = profesorService.crearProfesor(profesorDto);
        return new ResponseEntity<>(nuevoProfesor, HttpStatus.CREATED);
    }

    // Obtener un profesor por su ID
   /* @GetMapping("/{idProfesor}")
    public ResponseEntity<Profesor> obtenerProfesorPorId(@PathVariable("idProfesor") Integer idProfesor) {
        return profesorService.buscaProfesorporid(idProfesor);

    }*/


    @GetMapping("/{idProfesor}")
    public Profesor buscarProfesorPorId(@PathVariable("idProfesor") Integer idProfesor) {
        return profesorService.buscaProfesorporid(idProfesor);
    }

    @GetMapping("/materias/{idProfesor}")
    public List<Materia> buscarMateriasPorProefesorId(@PathVariable("idProfesor") Integer idProfesor) {
        return profesorService.buscarMateriasPorProfesorId(idProfesor);
    }

    // Modificar un profesor existente
    @PutMapping("/{idProfesor}")
    public Profesor modificarProfesor(@PathVariable("idProfesor") Integer idProfesor, @RequestBody ProfesorDto profesorDto) {
       return  profesorService.modificarProfesor(idProfesor, profesorDto);
    }




}

