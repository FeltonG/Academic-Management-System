package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.EstadoAsignatura;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public  abstract class AsignaturaDaoMemoryImpl implements AsignaturaDao {

    private static final String CSV_FILE_PATH = "ar/edu/utn/frbb/tup/persistence/dataCSV/asignaturaDATA.csv";

    public void guardarAsignatura(Asignatura asignatura) {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;

        try {
            // Abrir el archivo en modo de agregar (append = true)
            fileWriter = new FileWriter(CSV_FILE_PATH, true);
            printWriter = new PrintWriter(fileWriter);

            // Escribir los atributos del alumno en formato CSV
            printWriter.println(
                    asignatura.getId() + "," +
                            asignatura.getIdalumno() + "," +
                            asignatura.getIdmateria() + "," +
                            asignatura.getNota()+ "," +
                            asignatura.getEstado()


            );

            System.out.println("Asignatura guardado correctamente en el archivo CSV.");
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
    public List<Asignatura> buscarAsignatura() {
        List<Asignatura> asignaturas = new ArrayList<>();
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(CSV_FILE_PATH));
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length < 4) {
                    System.err.println("Línea con formato incorrecto: " + linea);
                    continue; // Saltar líneas con formato incorrecto
                }

                try {
                    int nota = Integer.parseInt(datos[1].trim());
                    long idmateria = Long.parseLong(datos[2].trim());
                    long idalumno = Long.parseLong(datos[3].trim());

                    Asignatura asignatura = new Asignatura(nota, idalumno, idmateria);
                    asignaturas.add(asignatura);
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

        return asignaturas;
    }

    @Override
    public Asignatura buscarAsignaturaporDni(int Dni) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length < 5) { // Cambiado de 4 a 5
                    System.err.println("Línea con formato incorrecto: " + linea);
                    continue; // Salta la línea con formato incorrecto
                }
                try {
                    int nota = Integer.parseInt(datos[1].trim());
                    long idmateria = Long.parseLong(datos[2].trim());
                    long idalumno = Long.parseLong(datos[3].trim());
                    int alumnoDni = Integer.parseInt(datos[4].trim());

                    if (alumnoDni == Dni) {
                        return new Asignatura(nota, idalumno, idmateria); // Asegúrate de que este constructor exista
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error al parsear número: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return null; // Devuelve null si no se encuentra ninguna asignatura
    }


    @Override
    public Asignatura buscarAsignaturaporId(long id) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(","); //
                if (datos.length < 4) {
                    System.err.println("Línea con formato incorrecto: " + linea);
                    continue; // Salta la línea con formato incorrecto
                }
                try {
                    int nota = Integer.parseInt(datos[1].trim());
                    long idmateria = Long.parseLong(datos[2].trim());
                    long idalumno = Long.parseLong(datos[3].trim());


                    if (idalumno ==id) {
                        return new Asignatura(nota,idalumno,idmateria);
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
