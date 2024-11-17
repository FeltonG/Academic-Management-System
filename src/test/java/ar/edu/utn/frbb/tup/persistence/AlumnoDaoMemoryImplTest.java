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
    public void testGuardarAlumno() throws IOException {
        // Limpiar el archivo CSV antes de la prueba
        FileWriter writer = new FileWriter(CSV_FILE_PATH);
        writer.close(); // Cierra el archivo vacío

        // Arrange: Crear el alumno a guardar
        Alumno alumno = new Alumno(1L, "Juan", "Perez", 12345678);

        // Act: Llamar al método para guardar el alumno
        alumnoDaoMemoryImpl.guardarAlumno(alumno);

        // Assert: Verificar que el archivo contiene la línea con los datos del alumno
        BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));
        String line = reader.readLine();
        reader.close();

        // Verificar que la línea leída contiene los datos esperados
        assertEquals("1,Juan,Perez,12345678", line);
    }


    @Test
    public void testBuscarAlumnoPorId() throws IOException {
        // Simular que el BufferedReader devuelve una línea de datos
        when(bufferedReader.readLine())
                .thenReturn("1,Juan,Perez,87654321") // Línea CSV que representa un alumno
                .thenReturn(null); // Indica el final del archivo

        // Llamar al método a probar
        Alumno alumno = alumnoDaoMemoryImpl.buscarAlumnoporid(1L);

        // Verificar que el alumno encontrado tiene los valores correctos
        assertNotNull(alumno);
        assertEquals(1L, alumno.getId()); // Comprobar el ID
        assertEquals("Juan", alumno.getNombre()); // Comprobar el nombre
        assertEquals("Perez", alumno.getApellido()); // Comprobar el apellido
        assertEquals("87654321", alumno.getDni()); // Comprobar el DNI como String
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
    public void testModificarAlumno() throws IOException {
        // Preparar el alumno y el archivo de datos
        Alumno alumnoOriginal = new Alumno(3, "Kevin", "Martinez", 12345678);
        Alumno alumnoModificado = new Alumno(3, "Felipe", "Garcia", 45501907);

        // Guardar el alumno original en el archivo
        alumnoDaoMemoryImpl.guardarAlumno(alumnoOriginal);

        // Modificar el alumno
        alumnoDaoMemoryImpl.modificarAlumno(alumnoModificado);

        // Buscar el alumno modificado
        Alumno alumnoRecuperado = alumnoDaoMemoryImpl.modificarAlumno(alumnoModificado);

        // Verificar que el alumno ha sido modificado correctamente
        assertNotNull(alumnoRecuperado);
        assertEquals("Felipe", alumnoRecuperado.getNombre());
        assertEquals("Garcia", alumnoRecuperado.getApellido());
        assertEquals(45501907, alumnoRecuperado.getDni());
    }
}
