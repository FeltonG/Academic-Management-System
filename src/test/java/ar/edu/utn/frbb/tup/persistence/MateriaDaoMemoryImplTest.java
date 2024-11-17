package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Materia;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import java.io.*;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MateriaDaoMemoryImplTest {
    @Mock
    private PrintWriter printWriter;
    @Mock
    private BufferedReader bufferedReader;

    @InjectMocks
    private MateriaDaoMemoryImpl materiaDao;

    private static final String CSV_FILE_PATH = "C:/Users/Felipe/IdeaProjects/LABORATORIO3/src/main/java/ar/edu/utn/frbb/tup/persistence/dataCSV/materiaDATA.csv";

    @Before
    public void setUp() throws Exception {
        // Inicializar los mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarMateria() throws IOException {
        // Prepara la materia a guardar
        Materia materia = new Materia(1L, "Matematica", 1, 1, 100L, List.of(2L, 3L));

        // Simula que el PrintWriter escribe correctamente en el archivo
        doNothing().when(printWriter).println(anyString());

        // Llamamos al método que estamos probando
        materiaDao.guardarMateria(materia);

        // Verificamos que PrintWriter haya sido llamado con el formato correcto
        verify(printWriter).println(contains("Matematica,1L,1,1,100L"));

        // Verificación adicional para asegurar que se invoca al menos una vez
        verify(printWriter, times(1)).println(anyString());
    }

    @Test
    public void testBuscarMaterias() throws IOException {
        // Configura el BufferedReader para simular la lectura de un archivo
        String mockData = "1,Calculo algoritmico 2,1,1,100,2,3\n";
        when(bufferedReader.readLine()).thenReturn(mockData).thenReturn(null);  // Devuelve una línea y luego null para terminar

        // Llamamos al método que estamos probando
        List<Materia> materias = materiaDao.buscarMaterias();

        // Verificamos que la lista de materias no esté vacía y contiene la materia esperada
        assertFalse(materias.isEmpty());
        assertEquals("Calculo algoritmico 2", materias.get(0).getNombre());
    }

    @Test
    public void testBuscarMateriaId() throws IOException {
        // Configura el BufferedReader para simular la lectura de un archivo
        String mockData = "1,Matematica Avanzada,1,1,100,2,3\n"; // Simula una línea de archivo con el nombre correcto
        when(bufferedReader.readLine()).thenReturn(mockData).thenReturn(null);  // Devuelve una línea y luego null para terminar

        // Verifica que el mockData sea correcto
        String dataLeida = bufferedReader.readLine();
        System.out.println(dataLeida); // Agrega un print para comprobar lo que lee el BufferedReader

        // Llamamos al método con un ID que existe en los datos simulados
        Materia materia = materiaDao.buscarMateriaId(1);

        // Verificamos que la materia recuperada tenga los valores correctos
        assertNotNull(materia);
        assertEquals("Matematica Avanzada", materia.getNombre());  // Verificamos que el nombre sea el correcto
    }

    @Test
    public void testBorrarMateriaPorId() throws IOException {
        // Configura el BufferedReader para simular la lectura de un archivo
        // El mockData debe tener el nombre correcto de la materia que estás probando
        String mockData = "1,Matematica,1,1,100,2,3\n";  // Esta es la materia que esperamos eliminar
        when(bufferedReader.readLine()).thenReturn(mockData).thenReturn(null);  // Devuelve una línea y luego null para terminar

        // Llamamos al método que estamos probando
        Materia materiaEliminada = materiaDao.borrarmateriaporid(1L);

        // Verificamos que la materia fue eliminada correctamente
        assertNotNull(materiaEliminada);  // La materia eliminada no debe ser null
        assertEquals("Matematica", materiaEliminada.getNombre());  // Verificamos que el nombre de la materia eliminada es el esperado
    }

    @Test
    public void testModificarMateria() throws IOException {
        // Configura el BufferedReader para simular la lectura de un archivo
        String mockData = "1,Matematica,1,1,100,2,3\n"; // Datos originales de la materia
        when(bufferedReader.readLine()).thenReturn(mockData).thenReturn(null);  // Devuelve una línea y luego null para terminar

        // Crea una materia con cambios a modificar (nuevos valores)
        Materia materia = new Materia(1L, "Matematica Avanzada", 2, 1, 100L, List.of(2L, 3L));

        // Llamamos al método que estamos probando
        Materia materiaModificada = materiaDao.modificarMateria(materia);

        // Verificamos que la materia haya sido modificada correctamente
        assertNotNull(materiaModificada);
        assertEquals("Matematica Avanzada", materiaModificada.getNombre());  // Verificamos que el nombre sea el correcto
    }
}
