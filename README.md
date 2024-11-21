Este proyecto representa una solución basada en un conjunto de APIs REST, construidas en Java con el uso de Spring Boot. La arquitectura adoptada es en capas, y su objetivo es gestionar las entidades clave de un sistema académico, que incluyen Alumno, Asignatura, Materia, Profesor y Carrera.


Características Principales del Proyecto

  Entidades principales:
  
    Alumno, Profesor, Materia, Asignatura y Carrera.
    
    DTOs como AlumnoDTO, MateriaDTO y ProfesorDto,CarreraDTO,AsignaturaDTO para transferir datos.
    
    Excepciones personalizadas para errores específicos.
    
  Servicios REST:
  
      Endpoints para CRUD de Alumnos, Profesores, Materias, Asignaturas y Carreras.
      Controladores como AlumnoController y ProfesorController,MateriaController,AsignaturaController,CarreraController.
      Manejo de Excepciones: Centralizado en handler con clases como UTNResponseEntityExceptionHandler.
  Persistencia de Datos:
  
      Uso de CSV para almacenar datos.
      
      DAOs con implementaciones en memoria (AlumnoDaoMemoryImpl).
      
  Arquitectura en Capas:
  
    Model: Entidades, DTOs y excepciones.
    
    Business: Lógica de negocio (implementaciones e interfaces).
    
    Controller: Endpoints REST y validaciones.
    
    Persistence: DAOs para acceso a datos.
  Testeo:
  
      Pruebas para los controller, persitence y service. 


Documentacion De Postman: 
https://documenter.getpostman.com/view/24685669/2sAYBPnF2S

