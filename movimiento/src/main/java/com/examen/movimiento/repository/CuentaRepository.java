package com.examen.movimiento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.movimiento.entity.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    public Optional<Cuenta> findByNumeroCuenta(Long numeroCuenta);
    public Optional<Cuenta> findByNumeroCuentaAndTipoCuenta(Long numeroCuenta, String tipoCuenta);
}
