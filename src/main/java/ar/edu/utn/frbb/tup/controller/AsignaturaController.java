package ar.edu.utn.frbb.tup.controller;
import ar.edu.utn.frbb.tup.business.AsignaturaService;
import ar.edu.utn.frbb.tup.controller.validator.AsignaturaValidator;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.dto.AsignaturaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("asignatura")
public class AsignaturaController {
    @Autowired
    private AsignaturaService asignaturaService;
    @Autowired
    private AsignaturaValidator asignaturaValidator;


    @PostMapping
    public ResponseEntity<Asignatura> crearAsignatura(@RequestBody AsignaturaDto asignaturaDto) {


        asignaturaValidator.validarAsignatura(asignaturaDto);
        Asignatura nuevaAsignatura = asignaturaService.crearAsignatura(asignaturaDto);
        return new ResponseEntity<>(nuevaAsignatura, HttpStatus.CREATED);
    }

    // Obtener una asignatura por su ID
    @GetMapping("/{idAsignatura}")
    public ResponseEntity<Asignatura> obtenerAsignatura(@PathVariable("idAsignatura") Integer idAsignatura) {
        Asignatura asignatura = asignaturaService.buscarAsignaturaId(idAsignatura);
        return ResponseEntity.ok(asignatura);
    }

    // Modificar una asignatura existente
    @PutMapping("/{idAsignatura}")
    public ResponseEntity<Asignatura> modificarAsignatura(
            @PathVariable("idAsignatura") Integer idAsignatura,
            @RequestBody AsignaturaDto asignaturaDto) {

        Asignatura asignaturaModificada = asignaturaService.modificarAsignatura(idAsignatura, asignaturaDto);
        return ResponseEntity.ok(asignaturaModificada);
    }

    // Eliminar una asignatura
    @DeleteMapping("/{idAsignatura}")
    public ResponseEntity<Void> eliminarAsignatura(@PathVariable("idAsignatura") Integer idAsignatura) {
        asignaturaService.borrarAsignaturaporid(idAsignatura);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
