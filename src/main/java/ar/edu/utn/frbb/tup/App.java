package ar.edu.utn.frbb.tup;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.persistence.AlumnoDaoMemoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App
{

    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
        //Alumno alu1 = new Alumno("agustin", "lopez", 4878798);
        //Alumno alu2 = new Alumno("pablo", "gimenez", 5248978);
        //AlumnoDaoMemoryImpl


    }
}
