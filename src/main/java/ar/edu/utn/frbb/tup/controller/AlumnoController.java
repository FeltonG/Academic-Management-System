package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
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


    @PostMapping("/")
    public ResponseEntity<Alumno> crearAlumno(@RequestBody AlumnoDto alumnoDto) {
        Alumno nuevoAlumno = alumnoService.crearAlumno(alumnoDto);
        return new ResponseEntity<>(nuevoAlumno, HttpStatus.CREATED);
    }

}
