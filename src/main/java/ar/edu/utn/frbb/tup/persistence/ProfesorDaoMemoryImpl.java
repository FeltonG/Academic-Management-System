package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


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

    @Override
    public List<Profesor> buscarProfesor() {
        List<Profesor> profesor = new ArrayList<>();
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(CSV_FILE_PATH));
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length < 5) {
                    System.err.println("Línea con formato incorrecto: " + linea);
                    continue; // Saltar líneas con formato incorrecto
                }

                try {
                    String nombre = String.valueOf(datos[1].trim());
                    String apellido =String.valueOf(datos[2].trim());
                    String titulo=String.valueOf(datos[3].trim());


                    Profesor profesor1 = new Profesor(nombre,apellido,titulo);
                    profesor.add(profesor1);
                } catch (NumberFormatException e) {
                    System.err.println("Error al parsear números en la línea: " + linea);
                    // Puedes decidir si continuar o lanzar una excepción
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
            // Dependiendo de tu lógica, podrías lanzar una excepción aquí
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el archivo: " + e.getMessage());
                }
            }
        }

        return profesor;
    }

    @Override
    public Profesor buscarProfesorDni(int Dni) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(","); //
                if (datos.length < 5) {
                    System.err.println("Línea con formato incorrecto: " + linea);
                    continue; // Salta la línea con formato incorrecto
                }
                try {
                    int profesorDni = Integer.parseInt(datos[0].trim());  // Asumiendo que el DNI está en la primera columna
                    String nombre = datos[1].trim();
                    String apellido = datos[2].trim();
                    String titulo = datos[3].trim();


                    if (profesorDni ==Dni) {
                        return new Profesor(nombre,apellido,titulo);
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

    @Override
    public Profesor borrarProfesordni(int dni) {
        File inputFile = new File(CSV_FILE_PATH);
        File tempFile = new File("tempFile.csv");
        Profesor profesorEliminado = null;
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(inputFile));
            printWriter = new PrintWriter(new FileWriter(tempFile));
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(",");
                int profesorDni = Integer.parseInt(datos[3]); // Suponiendo que el DNI está en la posición 3

                if (profesorDni == dni) {
                    // Crear un objeto Profesor con los datos leídos
                    profesorEliminado = new Profesor(datos[1], datos[2], datos[3]); // Nombre, Apellido, DNI
                    // No escribimos esta línea en el archivo temporal, eliminándola efectivamente
                } else {
                    // Escribimos todas las demás líneas
                    printWriter.println(linea);
                }
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el archivo: " + e.getMessage());
                }
            }
            if (printWriter != null) {
                printWriter.close();
            }
        }

        // Reemplazamos el archivo original con el temporal
        if (profesorEliminado != null) {
            if (!inputFile.delete()) {
                System.err.println("No se pudo eliminar el archivo original.");
                return null; // Si no se puede eliminar, retornamos null
            }
            if (!tempFile.renameTo(inputFile)) {
                System.err.println("No se pudo renombrar el archivo temporal.");
                return null; // Si no se puede renombrar, retornamos null
            }
            System.out.println("Profesor eliminado: " + profesorEliminado.getNombre() + " " + profesorEliminado.getApellido());
            return profesorEliminado;
        } else {
            System.out.println("No se encontró un profesor con el DNI proporcionado.");
            return null; // Si no se encontró, retornamos null
        }
    }

    @Override
    public Profesor modificarProfesor(Profesor profesor) {
        File inputFile = new File(CSV_FILE_PATH);
        File tempFile = new File("tempFile.csv");
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        Profesor profesorModificado = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(inputFile));
            printWriter = new PrintWriter(new FileWriter(tempFile));
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length < 3) { // Asegúrate de que tienes al menos nombre y apellido
                    printWriter.println(linea); // Copia la línea sin modificar
                    continue;
                }

                String profesorNombre = datos[1]; // Suponiendo que el nombre está en la posición 1
                String profesorApellido = datos[2]; // Suponiendo que el apellido está en la posición 2

                // Verifica si el nombre y apellido coinciden
                if (profesorNombre.equals(profesor.getNombre()) && profesorApellido.equals(profesor.getApellido())) {
                    // Reemplazamos la línea con los datos actualizados del profesor
                    printWriter.println(
                            profesor.getTitulo() + "," +
                                    profesor.getNombre() + "," +
                                    profesor.getApellido() // Solo escribimos los datos disponibles
                    );
                    profesorModificado = profesor; // Almacenamos el profesor modificado
                } else {
                    // Escribimos la línea existente sin modificaciones
                    printWriter.println(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo CSV: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (printWriter != null) printWriter.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }

        // Reemplazamos el archivo original con el temporal
        if (inputFile.delete()) {
            if (!tempFile.renameTo(inputFile)) {
                System.err.println("No se pudo renombrar el archivo temporal.");
            }
        } else {
            System.err.println("No se pudo eliminar el archivo original.");
        }

        if (profesorModificado != null) {
            System.out.println("Profesor modificado exitosamente: " + profesorModificado);
            return profesorModificado;
        } else {
            System.out.println("No se encontró un profesor con el nombre y apellido proporcionados.");
            return null; // Retornamos null si no se encontró
        }
    }



}
