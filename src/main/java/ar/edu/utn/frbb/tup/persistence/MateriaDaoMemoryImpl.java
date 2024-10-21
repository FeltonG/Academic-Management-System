package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Materia;

import ar.edu.utn.frbb.tup.model.Profesor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public  class MateriaDaoMemoryImpl implements MateriaDao {


    private static final String CSV_FILE_PATH = "C:/Users/Felipe/IdeaProjects/LABORATORIO3/src/main/java/ar/edu/utn/frbb/tup/persistence/dataCSV/materiaDATA.csv";

    public void guardarMateria(Materia materia) {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;

        try {
            // Abrir el archivo en modo de agregar (append = true)
            fileWriter = new FileWriter(CSV_FILE_PATH, true);
            printWriter = new PrintWriter(fileWriter);

            // Convertir la lista de correlatividades en un string con formato "7-4-5"
            String correlatividadesFormato = String.join("'",
                    materia.getCorrelatividades().stream()
                            .map(String::valueOf)  // Convertir cada correlatividad a String
                            .toArray(String[]::new) // Convertir el stream a un array de Strings
            );

            // Escribir los atributos de la materia en formato CSV
            printWriter.println(
                    materia.getId() + "," +
                            materia.getNombre() + "," +
                            materia.getAnio() + "," +
                            materia.getCuatrimestre() + "," +
                            correlatividadesFormato // Guardar en formato "7-4-5"
            );

            System.out.println("Materia guardada correctamente en el archivo CSV.");
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
    public List<Materia> buscarMaterias() {
        List<Materia> materias = new ArrayList<>();
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
                    Long id = Long.parseLong(datos[0].trim());
                    String nombre = String.valueOf(datos[1].trim());
                    int anio = Integer.parseInt(datos[2].trim());
                    int cuatrimestre = Integer.parseInt(datos[3].trim());
                    long idProfesor = Long.parseLong(datos[3].trim());

                    // Convertir las correlatividades en una lista de Long
                    List<Long> correlatividades = Arrays.stream(datos[4].trim().split("'")) // Dividir por el guion
                            .map(Long::parseLong) // Convertir cada elemento a Long
                            .collect(Collectors.toList()); // Recoger en una lista de Long

                    // Crear una nueva instancia de Materia con las correlatividades
                    Materia materia = new Materia(id,nombre, anio, cuatrimestre, idProfesor, correlatividades);
                    materias.add(materia); // Agregar la materia a la lista

                } catch (NumberFormatException e) {
                    System.err.println("Error al parsear números en la línea: " + linea);
                    // Dependiendo de tu lógica, puedes continuar o lanzar una excepción
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

        return materias;
    }

    @Override
    public List<Materia> buscarMateriasPorProfesorId(long idProfesor)
        {
            List<Materia> materias = new ArrayList<>();
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
                        Long id = Long.parseLong(datos[0].trim());
                        String nombre = String.valueOf(datos[1].trim());
                        int anio = Integer.parseInt(datos[2].trim());
                        int cuatrimestre = Integer.parseInt(datos[3].trim());
                        long idProf = Long.parseLong(datos[3].trim());

                        // Convertir las correlatividades en una lista de Long
                        List<Long> correlatividades = Arrays.stream(datos[4].trim().split("'")) // Dividir por el guion
                                .map(Long::parseLong) // Convertir cada elemento a Long
                                .collect(Collectors.toList()); // Recoger en una lista de Long

                        // Crear una nueva instancia de Materia con las correlatividades
                        Materia materia = new Materia(id,nombre, anio, cuatrimestre, idProfesor, correlatividades);
                        if(idProf == idProfesor) {
                            materias.add(materia); // Agregar la materia a la lista
                        }

                    } catch (NumberFormatException e) {
                        System.err.println("Error al parsear números en la línea: " + linea);
                        // Dependiendo de tu lógica, puedes continuar o lanzar una excepción
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

            return materias;
        }
    @Override
    public Materia buscarMateriaId(long id) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(","); //
                if (datos.length < 5) {
                    System.err.println("Línea con formato incorrecto: " + linea);
                    continue; // Salta la línea con formato incorrecto
                }
                try {
                    long Id = Long.parseLong(datos[0].trim());
                    String nombre= String.valueOf(datos[1]);
                    int anio=Integer.parseInt(datos[2]);
                    int cuatrimestre=Integer.parseInt(datos[3]);
                    long idprofesor = Long.parseLong(datos[4].trim());
                    // Convertir las correlatividades en una lista de Long
                    List<Long> correlatividades = Arrays.stream(datos[5].trim().split("'")) // Dividir por el guion
                            .map(Long::parseLong) // Convertir cada elemento a Long
                            .collect(Collectors.toList()); // Recoger en una lista de Long


                    if (Id==id) {

                        return new Materia(nombre,anio,cuatrimestre,idprofesor, correlatividades);
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
    public Materia borrarmateriaporid(long id) {
        File inputFile = new File(CSV_FILE_PATH);
        BufferedReader bufferedReader = null;
        File tempFile = new File("tempFile.csv");
        PrintWriter printWriter = null;
        Materia materiaEliminada = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(inputFile));
            printWriter = new PrintWriter(new FileWriter(tempFile));
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0].trim();
                int anio = Integer.parseInt(datos[1].trim());
                int cuatrimestre = Integer.parseInt(datos[2].trim());
                long idProfesorActual = Long.parseLong(datos[3].trim());
                // Convertir las correlatividades en una lista de Long
                List<Long> correlatividades = Arrays.stream(datos[4].trim().split("'")) // Dividir por el guion
                        .map(Long::parseLong) // Convertir cada elemento a Long
                        .collect(Collectors.toList()); // Recoger en una lista de Long

                // Si el id de la materia no coincide, escribir la línea en el archivo temporal
                if (idProfesorActual != id) {
                    printWriter.println(linea);
                } else {
                    // Si coincide, guardar la materia eliminada
                     materiaEliminada = new Materia( nombre, anio, cuatrimestre, idProfesorActual, correlatividades);
                }
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (printWriter != null) printWriter.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }

        if (materiaEliminada != null) {
            if (!inputFile.delete()) {
                System.out.println("No se pudo eliminar el archivo original");
                return null;
            }
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("No se pudo renombrar el archivo temporal");
                return null;
            }
            System.out.println("Asignatura eliminada exitosamente!");

            return materiaEliminada;
        } else {
            System.out.println("No existe asignatura con el id proporcionado: " + id);
            return null;
        }
    }

    @Override
    public Materia modificarMateria(Materia materia) {
        File inputFile = new File(CSV_FILE_PATH);
        File tempFile = new File("tempFile.csv");
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        Materia materiaModificada = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(inputFile));
            printWriter = new PrintWriter(new FileWriter(tempFile));
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length < 4) {  // Se espera que haya al menos 4 campos: nombre, anio, cuatrimestre, profesorId
                    printWriter.println(linea);  // Si la línea no tiene el formato correcto, la copiamos tal cual
                    continue;
                }

                String nombre = datos[0];
                int anio = Integer.parseInt(datos[1]);
                int cuatrimestre = Integer.parseInt(datos[2]);

                // Comparar con los datos de la materia a modificar
                if (nombre.equals(materia.getNombre()) && anio == materia.getAnio() && cuatrimestre == materia.getCuatrimestre()) {
                    // Reemplazar la línea con los datos actualizados de la asignatura
                    printWriter.println(
                            materia.getNombre() + "," +          // Nombre de la materia
                                    materia.getAnio() + "," +            // Año de la materia
                                    materia.getCuatrimestre() + ","    // Cuatrimestre de la materia
                                                  // ID del profesor
                    );

                    materiaModificada = materia;  // Guardar la referencia de la materia modificada
                } else {
                    // Escribir la línea existente sin modificaciones
                    printWriter.println(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo CSV: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al modificar la asignatura.");
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (printWriter != null) printWriter.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }

// Reemplazar el archivo original con el archivo temporal
        if (inputFile.delete()) {
            if (!tempFile.renameTo(inputFile)) {
                System.err.println("No se pudo renombrar el archivo temporal.");
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al renombrar el archivo temporal.");
            }
        } else {
            System.err.println("No se pudo eliminar el archivo original.");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el archivo original.");
        }

// Devolver la materia modificada si se realizó el cambio
        if (materiaModificada != null) {
            System.out.println("Asignatura modificada exitosamente.");
            return materiaModificada;
        } else {
            System.out.println("No se encontró una asignatura con los datos proporcionados.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignatura no encontrada.");
        }


    }

    @Override
    public int obtenerUltimoId() {
        BufferedReader bufferedReader = null;
        int ultimoId = 0;

        try {
            bufferedReader = new BufferedReader(new FileReader(CSV_FILE_PATH));
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length > 0) {
                    try {
                        int idActual = Integer.parseInt(datos[0].trim());
                        // Guardar el ID más alto encontrado
                        if (idActual > ultimoId) {
                            ultimoId = idActual;
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error al parsear el ID en la línea: " + linea);
                    }
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
        }

        return ultimoId;
    }
}
