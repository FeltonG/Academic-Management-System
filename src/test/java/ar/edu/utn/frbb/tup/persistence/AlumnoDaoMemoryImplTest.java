package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Alumno;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import java.io.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AlumnoDaoMemoryImplTest {

    @InjectMocks
    private AlumnoDaoMemoryImpl alumnoDaoMemoryImpl;

    @Mock
    private BufferedReader bufferedReader;

    @Mock
    private PrintWriter printWriter;

    @Mock
    private FileReader fileReader;

    @Mock
    private FileWriter fileWriter;

    private static final String CSV_FILE_PATH = "C:/Users/Felipe/IdeaProjects/LABORATORIO3/src/main/java/ar/edu/utn/frbb/tup/persistence/dataCSV/alumnoDATA.csv";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGuardarAlumno() {
        Alumno alumno = new Alumno(1L, "Juan", "Perez", 12345678);
        alumnoDaoMemoryImpl.guardarAlumno(alumno);
        Alumno alumnoGuardado = alumnoDaoMemoryImpl.buscarAlumnoporid(1L);
        assertNotNull(alumnoGuardado);
        assertEquals("Juan", alumnoGuardado.getNombre());
        assertEquals("Perez", alumnoGuardado.getApellido());
        assertEquals(12345678, alumnoGuardado.getDni());
    }



    @Test
    public void testBuscarAlumnoPorDni() {
        // Crear un nuevo alumno con un DNI específico
        Alumno alumno = new Alumno(2L, "Ana", "González", 87654321);

        // Guardar el alumno en el repositorio (suponiendo que el método guarda correctamente)
        alumnoDaoMemoryImpl.guardarAlumno(alumno);

        // Buscar el alumno por el DNI
        Alumno alumnoEncontrado = alumnoDaoMemoryImpl.buscarAlumnopordni(87654321);

        // Verificar que el alumno encontrado no sea nulo
        assertNotNull(alumnoEncontrado);

        // Verificar que el nombre y apellido del alumno encontrado sean correctos
        assertEquals("Ana", alumnoEncontrado.getNombre());
        assertEquals("González", alumnoEncontrado.getApellido());

        // Verificar que el DNI del alumno encontrado sea el correcto
        assertEquals(87654321, alumnoEncontrado.getDni());
    }

        @Test
    public void testBorrarAlumnoPorId() {
        Alumno alumno = new Alumno(2, "Martin", "Gimenez", 33345678);
        alumnoDaoMemoryImpl.guardarAlumno(alumno);  // Guardamos el alumno en el repositorio

        Alumno deletedAlumno = alumnoDaoMemoryImpl.borrarAlumnoporid(2L);  // Llamamos al método de eliminación

        // Verificamos que el alumno borrado es el esperado
        assertNotNull(deletedAlumno);
        assertEquals("Martin", deletedAlumno.getNombre());
        assertEquals("Gimenez", deletedAlumno.getApellido());
        assertEquals(33345678, deletedAlumno.getDni());

        // Verificamos que el alumno ya no existe en el repositorio
        assertNull(alumnoDaoMemoryImpl.buscarAlumnoporid(2));
    }

    @Test
    public void testModificarAlumno() {
        Alumno alumno = new Alumno(3L, "Carlos", "Rodríguez", 11223344);
        alumnoDaoMemoryImpl.guardarAlumno(alumno);
        Alumno alumnoModificado = new Alumno(3L, "Carlos", "Rodríguez", 99887766);
        alumnoDaoMemoryImpl.modificarAlumno(alumnoModificado);

        Alumno alumnoBuscado = alumnoDaoMemoryImpl.buscarAlumnoporid(3L);
        assertNotNull(alumnoBuscado);
        assertEquals(99887766, alumnoBuscado.getDni());
    }
    @Test
    public void testObtenerUltimoId() {
        Alumno alumno1 = new Alumno(7L, "Sofía", "García", 22334455);
        Alumno alumno2 = new Alumno(8L, "Pedro", "Martín", 66778899);
        alumnoDaoMemoryImpl.guardarAlumno(alumno1);
        alumnoDaoMemoryImpl.guardarAlumno(alumno2);

        int ultimoId = alumnoDaoMemoryImpl.obtenerUltimoId();
        assertEquals(8, ultimoId); // Verifica que el último ID guardado es 8
    }
}
