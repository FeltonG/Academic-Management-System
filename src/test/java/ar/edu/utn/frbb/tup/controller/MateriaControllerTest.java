package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MateriaControllerTest {

    @InjectMocks
    private MateriaController materiaController;

    @Mock
    private MateriaService materiaService;

    private MateriaDto materiaDto;
    private Materia materia;
    private List<Materia> materias;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);


        materiaDto = new MateriaDto();
        materiaDto.setNombre("Matemáticas");


        materia = new Materia();
        materia.setId(1);
        materia.setNombre("Matemáticas");


        materias = Arrays.asList(new Materia(), new Materia());
    }

    @Test
    public void testCrearMateria() {

        when(materiaService.crearMateria(materiaDto)).thenReturn(materia);


        Materia response = materiaController.crearMateria(materiaDto);


        assertNotNull(response);
        assertEquals("Matemáticas", response.getNombre());
    }

    @Test
    public void testBuscarMateriaId() {

        when(materiaService.buscarmateriaId(1)).thenReturn(materia);


        Materia response = materiaController.buscarMateriaId(1);


        assertNotNull(response);
        assertEquals("Matemáticas", response.getNombre());
    }

    @Test
    public void testModificarMateria() {

        when(materiaService.modificarMateria(1, materiaDto)).thenReturn(materia);


        Materia response = materiaController.modificarMateria(1, materiaDto);

        assertNotNull(response);
        assertEquals("Matemáticas", response.getNombre());
    }

    @Test
    public void testEliminarMateria() {

        when(materiaService.borrarmateriaId(1)).thenReturn(materia);


        Materia response = materiaController.eliminarMateria(1);


        assertNotNull(response);
        assertEquals("Matemáticas", response.getNombre());

    }
}
