package com.ceiba.biblioteca.entidades;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table (name = "prestamo")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "isbn", length = 10)
    private String isbn;
    @Column(name = "identificacion_usuario", length = 10)
    private String identificacionUsuario;

    @Column(name = "fecha_maxima_devolucion")
    private String  fechaMaximaDevolucion;

    @Column(name = "tipo_usuario")
    private Integer tipoUsuario;

    @Transient
    private String MensajeDeError;

    public Prestamo() {
    }

    public Prestamo(Integer id, String isbn, String identificacionUsuario, String fechaMaximaDevolucion, Integer tipoUsuario, String mensajeDeError) {
        this.id = id;
        this.isbn = isbn;
        this.identificacionUsuario = identificacionUsuario;
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
        this.tipoUsuario = tipoUsuario;
        MensajeDeError = mensajeDeError;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public void setIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }

    public String getFechaMaximaDevolucion() {
        return fechaMaximaDevolucion;
    }

    public void setFechaMaximaDevolucion(String fechaMaximaDevolucion) {
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getMensajeDeError() {
        return MensajeDeError;
    }

    public void setMensajeDeError(String mensajeDeError) {
        MensajeDeError = mensajeDeError;
    }
}
