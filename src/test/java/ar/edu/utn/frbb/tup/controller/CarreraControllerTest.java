package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.CarreraService;
import ar.edu.utn.frbb.tup.business.ProfesorService;
import ar.edu.utn.frbb.tup.controller.CarreraController;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
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

public class CarreraControllerTest {

    @Mock
    private CarreraService carreraService;

    @Mock
    private ProfesorService profesorService;

    @InjectMocks
    private CarreraController carreraController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCrearCarrera() {
        CarreraDto carreraDto = new CarreraDto();
        Carrera nuevaCarrera = new Carrera();
        when(carreraService.crearCarrera(carreraDto)).thenReturn(nuevaCarrera);

        ResponseEntity<Carrera> response = carreraController.crearCarrera(carreraDto);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(nuevaCarrera, response.getBody());
        verify(carreraService, times(1)).crearCarrera(carreraDto);
    }


    @Test
    public void testBuscarCarreraId() {
        Integer idCarrera = 1;
        Carrera carrera = new Carrera();
        when(carreraService.buscarCarreraId(idCarrera)).thenReturn(carrera);

        Carrera resultado = carreraController.buscarCarreraId(idCarrera);

        assertNotNull(resultado);
        assertEquals(carrera, resultado);
        verify(carreraService, times(1)).buscarCarreraId(idCarrera);
    }


    @Test
    public void testModificarCarrera() {
        Integer idCarrera = 1;
        CarreraDto carreraDto = new CarreraDto();
        Carrera carreraModificada = new Carrera();
        when(carreraService.modificarCarrera(idCarrera, carreraDto)).thenReturn(carreraModificada);

        Carrera resultado = carreraController.modificarCarrera(idCarrera, carreraDto);

        assertNotNull(resultado);
        assertEquals(carreraModificada, resultado);
        verify(carreraService, times(1)).modificarCarrera(idCarrera, carreraDto);
    }


    @Test
    public void testEliminarProfesor() {
        Integer idProfesor = 1;
        Profesor profesor = new Profesor("Felipe","Garcia","licenciado");
        when(profesorService.borrarProfesorporid(idProfesor)).thenReturn(profesor);

        Profesor resultado = carreraController.eliminarProfesor(idProfesor);

        assertNotNull(resultado);
        assertEquals(profesor, resultado);
        verify(profesorService, times(1)).borrarProfesorporid(idProfesor);


    }
}
