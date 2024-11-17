package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.EstadoAsignatura;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AsignaturaDaoMemoryImplTest {

    @InjectMocks
    private AsignaturaDaoMemoryImpl asignaturaDaoMemoryImpl;

    @Mock
    private BufferedReader bufferedReader;

    @Mock
    private PrintWriter printWriter;

    @Mock
    private FileReader fileReader;

    @Mock
    private FileWriter fileWriter;

    private static final String CSV_FILE_PATH = "C:/Users/Felipe/IdeaProjects/LABORATORIO3/src/main/java/ar/edu/utn/frbb/tup/persistence/dataCSV/asignaturaDATA.csv";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGuardarAsignatura() throws IOException {
        // Limpiar el archivo CSV antes de la prueba
        FileWriter writer = new FileWriter(CSV_FILE_PATH);
        writer.close(); // Cierra el archivo vacío

        // Arrange: Crear la asignatura a guardar
        Asignatura asignatura = new Asignatura(1L, EstadoAsignatura.CURSADA, 8, 123L, 456L);

        // Act: Llamar al método para guardar la asignatura
        asignaturaDaoMemoryImpl.guardarAsignatura(asignatura);

        // Assert: Verificar que el archivo contiene la línea con los datos de la asignatura
        BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));
        String line = reader.readLine();
        reader.close();

        // Verificar que la línea leída contiene los datos esperados en el orden correcto
        assertEquals("1,123,456,8,CURSADA", line);
    }

    @Test
    public void testBuscarAsignaturaPorId() throws IOException {
        // Simular que el BufferedReader devuelve una línea de datos
        when(bufferedReader.readLine())
                .thenReturn("1,CURSADA,8,123,456") // Línea CSV que representa una asignatura
                .thenReturn(null); // Indica el final del archivo

        // Llamar al método a probar
        Asignatura asignatura = asignaturaDaoMemoryImpl.buscarAsignaturaporId(1L);

        // Verificar que la asignatura encontrada tiene los valores correctos
        assertNotNull(asignatura);
        assertEquals(1L, asignatura.getId()); // Comprobar el ID
        assertEquals(EstadoAsignatura.CURSADA, asignatura.getEstado()); // Comprobar el estado
        assertEquals(8, asignatura.getNota()); // Comprobar la nota
        System.out.println(asignatura.getNota()); // Agregar un print para depurar
    }

    @Test
    public void testBorrarAsignaturaPorId() {
        Asignatura asignatura = new Asignatura(2L, EstadoAsignatura.CURSADA, 7, 124L, 457L);
        asignaturaDaoMemoryImpl.guardarAsignatura(asignatura);  // Guardamos la asignatura en el repositorio

        // Verificamos que la asignatura con ID 2 está en el repositorio
        assertNotNull(asignaturaDaoMemoryImpl.buscarAsignaturaporId(2L));

        Asignatura deletedAsignatura = asignaturaDaoMemoryImpl.borrarAsignaturaporid(2L);  // Llamamos al método de eliminación

        // Verificamos que la asignatura borrada es la esperada
        assertNotNull(deletedAsignatura);
        assertEquals(4L, deletedAsignatura.getId());  // Verificamos el ID de la asignatura borrada
        assertEquals(EstadoAsignatura.CURSADA, deletedAsignatura.getEstado());  // Verificamos el estado de la asignatura

        // Verificamos que la asignatura ya no existe en el repositorio
        assertNull(asignaturaDaoMemoryImpl.buscarAsignaturaporId(2L));  // Buscamos por el ID correcto, que debería ser 2L
    }

    @Test
    public void testModificarAsignatura() throws IOException {
        // Preparar la asignatura original y la modificada
        Asignatura asignaturaOriginal = new Asignatura(3L, EstadoAsignatura.APROBADA, 8, 123L, 456L);
        Asignatura asignaturaModificada = new Asignatura(3L, EstadoAsignatura.CURSADA, 9, 124L, 457L);

        // Guardar la asignatura original en el archivo
        asignaturaDaoMemoryImpl.guardarAsignatura(asignaturaOriginal);

        // Modificar la asignatura
        asignaturaDaoMemoryImpl.modificarAsignatura(asignaturaModificada);

        // Buscar la asignatura modificada
        Asignatura asignaturaRecuperada = asignaturaDaoMemoryImpl.modificarAsignatura(asignaturaModificada);

        // Verificar que la asignatura ha sido modificada correctamente
        assertNotNull(asignaturaRecuperada);
        assertEquals(EstadoAsignatura.CURSADA, asignaturaRecuperada.getEstado());
        assertEquals(9, asignaturaRecuperada.getNota());

    }
}
