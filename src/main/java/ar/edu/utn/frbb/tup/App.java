package ar.edu.utn.frbb.tup;

import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.business.AsignaturaService;
import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.business.ProfesorService;
import ar.edu.utn.frbb.tup.business.impl.AsignaturaServiceImpl;
import ar.edu.utn.frbb.tup.business.impl.MateriaServiceImpl;
import ar.edu.utn.frbb.tup.model.*;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.dto.AsignaturaDto;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.model.dto.ProfesorDto;
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
        ApplicationContext context = SpringApplication.run(App.class, args);
        /*     // AsignaturaService asignaturaService = context.getBean(AsignaturaService.class);

        // Crear una nueva asignatura usando AsignaturaDto
        // AsignaturaDto asig = new AsignaturaDto(7, EstadoAsignatura.CURSADA, 20, 8);
        // asig.setEstado(EstadoAsignatura.CURSADA);
        // asig.setNota(7);
        // asig.setIdmateria(20);
        // asig.setIdalumno(8);

        // Borrar asignatura por ID
        // asignaturaService.borrarAsignaturaporid(5);

        // Crear la asignatura
        // Asignatura asignaturaCreada = asignaturaService.crearAsignatura(asig);

        // Modificar asignatura existente
        // AsignaturaDto asmodificado = new AsignaturaDto(9, EstadoAsignatura.APROBADA, 34, 33);
        // asignaturaService.modificarAsignatura(20,asmodificado);

        // System.out.println("Asignatura creada: " + asignaturaCreada);
           MateriaDto materia = new MateriaDto();
        materia.setNombre("paula");
        materia.setCuatrimestre(10);
        materia.setProfesorId(11);
        materia.setAnio(12);

        //Verifica si crearMateria devuelve un MateriaDto
        Materia materiaCreada = materiaService.crearMateria(materia);
        System.out.println("Materia creada: " + materiaCreada);

// Modificar una materia
        MateriaDto materiamodificada = new MateriaDto();
        materiamodificada.setNombre("fe");
        materiamodificada.setAnio(4234242);
// Establece otros valores a modificar

// Modificar la materia con ID 23
//        materiaCreada= materiaService.modificarMateria(11, materiamodificada);
        //       System.out.println("Materia modificada: " + materiamodificada);

       */

       /* MateriaService materiaService = context.getBean(MateriaService.class);

         MateriaDaoMemoryImpl mdmi = new MateriaDaoMemoryImpl();
        List<Materia> materias = mdmi.buscarMateriasPorProfesorId(2);
        for (Materia materia:materias)
        {
            System.out.println(materia.toString());
        }*/


       /* AsignaturaService asignaturaService = context.getBean(AsignaturaService.class);

        AsignaturaDto asignatura= new AsignaturaDto();
        asignatura.setEstado(EstadoAsignatura.CURSADA);
        asignatura.setNota(7);
        asignatura.setIdalumno(3);
        asignatura.setIdmateria(2);

        Asignatura asignaturacreada= asignaturaService.crearAsignatura(asignatura);*/

    /* AsignaturaService asignaturaService = context.getBean(AsignaturaService.class);

        AsignaturaDaoMemoryImpl admi = new AsignaturaDaoMemoryImpl();
        List<Asignatura> asignaturas = admi.buscarAsignaturas();
        for (Asignatura asignatura:asignaturas)
        {
            System.out.println(asignatura.toString());
        }*/






      /* MateriaDto materia1=new MateriaDto();
       materia1.setNombre("Calculo matematico");
       materia1.setAnio(2);
        materia1.setCuatrimestre(1);
        materia1.setProfesorId(3);
        List<Long> correlatividades_materia = new ArrayList<Long>();
        correlatividades_materia.add(3l);
        correlatividades_materia.add(5l);
        correlatividades_materia.add(6l);
        materia1.setCorrelatividades(correlatividades_materia);
        Materia materiaCreada= materiaService.crearMateria(materia1);
*/

























/*



        ProfesorService profesorService =context.getBean(ProfesorService.class);


        ProfesorDto profesor1=new ProfesorDto();
        profesor1.setTitulo("matematico");
        profesor1.setNombre("carlos");
        profesor1.setApellido("lopez");


        Profesor profesorcreado= profesorService.crearProfesor(profesor1);

        System.out.println("se creo el "+ profesorcreado);


*/
    /*    ProfesorService profesorService =context.getBean(ProfesorService.class);
        profesorService.borrarProfesorporid(3);
    */
        AlumnoService alumnoService= context.getBean(AlumnoService.class);
        //alumnoService.borraralumnoId(5);
         Alumno alumn_buscado= alumnoService.buscarAlumnoId(5);
        System.out.println(alumn_buscado.toString());


    }
}


