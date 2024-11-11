package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.business.impl.CarreraServiceImpl;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.persistence.CarreraDaoMemoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CarreraServiceImplTest {

    @InjectMocks
    private CarreraServiceImpl carreraService;

    @Mock
    private CarreraDaoMemoryImpl carreraDaoMemoryImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearCarreraConDatosValidos() {
        CarreraDto carreraDto = new CarreraDto();
        carreraDto.setNombre("Ingeniería en Sistemas");

        Carrera carrera = new Carrera("Ingeniería en Sistemas");

         //when(carreraDaoMemoryImpl.guardarCarrera(any(Carrera.class))).thenReturn(carrera);

        Carrera resultado = carreraService.crearCarrera(carreraDto);

        assertNotNull(resultado);
        assertEquals(carreraDto.getNombre(), resultado.getNombre());
        verify(carreraDaoMemoryImpl, times(1)).guardarCarrera(any(Carrera.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearCarreraConNombreVacio() {
        CarreraDto carreraDto = new CarreraDto();
        carreraDto.setNombre("  ");

        carreraService.crearCarrera(carreraDto);

        // Verificar que se lanza la excepción y no se llama al método guardarCarrera
        verify(carreraDaoMemoryImpl, never()).guardarCarrera(any(Carrera.class));
    }

    @Test
    public void testBuscarCarreras() {
        List<Carrera> listaCarreras = new ArrayList<>();
        listaCarreras.add(new Carrera("Ingeniería en Sistemas"));
        listaCarreras.add(new Carrera("Licenciatura en Administración"));

        when(carreraDaoMemoryImpl.buscarCarrera()).thenReturn(listaCarreras);

        List<Carrera> resultado = carreraService.buscarCarreras();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(carreraDaoMemoryImpl, times(1)).buscarCarrera();
    }

    @Test
    public void testBuscarCarreraPorId() {
        long id = 1;
        Carrera carrera = new Carrera("Ingeniería en Sistemas");
        when(carreraDaoMemoryImpl.buscarCarreraporId(id)).thenReturn(carrera);

        Carrera resultado = carreraService.buscarCarreraId(id);

        assertNotNull(resultado);
        assertEquals("Ingeniería en Sistemas", resultado.getNombre());
        verify(carreraDaoMemoryImpl, times(1)).buscarCarreraporId(id);
    }

    @Test
    public void testModificarCarreraExistente() {
        long id = 1;
        CarreraDto carreraDto = new CarreraDto();
        carreraDto.setNombre("Ingeniería en Software");

        Carrera carreraExistente = new Carrera("Ingeniería en Sistemas");
        when(carreraDaoMemoryImpl.buscarCarreraporId(id)).thenReturn(carreraExistente);

        Carrera resultado = carreraService.modificarCarrera(id, carreraDto);

        assertNotNull(resultado);
        assertEquals("Ingeniería en Software", resultado.getNombre());
        verify(carreraDaoMemoryImpl, times(1)).modificarCarrera(carreraExistente);
    }

    @Test
    public void testModificarCarreraNoExistente() {
        long id = 1;
        CarreraDto carreraDto = new CarreraDto();
        carreraDto.setNombre("Ingeniería en Software");

        when(carreraDaoMemoryImpl.buscarCarreraporId(id)).thenReturn(null);

        Carrera resultado = carreraService.modificarCarrera(id, carreraDto);

        assertNull(resultado);
        verify(carreraDaoMemoryImpl, never()).modificarCarrera(any(Carrera.class));
    }

    @Test
    public void testBorrarCarreraPorIdExistente() {
        long id = 1;
        Carrera carreraExistente = new Carrera("Ingeniería en Sistemas");
        when(carreraDaoMemoryImpl.buscarCarreraporId(id)).thenReturn(carreraExistente);

        Carrera resultado = carreraService.borrarCarreraporid(id);

        assertNotNull(resultado);
        assertEquals("Ingeniería en Sistemas", resultado.getNombre());
        verify(carreraDaoMemoryImpl, times(1)).borrarCarreraporid(id);
    }

    @Test
    public void testBorrarCarreraPorIdNoExistente() {
        long id = 1;
        when(carreraDaoMemoryImpl.buscarCarreraporId(id)).thenReturn(null);

        Carrera resultado = carreraService.borrarCarreraporid(id);

        assertNull(resultado);
        verify(carreraDaoMemoryImpl, never()).borrarCarreraporid(id);
    }
}

