package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Profesor;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public  abstract class ProfesorDaoMemoryImpl implements ProfesorDao{

    private static final String CSV_FILE_PATH = "ar/edu/utn/frbb/tup/persistence/dataCSV/profesorDATA.csv";

    public void guardarProfesor(Profesor profesor) {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;

        try {
            // Abrir el archivo en modo de agregar (append = true)
            fileWriter = new FileWriter(CSV_FILE_PATH, true);
            printWriter = new PrintWriter(fileWriter);

            // Escribir los atributos del alumno en formato CSV
            printWriter.println(
                    profesor.getNombre() + "," +
                            profesor.getApellido() + "," +
                            profesor.getTitulo() + ","



            );

            System.out.println("profesor  guardado correctamente en el archivo CSV.");
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
