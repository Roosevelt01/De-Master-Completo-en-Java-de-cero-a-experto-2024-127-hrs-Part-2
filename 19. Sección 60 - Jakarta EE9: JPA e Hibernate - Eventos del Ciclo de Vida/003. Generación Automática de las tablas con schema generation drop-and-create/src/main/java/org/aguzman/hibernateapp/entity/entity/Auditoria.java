package org.aguzman.hibernateapp.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

//Paso 1: La anotación @Embeddable marca esta clase como un componente que puede ser
// incrustado en otras entidades. No es una entidad por sí misma (no tiene @Id).
@Embeddable
public class Auditoria {
    @Column(name = "creado_en")
    private LocalDateTime creadoEn;

    @Column(name = "editado_en")
    private LocalDateTime editadoEn;

    @PrePersist
    public void prePersist(){
        System.out.println("Inicializar algo justo antesl del persist");
        this.creadoEn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        System.out.println("Inicializar algo justo antesl del update");
        this.editadoEn = LocalDateTime.now();
    }

    public LocalDateTime getCreadoEn() {return creadoEn;}

    public void setCreadoEn(LocalDateTime creadoEn) {this.creadoEn = creadoEn;}

    public LocalDateTime getEditadoEn() {return editadoEn;}

    public void setEditadoEn(LocalDateTime editadoEn) {this.editadoEn = editadoEn;}
}