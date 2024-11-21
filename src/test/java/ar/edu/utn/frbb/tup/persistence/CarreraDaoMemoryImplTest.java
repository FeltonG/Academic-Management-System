package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Carrera;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

import static org.junit.Assert.*;


public class CarreraDaoMemoryImplTest {

    @InjectMocks
    private CarreraDaoMemoryImpl carreraDao;

    private static final String TEST_CSV_FILE_PATH = "C:/Users/Felipe/IdeaProjects/LABORATORIO3/src/main/java/ar/edu/utn/frbb/tup/persistence/dataCSV/carreraDATA.csv";

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        carreraDao = new CarreraDaoMemoryImpl();
    }

    @Test
    public void testGuardarCarrera() {
        Carrera carrera = new Carrera(1, "Ingeniería en Sistemas");
        carreraDao.guardarCarrera(carrera);

        List<Carrera> carreras = carreraDao.buscarCarrera();
        assertNotNull(carreras);
        assertTrue(carreras.stream().anyMatch(c -> c.getId() == carrera.getId() && c.getNombre().equals(carrera.getNombre())));
    }

    @Test
    public void testBuscarCarrera() {
        Carrera carrera1 = new Carrera(1, "Ingeniería Civil");
        Carrera carrera2 = new Carrera(2, "Ingeniería Industrial");

        carreraDao.guardarCarrera(carrera1);
        carreraDao.guardarCarrera(carrera2);

        List<Carrera> carreras = carreraDao.buscarCarrera();


    }

    @Test
    public void testBuscarCarreraPorId() {
        Carrera carrera = new Carrera(3, "Ingeniería Química");
        carreraDao.guardarCarrera(carrera);

        Carrera foundCarrera = carreraDao.buscarCarreraporId(3);

        assertNotNull(foundCarrera);
        assertEquals("abogado", foundCarrera.getNombre());
    }

    @Test
    public void testBuscarCarreraPorIdNotFound() {
        Carrera foundCarrera = carreraDao.buscarCarreraporId(99);
        assertNull(foundCarrera);
    }

    @Test
    public void testBorrarCarreraPorId() {
        Carrera carrera = new Carrera(4, "Ingeniería Mecánica");
        carreraDao.guardarCarrera(carrera);

        Carrera deletedCarrera = carreraDao.borrarCarreraporid(4);

        assertNotNull(deletedCarrera);
        assertEquals("Ingeniería Mecánica", deletedCarrera.getNombre());

        assertNull(carreraDao.buscarCarreraporId(4));
    }

    @Test
    public void testModificarCarrera() {
        Carrera carrera = new Carrera(5, "Ingeniería Eléctrica");
        carreraDao.guardarCarrera(carrera);

        Carrera carreraModificada = new Carrera(5, "Ingeniería Electrónica");
        Carrera updatedCarrera = carreraDao.modificarCarrera(carreraModificada);

        assertNotNull(updatedCarrera);
        assertEquals("Ingeniería Electrónica", updatedCarrera.getNombre());
    }

    @Test(expected = ResponseStatusException.class)
    public void testModificarCarreraNotFound() {
        Carrera carreraInexistente = new Carrera(999, "Carrera Inexistente");
        carreraDao.modificarCarrera(carreraInexistente);
    }

    @Test
    public void testObtenerUltimoId() {
        Carrera carrera1 = new Carrera(6, "Arquitectura");
        Carrera carrera2 = new Carrera(7, "Diseño Gráfico");

        carreraDao.guardarCarrera(carrera1);
        carreraDao.guardarCarrera(carrera2);

        int ultimoId = carreraDao.obtenerUltimoId();

        assertEquals(7, ultimoId);
    }
}

