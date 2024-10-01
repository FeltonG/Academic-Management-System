package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Alumno;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
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
@Override
    public Alumno buscarAlumnoporid(long id)
    {

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(CSV_FILE_PATH));
            String linea;  // id, nombre, apellido, dni
            while ((linea = bufferedReader.readLine()) != null)
            {
                String[] datos = linea.split(","); // datos = [id, nombre, apellido, dni]
                int alumnoId = Integer.parseInt(datos[0]);
                int alumnoDni = Integer.parseInt(datos[3]);
                if (alumnoId == id) {
                    Alumno alumno = new Alumno(alumnoId, datos[1], datos[2],alumnoDni);
                    return alumno;
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
    @Override
    public Alumno borrarAlumnoporid(long id)
    {
        File inputFile = new File(CSV_FILE_PATH);
        BufferedReader bufferedReader = null;
        File tempFile = new File("tempFile.csv");
        PrintWriter printWriter = null;
        Alumno alumnoEliminado = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(inputFile));
            printWriter = new PrintWriter(new FileWriter(tempFile));
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(",");
                int alumnoId = Integer.parseInt(datos[0]);
                int alumnoDni = Integer.parseInt(datos[3]);
                if (alumnoId != id) {
                    printWriter.println(linea);
                }else
                {
                    alumnoEliminado = new Alumno(
                            alumnoId,                  // ID
                            datos[1],                  // Nombre
                            datos[2],                  // Apellido
                            alumnoDni                   // DNI
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

        if(alumnoEliminado != null)
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
            return alumnoEliminado;
        }
        else
        {
            System.out.println("No existe alumno con ese id");
            return null;
        }

    }

    @Override
    public Alumno borrarAlumnoDNI(int Dni) {
        File inputFile = new File(CSV_FILE_PATH);
        BufferedReader bufferedReader = null;
        File tempFile = new File("tempFile.csv");
        PrintWriter printWriter = null;
        Alumno alumnoEliminado = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(inputFile));
            printWriter = new PrintWriter(new FileWriter(tempFile));
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(",");
                int alumnoId = Integer.parseInt(datos[0]);
                int alumnoDni = Integer.parseInt(datos[3]);
                if (alumnoId !=Dni) {
                    printWriter.println(linea);
                }else
                {
                    alumnoEliminado = new Alumno(
                            alumnoId,                  // ID
                            datos[1],                  // Nombre
                            datos[2],                  // Apellido
                            alumnoDni                   // DNI
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

        if(alumnoEliminado != null)
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
            return alumnoEliminado;
        }
        else
        {
            System.out.println("No existe alumno con ese id");
            return null;
        }
    }

    @Override
    public Alumno buscarAlumnoporDni(int Dni) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String linea; // id, nombre, apellido, dni
            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(","); // datos = [id, nombre, apellido, dni]
                if (datos.length < 4) {
                    System.err.println("Línea con formato incorrecto: " + linea);
                    continue; // Salta la línea con formato incorrecto
                }
                try {
                    int alumnoId = Integer.parseInt(datos[0]);
                    int alumnoDni = Integer.parseInt(datos[3]);
                    if (alumnoDni == Dni) {
                        return new Alumno(alumnoId, datos[1], datos[2], alumnoDni);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error al parsear número: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return null;
}
}

