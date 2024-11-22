package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.ProfesorService;
import ar.edu.utn.frbb.tup.controller.validator.profesorValidator;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.dto.ProfesorDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProfesorControllerTest {

    @InjectMocks
    private ProfesorController profesorController;

    @Mock
    private ProfesorService profesorService;
    @Mock
    private profesorValidator profesorValidator;

    private ProfesorDto profesorDto;
    private Profesor profesor;
    private List<Materia> materias;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);


        profesorDto = new ProfesorDto();
        profesorDto.setNombre("Juan");
        profesorDto.setApellido("Perez");


        profesor = new Profesor("Felipe","Garcia","Licenciado");
        profesor.setId(1);
        profesor.setNombre("Felipe");
        profesor.setApellido("Garcia");
        profesor.setTitulo("Licenciado");


        materias = Arrays.asList(new Materia(), new Materia());
    }

    @Test
    public void testCrearProfesor() {
        // Configurar el comportamiento del validador
        doNothing().when(profesorValidator).profesorValidation(profesorDto);

        // Configurar el comportamiento del servicio
        when(profesorService.crearProfesor(profesorDto)).thenReturn(profesor);

        // Ejecutar el método del controlador
        ResponseEntity<Profesor> response = profesorController.crearProfesor(profesorDto);

        // Verificaciones
        assertNotNull(response);  // Verificar que la respuesta no sea nula
        assertEquals(HttpStatus.CREATED, response.getStatusCode());  // Verificar que el código de estado sea 201
        assertEquals(profesor, response.getBody());  // Verificar que el profesor creado sea el mismo que el esperado

        // Verificar interacciones con el validador y el servicio
        verify(profesorValidator, times(1)).profesorValidation(profesorDto);  // Verificar que el validador fue invocado una vez
        verify(profesorService, times(1)).crearProfesor(profesorDto);  // Verificar que el servicio fue invocado una vez
    }

    @Test
    public void testBuscarProfesorPorId() {

        when(profesorService.buscaProfesorporid(1)).thenReturn(profesor);


        Profesor response = profesorController.buscarProfesorPorId(1);


        assertNotNull(response);
        assertEquals("Felipe", response.getNombre());
        assertEquals("Garcia", response.getApellido());
    }

    @Test
    public void testBuscarMateriasPorProfesorId() {

        when(profesorService.buscarMateriasPorProfesorId(1)).thenReturn(materias);


        List<Materia> response = profesorController.buscarMateriasPorProefesorId(1);


        assertNotNull(response);
        assertEquals(2, response.size());
    }

    @Test
    public void testModificarProfesor() {

        when(profesorService.modificarProfesor(1, profesorDto)).thenReturn(profesor);


        Profesor response = profesorController.modificarProfesor(1, profesorDto);


        assertNotNull(response);
        assertEquals("Felipe", response.getNombre());
        assertEquals("Garcia", response.getApellido());
    }
}
