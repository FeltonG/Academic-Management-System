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

    // Crear una nueva materia
    @PostMapping
    public Materia crearMateria(@RequestBody MateriaDto materiaDto) {
        return materiaService.crearMateria(materiaDto);
    }

    // Obtener una materia por su ID
    @GetMapping("/{idMateria}")
    public Materia buscarMateriaId(@PathVariable("idMateria") Integer idMateria) {
        return materiaService.buscarmateriaId(idMateria);
    }

    // Modificar una materia existente
    @PutMapping("/{idMateria}")
    public Materia modificarMateria(@PathVariable("idMateria") Integer idMateria, @RequestBody MateriaDto materiaDto) {
        return materiaService.modificarMateria(idMateria, materiaDto);
    }

    // Eliminar una materia
    @DeleteMapping("/{idMateria}")
    public Materia eliminarMateria(@PathVariable("idMateria") Integer idMateria) {
        return materiaService.borrarmateriaId(idMateria);
    }
}
