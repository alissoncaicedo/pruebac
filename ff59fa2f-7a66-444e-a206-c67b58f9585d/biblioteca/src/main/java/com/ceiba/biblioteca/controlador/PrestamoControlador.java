package com.ceiba.biblioteca.controlador;


import com.ceiba.biblioteca.error.MensajeError;
import com.ceiba.biblioteca.entidades.Prestamo;
import com.ceiba.biblioteca.servicio.PrestamoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {
    @Autowired
    protected PrestamoServicio prestamoServicio;
    @PostMapping
    public ResponseEntity<?> crearPrestamo(@RequestBody Prestamo prestamo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(prestamoServicio.crearPrestamo(prestamo));
        } catch (Exception e) {
            String mensajeDeError = e.getMessage();
            MensajeError errorPrestamo = new MensajeError();
            errorPrestamo.setMensaje(mensajeDeError);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorPrestamo);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo>buscarPorId(@PathVariable Integer id) {
        try {
            Prestamo prestamoEncontrado = prestamoServicio.buscarPorId(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(prestamoEncontrado);
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);

        }
    }

}

