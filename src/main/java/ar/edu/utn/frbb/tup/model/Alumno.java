package ar.edu.utn.frbb.tup.model;


public class Alumno {

    private static long idContador = 0;
    private long id;
    private String nombre;
    private String apellido;
    private long dni;

    public Alumno() {
    }

    public Alumno(String nombre, String apellido, long dni) {

        incrementarId();
        this.id = idContador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;

    }

    public Alumno(long id, String nombre, String apellido, long dni) {

        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public long getDni() {
        return dni;
    }

    public boolean puedeAprobar(Asignatura asignatura) {
        return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private void incrementarId()
    {
        this.idContador++;

    }
}
