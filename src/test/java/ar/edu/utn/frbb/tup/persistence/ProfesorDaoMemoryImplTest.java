package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Profesor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProfesorDaoMemoryImplTest {

    @Mock
    private ProfesorDaoMemoryImpl profesorDao;

    private Profesor profesor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        // Configuración básica del profesor para pruebas
        profesor = new Profesor(1L, "Juan", "Perez", "Licenciado en Física");
    }

    @Test
    public void testGuardarProfesor() {
        // Simulamos la guardado de un profesor (escribiendo en el archivo)
        doNothing().when(profesorDao).guardarProfesor(any(Profesor.class));

        // Llamamos al método
        profesorDao.guardarProfesor(profesor);

        // Verificamos que el método fue llamado
        verify(profesorDao, times(1)).guardarProfesor(any(Profesor.class));
    }

    @Test
    public void testBuscarProfesorporId() throws IOException {
        // Creamos una respuesta mock que simula encontrar el profesor
        when(profesorDao.buscarProfesorporid(1L)).thenReturn(profesor);

        // Llamamos al método
        Profesor encontrado = profesorDao.buscarProfesorporid(1L);

        // Verificamos que el profesor es el correcto
        assertNotNull(encontrado);
        assertEquals("Juan", encontrado.getNombre());
        assertEquals("Perez", encontrado.getApellido());
        assertEquals("Licenciado en Física", encontrado.getTitulo());
    }



    @Test
    public void testModificarProfesor() throws IOException {
        // Modificamos el profesor
        profesor.setNombre("Carlos");
        profesor.setApellido("Gonzalez");

        // Simulamos que el profesor fue modificado correctamente
        when(profesorDao.modificarProfesor(any(Profesor.class))).thenReturn(profesor);

        // Llamamos al método
        Profesor modificado = profesorDao.modificarProfesor(profesor);

        // Verificamos que el nombre y apellido se hayan modificado
        assertNotNull(modificado);
        assertEquals("Carlos", modificado.getNombre());
        assertEquals("Gonzalez", modificado.getApellido());

        // Verificamos que el método fue llamado correctamente
        verify(profesorDao, times(1)).modificarProfesor(any(Profesor.class));
    }

    @Test
    public void testObtenerUltimoId() throws IOException {
        // Simulamos que el último ID es 5
        when(profesorDao.obtenerUltimoId()).thenReturn(5);

        // Llamamos al método
        int ultimoId = profesorDao.obtenerUltimoId();

        // Verificamos que el último ID es el esperado
        assertEquals(5, ultimoId);
    }
}
