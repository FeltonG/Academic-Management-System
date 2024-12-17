package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.business.impl.ProfesorServiceImpl;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.dto.ProfesorDto;
import ar.edu.utn.frbb.tup.model.exception.MateriaNoEncontradaException;
import ar.edu.utn.frbb.tup.model.exception.ProfesorNoEncontradoException;
import ar.edu.utn.frbb.tup.model.exception.ProfesorYaExisteException;
import ar.edu.utn.frbb.tup.persistence.MateriaDaoMemoryImpl;
import ar.edu.utn.frbb.tup.persistence.ProfesorDaoMemoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProfesorServiceImplTest {

    @InjectMocks
    private ProfesorServiceImpl profesorService;

    @Mock
    private ProfesorDaoMemoryImpl profesorDaoMemoryimpl;

    @Mock
    private MateriaDaoMemoryImpl materiaDaoMemoryimpl;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearProfesor() throws ProfesorYaExisteException {
        ProfesorDto profesorDto = new ProfesorDto();
        profesorDto.setNombre("Juan");
        profesorDto.setApellido("Pérez");
        profesorDto.setTitulo("Ingeniero");

        Profesor profesor = new Profesor(profesorDto.getNombre(), profesorDto.getApellido(), profesorDto.getTitulo());

       // when(profesorDaoMemoryimpl.guardarProfesor(any(Profesor.class))).thenReturn(profesor);

        Profesor resultado = profesorService.crearProfesor(profesorDto);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        assertEquals("Pérez", resultado.getApellido());
        assertEquals("Ingeniero", resultado.getTitulo());
        verify(profesorDaoMemoryimpl, times(1)).guardarProfesor(any(Profesor.class));
    }

    @Test
    public void testBorrarProfesor() throws ProfesorNoEncontradoException {
        long id = 1L;
        Profesor profesor = new Profesor("Juan", "Pérez", "Ingeniero");

        when(profesorDaoMemoryimpl.buscarProfesorporid(id)).thenReturn(profesor);

        Profesor resultado = profesorService.borrarProfesorporid(id);

        assertNotNull(resultado);
        assertEquals(profesor, resultado);
        verify(profesorDaoMemoryimpl, times(1)).borrarProfesorporid(id);
    }

    @Test
    public void testBuscarProfesores() {
        List<Profesor> listaProfesores = new ArrayList<>();
        listaProfesores.add(new Profesor("Juan", "Pérez", "Ingeniero"));
        listaProfesores.add(new Profesor("María", "García", "Licenciada"));

        when(profesorDaoMemoryimpl.buscarProfesores()).thenReturn(listaProfesores);

        List<Profesor> resultado = profesorService.buscarProfesores();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(profesorDaoMemoryimpl, times(1)).buscarProfesores();
    }

    @Test
    public void testBuscaProfesorporid() {
        long id = 1L;
        Profesor profesor = new Profesor("Juan", "Pérez", "Ingeniero");

        when(profesorDaoMemoryimpl.buscarProfesorporid(id)).thenReturn(profesor);

        Profesor resultado = profesorService.buscaProfesorporid(id);

        assertNotNull(resultado);
        assertEquals(profesor, resultado);
        verify(profesorDaoMemoryimpl, times(1)).buscarProfesorporid(id);
    }

    @Test
    public void testModificarProfesor() throws ProfesorNoEncontradoException {
        long id = 1L;
        ProfesorDto profesorDto = new ProfesorDto();
        profesorDto.setNombre("Carlos");
        profesorDto.setApellido("Ramírez");
        profesorDto.setTitulo("Doctor");

        Profesor profesorExistente = new Profesor("Juan", "Pérez", "Ingeniero");

        when(profesorDaoMemoryimpl.buscarProfesorporid(id)).thenReturn(profesorExistente);

        Profesor resultado = profesorService.modificarProfesor(id, profesorDto);

        assertNotNull(resultado);
        assertEquals("Carlos", resultado.getNombre());
        assertEquals("Ramírez", resultado.getApellido());
        assertEquals("Doctor", resultado.getTitulo());
        verify(profesorDaoMemoryimpl, times(1)).modificarProfesor(profesorExistente);
    }

    @Test
    public void testBuscarMateriasPorProfesorId() throws ProfesorNoEncontradoException, MateriaNoEncontradaException {
        long idProfesor = 1L;

        // Crear materias con nombres específicos
        Materia materia1 = new Materia();
        materia1.setNombre("Matemáticas");

        Materia materia2 = new Materia();
        materia2.setNombre("Física");

        List<Materia> listaMaterias = new ArrayList<>();
        listaMaterias.add(materia1);
        listaMaterias.add(materia2);

        // Simular el comportamiento del método buscarMateriasPorProfesorId
        when(materiaDaoMemoryimpl.buscarMateriasPorProfesorId(idProfesor)).thenReturn(listaMaterias);

        // Ejecutar el método que se quiere probar
        List<Materia> resultado = profesorService.buscarMateriasPorProfesorId(idProfesor);

        // Validar el resultado
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Física", resultado.get(0).getNombre()); // Materias están ordenadas alfabéticamente
        assertEquals("Matemáticas", resultado.get(1).getNombre());

        // Verificar que el método del DAO se llamó una vez
        verify(materiaDaoMemoryimpl, times(1)).buscarMateriasPorProfesorId(idProfesor);
    }

}
