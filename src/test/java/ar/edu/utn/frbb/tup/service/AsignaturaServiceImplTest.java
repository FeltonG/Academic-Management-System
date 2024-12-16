package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.business.impl.AsignaturaServiceImpl;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.EstadoAsignatura;
import ar.edu.utn.frbb.tup.model.dto.AsignaturaDto;
import ar.edu.utn.frbb.tup.model.exception.AsignaturaYaExisteException;
import ar.edu.utn.frbb.tup.persistence.AsignaturaDaoMemoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AsignaturaServiceImplTest {

    @InjectMocks
    private AsignaturaServiceImpl asignaturaService;

    @Mock
    private AsignaturaDaoMemoryImpl asignaturaDaoMemoryImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCrearAsignaturaConDatosValidos() throws AsignaturaYaExisteException {
        AsignaturaDto asignaturaDto = new AsignaturaDto();
        asignaturaDto.setNota(7);
        asignaturaDto.setIdalumno(1);
        asignaturaDto.setIdmateria(1);
        asignaturaDto.setEstado(EstadoAsignatura.NO_CURSADA);

        Asignatura asignatura = new Asignatura(
                asignaturaDto.getEstado(),
                asignaturaDto.getNota(),
                asignaturaDto.getIdalumno(),
                asignaturaDto.getIdmateria()
        );

        Asignatura resultado = asignaturaService.crearAsignatura(asignaturaDto);

        assertNotNull(resultado);
        assertEquals(asignaturaDto.getNota(), Optional.of(resultado.getNota()).orElse(null));
        assertEquals(asignaturaDto.getIdalumno(), resultado.getIdalumno());
        assertEquals(asignaturaDto.getIdmateria(), resultado.getIdmateria());
        verify(asignaturaDaoMemoryImpl, times(1)).guardarAsignatura(any(Asignatura.class));
    }


    @Test
    public void testCrearAsignaturaConNotaNula() {
        AsignaturaDto asignaturaDto = new AsignaturaDto();
        asignaturaDto.setIdalumno(1);
        asignaturaDto.setIdmateria(1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                asignaturaService.crearAsignatura(asignaturaDto)
        );
        assertEquals("La nota no puede ser nula", exception.getMessage());
    }

    @Test
    public void testBuscarAsignaturaId() {
        long id = 1;
        Asignatura asignatura = new Asignatura();
        asignatura.setNota(8);

        when(asignaturaDaoMemoryImpl.buscarAsignaturaporId(id)).thenReturn(asignatura);

        Asignatura resultado = asignaturaService.buscarAsignaturaId(id);

        assertNotNull(resultado);
        assertEquals(asignatura.getNota(), resultado.getNota());
        verify(asignaturaDaoMemoryImpl, times(1)).buscarAsignaturaporId(id);
    }

    @Test
    public void testBorrarAsignaturaporId() {
        long id = 1;
        Asignatura asignatura = new Asignatura(EstadoAsignatura.APROBADA, 7, 1, 1);

        when(asignaturaDaoMemoryImpl.borrarAsignaturaporid(id)).thenReturn(asignatura);

        Asignatura resultado = asignaturaService.borrarAsignaturaporid(id);

        assertNotNull(resultado);
        assertEquals(asignatura, resultado);
        verify(asignaturaDaoMemoryImpl, times(2)).borrarAsignaturaporid(id);

    }

    @Test
    public void testModificarAsignaturaExistente() {
        long id = 1;
        AsignaturaDto asignaturaDto = new AsignaturaDto();
        asignaturaDto.setNota(8);
        asignaturaDto.setEstado(EstadoAsignatura.APROBADA);
        asignaturaDto.setIdalumno(1);
        asignaturaDto.setIdmateria(1);

        Asignatura asignaturaExistente = new Asignatura(EstadoAsignatura.APROBADA, 7, 1, 1);

        when(asignaturaDaoMemoryImpl.buscarAsignaturaporId(id)).thenReturn(asignaturaExistente);
        when(asignaturaDaoMemoryImpl.modificarAsignatura(any(Asignatura.class))).thenReturn(asignaturaExistente);

        Asignatura resultado = asignaturaService.modificarAsignatura(id, asignaturaDto);

        assertNotNull(resultado);
        assertEquals(Optional.of(resultado.getNota()), Optional.ofNullable(asignaturaDto.getNota()));
        assertEquals(asignaturaDto.getEstado(), resultado.getEstado());
        verify(asignaturaDaoMemoryImpl, times(1)).modificarAsignatura(asignaturaExistente);
    }

    @Test
    public void testModificarEstadoAsignatura() {
        long idAlumno = 1;
        long idAsignatura = 1;
        Asignatura asignatura = new Asignatura(EstadoAsignatura.APROBADA, 7, idAlumno, idAsignatura);

        when(asignaturaDaoMemoryImpl.buscarAsignaturaporIdAsignaturaIdAlumno(idAsignatura, idAlumno)).thenReturn(asignatura);

        Asignatura resultado = asignaturaService.modificarEstadoAsignatura(idAlumno, idAsignatura);

        assertNotNull(resultado);
        assertEquals(EstadoAsignatura.APROBADA, resultado.getEstado());
        verify(asignaturaDaoMemoryImpl, times(1)).modificarAsignatura(asignatura);
    }
}
