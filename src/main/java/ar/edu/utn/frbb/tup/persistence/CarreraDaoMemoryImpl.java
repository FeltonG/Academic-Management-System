package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Carrera;

import java.util.List;

public class CarreraDaoMemoryImpl implements  CarreraDao {

    private static final String CSV_FILE_PATH = "C:/Users/Felipe/IdeaProjects/LABORATORIO3/src/main/java/ar/edu/utn/frbb/tup/persistence/dataCSV/carreraDATA.csv";

    @Override
    public void guardarCarrera(Carrera carrera) {

    }


    @Override
    public Asignatura borrarCarreraporid(long id) {
        return null;
    }

    @Override
    public List<Carrera> buscarCarrera() {
        return List.of();
    }

    @Override
    public Asignatura buscarCarreraporId(long id) {
        return null;
    }

    @Override
    public Asignatura modificarCarrera(Carrera carrera) {
        return null;
    }
}
