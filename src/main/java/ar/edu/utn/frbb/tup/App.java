package ar.edu.utn.frbb.tup;

import ar.edu.utn.frbb.tup.business.*;
import ar.edu.utn.frbb.tup.business.impl.AsignaturaServiceImpl;
import ar.edu.utn.frbb.tup.business.impl.MateriaServiceImpl;
import ar.edu.utn.frbb.tup.model.*;
import ar.edu.utn.frbb.tup.model.dto.*;
import ar.edu.utn.frbb.tup.persistence.AlumnoDaoMemoryImpl;
import ar.edu.utn.frbb.tup.persistence.AsignaturaDaoMemoryImpl;
import ar.edu.utn.frbb.tup.persistence.MateriaDaoMemoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.util.List;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
       // ApplicationContext context = SpringApplication.run(App.class, args);

        SpringApplication.run(App.class, args);


    }

}
