package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.model.exception.MateriaNoEncontradaException;
import ar.edu.utn.frbb.tup.model.exception.MateriaYaExisteException;
import ar.edu.utn.frbb.tup.model.exception.ProfesorNoEncontradoException;
import ar.edu.utn.frbb.tup.persistence.MateriaDaoMemoryImpl;
import ar.edu.utn.frbb.tup.business.impl.MateriaServiceImpl;
import ar.edu.utn.frbb.tup.persistence.ProfesorDaoMemoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class MateriaServiceImplTest {

    @InjectMocks
    private MateriaServiceImpl materiaService;

    @Mock
    private MateriaDaoMemoryImpl materiaDaoMemoryImpl;
    @Mock
    private ProfesorDaoMemoryImpl profesorDaoMemory;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearMateria_Success() throws ProfesorNoEncontradoException, MateriaYaExisteException {
        // Configurar DTO de Materia
        MateriaDto materiaDto = new MateriaDto();
        materiaDto.setNombre("Matemáticas");
        materiaDto.setAnio(1);
        materiaDto.setCuatrimestre(1);
        materiaDto.setProfesorId(1L);
        materiaDto.setCorrelatividades(List.of(2L));

        // Configurar mocks
        when(profesorDaoMemory.buscarProfesorporid(1L)).thenReturn(new Profesor("Felipe", "Garcia", "Tecnico"));
        when(materiaDaoMemoryImpl.buscarMaterias()).thenReturn(new ArrayList<>());

        // Ejecutar el método
        Materia resultado = materiaService.crearMateria(materiaDto);

        // Verificar resultado
        assertNotNull(resultado);
        assertEquals("Matemáticas", resultado.getNombre());
        verify(materiaDaoMemoryImpl, times(1)).guardarMateria(any(Materia.class));
    }

    @Test
    public void testBuscarMateria() {
        Materia materia = new Materia("Física", 1, 1, 101L, Collections.emptyList());
        when(materiaDaoMemoryImpl.buscarMaterias()).thenReturn(Arrays.asList(materia));

        List<Materia> resultado = materiaService.buscarMateria();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Física", resultado.get(0).getNombre());
        verify(materiaDaoMemoryImpl, times(1)).buscarMaterias();
    }

    @Test
    public void testBuscarmateriaId() {
        long id = 1L;
        Materia materia = new Materia("Química", 2, 2, 102L, Collections.emptyList());
        when(materiaDaoMemoryImpl.buscarMateriaId(id)).thenReturn(materia);

        Materia resultado = materiaService.buscarmateriaId(id);

        assertNotNull(resultado);
        assertEquals("Química", resultado.getNombre());
        assertEquals(2, resultado.getAnio());
        assertEquals(2, resultado.getCuatrimestre());
        verify(materiaDaoMemoryImpl, times(1)).buscarMateriaId(id);
    }

    @Test
    public void testModificarMateria_Success() throws MateriaNoEncontradaException, ProfesorNoEncontradoException, MateriaYaExisteException {
        // Simular datos existentes y DTO modificado
        Materia materiaExistente = new Materia("Matemáticas", 1, 1, 1L, List.of());
        MateriaDto materiaModificada = new MateriaDto();
        materiaModificada.setNombre("Álgebra");
        materiaModificada.setAnio(2);
        materiaModificada.setCuatrimestre(2);
        materiaModificada.setProfesorId(1L);
        materiaModificada.setCorrelatividades(List.of(3L));

        when(materiaDaoMemoryImpl.buscarMateriaId(1L)).thenReturn(materiaExistente);
        when(profesorDaoMemory.buscarProfesorporid(2L)).thenReturn(new Profesor("Felipe", "Garcia", "Tecnico"));
        when(materiaDaoMemoryImpl.buscarMaterias()).thenReturn(new ArrayList<>());

        // Ejecutar el método
        Materia resultado = materiaService.modificarMateria(1L, materiaModificada);

        // Verificar resultado
        assertNotNull(resultado);
        assertEquals("Álgebra", resultado.getNombre());
        verify(materiaDaoMemoryImpl, times(1)).modificarMateria(any(Materia.class));
    }



    @Test(expected = MateriaNoEncontradaException.class)
    public void testBorrarMateriaId_NoEncontrada() throws MateriaNoEncontradaException {
        // Simular que no se encuentra la materia
        when(materiaDaoMemoryImpl.buscarMateriaId(1L)).thenReturn(null);

        // Ejecutar el método
        materiaService.borrarmateriaId(1L);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testBuscarMateriaId_NoEncontrada() {
        // Simular que no se encuentra la materia
        when(materiaDaoMemoryImpl.buscarMateriaId(1L)).thenReturn(null);

        // Ejecutar el método
        materiaService.buscarmateriaId(1L);
    }
    @Test(expected = MateriaYaExisteException.class)
    public void testCrearMateria_Duplicada() throws ProfesorNoEncontradoException, MateriaYaExisteException {
        // Simular una materia que ya existe
        MateriaDto materiaDto = new MateriaDto();
        materiaDto.setNombre("Matemáticas");
        materiaDto.setAnio(1);
        materiaDto.setCuatrimestre(1);
        materiaDto.setProfesorId(1L);
        materiaDto.setCorrelatividades(List.of(2L));

        when(materiaDaoMemoryImpl.buscarMaterias()).thenReturn(Arrays.asList(new Materia("Matemáticas", 1, 1, 1L, Collections.emptyList())));

        // Ejecutar el método
        materiaService.crearMateria(materiaDto);
    }

}
