package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Materia;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MateriaDaoMemoryImplTest {

    @Mock
    private Materia materiaMock;

    @InjectMocks
    private MateriaDaoMemoryImpl materiaDao;

    private final String testFilePath = "C:/Users/Felipe/IdeaProjects/Academic-Management-System/src/main/java/ar/edu/utn/frbb/tup/persistence/dataCSV/materiaDATA.csv";

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        // Crear un archivo de prueba si no existe
        File testFile = new File(testFilePath);
        if (testFile.exists()) {
            assertTrue(testFile.delete());
        }
        assertTrue(testFile.createNewFile());
    }

    @Test
    public void testGuardarMateria() throws IOException {
        // Crear un objeto Materia
        Materia materia = new Materia(1L, "Matemática", 2024, 1, 100L, new ArrayList<>());

        // Llamar al método guardarMateria (usando el PrintWriter real)
        materiaDao.guardarMateria(materia);

        // Leer el archivo para verificar que se haya guardado la información
        try (BufferedReader reader = new BufferedReader(new FileReader(testFilePath))) {
            String line = reader.readLine();
            assertNotNull(line);  // Verifica que haya contenido en el archivo
            assertTrue(line.contains("Matemática"));  // Verifica que el contenido esté en el archivo
        }
    }

    @Test
    public void testBuscarMaterias() throws IOException {
        // Preparar archivo con datos
        try (FileWriter writer = new FileWriter(testFilePath)) {
            writer.write("1,Matemática,1,1,100,\n");
            writer.write("2,Física,2,1,101,\n");
        }

// Verifica que el método buscarMaterias esté leyendo correctamente las líneas
        List<Materia> materias = materiaDao.buscarMaterias();

// Verifica la cantidad de materias y sus nombres
        assertEquals(2, materias.size());
        assertEquals("Matemática", materias.get(0).getNombre());
        assertEquals("Física", materias.get(1).getNombre());  // Verifica el nombre de la segunda materia
    }

    @Test
    public void testBuscarMateriaId() throws IOException {
        // Preparar archivo con datos
        try (PrintWriter writer = new PrintWriter(new FileWriter(testFilePath))) {
            writer.println("1,Matemática,1,1,100,");
        }

        Materia materia = materiaDao.buscarMateriaId(1L);
        assertNotNull(materia);
        assertEquals("Matemática", materia.getNombre());
    }

    @Test
    public void testBorrarMateriaPorId() throws IOException {
        // Preparar archivo con datos
        try (PrintWriter writer = new PrintWriter(new FileWriter(testFilePath))) {
            writer.println("1,Matemática,1,1,100,");
        }

        Materia materia = materiaDao.borrarmateriaporid(1L);
        assertNotNull(materia);
        assertEquals("Matemática", materia.getNombre());

        // Comprobar que el archivo se haya vaciado después de borrar
        try (BufferedReader reader = new BufferedReader(new FileReader(testFilePath))) {
            assertNull(reader.readLine());  // No debería haber nada en el archivo
        }
    }
}
