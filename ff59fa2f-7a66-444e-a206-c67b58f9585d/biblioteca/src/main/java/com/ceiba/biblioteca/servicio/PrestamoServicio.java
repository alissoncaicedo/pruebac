package com.ceiba.biblioteca.servicio;

import com.ceiba.biblioteca.entidades.Prestamo;
import com.ceiba.biblioteca.repositorios.PrestamoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class PrestamoServicio  {

    @Autowired
    public PrestamoRepositorio prestamoRepositorio;


    public Prestamo crearPrestamo(Prestamo prestamo) throws Exception {
        try {
            if (prestamo.getTipoUsuario() == 3 && prestamoRepositorio.existsByIdentificacionUsuario(prestamo.getIdentificacionUsuario())) {
                throw new IllegalArgumentException("El usuario con identificación " + prestamo.getIdentificacionUsuario() + " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");
            }
            String fechaMaximaDevolucion = calcularFechaMaximaDevolucion(prestamo);
            prestamo.setFechaMaximaDevolucion(fechaMaximaDevolucion);
            return prestamoRepositorio.save(prestamo);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }



    public String calcularFechaMaximaDevolucion(Prestamo prestamo) {
        Integer tipoUsuario = prestamo.getTipoUsuario();
        LocalDate fechaActual = LocalDate.now();
        String fechaMaximaDevolucion = "";
        if (tipoUsuario == 1) {
            fechaActual = calcularFechaExcluyendoSabadosyDomingos(fechaActual, 10);
        } else if (tipoUsuario == 2) {
            fechaActual = calcularFechaExcluyendoSabadosyDomingos(fechaActual, 8);
        } else if (tipoUsuario == 3) {
            fechaActual = calcularFechaExcluyendoSabadosyDomingos(fechaActual, 7);
        } else {
            throw new IllegalArgumentException("Tipo de usuario no permitido en la biblioteca");
        }

        fechaMaximaDevolucion = formateparaFecha(fechaActual);

        return fechaMaximaDevolucion;
    }

    public LocalDate calcularFechaExcluyendoSabadosyDomingos (LocalDate fechaActual, Integer diasASumar){
        LocalDate resultado = fechaActual;
        Integer diasSumados = 0;
        while (diasSumados < diasASumar){
            resultado = resultado.plusDays(1);
            if (resultado.getDayOfWeek() != DayOfWeek.SATURDAY && resultado.getDayOfWeek() != DayOfWeek.SUNDAY){
                diasSumados++;
            }

        }
        return resultado;
    }



    public Prestamo buscarPorId(Integer id) throws Exception {
        try{
            Optional<Prestamo> prestamoOpcional =prestamoRepositorio.findById(id);
            if(prestamoOpcional.isPresent()){
                return prestamoOpcional.get();
            }else{
                throw new Exception("Usuario no encontrado");
            }
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }
    }
    public String formateparaFecha(LocalDate fechaParaFormatear) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaParaFormatear.format(formatter);
    }

}
