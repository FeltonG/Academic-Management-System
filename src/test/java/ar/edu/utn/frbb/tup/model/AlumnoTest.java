package ar.edu.utn.frbb.tup.model;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.persistence.AlumnoDaoMemoryImpl;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlumnoTest {
    private AlumnoDaoMemoryImpl alumnoDao;
    private final String tempCsvPath = "tempAlumnoDATA.csv";
    @BeforeEach
    void setUp() {
        alumnoDao = new AlumnoDaoMemoryImpl();


        try (PrintWriter writer = new PrintWriter(new FileWriter(tempCsvPath))) {
            writer.println("1,John,Doe,12345678");
            writer.println("2,Jane,Smith,87654321");
        } catch (IOException e) {
            fail("Error al preparar archivo de prueba: " + e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        new File(tempCsvPath).delete();
    }

    @Test
    void testGuardarAlumno() {
        Alumno nuevoAlumno = new Alumno(3, "Alice", "Brown", 11223344);
        alumnoDao.guardarAlumno(nuevoAlumno);

        Alumno resultado = alumnoDao.buscarAlumnoporid(3);
        assertNotNull(resultado);
        assertEquals("Alice", resultado.getNombre());
    }

    @Test
    void testBuscarAlumnoporid() {
        Alumno alumno = alumnoDao.buscarAlumnoporid(1);
        assertNotNull(alumno);
        assertEquals("John", alumno.getNombre());

        Alumno noExistente = alumnoDao.buscarAlumnoporid(99);
        assertNull(noExistente);
    }

    @Test
    void testBorrarAlumnoporid() {
        Alumno alumnoEliminado = alumnoDao.borrarAlumnoporid(2);
        assertNotNull(alumnoEliminado);
        assertEquals("Jane", alumnoEliminado.getNombre());

        Alumno alumnoNoEncontrado = alumnoDao.borrarAlumnoporid(99);
        assertNull(alumnoNoEncontrado);
    }

    @Test
    void testModificarAlumno() {
        Alumno alumnoModificado = new Alumno(1, "John", "Updated", 12345678);
        Alumno resultado = alumnoDao.modificarAlumno(alumnoModificado);
        assertNotNull(resultado);
        assertEquals("Updated", resultado.getApellido());

        Alumno noExistente = new Alumno(99, "Non", "Existent", 99999999);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            alumnoDao.modificarAlumno(noExistente);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void testBuscarAlumnos() {
        List<Alumno> alumnos = alumnoDao.buscarAlumnos();
        assertEquals(2, alumnos.size());
    }

    @Test
    void testObtenerUltimoId() {
        int ultimoId = alumnoDao.obtenerUltimoId();
        assertEquals(2, ultimoId);
    }
}
