package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.business.AsignaturaService;
import ar.edu.utn.frbb.tup.controller.AlumnoController;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.dto.AsignaturaDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class AlumnoControllerTest {

    @Mock
    private AlumnoService alumnoService;

    @Mock
    private AsignaturaService asignaturaService;

    @InjectMocks
    private AlumnoController alumnoController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearAlumno() {
        AlumnoDto alumnoDto = new AlumnoDto();
        Alumno alumno = new Alumno();

        when(alumnoService.crearAlumno(alumnoDto)).thenReturn(alumno);

        ResponseEntity<Alumno> response = alumnoController.crearAlumno(alumnoDto);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(alumno, response.getBody());
        verify(alumnoService, times(1)).crearAlumno(alumnoDto);
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
