package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Alumno;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Service
public abstract class AlumnoDaoMemoryImpl implements AlumnoDao {

    private static final String CSV_FILE_PATH = "ar/edu/utn/frbb/tup/persistence/dataCSV/alumnoDATA.csv";

    public void guardarAlumno(Alumno alumno) {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;

        try {
            // Abrir el archivo en modo de agregar (append = true)
            fileWriter = new FileWriter(CSV_FILE_PATH, true);
            printWriter = new PrintWriter(fileWriter);

            // Escribir los atributos del alumno en formato CSV
            printWriter.println(
                    alumno.getId() + "," +
                            alumno.getNombre() + "," +
                            alumno.getApellido() + "," +
                            alumno.getDni()

            );

            System.out.println("Alumno guardado correctamente en el archivo CSV.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo CSV: " + e.getMessage());
        } finally {
            // Cerrar los recursos
            if (printWriter != null) {
                printWriter.close();
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el archivo: " + e.getMessage());
                }
            }
        }
    }
}

