package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Alumno;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.util.List;

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
        Alumno alumno = new Alumno(1L, "Juan", "Perez", 12345678);

        // Simulamos la creación de un PrintWriter y FileWriter
        FileWriter mockFileWriter = mock(FileWriter.class);
        PrintWriter mockPrintWriter = mock(PrintWriter.class);
        when(mockFileWriter.append(anyString())).thenReturn(mockFileWriter);
        //when(mockPrintWriter.println(anyString())).thenReturn(null);

        // Simulamos que se pase el PrintWriter correctamente a la clase
        when(fileWriter).thenReturn(mockFileWriter);
        when(printWriter).thenReturn(mockPrintWriter);

        alumnoDaoMemoryImpl.guardarAlumno(alumno);

        // Verificar que el método printWriter.println() se llama correctamente
        verify(mockPrintWriter).println("1,Juan,Perez,12345678");
    }

    @Test
    public void testBuscarAlumnoporid() throws IOException {
        // Crear un alumno para retornar
        Alumno expectedAlumno = new Alumno(1L, "Juan", "Perez", 12345678);

        // Simulamos el comportamiento del BufferedReader
        FileReader mockFileReader = mock(FileReader.class);
        when(mockFileReader.read()).thenReturn(1);  // Simulamos que el archivo existe
        when(new BufferedReader(mockFileReader)).thenReturn(bufferedReader);

        // Simulamos la lectura de una línea con el alumno
        when(bufferedReader.readLine()).thenReturn("1,Juan,Perez,12345678").thenReturn(null);  // Retorna la línea con el alumno

        Alumno alumno = alumnoDaoMemoryImpl.buscarAlumnoporid(1L);

        // Verificar que el alumno encontrado es el esperado
        assertNotNull(alumno);
        assertEquals(expectedAlumno.getId(), alumno.getId());
        assertEquals(expectedAlumno.getNombre(), alumno.getNombre());
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
        assertNull(alumnoDaoMemoryImpl.buscarAlumnoporid(2L));
    }

    @Test
    public void testModificarAlumno() throws IOException {
        // Preparar el alumno y el archivo de datos
        Alumno alumnoOriginal = new Alumno(3, "Kevin", "Martinez", 12345678);
        Alumno alumnoModificado = new Alumno(3, "Kevin", "Martinez", 87654321);

        // Guardar el alumno original en el archivo
        alumnoDaoMemoryImpl.guardarAlumno(alumnoOriginal);

        // Modificar el alumno
        alumnoDaoMemoryImpl.modificarAlumno(alumnoModificado);

        // Buscar el alumno modificado
        Alumno alumnoRecuperado = alumnoDaoMemoryImpl.modificarAlumno(alumnoModificado);

        // Verificar que el alumno ha sido modificado correctamente
        assertNotNull(alumnoRecuperado);
        assertEquals("Juan", alumnoRecuperado.getNombre());
        assertEquals("Gonzalez", alumnoRecuperado.getApellido());
        assertEquals(87654321, alumnoRecuperado.getDni());
    }
}
