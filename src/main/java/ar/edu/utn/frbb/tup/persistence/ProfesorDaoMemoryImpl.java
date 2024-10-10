package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;



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
    public Profesor buscarProfesorporid(long id)
    {

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(CSV_FILE_PATH));
            String linea;  // id, nombre, apellido, dni
            while ((linea = bufferedReader.readLine()) != null)
            {
                String[] datos = linea.split(",");
                int profesorId = Integer.parseInt(datos[0]);
                String profesorTitulo = datos[1];
                String profesornombre = datos[2];
                String profesorapellido= datos[3];
                if (profesorId == id) {
                    // Crear un objeto Profesor con id, título y dni
                    Profesor profesor = new Profesor(profesorTitulo,profesornombre,profesorapellido );
                    return profesor;
                }
            }

        }
        catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el archivo: " + e.getMessage());
                }
            }
        }
        return null;

    }

    public Profesor borrarProfesorporid(long id)
    {
        File inputFile = new File(CSV_FILE_PATH);
        BufferedReader bufferedReader = null;
        File tempFile = new File("tempFile.csv");
        PrintWriter printWriter = null;
        Profesor profesoreliminado = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(inputFile));
            printWriter = new PrintWriter(new FileWriter(tempFile));
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(",");
                int profesorId = Integer.parseInt(datos[0]);
                if (profesorId != id) {
                    printWriter.println(linea);
                }else
                {
                    profesoreliminado = new Profesor(
                                           // ID
                            datos[1],                  // Nombre
                            datos[2],                  // Apellido
                            datos[3]                   // titulo
                    );

                }
            }

        }catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        finally
        {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (printWriter != null) printWriter.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }

        if(profesoreliminado != null)
        {
            if(!inputFile.delete())
            {
                System.out.println("No se pudo eliminar el archivo original");
                return null;
            }
            if(!tempFile.renameTo(inputFile))
            {
                System.out.println("No se pudo renombrar el archivo temporal");
                return null;
            }
            System.out.println("Alumno eliminado exitosamente! ");
            return profesoreliminado;
        }
        else
        {
            System.out.println("No existe alumno con ese id");
            return null;
        }

    }



}
