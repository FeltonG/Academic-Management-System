package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.business.AsignaturaService;
import ar.edu.utn.frbb.tup.controller.AlumnoController;
import ar.edu.utn.frbb.tup.controller.validator.alumnoValidator;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.dto.AsignaturaDto;
import ar.edu.utn.frbb.tup.model.exception.AlumnoYaExisteException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class AlumnoControllerTest {
    @InjectMocks
    private AlumnoController alumnoController;

    @Mock
    private AlumnoService alumnoService;

    @Mock
    private alumnoValidator alumValidator;
    @Mock
    private AsignaturaService asignaturaService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearAlumno() throws AlumnoYaExisteException {
        // Configurar datos de prueba
        AlumnoDto alumnoDto = new AlumnoDto();
        alumnoDto.setNombre("Juan Perez");
        alumnoDto.setDni(12345678);

        Alumno alumno = new Alumno();
        alumno.setNombre("Juan Perez");
        alumno.setApellido("Garcia");
        alumno.setDni(12345678);

        // Configurar comportamiento del validador
        doNothing().when(alumValidator).validarAlumno(alumnoDto);

        // Configurar comportamiento del servicio
        when(alumnoService.crearAlumno(alumnoDto)).thenReturn(alumno);

        // Ejecutar el método del controlador
        ResponseEntity<Alumno> response = alumnoController.crearAlumno(alumnoDto);

        // Verificaciones
        assertNotNull(response);
        assertEquals(ResponseEntity.status(201).build().getStatusCode(), response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Juan Perez", response.getBody().getNombre());
        assertEquals(12345678, response.getBody().getDni());

        // Verificar interacciones con el validador y el servicio
        verify(alumValidator, times(1)).validarAlumno(alumnoDto);
        verify(alumnoService, times(1)).crearAlumno(alumnoDto);

        System.out.println(alumno);
    }



    @Test
    public void testBuscarAlumnoId() {
        Integer idAlumno = 1;
        Alumno alumno = new Alumno();

        when(alumnoService.buscarAlumnoId(idAlumno)).thenReturn(alumno);

        Alumno result = alumnoController.buscarAlumnoId(idAlumno);

        assertNotNull(result);
        assertEquals(alumno, result);
        verify(alumnoService, times(1)).buscarAlumnoId(idAlumno);
    }

    @Test
    public void testModificarAlumno() {
        Integer idAlumno = 1;
        AlumnoDto alumnoDto = new AlumnoDto();
        Alumno alumno = new Alumno();

        when(alumnoService.modificarAlumno(idAlumno, alumnoDto)).thenReturn(alumno);

        Alumno result = alumnoController.modificarAlumno(idAlumno, alumnoDto);

        assertNotNull(result);
        assertEquals(alumno, result);
        verify(alumnoService, times(1)).modificarAlumno(idAlumno, alumnoDto);
    }

    @Test
    public void testModificarEstadoAsignatura() {
        Integer idAlumno = 1;
        Integer idAsignatura = 1;
        Asignatura asignatura = new Asignatura();

        when(asignaturaService.modificarEstadoAsignatura(idAlumno, idAsignatura)).thenReturn(asignatura);

        Asignatura result = alumnoController.modificarEstadoAsignatura(idAlumno, idAsignatura);

        assertNotNull(result);
        assertEquals(asignatura, result);
        verify(asignaturaService, times(1)).modificarEstadoAsignatura(idAlumno, idAsignatura);
    }

    @Test
    public void testEliminarAlumno() {
        Integer idAlumno = 1;
        Alumno alumno = new Alumno();

        when(alumnoService.borraralumnoId(idAlumno)).thenReturn(alumno);

        Alumno result = alumnoController.eliminarAlumno(idAlumno);

        assertNotNull(result);
        assertEquals(alumno, result);
        verify(alumnoService, times(1)).borraralumnoId(idAlumno);
    }
}
