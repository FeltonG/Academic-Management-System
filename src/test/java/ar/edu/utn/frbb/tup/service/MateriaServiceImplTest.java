package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.model.exception.MateriaNoEncontradaException;
import ar.edu.utn.frbb.tup.model.exception.MateriaYaExisteException;
import ar.edu.utn.frbb.tup.model.exception.ProfesorNoEncontradoException;
import ar.edu.utn.frbb.tup.persistence.MateriaDaoMemoryImpl;
import ar.edu.utn.frbb.tup.business.impl.MateriaServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearMateria() throws ProfesorNoEncontradoException, MateriaYaExisteException {
        MateriaDto materiaDto = new MateriaDto();
        materiaDto.setNombre("Matemáticas");
        materiaDto.setAnio(1);
        materiaDto.setCuatrimestre(1);
        materiaDto.setProfesorId(100L);
        materiaDto.setCorrelatividades(Collections.emptyList());

        Materia materia = new Materia(
                materiaDto.getNombre(),
                materiaDto.getAnio(),
                materiaDto.getCuatrimestre(),
                materiaDto.getProfesorId(),
                materiaDto.getCorrelatividades()
        );

       // when(materiaDaoMemoryImpl.guardarMateria(any(Materia.class))).thenReturn(materia);

        Materia resultado = materiaService.crearMateria(materiaDto);

        assertNotNull(resultado);
        assertEquals(materiaDto.getNombre(), resultado.getNombre());
        assertEquals(materiaDto.getAnio(), resultado.getAnio());
        assertEquals(materiaDto.getCuatrimestre(), resultado.getCuatrimestre());
        assertEquals(materiaDto.getProfesorId(), resultado.getIdprofesor());
        assertEquals(materiaDto.getCorrelatividades(), resultado.getCorrelatividades());
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
    public void testModificarMateria() throws MateriaNoEncontradaException, ProfesorNoEncontradoException, MateriaYaExisteException {
        long id = 1L;
        MateriaDto materiaModificada = new MateriaDto();
        materiaModificada.setNombre("Biología");
        materiaModificada.setAnio(3);
        materiaModificada.setCuatrimestre(1);
        materiaModificada.setProfesorId(103L);
        materiaModificada.setCorrelatividades(Collections.emptyList());

        Materia materiaExistente = new Materia("Química", 2, 2, 102L, Collections.emptyList());
        when(materiaDaoMemoryImpl.buscarMateriaId(id)).thenReturn(materiaExistente);
        when(materiaDaoMemoryImpl.modificarMateria(any(Materia.class))).thenReturn(materiaExistente);

        Materia resultado = materiaService.modificarMateria(id, materiaModificada);

        assertNotNull(resultado);
        assertEquals("Biología", resultado.getNombre());
        assertEquals(3, resultado.getAnio());
        assertEquals(1, resultado.getCuatrimestre());
        assertEquals(103L, resultado.getIdprofesor());
        verify(materiaDaoMemoryImpl, times(1)).modificarMateria(any(Materia.class));
    }

    @Test
    public void testBorrarmateriaId() throws MateriaNoEncontradaException {
        long id = 1L;
        Materia materiaExistente = new Materia("Filosofía", 2, 1, 104L, Collections.emptyList());

        when(materiaDaoMemoryImpl.buscarMateriaId(id)).thenReturn(materiaExistente);

        Materia resultado = materiaService.borrarmateriaId(id);

        assertNotNull(resultado);
        assertEquals("Filosofía", resultado.getNombre());
        verify(materiaDaoMemoryImpl, times(1)).borrarmateriaporid(id);
    }
}
