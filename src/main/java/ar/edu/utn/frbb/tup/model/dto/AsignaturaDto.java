package ar.edu.utn.frbb.tup.model.dto;

import ar.edu.utn.frbb.tup.model.EstadoAsignatura;

public class AsignaturaDto {
    private EstadoAsignatura estado;
    private Integer nota;
    private long idmateria;
    private long idalumno;


    public EstadoAsignatura getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsignatura estado) {
        this.estado = estado;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public long getIdmateria() {
        return idmateria;
    }

    public void setIdmateria(long idmateria) {
        this.idmateria = idmateria;
    }

    public long getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(long idalumno) {
        this.idalumno = idalumno;
    }


}
