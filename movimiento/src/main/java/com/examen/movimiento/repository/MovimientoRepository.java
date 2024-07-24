package com.examen.movimiento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.movimiento.entity.Cuenta;
import com.examen.movimiento.entity.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    public Optional<Movimiento> findFirstByCuentaOrderByFechaDesc(Cuenta cuenta);

   // List<Movimiento> findAllByCuentaClienteAndFechaBetween(String identificacion, Date fechaInicio, Date fechaFin);
}
