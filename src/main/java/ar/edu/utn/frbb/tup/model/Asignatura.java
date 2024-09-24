package ar.edu.utn.frbb.tup.model;

import ar.edu.utn.frbb.tup.model.exception.EstadoIncorrectoException;

import java.util.Optional;

public class Asignatura {

    private static long contador=0;

    private long id;
    private EstadoAsignatura estado;
    private Integer nota;
    private long idmateria;
    private long idalumno;

    public Asignatura() {
    }
    public Asignatura(Integer nota,long idalumno,long idmateria) {
        incrementarId();
        this.id = contador;
        this.estado = EstadoAsignatura.NO_CURSADA;
        this.nota=nota;
        this.idalumno=idalumno;
        this.idmateria=idmateria;
    }

    public long getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(long idalumno) {
        this.idalumno = idalumno;
    }

    public long getIdmateria() {
        return idmateria;
    }

    public void setIdmateria(long idmateria) {
        this.idmateria = idmateria;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Optional<Integer> getNota() {
        return Optional.ofNullable(nota);
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public EstadoAsignatura getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsignatura estado) {
        this.estado = estado;
    }



    public void cursarAsignatura(){
        this.estado = EstadoAsignatura.CURSADA;
    }

    public void aprobarAsignatura(int nota) throws EstadoIncorrectoException {
        if (!this.estado.equals(EstadoAsignatura.CURSADA)) {
            throw new EstadoIncorrectoException("La materia debe estar cursada");
        }
        if (nota>=4) {
            this.estado = EstadoAsignatura.APROBADA;
            this.nota = nota;
        }
    }

    private void incrementarId()
    {
        this.contador++;

    }

}
