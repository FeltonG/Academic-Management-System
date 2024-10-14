package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("materia")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;


    @PostMapping
    public Materia crearMateria(@RequestBody MateriaDto materiaDto) {
        return materiaService.crearMateria(materiaDto);
    }

    @GetMapping("/{idMateria}")
    public Materia buscarmateriaId(@PathVariable Integer idMateria)  {
        return materiaService.buscarmateriaId(idMateria);
    }

    // Modificar un alumno existente
    @PutMapping("/{idMateria}")
    public Materia modificarMateria(@PathVariable("idmateria") Integer idmateria, @RequestBody MateriaDto materiaDto) {
        return materiaService.modificarMateria(idmateria, materiaDto);
    }

    // Eliminar un alumno
    @DeleteMapping("/{idMateria}")
    public Materia eliminarAlumno(@PathVariable("idMateria") Integer idmateria) {
        return materiaService.borrarmateriaId(idmateria);
    }
}
