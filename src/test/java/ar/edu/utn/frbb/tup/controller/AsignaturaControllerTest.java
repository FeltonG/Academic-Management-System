package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.AsignaturaService;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.EstadoAsignatura;
import ar.edu.utn.frbb.tup.model.dto.AsignaturaDto;
import ar.edu.utn.frbb.tup.controller.AsignaturaController;
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

public class AsignaturaControllerTest {

    @Mock
    private AsignaturaService asignaturaService;

    @InjectMocks
    private AsignaturaController asignaturaController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCrearAsignatura() {
        // Datos de entrada
        AsignaturaDto asignaturaDto = new AsignaturaDto();
        asignaturaDto.setNota(7);
        asignaturaDto.setIdalumno(1);
        asignaturaDto.setIdmateria(1);
        Asignatura asignatura = new Asignatura();
        asignatura.setNota(7);


        when(asignaturaService.crearAsignatura(asignaturaDto)).thenReturn(asignatura);


        ResponseEntity<Asignatura> response = asignaturaController.crearAsignatura(asignaturaDto);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(asignatura, response.getBody());
        verify(asignaturaService, times(1)).crearAsignatura(asignaturaDto);
    }

    @Test
    public void testObtenerAsignatura() {

        Integer idAsignatura = 1;
        Asignatura asignatura = new Asignatura();
        asignatura.setNota(7);
        asignatura.setId(1);
        asignatura.setEstado(EstadoAsignatura.CURSADA);

        when(asignaturaService.buscarAsignaturaId(idAsignatura)).thenReturn(asignatura);
        System.out.println(asignatura);


        ResponseEntity<Asignatura> response = asignaturaController.obtenerAsignatura(idAsignatura);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(asignatura, response.getBody());
        verify(asignaturaService, times(1)).buscarAsignaturaId(idAsignatura);
    }

    @Test
    public void testModificarAsignatura() {

        Integer idAsignatura = 1;
        AsignaturaDto asignaturaDto = new AsignaturaDto();
        asignaturaDto.setNota(8);
        Asignatura asignaturaModificada = new Asignatura();
        asignaturaModificada.setNota(8);

        when(asignaturaService.modificarAsignatura(idAsignatura, asignaturaDto)).thenReturn(asignaturaModificada);


        ResponseEntity<Asignatura> response = asignaturaController.modificarAsignatura(idAsignatura, asignaturaDto);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(asignaturaModificada, response.getBody());
        verify(asignaturaService, times(1)).modificarAsignatura(idAsignatura, asignaturaDto);
    }

    @Test
    public void testEliminarAsignatura() {

        Integer idAsignatura = 1;


        ResponseEntity<Void> response = asignaturaController.eliminarAsignatura(idAsignatura);


        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(asignaturaService, times(1)).borrarAsignaturaporid(idAsignatura);
    }
}
